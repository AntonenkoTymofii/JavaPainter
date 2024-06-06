package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MainWindowTest {

    private MainWindow mainWindow;
    private MyTable table;

    @BeforeEach
    void setUp() {
        table = mock(MyTable.class);
        MyTable.setInstance(table); // Assuming a setInstance method to inject mock
        mainWindow = new MainWindow();
    }

    @Test
    void testMainWindowInitialization() {
        assertNotNull(mainWindow);
        assertEquals("Lab4 SDMT", mainWindow.getTitle());
        assertEquals(1250, mainWindow.getWidth());
        assertEquals(650, mainWindow.getHeight());
        assertEquals(JFrame.EXIT_ON_CLOSE, mainWindow.getDefaultCloseOperation());
    }

    @Test
    void testOpenTableAction() {
        ActionEvent e = mock(ActionEvent.class);
        JButton openTable = (JButton) mainWindow.getJMenuBar().getComponent(1);
        openTable.getActionListeners()[0].actionPerformed(e);
        verify(table, times(1)).setVisible(true);
    }
}