package com.ejemplo.carmenuy.ui;

import com.ejemplo.carmenuy.service.JuegoService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que representa la ventana principal del juego Carmen Sandiego Uruguay.
 */
public class VentanaJuego extends JFrame {
    private final JuegoService juegoService;
    private final JTextArea textArea;

    public VentanaJuego(JuegoService juegoService) {
        this.juegoService = juegoService;
        setTitle("Carmen Sandiego Uruguay");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        getContentPane().add(scrollPane, BorderLayout.CENTER);

        configurarAccionesBotones();
        configurarAtajosTeclado();
        actualizarInterfaz();
    }

    private void configurarAccionesBotones() {
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_H:
                        // Acción para la tecla H (por ejemplo, obtener pista)
                        obtenerPista();
                        break;
                    case KeyEvent.VK_J:
                        // Acción para la tecla J (por ejemplo, responder No a una pregunta)
                        responderNo();
                        break;
                    case KeyEvent.VK_K:
                        // Acción para la tecla K (por ejemplo, responder Sí a una pregunta)
                        responderSi();
                        break;
                }
            }
        });
    }

    private void configurarAtajosTeclado() {
        // Implementar los atajos de teclado aquí, en este caso, los manejamos en configurarAccionesBotones
    }

    private void obtenerPista() {
        // Implementación para obtener una pista
        String pista = juegoService.obtenerPista();
        textArea.setText(pista);
    }

    private void responderNo() {
        // Implementación para responder No a una pregunta
        juegoService.responderNo();
        actualizarInterfaz();
    }

    private void responderSi() {
        // Implementación para responder Sí a una pregunta
        juegoService.responderSi();
        actualizarInterfaz();
    }

    private void actualizarInterfaz() {
        textArea.setText(juegoService.obtenerNarrativaLocalidad());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db"); // Inicializar la conexión correctamente
                JuegoService juegoService = new JuegoService(connection);
                VentanaJuego ventana = new VentanaJuego(juegoService);
                ventana.setVisible(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
