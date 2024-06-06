package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.mockito.Mockito.*;

class LineWithEllipseTest {

    private LineWithEllipse lineWithEllipse;
    private Graphics graphics;
    private Point startPoint;
    private Point endPoint;
    private Color color;

    @BeforeEach
    void setUp() {
        startPoint = new Point(10, 10);
        endPoint = new Point(50, 50);
        color = Color.RED;
        lineWithEllipse = new LineWithEllipse(startPoint, endPoint, color);
        graphics = mock(Graphics.class);
    }

    @Test
    void testDraw() {
        lineWithEllipse.draw(graphics);

        verify(graphics, times(4)).setColor(color);

        verify(graphics, times(1)).drawLine(eq(startPoint.x), eq(startPoint.y), eq(endPoint.x), eq(endPoint.y));

        int ellipseWidth = 20 * 2;
        int ellipseHeight = 20 * 2;
        int ellipse1X = startPoint.x - (ellipseWidth / 2);
        int ellipse1Y = startPoint.y - (ellipseHeight / 2);
        int ellipse2X = endPoint.x - (ellipseWidth / 2);
        int ellipse2Y = endPoint.y - (ellipseHeight / 2);

        verify(graphics, times(4)).setColor(color); // once for line, once for each ellipse

        verify(graphics, times(1)).drawOval(eq(ellipse1X), eq(ellipse1Y), eq(ellipseWidth), eq(ellipseHeight));
        verify(graphics, times(1)).drawOval(eq(ellipse2X), eq(ellipse2Y), eq(ellipseWidth), eq(ellipseHeight));
    }

    @Test
    void testDrawWithDifferentCoordinates() {
        Point newStartPoint = new Point(30, 30);
        Point newEndPoint = new Point(70, 70);
        lineWithEllipse = new LineWithEllipse(newStartPoint, newEndPoint, color);
        lineWithEllipse.draw(graphics);

        verify(graphics, times(4)).setColor(color);

        verify(graphics, times(1)).drawLine(eq(newStartPoint.x), eq(newStartPoint.y), eq(newEndPoint.x), eq(newEndPoint.y));

        int ellipseWidth = 20 * 2;
        int ellipseHeight = 20 * 2;
        int ellipse1X = newStartPoint.x - (ellipseWidth / 2);
        int ellipse1Y = newStartPoint.y - (ellipseHeight / 2);
        int ellipse2X = newEndPoint.x - (ellipseWidth / 2);
        int ellipse2Y = newEndPoint.y - (ellipseHeight / 2);

        verify(graphics, times(4)).setColor(color); // once for line, once for each ellipse

        verify(graphics, times(1)).drawOval(eq(ellipse1X), eq(ellipse1Y), eq(ellipseWidth), eq(ellipseHeight));
        verify(graphics, times(1)).drawOval(eq(ellipse2X), eq(ellipse2Y), eq(ellipseWidth), eq(ellipseHeight));
    }
}