package com.cgvsu.rasterization.algorithm;

import com.cgvsu.rasterization.render.CanvasContext;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.HashSet;
import java.util.Set;

public class TestCanvasContext extends CanvasContext {

    private final Set<String> setPixels = new HashSet<>();
    private int pixelSetCount = 0;

    public TestCanvasContext() {
        super(createMinimalGraphicsContext(), 100, 100);
    }

    private static GraphicsContext createMinimalGraphicsContext() {
        javafx.scene.canvas.Canvas canvas = new javafx.scene.canvas.Canvas(1, 1);
        return canvas.getGraphicsContext2D();
    }

    public void setPixel(int x, int y, Color color) { // вместо реального пикселя просто запоминаем координаты
        setPixels.add(x + "," + y);
        pixelSetCount++;
    }

    public boolean wasPixelSet(int x, int y) { // был ли нарисован пиксель
        return setPixels.contains(x + "," + y);
    }

    public int getPixelSetCount() {
        return pixelSetCount;
    }

}