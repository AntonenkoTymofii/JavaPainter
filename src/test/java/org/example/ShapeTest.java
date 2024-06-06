package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ShapeTest {

    private Shape shape;

    @BeforeEach
    void setUp() {
        Point startPoint = new Point(0, 0);
        Point endPoint = new Point(10, 10);
        Color color = Color.BLACK;
        shape = new TestShape(startPoint, endPoint, color);
    }

    @Test
    void testGetFillColor() {
        Shape.setFillColor(Color.RED);
        assertEquals(Color.RED, Shape.getFillColor());
    }

    @Test
    void testSetColor() {
        shape.setColor(Color.BLUE);
        assertEquals(Color.BLUE, shape.color);
    }

    @Test
    void testSetFillColor() {
        Shape.setFillColor(Color.GREEN);
        assertEquals(Color.GREEN, Shape.getFillColor());
    }

    @Test
    void testSetEndPoint() {
        Point newEndPoint = new Point(20, 20);
        shape.setEndPoint(newEndPoint);
        assertEquals(newEndPoint, shape.endPoint);
    }

    private static class TestShape extends Shape {
        public TestShape(Point startPoint, Point endPoint, Color color) {
            super(startPoint, endPoint, color);
        }

        public void draw(Graphics g) {
            g.setColor(color);
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        }
    }
}