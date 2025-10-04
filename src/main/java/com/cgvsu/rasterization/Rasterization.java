package com.cgvsu.rasterization;

import com.cgvsu.rasterization.render.CanvasContext;
import com.cgvsu.rasterization.render.Renderer;
import javafx.scene.canvas.GraphicsContext;

/**
 * Фасадный класс для упрощенного доступа к функциональности растеризации
 */
public class Rasterization {

    public static void drawBezierCurve(GraphicsContext gc, int width, int height,
                                       com.cgvsu.rasterization.model.BezierCurve curve) {
        CanvasContext context = new CanvasContext(gc, width, height);
        Renderer renderer = new Renderer(context);
        renderer.renderScene(curve);
    }
}
