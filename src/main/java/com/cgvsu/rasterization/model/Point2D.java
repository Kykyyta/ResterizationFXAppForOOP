package com.cgvsu.rasterization.model;

// неизменяемый класс для представления точки в 2D пространстве

public final class Point2D {

    private final double x;
    private final double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public double distanceTo(Point2D other) { // расстояние до другой точки

        double dx = x - other.x;
        double dy = y - other.y;

        return Math.sqrt(dx * dx + dy * dy); // формула между точками

    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Point2D)) {
            return false;
        }
        Point2D other = (Point2D) obj;

        return Double.compare(x, other.x) == 0 && Double.compare(y, other.y) == 0; // сравнение координат

    }

    @Override
    public String toString() {
        return String.format("Point2D(%.2f, %.2f)", x, y); // 2 знака
    }
}