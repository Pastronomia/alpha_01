package com.ejemplo.carmenuy.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaResultado extends JFrame {
    private JTextArea textArea;

    public VentanaResultado(String titulo, String mensaje) {
        setTitle(titulo);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText(mensaje);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnCerrar);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);

        configurarAccionesTeclado();
    }

    private void configurarAccionesTeclado() {
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_H:
                        System.out.println("Salir sin guardar");
                        dispose();
                        break;
                    case KeyEvent.VK_J:
                        System.out.println("Proseguir");
                        dispose();
                        break;
                    case KeyEvent.VK_K:
                        System.out.println("Salir y guardar");
                        // Implementar lógica para guardar el juego aquí
                        dispose();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public static void mostrarVentana(String titulo, String mensaje) {
        SwingUtilities.invokeLater(() -> {
            VentanaResultado ventana = new VentanaResultado(titulo, mensaje);
            ventana.setVisible(true);
        });
    }
}
