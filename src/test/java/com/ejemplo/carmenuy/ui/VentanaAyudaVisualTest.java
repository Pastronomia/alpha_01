// Path: src/test/java/com/ejemplo/carmenuy/ui/VentanaAyudaVisualTest.java

package com.ejemplo.carmenuy.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static org.junit.jupiter.api.Assertions.*;

class VentanaAyudaVisualTest {

    private VentanaAyudaVisual ventanaAyudaVisual;

    @BeforeEach
    void setUp() {
        ventanaAyudaVisual = new VentanaAyudaVisual();
    }

    @Test
    void testMostrarMensaje() {
        String mensaje = "Este es un mensaje de prueba";
        ventanaAyudaVisual.mostrarMensaje(mensaje);
        JTextArea textArea = (JTextArea) ((JScrollPane) ventanaAyudaVisual.getContentPane().getComponent(0)).getViewport().getView();
        assertEquals(mensaje, textArea.getText());
    }

    @Test
    void testVentanaCerrada() throws InterruptedException {
        boolean[] closed = {false};
        ventanaAyudaVisual.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                closed[0] = true;
            }
        });

        ventanaAyudaVisual.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaAyudaVisual.setVisible(true);

        // Esperar unos segundos para asegurarse de que la ventana se muestra
        Thread.sleep(2000);

        ventanaAyudaVisual.dispatchEvent(new WindowEvent(ventanaAyudaVisual, WindowEvent.WINDOW_CLOSING));

        // Esperar unos segundos para asegurarse de que la ventana se cierra
        Thread.sleep(2000);

        assertTrue(closed[0]);
    }

    @Test
    void testVentanaVisible() {
        ventanaAyudaVisual.setVisible(true);
        assertTrue(ventanaAyudaVisual.isVisible());
    }
}
