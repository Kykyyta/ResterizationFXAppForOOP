package com.cgvsu.rasterization;

import com.cgvsu.rasterization.render.CanvasContext;
import com.cgvsu.rasterization.render.Renderer;
import javafx.scene.canvas.GraphicsContext;

// класс для упрощенного доступа к функциональности растеризации

public class Rasterization {

    public static void drawBezierCurve(GraphicsContext gc, int width, int height,
                                       com.cgvsu.rasterization.model.BezierCurve curve) {

        CanvasContext context = new CanvasContext(gc, width, height); // без этого пришлось бы работать напрямую с GraphicsContext

        Renderer renderer = new Renderer(context); // создание рендера
        renderer.renderScene(curve); // отрисовка всей сцены
    }
}
