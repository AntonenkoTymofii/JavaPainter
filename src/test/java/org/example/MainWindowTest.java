package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MainWindowTest {

    MainWindow mainWindowTest;
    MyTable table;

    @BeforeEach
    void setUp() {
        MyTable.setInstance(table);
        table = mock(MyTable.class);
        mainWindowTest = new MainWindow();
    }

    @Test
    void testMainWindowInitialization() {
        assertNotNull(mainWindowTest);
        assertEquals("Lab4 SDMT", mainWindowTest.getTitle());
        assertEquals(1250, mainWindowTest.getWidth());
        assertEquals(650, mainWindowTest.getHeight());
        assertEquals(JFrame.EXIT_ON_CLOSE, mainWindowTest.getDefaultCloseOperation());
    }

    @Test
    void testOpenTableAction() {
        ActionEvent e = mock(ActionEvent.class);
        JButton openTable = (JButton) mainWindowTest.getJMenuBar().getComponent(1);
        openTable.getActionListeners()[0].actionPerformed(e);
        verify(table, times(0)).setVisible(true);
    }
}