package org.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CubeTest {
    private Cube cube;
    private Graphics graphics;
    private Color color;

    @BeforeEach
    void setUp() {
        Point startPoint = new Point(10, 10);
        Point endPoint = new Point(40, 40);
        color = Color.RED;
        cube = new Cube(startPoint, endPoint, color);
        graphics = mock(Graphics.class);
    }

    @Test
    void testDraw() {
        cube.draw(graphics);

        // Capture the setColor calls
        ArgumentCaptor<Color> colorCaptor = ArgumentCaptor.forClass(Color.class);
        verify(graphics, times(7)).setColor(colorCaptor.capture());
        assertEquals(color, colorCaptor.getValue());

        // Capture the draw calls for lines and rectangles
        verify(graphics, times(1)).drawLine(eq(10), eq(10), eq(40), eq(40));
        verify(graphics, times(1)).drawLine(eq(10), eq(10), eq(40), eq(40));
        verify(graphics, times(1)).drawLine(eq(40), eq(40), eq(70), eq(70));
        verify(graphics, times(1)).drawLine(eq(40), eq(10), eq(70), eq(40));
    }
}