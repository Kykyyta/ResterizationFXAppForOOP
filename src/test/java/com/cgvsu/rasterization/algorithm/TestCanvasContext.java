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
        // Создаем минимальный GraphicsContext чтобы избежать NPE
        super(createMinimalGraphicsContext(), 100, 100);
    }

    public TestCanvasContext(int width, int height) {
        super(createMinimalGraphicsContext(), width, height);
    }

    private static GraphicsContext createMinimalGraphicsContext() {
        // Создаем минимальный Canvas чтобы избежать NPE в GraphicsContext
        javafx.scene.canvas.Canvas canvas = new javafx.scene.canvas.Canvas(1, 1);
        return canvas.getGraphicsContext2D();
    }

    // Переопределяем setPixel чтобы записывать пиксели
    public void setPixel(int x, int y, Color color) {
        // Записываем все пиксели, игнорируя границы для тестирования
        setPixels.add(x + "," + y);
        pixelSetCount++;
    }

    public boolean wasPixelSet(int x, int y) {
        return setPixels.contains(x + "," + y);
    }

    public int getPixelSetCount() {
        return pixelSetCount;
    }

    public void clearRecordedPixels() {
        setPixels.clear();
        pixelSetCount = 0;
    }
}