package com.cgvsu.rasterization.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Point2DTest {

    @Test
    void testConstructorAndGetters() { // правильность сохранения координат
        Point2D point = new Point2D(3.5, 4.2);
        assertEquals(3.5, point.getX(), 0.001);
        assertEquals(4.2, point.getY(), 0.001);
    }

    @Test
    void testDistanceBetweenPoints() { // расстояние между 2 точками
        Point2D p1 = new Point2D(0, 0);
        Point2D p2 = new Point2D(3, 4);
        assertEquals(5.0, p1.distanceTo(p2), 0.001);
    }

    @Test
    void testDistanceToSelf() { // от точки до точки = 0
        Point2D p1 = new Point2D(5, 5);
        assertEquals(0.0, p1.distanceTo(p1), 0.001);
    }

    @Test
    void testPointEquality() { // одинаковые координаты равны(для точек)
        Point2D p1 = new Point2D(1.0, 2.0);
        Point2D p2 = new Point2D(1.0, 2.0);
        Point2D p3 = new Point2D(1.0, 3.0);

        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
        assertEquals(p1, p1);
    }

    @Test
    void testInequalityWithNull() { // точка не null
        Point2D point = new Point2D(1, 2);
        assertNotEquals(null, point);
    }

    @Test
    void testPointWithNegativeCoordinates() { // если отрицательные координаты
        Point2D point = new Point2D(-5.5, -10.2);
        assertEquals(-5.5, point.getX(), 0.001);
        assertEquals(-10.2, point.getY(), 0.001);
    }

    @Test
    void testPointWithZeroCoordinates() { // с нулевыми
        Point2D point = new Point2D(0, 0);
        assertEquals(0, point.getX(), 0.001);
        assertEquals(0, point.getY(), 0.001);
    }
}