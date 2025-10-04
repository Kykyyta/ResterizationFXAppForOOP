package com.cgvsu.rasterization.algorithm;

import com.cgvsu.rasterization.model.BezierCurve;
import com.cgvsu.rasterization.model.ControlPoint;
import com.cgvsu.rasterization.model.Point2D;
import com.cgvsu.rasterization.render.CanvasContext;
import javafx.scene.paint.Color;
import java.util.List;

/**
 * Алгоритм вычисления и рисования кривой Безье
 */
public class BezierAlgorithm implements DrawingAlgorithm {
    private final BezierCurve curve;
    private static final int DEFAULT_SEGMENTS = 1000;

    public BezierAlgorithm(BezierCurve curve) {
        this.curve = curve;
    }

    @Override
    public void draw(CanvasContext context, Color color) {
        if (!curve.canDrawCurve()) return;

        List<ControlPoint> points = curve.getControlPoints();
        int segments = calculateSegments(points.size());

        Point2D previous = calculatePoint(0, points);
        for (int i = 1; i <= segments; i++) {
            double t = (double) i / segments;
            Point2D current = calculatePoint(t, points);

            new BresenhamAlgorithm(
                    (int) Math.round(previous.getX()),
                    (int) Math.round(previous.getY()),
                    (int) Math.round(current.getX()),
                    (int) Math.round(current.getY())
            ).draw(context, color);

            previous = current;
        }
    }

    private Point2D calculatePoint(double t, List<ControlPoint> points) {
        int n = points.size() - 1;
        double x = 0, y = 0;

        for (int i = 0; i <= n; i++) {
            double coefficient = binomialCoefficient(n, i) *
                    Math.pow(1 - t, n - i) *
                    Math.pow(t, i);
            x += coefficient * points.get(i).getX();
            y += coefficient * points.get(i).getY();
        }

        return new Point2D(x, y);
    }

    private long binomialCoefficient(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (k == 0 || k == n) return 1;
        if (k > n - k) k = n - k;

        long result = 1;
        for (int i = 1; i <= k; i++) {
            result = result * (n - i + 1) / i;
        }
        return result;
    }

    private int calculateSegments(int pointCount) {
        return Math.min(DEFAULT_SEGMENTS, 100 + pointCount * 50);
    }
}