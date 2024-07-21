package com.ejemplo.carmenuy.ui;

import com.ejemplo.carmenuy.service.JuegoService;
import com.ejemplo.carmenuy.model.Pista;
import com.ejemplo.carmenuy.model.Localidad;
import com.ejemplo.carmenuy.dao.PistaDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class VentanaJuego extends JFrame {
    private final JuegoService juegoService;
    private final JTextArea textArea;
    private List<Pista> pistasActuales;

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
        actualizarInterfaz();
    }

    private void configurarAccionesBotones() {
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_H:
                        if (pistasActuales != null && !pistasActuales.isEmpty()) {
                            juegoService.manejarSeleccionPista(pistasActuales.get(0));
                        }
                        break;
                    case KeyEvent.VK_J:
                        if (pistasActuales != null && pistasActuales.size() > 1) {
                            juegoService.manejarSeleccionPista(pistasActuales.get(1));
                        }
                        break;
                    case KeyEvent.VK_K:
                        if (pistasActuales != null && pistasActuales.size() > 2) {
                            juegoService.manejarSeleccionPista(pistasActuales.get(2));
                        }
                        break;
                    case KeyEvent.VK_ESCAPE:
                        solicitarSalida();
                        break;
                }
            }
        });
    }

    private void obtenerPistas() {
        Localidad localidadActual = juegoService.obtenerLocalidadActual();
        pistasActuales = juegoService.obtenerTresPistasAlAzar(localidadActual);
        mostrarPistas(pistasActuales);
    }

    private void mostrarPistas(List<Pista> pistas) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pistas.size(); i++) {
            sb.append((i == 0 ? "H" : i == 1 ? "J" : "K")).append(": ").append(pistas.get(i).getDescripcion()).append("\n");
        }
        textArea.setText(sb.toString());
    }

    private void solicitarSalida() {
        // Lógica para solicitar salida del juego
        int opcion = JOptionPane.showConfirmDialog(this, "¿Deseas salir del juego?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void actualizarInterfaz() {
        obtenerPistas();
        // Otras actualizaciones de interfaz
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
                PistaDAO pistaDAO = new PistaDAO(connection);
                JuegoService juegoService = new JuegoService(pistaDAO);
                VentanaJuego ventana = new VentanaJuego(juegoService);
                ventana.setVisible(true);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
