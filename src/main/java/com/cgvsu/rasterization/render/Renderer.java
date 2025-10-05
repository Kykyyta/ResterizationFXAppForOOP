package com.cgvsu.rasterization.render;

import com.cgvsu.rasterization.algorithm.BezierAlgorithm;
import com.cgvsu.rasterization.algorithm.BresenhamAlgorithm;
import com.cgvsu.rasterization.algorithm.CircleAlgorithm;
import com.cgvsu.rasterization.model.BezierCurve;
import com.cgvsu.rasterization.model.ControlPoint;
import javafx.scene.paint.Color;

// основной класс для отрисовки всех элементов сцены

public class Renderer {

    private final CanvasContext context;

    public Renderer(CanvasContext context) {

        this.context = context;

    }

    public void renderScene(BezierCurve curve) {

        // очистка сцены
        context.clear(Color.WHITE);

        // отрисовка кривой Безье
        if (curve.canDrawCurve()) {
            new BezierAlgorithm(curve).draw(context, Color.BLUE);
        }

        // отрисовка контрольного многоугольника
        renderControlPolygon(curve);

        // отрисовка контрольных точек
        renderControlPoints(curve);
    }

    private void renderControlPolygon(BezierCurve curve) {

        var points = curve.getControlPoints(); // автоматический вывод типа для безопаски

        for (int i = 0; i < points.size() - 1; i++) { // проходка между точками

            ControlPoint p1 = points.get(i); // начало отрезка
            ControlPoint p2 = points.get(i + 1); // конец отрезка

            new BresenhamAlgorithm(
                    (int) Math.round(p1.getX()),
                    (int) Math.round(p1.getY()),
                    (int) Math.round(p2.getX()),
                    (int) Math.round(p2.getY())
            ).draw(context, Color.LIGHTGRAY);
        }
    }

    private void renderControlPoints(BezierCurve curve) {

        for (ControlPoint point : curve.getControlPoints()) { // для контрольной т

            new CircleAlgorithm(
                    (int) Math.round(point.getX()),
                    (int) Math.round(point.getY()),
                    6
            ).draw(context, point.getColor()); // смотря какой выбор
        }
    }
}