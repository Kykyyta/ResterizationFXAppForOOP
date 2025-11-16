package com.cgvsu.rasterization.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class BezierCurveTest {

    private BezierCurve curve;
    private ControlPoint point1, point2, point3;

    @BeforeEach
    void setUp() {
        curve = new BezierCurve();
        point1 = new ControlPoint(new Point2D(0, 0));
        point2 = new ControlPoint(new Point2D(10, 10));
        point3 = new ControlPoint(new Point2D(20, 0));
    }

    @Test
    void testAddControlPoint() { // проверка добавление точек в кривую
        curve.addControlPoint(point1);
        assertEquals(1, curve.getControlPoints().size());
        assertTrue(curve.getControlPoints().contains(point1));
    }

    @Test
    void testAddNullPointThrowsException() { // защита от добавления null точек
        assertThrows(IllegalArgumentException.class, () -> curve.addControlPoint(null));
    }

    @Test
    void testRemoveControlPoint() { // удаление точек
        curve.addControlPoint(point1);
        curve.addControlPoint(point2);

        assertTrue(curve.removeControlPoint(point1));
        assertEquals(1, curve.getControlPoints().size());
        assertFalse(curve.getControlPoints().contains(point1));
    }

    @Test
    void testRemoveNonExistentPoint() { // попытка удалить несуществующую точку
        curve.addControlPoint(point1);
        assertFalse(curve.removeControlPoint(point2));
    }

    @Test
    void testFindPointByPosition() { // проверка поиска точки по клику для перетаскивания
        curve.addControlPoint(point1);
        curve.addControlPoint(point2);

        Point2D searchPosition = new Point2D(1, 1);
        Optional<ControlPoint> found = curve.findPointAt(searchPosition, 5.0);

        assertTrue(found.isPresent());
        assertEquals(point1, found.get());
    }

    @Test
    void testFindPointNotFound() { // если точка не найдена
        curve.addControlPoint(point1);

        Point2D searchPosition = new Point2D(100, 100);
        Optional<ControlPoint> found = curve.findPointAt(searchPosition, 5.0);

        assertFalse(found.isPresent());
    }

    @Test
    void testCanDrawCurve() { // можно ли рисовать кривую(min 2 точки нужно)
        assertFalse(curve.canDrawCurve());

        curve.addControlPoint(point1);
        assertFalse(curve.canDrawCurve());

        curve.addControlPoint(point2);
        assertTrue(curve.canDrawCurve());
    }

    @Test
    void testDeselectAllPoints() { // снятие выделения со всех точек
        point1.setSelected(true);
        point2.setSelected(true);

        curve.addControlPoint(point1);
        curve.addControlPoint(point2);
        curve.addControlPoint(point3);

        curve.deselectAllPoints();

        assertFalse(point1.isSelected());
        assertFalse(point2.isSelected());
        assertFalse(point3.isSelected());
    }

    @Test
    void testGetUnmodifiablePointsList() {
        curve.addControlPoint(point1);

        var points = curve.getControlPoints();
        assertThrows(UnsupportedOperationException.class, () -> points.add(point2));
    }
}