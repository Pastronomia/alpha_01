package com.ejemplo.carmenuy.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Clase que representa la ventana de inicio de sesión en el juego Carmen Sandiego Uruguay.
 */
public class VentanaLogin extends JFrame {
    private JTextField textFieldUsuario;
    private JPasswordField passwordField;

    public VentanaLogin() {
        setTitle("Inicio de Sesión");
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

        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.addActionListener(this::onLoginButtonClicked);
        panel.add(btnLogin);

        JButton btnRegistro = new JButton("Registrarse");
        btnRegistro.addActionListener(e -> mostrarVentanaRegistro());
        panel.add(btnRegistro);

        getContentPane().add(panel);

        // Configurar atajos de teclado
        configurarAtajosTeclado();
    }

    private void configurarAtajosTeclado() {
        textFieldUsuario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_H) {
                    onLoginButtonClicked(null);
                } else if (e.getKeyCode() == KeyEvent.VK_J) {
                    mostrarVentanaRegistro();
                }
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_H) {
                    onLoginButtonClicked(null);
                } else if (e.getKeyCode() == KeyEvent.VK_J) {
                    mostrarVentanaRegistro();
                }
            }
        });
    }

    private void onLoginButtonClicked(ActionEvent e) {
        String usuario = textFieldUsuario.getText();
        String contrasena = new String(passwordField.getPassword());

        // Lógica de autenticación aquí
    }

    private void mostrarVentanaRegistro() {
        VentanaRegistro ventanaRegistro = new VentanaRegistro();
        ventanaRegistro.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaLogin ventana = new VentanaLogin();
            ventana.setVisible(true);
        });
    }
}
