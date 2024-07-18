package com.ejemplo.carmenuy.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Clase que representa la ventana de registro en el juego Carmen Sandiego Uruguay.
 */
public class VentanaRegistro extends JFrame {
    private JTextField textFieldUsuario;
    private JPasswordField passwordField;
    private JButton btnRegistrar;
    private JButton btnLogin;

    public VentanaRegistro() {
        setTitle("Registro");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        panel.add(new JLabel("Usuario:"));
        textFieldUsuario = new JTextField();
        panel.add(textFieldUsuario);

        panel.add(new JLabel("Contraseña:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(this::onRegistrarButtonClicked);
        panel.add(btnRegistrar);

        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.addActionListener(e -> mostrarVentanaLogin());
        panel.add(btnLogin);

        getContentPane().add(panel);

        // Configurar atajos de teclado
        configurarAtajosTeclado();
    }

    private void configurarAtajosTeclado() {
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_H) {
                    onRegistrarButtonClicked(null);
                } else if (e.getKeyCode() == KeyEvent.VK_J) {
                    mostrarVentanaLogin();
                }
            }
        };

        textFieldUsuario.addKeyListener(keyAdapter);
        passwordField.addKeyListener(keyAdapter);
        btnRegistrar.addKeyListener(keyAdapter);
        btnLogin.addKeyListener(keyAdapter);
    }

    private void onRegistrarButtonClicked(ActionEvent e) {
        String usuario = textFieldUsuario.getText();
        String contrasena = new String(passwordField.getPassword());

        // Lógica de registro aquí
    }

    private void mostrarVentanaLogin() {
        VentanaLogin ventanaLogin = new VentanaLogin();
        ventanaLogin.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaRegistro ventana = new VentanaRegistro();
            ventana.setVisible(true);
        });
    }
}
