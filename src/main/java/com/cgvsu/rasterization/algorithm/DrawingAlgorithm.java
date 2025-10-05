package com.cgvsu.rasterization.algorithm;

import com.cgvsu.rasterization.render.CanvasContext;
import javafx.scene.paint.Color;

// Интерфейс для всех алгоритмов рисования

public interface DrawingAlgorithm {

    void draw(CanvasContext context, Color color);

}
