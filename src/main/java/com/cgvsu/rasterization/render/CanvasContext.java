package com.cgvsu.rasterization.render;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

// абстракция над GraphicsContext для управления пикселями

public class CanvasContext {

    private final PixelWriter pixelWriter; // для записи пикселей

    private final int width;
    private final int height;

    public CanvasContext(GraphicsContext graphicsContext, int width, int height) {

        this.pixelWriter = graphicsContext.getPixelWriter();
        this.width = width;
        this.height = height;

    }

    public void setPixel(int x, int y, Color color) {

        if (isWithinBounds(x, y)) { // проверка выхода за границу
            pixelWriter.setColor(x, y, color);
        }

    }

    public void clear(Color color) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                setPixel(x, y, color);
            }
        }
    }

    private boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height; // в пределах холста?
    }

}