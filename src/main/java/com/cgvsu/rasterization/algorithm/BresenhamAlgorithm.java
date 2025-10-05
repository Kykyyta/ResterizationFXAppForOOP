package com.cgvsu.rasterization.algorithm;

import com.cgvsu.rasterization.render.CanvasContext;
import javafx.scene.paint.Color;

// Реализация алгоритма Брезенхема для рисования линии

public class BresenhamAlgorithm implements DrawingAlgorithm {

    private final int x0, y0, x1, y1;

    public BresenhamAlgorithm(int x0, int y0, int x1, int y1) {

        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;

    }

    @Override
    public void draw(CanvasContext context, Color color) {

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        int sx = (x0 < x1) ? 1 : -1; // 1 - вправо, -1 - влево
        int sy = (y0 < y1) ? 1 : -1; // 1 - вниз, -1 - вверх

        int err = dx - dy; // какой пиксель некст

        int x = x0;
        int y = y0;

        while (true) {

            context.setPixel(x, y, color);

            if (x == x1 && y == y1) { // конечка
                break;
            }

            int e2 = 2 * err;

            if (e2 > -dy) {
                err -= dy;
                x += sx;
            }
            if (e2 < dx) {
                err += dx;
                y += sy;
            }
        }
    }
}