package com.ejemplo.carmenuy.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que representa la ventana de ayuda visual en el juego Carmen Sandiego Uruguay.
 */
public class VentanaAyudaVisual extends JFrame {
    private JTextArea textArea;

    public VentanaAyudaVisual() {
        setTitle("Ayuda Visual");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnCerrar);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);
    }

    public void mostrarMensaje(String mensaje) {
        textArea.setText(mensaje);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaAyudaVisual ventana = new VentanaAyudaVisual();
                ventana.setVisible(true);
                ventana.mostrarMensaje("Bienvenido a la ayuda visual del juego Carmen Sandiego Uruguay.");
            }
        });
    }
}
