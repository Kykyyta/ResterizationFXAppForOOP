package com.cgvsu.rasterization.algorithm;

import com.cgvsu.rasterization.model.BezierCurve;
import com.cgvsu.rasterization.model.ControlPoint;
import com.cgvsu.rasterization.model.Point2D;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BezierAlgorithmTest {

    private BezierCurve curve;
    private TestCanvasContext testContext;

    @BeforeEach
    void setUp() {
        curve = new BezierCurve();
        testContext = new TestCanvasContext();
    }

    @Test
    void testDrawWithInsufficientPoints() {
        curve.addControlPoint(new ControlPoint(new Point2D(50, 50)));

        BezierAlgorithm algorithm = new BezierAlgorithm(curve);
        algorithm.draw(testContext, Color.BLACK);

        assertEquals(0, testContext.getPixelSetCount(),
                "С одной точкой не должно рисоваться ничего");
    }

    @Test
    void testDrawWithTwoPoints() {
        curve.addControlPoint(new ControlPoint(new Point2D(20, 20)));
        curve.addControlPoint(new ControlPoint(new Point2D(80, 80)));

        BezierAlgorithm algorithm = new BezierAlgorithm(curve);
        algorithm.draw(testContext, Color.BLACK);

        assertTrue(testContext.getPixelSetCount() > 10,
                "Линия между двумя точками должна нарисовать более 10 пикселей");

        boolean hasStartPixel = false;
        boolean hasEndPixel = false;

        for (int x = 15; x <= 25; x++) {
            for (int y = 15; y <= 25; y++) {
                if (testContext.wasPixelSet(x, y)) hasStartPixel = true;
            }
        }

        for (int x = 75; x <= 85; x++) {
            for (int y = 75; y <= 85; y++) {
                if (testContext.wasPixelSet(x, y)) hasEndPixel = true;
            }
        }

        assertTrue(hasStartPixel, "Должны быть пиксели в районе начальной точки");
        assertTrue(hasEndPixel, "Должны быть пиксели в районе конечной точки");
    }

    @Test
    void testDrawWithThreePoints() { // квадратическая кривая Безье
        curve.addControlPoint(new ControlPoint(new Point2D(20, 80)));
        curve.addControlPoint(new ControlPoint(new Point2D(50, 20)));
        curve.addControlPoint(new ControlPoint(new Point2D(80, 80)));

        BezierAlgorithm algorithm = new BezierAlgorithm(curve);
        algorithm.draw(testContext, Color.BLACK);

        assertTrue(testContext.getPixelSetCount() > 20,
                "Квадратичная кривая должна нарисовать более 20 пикселей");
    }

    @Test
    void testDrawWithFourPoints() { // кубическая кривая Безье
        curve.addControlPoint(new ControlPoint(new Point2D(20, 80)));
        curve.addControlPoint(new ControlPoint(new Point2D(40, 20)));
        curve.addControlPoint(new ControlPoint(new Point2D(60, 20)));
        curve.addControlPoint(new ControlPoint(new Point2D(80, 80)));

        BezierAlgorithm algorithm = new BezierAlgorithm(curve);
        algorithm.draw(testContext, Color.BLACK);

        assertTrue(testContext.getPixelSetCount() > 30,
                "Кубическая кривая должна нарисовать более 30 пикселей");
    }
}