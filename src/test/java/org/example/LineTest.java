package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.mockito.Mockito.*;

class LineTest {

    private Line line;
    private Graphics graphics;
    private Point startPoint;
    private Point endPoint;
    private Color color;

    @BeforeEach
    void setUp() {
        startPoint = new Point(10, 10);
        endPoint = new Point(20, 20);
        color = Color.RED;
        line = new Line(startPoint, endPoint, color);
        graphics = mock(Graphics.class);
    }

    @Test
    void testDraw() {
        line.draw(graphics);

        // Verify setColor call
        verify(graphics, times(1)).setColor(color);

        // Verify drawLine call
        verify(graphics, times(1)).drawLine(eq(startPoint.x), eq(startPoint.y), eq(endPoint.x), eq(endPoint.y));
    }

    @Test
    void testDrawWithDifferentCoordinates() {
        Point newStartPoint = new Point(15, 15);
        Point newEndPoint = new Point(25, 25);
        line = new Line(newStartPoint, newEndPoint, color);
        line.draw(graphics);

        // Verify setColor call
        verify(graphics, times(1)).setColor(color);

        // Verify drawLine call
        verify(graphics, times(1)).drawLine(eq(newStartPoint.x), eq(newStartPoint.y), eq(newEndPoint.x), eq(newEndPoint.y));
    }
}