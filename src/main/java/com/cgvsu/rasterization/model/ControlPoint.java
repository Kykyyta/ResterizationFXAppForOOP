package com.cgvsu.rasterization.model;

import javafx.scene.paint.Color;

/**
 * Контрольная точка кривой Безье с состоянием выбора
 */
public class ControlPoint {
    private Point2D position;
    private boolean selected;
    private final int id;
    private static int nextId = 0;

    public ControlPoint(Point2D position) {
        this.position = position;
        this.selected = false;
        this.id = nextId++;
    }

    public double getX() { return position.getX(); }
    public double getY() { return position.getY(); }

    // с валидацией
    public void setPosition(Point2D position) {
        if (position == null) throw new IllegalArgumentException("Position cannot be null");
        this.position = position;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Color getColor() {
        return selected ? Color.DARKBLUE : Color.RED;
    }

    public double distanceTo(Point2D point) {
        return position.distanceTo(point);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ControlPoint)) return false;
        return this.id == ((ControlPoint) obj).id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}