package com.cgvsu.rasterization.algorithm;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BresenhamAlgorithmTest {

    @Test
    void testDrawHorizontalLine() {
        TestCanvasContext context = new TestCanvasContext();
        BresenhamAlgorithm algorithm = new BresenhamAlgorithm(10, 10, 50, 10);

        algorithm.draw(context, Color.BLACK);

        int pixelCount = context.getPixelSetCount();
        assertTrue(pixelCount >= 41,
                "Горизонтальная линия длиной 40 пикселей должна нарисовать хотя бы 41 пиксель. Получено: " + pixelCount);

        // Проверяем что линия горизонтальная
        for (int x = 10; x <= 50; x++) {
            assertTrue(context.wasPixelSet(x, 10),
                    "Пиксель на позиции (" + x + ", 10) должен быть установлен");
        }
    }

    @Test
    void testDrawVerticalLine() {
        TestCanvasContext context = new TestCanvasContext();
        BresenhamAlgorithm algorithm = new BresenhamAlgorithm(10, 10, 10, 50);

        algorithm.draw(context, Color.BLACK);

        int pixelCount = context.getPixelSetCount();
        assertTrue(pixelCount >= 41,
                "Вертикальная линия длиной 40 пикселей должна нарисовать хотя бы 41 пиксель. Получено: " + pixelCount);

        for (int y = 10; y <= 50; y++) {
            assertTrue(context.wasPixelSet(10, y),
                    "Пиксель на позиции (10, " + y + ") должен быть установлен");
        }
    }

    @Test
    void testDrawDiagonalLine() {
        TestCanvasContext context = new TestCanvasContext();
        BresenhamAlgorithm algorithm = new BresenhamAlgorithm(10, 10, 50, 50);

        algorithm.draw(context, Color.BLACK);

        int pixelCount = context.getPixelSetCount();
        assertTrue(pixelCount >= 41,
                "Диагональная линия длиной 40 пикселей должна нарисовать хотя бы 41 пиксель. Получено: " + pixelCount);

        // Проверяем ключевые точки диагонали
        assertTrue(context.wasPixelSet(10, 10), "Начальная точка должна быть установлена");
        assertTrue(context.wasPixelSet(50, 50), "Конечная точка должна быть установлена");
        assertTrue(context.wasPixelSet(30, 30), "Средняя точка должна быть установлена");
    }

    @Test
    void testDrawSinglePoint() {
        TestCanvasContext context = new TestCanvasContext();
        BresenhamAlgorithm algorithm = new BresenhamAlgorithm(25, 25, 25, 25);

        algorithm.draw(context, Color.BLACK);

        assertEquals(1, context.getPixelSetCount(),
                "Одна точка должна нарисовать ровно 1 пиксель");
        assertTrue(context.wasPixelSet(25, 25),
                "Пиксель в (25, 25) должен быть установлен");
    }

    @Test
    void testDrawShortLine() {
        TestCanvasContext context = new TestCanvasContext();
        BresenhamAlgorithm algorithm = new BresenhamAlgorithm(10, 10, 15, 15);

        algorithm.draw(context, Color.BLACK);

        assertTrue(context.getPixelSetCount() >= 6,
                "Короткая линия должна нарисовать хотя бы 6 пикселей");
    }
}