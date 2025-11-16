package com.cgvsu.rasterizationfxapp;

import com.cgvsu.rasterization.model.BezierCurve;
import com.cgvsu.rasterization.model.ControlPoint;
import com.cgvsu.rasterization.model.Point2D;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RasterizationControllerTest {

    @Test
    void testBezierCurveCreation() {
        BezierCurve curve = new BezierCurve();
        assertNotNull(curve);
    }

    @Test
    void testAddPointToCurve() {
        BezierCurve curve = new BezierCurve();
        curve.addControlPoint(new ControlPoint(new Point2D(10, 10)));
        assertEquals(1, curve.getControlPoints().size());
    }

    @Test
    void testCanDrawWithTwoPoints() {
        BezierCurve curve = new BezierCurve();
        curve.addControlPoint(new ControlPoint(new Point2D(0, 0)));
        curve.addControlPoint(new ControlPoint(new Point2D(10, 10)));
        assertTrue(curve.canDrawCurve());
    }
}