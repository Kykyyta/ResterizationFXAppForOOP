package com.cgvsu.rasterization.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Модель кривой Безье с контрольными точками
 */
public class BezierCurve {
    private final List<ControlPoint> controlPoints;
    private static final int MIN_POINTS_FOR_CURVE = 2;

    public BezierCurve() {
        this.controlPoints = new ArrayList<>();
    }

    // основные операции с точками
    public void addControlPoint(ControlPoint point) {
        if (point == null) throw new IllegalArgumentException("Control point cannot be null");
        controlPoints.add(point);
    }

    public boolean removeControlPoint(ControlPoint point) {
        return controlPoints.remove(point);
    }

    // ищем точки
    public Optional<ControlPoint> findPointAt(Point2D position, double maxDistance) {
        return controlPoints.stream()
                .filter(point -> point.distanceTo(position) <= maxDistance)
                .findFirst();
    }

    public List<ControlPoint> getControlPoints() {
        return Collections.unmodifiableList(controlPoints);
    }


    public boolean canDrawCurve() {
        return controlPoints.size() >= MIN_POINTS_FOR_CURVE;
    }

    // Выбор/снятие выбора всех точек
    public void deselectAllPoints() {
        controlPoints.forEach(point -> point.setSelected(false));
    }

}