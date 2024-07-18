package com.ejemplo.carmenuy.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que representa la ventana de captura en el juego Carmen Sandiego Uruguay.
 */
public class VentanaCaptura extends JFrame {
    private JTextArea textArea;

    public VentanaCaptura() {
        setTitle("Captura");
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
                VentanaCaptura ventana = new VentanaCaptura();
                ventana.setVisible(true);
                ventana.mostrarMensaje("Has capturado a un secuaz!");
            }
        });
    }
}
