package com.cgvsu.rasterizationfxapp;

import com.cgvsu.rasterization.Rasterization;
import com.cgvsu.rasterization.model.BezierCurve;
import com.cgvsu.rasterization.model.ControlPoint;
import com.cgvsu.rasterization.model.Point2D;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;

import java.util.Optional;

public class RasterizationController {

    @FXML private AnchorPane anchorPane; // связь с элементами из FXML
    @FXML private Canvas canvas; // холст

    private final BezierCurve bezierCurve = new BezierCurve(); // модель кривой
    private Optional<ControlPoint> selectedPoint = Optional.empty();
    private boolean isDragging = false; // перетаскивание
    private static final double SNAP_DISTANCE = 20.0;

    @FXML
    private void initialize() {

        setupCanvasResizing(); // размер
        setupMouseHandlers(); // мышь
        redrawCanvas(); // первоначальная отрисовка

    }

    private void setupCanvasResizing() {

        anchorPane.prefWidthProperty().addListener((obs, oldVal, newVal) -> {
            canvas.setWidth(newVal.doubleValue());
            redrawCanvas();
        });
        anchorPane.prefHeightProperty().addListener((obs, oldVal, newVal) -> {
            canvas.setHeight(newVal.doubleValue());
            redrawCanvas();
        });
    }

    private void setupMouseHandlers() {
        canvas.setOnMouseClicked(event -> {
            if (isDragging) {
                isDragging = false;
                return;
            }

            Point2D clickPoint = new Point2D(event.getX(), event.getY());

            if (event.getButton() == MouseButton.PRIMARY) {
                handleLeftClick(clickPoint);
            } else if (event.getButton() == MouseButton.SECONDARY) {
                handleRightClick(clickPoint);
            }
        });

        canvas.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                Point2D pressedPoint = new Point2D(event.getX(), event.getY());
                selectedPoint = bezierCurve.findPointAt(pressedPoint, SNAP_DISTANCE);

                if (selectedPoint.isPresent()) {
                    isDragging = true;
                    bezierCurve.deselectAllPoints();
                    selectedPoint.get().setSelected(true);
                }
            }
        });

        canvas.setOnMouseDragged(event -> {
            if (isDragging && selectedPoint.isPresent()) {
                Point2D newPosition = new Point2D(event.getX(), event.getY());
                selectedPoint.get().setPosition(newPosition);
                redrawCanvas();
            }
        });

        canvas.setOnMouseReleased(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                selectedPoint.ifPresent(point -> point.setSelected(false));
                selectedPoint = Optional.empty();
                isDragging = false;
                redrawCanvas();
            }
        });
    }

    private void handleLeftClick(Point2D clickPoint) {
        Optional<ControlPoint> existingPoint = bezierCurve.findPointAt(clickPoint, SNAP_DISTANCE);

        if (existingPoint.isEmpty()) {
            ControlPoint newPoint = new ControlPoint(clickPoint);
            bezierCurve.addControlPoint(newPoint);
            redrawCanvas();
        }
    }

    private void handleRightClick(Point2D clickPoint) {

        Optional<ControlPoint> pointToRemove = bezierCurve.findPointAt(clickPoint, SNAP_DISTANCE);
        pointToRemove.ifPresent(point -> {
            bezierCurve.removeControlPoint(point);
            redrawCanvas();
        });

    }

    private void redrawCanvas() { // перерисовка холста
        Rasterization.drawBezierCurve(
                canvas.getGraphicsContext2D(),
                (int) canvas.getWidth(),
                (int) canvas.getHeight(),
                bezierCurve
        );
    }
}
