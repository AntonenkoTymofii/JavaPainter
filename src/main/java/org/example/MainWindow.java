package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainWindow extends JFrame {

    private final ShapeEditor shapeEditor;
    public static JRadioButton dotButton = new JRadioButton("Dot");
    public static JRadioButton lineButton = new JRadioButton("Line");
    public static JRadioButton rectangleButton = new JRadioButton("Rectangle");
    public static JRadioButton ellipseButton = new JRadioButton("Ellipse");
    public static JRadioButton cubeButton = new JRadioButton("Cube");
    public static JRadioButton lineEllipseButton = new JRadioButton("Line with two ellipses");
    private final MyTable table;
    private Point startPoint;
    private Point endPoint;


    public MainWindow() {
        ShapeEditor shapeEditor1;
        super.setTitle("Lab4 SDMT");
        super.setBounds(15, 15, 1250, 650);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.repaint();

        shapeEditor1 = new ShapeEditor();
        table = MyTable.getInstance(this, shapeEditor1);

        JButton clearDesk = new JButton("Clear");
        clearDesk.addActionListener(e -> clearPanel());

        JButton openTable = new JButton("Open Table");
        openTable.addActionListener(e -> {
            table.setVisible(true);
            super.repaint();
        });

        JButton openFillColorWindow = new JButton("Fill Color Settings");
        openFillColorWindow.addActionListener(e -> {
            Color initialColor = openFillColorWindow.getForeground();

            Color selectedColor = JColorChooser.showDialog(this, "Choose Color", initialColor);

            if (selectedColor != null) {
                Shape.setFillColor(selectedColor);
            }
        });

        shapeEditor1 = table.getShapeEditor();
        shapeEditor = shapeEditor1;
        super.repaint();
        JPanel panel = getJPanel();
        super.repaint();
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Draw");

        ButtonGroup buttonGroup = new ButtonGroup();

        buttonGroup.add(dotButton);
        buttonGroup.add(lineButton);
        buttonGroup.add(rectangleButton);
        buttonGroup.add(ellipseButton);
        buttonGroup.add(cubeButton);
        buttonGroup.add(lineEllipseButton);

        optionsMenu.add(dotButton);
        optionsMenu.add(lineButton);
        optionsMenu.add(rectangleButton);
        optionsMenu.add(ellipseButton);
        optionsMenu.add(cubeButton);
        optionsMenu.add(lineEllipseButton);


        menuBar.add(optionsMenu);
        menuBar.add(openTable);
        menuBar.add(openFillColorWindow);
        menuBar.add(clearDesk);
        super.setJMenuBar(menuBar);
        super.repaint();
        super.add(panel);
        super.repaint();
    }

    void clearPanel() {
        String message = shapeEditor.getSize() + " figures were cleared";
        shapeEditor.clearShapes();
        repaint();
        JOptionPane.showMessageDialog(this, message);
        table.clearTable();
    }

    JPanel getJPanel() {
        super.repaint();
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graph) {
                super.paintComponent(graph);
                for (Shape shape : shapeEditor.getShapes()) {
                    shape.draw(graph);
                    repaint();
                }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
                Shape newShape = null;
                if (rectangleButton.isSelected()) {
                    newShape = new Rectangle(startPoint, startPoint, Color.BLUE);
                } else if (dotButton.isSelected()) {
                    newShape = new Dot(startPoint, startPoint, Color.BLUE);
                } else if (lineButton.isSelected()) {
                    newShape = new Line(startPoint, startPoint, Color.BLUE);
                } else if (ellipseButton.isSelected()) {
                    newShape = new Ellipse(startPoint, startPoint, Color.BLUE);
                } else if (cubeButton.isSelected()) {
                    newShape = new Cube(startPoint, startPoint, Color.BLUE);
                } else if (lineEllipseButton.isSelected()) {
                    newShape = new LineWithEllipse(startPoint, startPoint, Color.BLUE);
                }
                if (newShape != null) {
                    newShape.setColor(Color.BLUE);
                    shapeEditor.addShape(newShape);
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ArrayList<Shape> shapes = shapeEditor.getShapes();
                if (!shapes.isEmpty()) {
                    Shape s = shapes.get((shapes.size() - 1));
                    endPoint = e.getPoint();
                    s.endPoint = endPoint;
                    s.setColor(Color.BLACK);
                    if (rectangleButton.isSelected()) {
                        table.addObjectTable("Rectangle", startPoint, endPoint);
                    } else if (ellipseButton.isSelected()) {
                        table.addObjectTable("Ellipse", startPoint, endPoint);
                    } else if (cubeButton.isSelected()) {
                        table.addObjectTable("Cube", startPoint, endPoint);
                    } else if (lineEllipseButton.isSelected()) {
                        table.addObjectTable("Line with two ellipses", startPoint, endPoint);
                    } else if (lineButton.isSelected()) {
                        table.addObjectTable("Line", startPoint, endPoint);
                    } else if (dotButton.isSelected()) {
                        table.addObjectTable("Dot", startPoint, endPoint);
                    }
                    repaint();
                }
            }
        });
        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                ArrayList<Shape> shapes = shapeEditor.getShapes();
                if (!shapes.isEmpty()) {
                    shapes.get(shapes.size() - 1).endPoint = e.getPoint();
                    repaint();
                }
            }
        });
        return panel;
    }
}