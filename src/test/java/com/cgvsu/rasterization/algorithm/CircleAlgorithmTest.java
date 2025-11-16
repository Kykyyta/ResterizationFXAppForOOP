package com.cgvsu.rasterization.algorithm;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CircleAlgorithmTest {

    @Test
    void testDrawSmallCircle() { //
        TestCanvasContext context = new TestCanvasContext();
        CircleAlgorithm algorithm = new CircleAlgorithm(50, 50, 10);

        algorithm.draw(context, Color.BLACK);

        assertTrue(context.getPixelSetCount() > 0, "Круг должен нарисовать хотя бы несколько пикселей");

        assertTrue(context.wasPixelSet(50, 50), "Центр круга должен быть закрашен");

        assertTrue(context.wasPixelSet(50, 40) || context.wasPixelSet(50, 60) ||
                        context.wasPixelSet(40, 50) || context.wasPixelSet(60, 50),
                "Должны быть закрашены граничные точки круга");
    }

    @Test
    void testDrawMediumCircle() {
        TestCanvasContext context = new TestCanvasContext();
        CircleAlgorithm algorithm = new CircleAlgorithm(50, 50, 20);

        algorithm.draw(context, Color.BLACK);

        int pixelCount = context.getPixelSetCount();
        assertTrue(pixelCount > 50, "Средний круг должен нарисовать больше 50 пикселей. Получено: " + pixelCount);
        assertTrue(context.wasPixelSet(50, 50), "Центр круга должен быть закрашен");
    }

    @Test
    void testDrawCircleAtEdge() { // у края холста
        TestCanvasContext context = new TestCanvasContext();
        CircleAlgorithm algorithm = new CircleAlgorithm(5, 5, 5);

        algorithm.draw(context, Color.BLACK);

        assertTrue(context.getPixelSetCount() > 0, "Круг у края должен нарисовать пиксели");
    }

    @Test
    void testDrawCircleWithZeroRadius() { // точка
        TestCanvasContext context = new TestCanvasContext();
        CircleAlgorithm algorithm = new CircleAlgorithm(50, 50, 0);

        algorithm.draw(context, Color.BLACK);

        assertEquals(1, context.getPixelSetCount(), "Круг с нулевым радиусом должен нарисовать 1 пиксель");
        assertTrue(context.wasPixelSet(50, 50), "Центр должен быть закрашен");
    }

    @Test
    void testDrawMultipleCircles() { // несколько
        TestCanvasContext context = new TestCanvasContext();

        new CircleAlgorithm(30, 30, 10).draw(context, Color.BLACK);
        new CircleAlgorithm(70, 70, 15).draw(context, Color.BLACK);

        assertTrue(context.getPixelSetCount() > 10, "Два круга должны нарисовать больше 10 пикселей");
    }
}