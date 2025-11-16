package com.cgvsu.rasterization.render;

// ОТРИСОВКА СЦЕНЫ

import com.cgvsu.rasterization.algorithm.TestCanvasContext;
import com.cgvsu.rasterization.model.BezierCurve;
import com.cgvsu.rasterization.model.ControlPoint;
import com.cgvsu.rasterization.model.Point2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RendererTest {

    private TestCanvasContext testContext;
    private Renderer renderer;
    private BezierCurve curve;

    @BeforeEach
    void setUp() {
        testContext = new TestCanvasContext();
        renderer = new Renderer(testContext);
        curve = new BezierCurve();
    }

    @Test
    void testRenderSceneWithEmptyCurve() { // пустая сцена
        renderer.renderScene(curve);
        assertTrue(testContext.getPixelSetCount() >= 0,
                "Рендер с пустой кривой не должен падать");
    }

    @Test
    void testRenderSceneWithTwoPoints() { // 2 точки
        curve.addControlPoint(new ControlPoint(new Point2D(30, 30)));
        curve.addControlPoint(new ControlPoint(new Point2D(70, 70)));

        renderer.renderScene(curve);

        assertTrue(testContext.getPixelSetCount() > 20,
                "С 2 точками должно быть нарисовано более 20 пикселей");
    }

    @Test
    void testRenderSceneWithThreePoints() { // 3 точки
        curve.addControlPoint(new ControlPoint(new Point2D(20, 80)));
        curve.addControlPoint(new ControlPoint(new Point2D(50, 20)));
        curve.addControlPoint(new ControlPoint(new Point2D(80, 80)));

        renderer.renderScene(curve);

        assertTrue(testContext.getPixelSetCount() > 30,
                "С 3 точками должно быть нарисовано более 30 пикселей");
    }

    @Test
    void testRenderSceneControlPoints() { // кругов
        ControlPoint point1 = new ControlPoint(new Point2D(30, 30));
        ControlPoint point2 = new ControlPoint(new Point2D(70, 70));

        curve.addControlPoint(point1);
        curve.addControlPoint(point2);

        renderer.renderScene(curve);

        boolean hasPoint1 = false;
        boolean hasPoint2 = false;

        for (int x = 25; x <= 35; x++) {
            for (int y = 25; y <= 35; y++) {
                if (testContext.wasPixelSet(x, y)) hasPoint1 = true;
            }
        }

        for (int x = 65; x <= 75; x++) {
            for (int y = 65; y <= 75; y++) {
                if (testContext.wasPixelSet(x, y)) hasPoint2 = true;
            }
        }

        assertTrue(hasPoint1, "Должны быть пиксели вокруг первой контрольной точки");
        assertTrue(hasPoint2, "Должны быть пиксели вокруг второй контрольной точки");
    }
}