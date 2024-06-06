package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.mockito.Mockito.*;

class EllipseTest {

    private Ellipse ellipse;
    private Graphics graphics;
    private Point startPoint;
    private Point endPoint;
    private Color color;

    @BeforeEach
    void setUp() {
        startPoint = new Point(10, 10);
        endPoint = new Point(20, 20);
        color = Color.RED;
        ellipse = new Ellipse(startPoint, endPoint, color);
        graphics = mock(Graphics.class);
    }

    @Test
    void testDrawWithFillColor() {
        Color fillColor = Color.YELLOW;
        Shape.setFillColor(fillColor);

        ellipse.draw(graphics);

        int width = Math.abs(endPoint.x - startPoint.x) * 2;
        int height = Math.abs(endPoint.y - startPoint.y) * 2;
        int x = startPoint.x - (width / 2);
        int y = startPoint.y - (height / 2);

        verify(graphics, times(1)).setColor(fillColor);
        verify(graphics, times(1)).fillOval(eq(x), eq(y), eq(width), eq(height));

        verify(graphics, times(1)).setColor(color);
        verify(graphics, times(1)).drawOval(eq(x), eq(y), eq(width), eq(height));
    }

    @Test
    void testDrawWithoutFillColor() {
        Shape.setFillColor(null);

        ellipse.draw(graphics);

        int width = Math.abs(endPoint.x - startPoint.x) * 2;
        int height = Math.abs(endPoint.y - startPoint.y) * 2;
        int x = startPoint.x - (width / 2);
        int y = startPoint.y - (height / 2);

        // Verify drawOval call
        verify(graphics, times(1)).setColor(color);
        verify(graphics, times(1)).drawOval(eq(x), eq(y), eq(width), eq(height));

        // Verify fillOval was not called
        verify(graphics, never()).fillOval(anyInt(), anyInt(), anyInt(), anyInt());
    }
}