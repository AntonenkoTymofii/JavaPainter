package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

class ShapeEditorTest {

    ShapeEditor shapeEditorTest;

    @BeforeEach
    void setUp() {
        shapeEditorTest = new ShapeEditor();
    }

    @Test
    void addShape() {
        Line line1 = new Line(new Point(0, 0), new Point(10, 10), new Color(255, 255, 255));
        shapeEditorTest.addShape(line1);
        Assertions.assertEquals(shapeEditorTest.getSize(), 1);
    }

    @Test
    void getShapes() {
        ArrayList<Shape> shapeArr = new ArrayList<>();
        Line line1 = new Line(new Point(0, 0), new Point(10, 10), new Color(255, 255, 255));
        shapeEditorTest.addShape(line1);
        shapeArr.add(line1);
        Assertions.assertEquals(shapeEditorTest.getShapes(), shapeArr);
    }

    @Test
    void clearShapes() {
        Line line1 = new Line(new Point(0, 0), new Point(10, 10), new Color(255, 255, 255));
        shapeEditorTest.clearShapes();
        Assertions.assertEquals(shapeEditorTest.getSize(), 0);
    }

    @Test
    void getSize() {
        Line line1 = new Line(new Point(0, 0), new Point(10, 10), new Color(255, 255, 255));
        for (int i = 0; i < 3; i++) {
            shapeEditorTest.addShape(line1);
        }
        int count = shapeEditorTest.getSize();
        Assertions.assertEquals(count, 3);
    }

    @Test
    void deleteShape() {
        Line line1 = new Line(new Point(0, 0), new Point(10, 10), new Color(255, 255, 255));
        for (int i = 0; i < 3; i++) {
            shapeEditorTest.addShape(line1);
        }
        shapeEditorTest.deleteShape(1);
        int count = shapeEditorTest.getSize();
        Assertions.assertEquals(count, 2);
    }
}