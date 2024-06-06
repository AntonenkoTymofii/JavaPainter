package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.mockito.Mockito.*;

class DotTest {

    private Dot dot;
    private Graphics graphics;
    private Color color;

    @BeforeEach
    void setUp() {
        Point startPoint = new Point(10, 10);
        Point endPoint = new Point(10, 10);
        color = Color.RED;
        dot = new Dot(startPoint, endPoint, color);
        graphics = mock(Graphics.class);
    }

    @Test
    void testDraw() {
        dot.draw(graphics);
        verify(graphics, times(1)).fillOval(eq(7), eq(7), eq(4), eq(4));
    }

    @Test
    void testDrawWithDifferentCoordinates() {
        Point newStartPoint = new Point(20, 20);
        dot = new Dot(newStartPoint, newStartPoint, color);
        dot.draw(graphics);

        // Capture the fillOval call
        verify(graphics, times(1)).fillOval(eq(17), eq(17), eq(4), eq(4));
    }
}