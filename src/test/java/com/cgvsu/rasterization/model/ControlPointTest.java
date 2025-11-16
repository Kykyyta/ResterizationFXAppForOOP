package com.cgvsu.rasterization.model;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ControlPointTest {

    @Test
    void testConstructorAndGetters() {
        Point2D position = new Point2D(10, 20);
        ControlPoint point = new ControlPoint(position);

        assertEquals(10.0, point.getX(), 0.001);
        assertEquals(20.0, point.getY(), 0.001);
        assertFalse(point.isSelected());
    }

    @Test
    void testSetPosition() {
        ControlPoint point = new ControlPoint(new Point2D(0, 0));
        Point2D newPosition = new Point2D(15, 25);

        point.setPosition(newPosition);
        assertEquals(15.0, point.getX(), 0.001);
        assertEquals(25.0, point.getY(), 0.001);
    }

    @Test
    void testSetNullPositionThrowsException() {
        ControlPoint point = new ControlPoint(new Point2D(0, 0));
        assertThrows(IllegalArgumentException.class, () -> point.setPosition(null));
    }

    @Test
    void testSelectionAndColor() {
        ControlPoint point = new ControlPoint(new Point2D(0, 0));

        assertEquals(Color.RED, point.getColor());

        point.setSelected(true);
        assertEquals(Color.DARKBLUE, point.getColor());

        point.setSelected(false);
        assertEquals(Color.RED, point.getColor());
    }

    @Test
    void testDistanceToOtherPoint() {
        ControlPoint point = new ControlPoint(new Point2D(0, 0));
        Point2D other = new Point2D(3, 4);

        assertEquals(5.0, point.distanceTo(other), 0.001);
    }

    @Test
    void testEqualsAndHashCode() {
        ControlPoint p1 = new ControlPoint(new Point2D(1, 2));
        ControlPoint p2 = new ControlPoint(new Point2D(1, 2));

        assertNotEquals(p1, p2);
        assertNotEquals(p1.hashCode(), p2.hashCode());
        assertEquals(p1, p1);
    }
}