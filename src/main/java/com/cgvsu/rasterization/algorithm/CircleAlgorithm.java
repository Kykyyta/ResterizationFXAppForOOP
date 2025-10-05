package com.cgvsu.rasterization.algorithm;

import com.cgvsu.rasterization.render.CanvasContext;
import javafx.scene.paint.Color;

// Алгоритм рисования заполненного круга

public class CircleAlgorithm implements DrawingAlgorithm {

    private final int centerX, centerY, radius;

    public CircleAlgorithm(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    @Override
    public void draw(CanvasContext context, Color color) {

        int r2 = radius * radius;

        for (int y = -radius; y <= radius; y++) { // вертикально
            for (int x = -radius; x <= radius; x++) { // горизонтально
                if (x * x + y * y <= r2) {
                    context.setPixel(centerX + x, centerY + y, color);
                }
            }
        }
    }
}