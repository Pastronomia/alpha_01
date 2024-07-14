// CarmenSandiegoUruguay/src/main/java/com/ejemplo/carmenuy/ui/VentanaLogin.java

package com.ejemplo.carmenuy.ui;

import com.ejemplo.carmenuy.service.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Ventana de inicio de sesión para el juego Carmen Sandiego Uruguay.
 */
public class VentanaLogin extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(VentanaLogin.class.getName());

    private final JTextField txtNombre;
    private final JPasswordField txtContrasena;
    private final JButton btnLogin;
    private final JButton btnRegistro;
    private final UsuarioService usuarioService;

    /**
     * Constructor de la ventana de login.
     *
     * @param usuarioService Servicio para manejar la lógica de usuarios.
     */
    public VentanaLogin(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCampos = new JPanel(new GridLayout(2, 2));

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        JLabel lblContrasena = new JLabel("Contraseña:");
        txtContrasena = new JPasswordField();

        panelCampos.add(lblNombre);
        panelCampos.add(txtNombre);
        panelCampos.add(lblContrasena);
        panelCampos.add(txtContrasena);

        add(panelCampos, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());

        btnLogin = new JButton("Login");
        btnLogin.setMnemonic(KeyEvent.VK_L); // Atajo de teclado Alt+L
        btnRegistro = new JButton("Registro");
        btnRegistro.setMnemonic(KeyEvent.VK_R); // Atajo de teclado Alt+R

        panelBotones.add(btnLogin);
        panelBotones.add(btnRegistro);

        add(panelBotones, BorderLayout.SOUTH);

        btnLogin.addActionListener(this::onLoginButtonClicked);
        btnRegistro.addActionListener(e -> mostrarVentanaRegistro());
    }
}
public class VentanaLogin extends JFrame {


    private void onLoginButtonClicked(ActionEvent e) {
        try {
            login();
        } catch (SQLException ex) {
            LOGGER.severe("Error durante el login: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Error al intentar iniciar sesión", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void login() throws SQLException {
        String nombre = txtNombre.getText();
        String contrasena = new String(txtContrasena.getPassword());

        if (usuarioService.validarUsuario(nombre, contrasena)) {
            LOGGER.info("Login exitoso para el usuario: " + nombre);
            JOptionPane.showMessageDialog(this, "Login exitoso");
            mostrarVentanaJuego();
        } else {
            LOGGER.warning("Intento de login fallido para el usuario: " + nombre);
            JOptionPane.showMessageDialog(this, "Nombre o contraseña incorrectos", "Error de login", JOptionPane.ERROR_MESSAGE);
        }
    }
}
public class VentanaLogin extends JFrame {


    private void mostrarVentanaJuego() {
        SwingUtilities.invokeLater(() -> {
            VentanaJuego ventanaJuego = new VentanaJuego();
            ventanaJuego.setVisible(true);
            dispose();
        });
    }

    private void mostrarVentanaRegistro() {
        SwingUtilities.invokeLater(() -> {
            VentanaRegistro ventanaRegistro = new VentanaRegistro(usuarioService);
            ventanaRegistro.setVisible(true);
            dispose();
        });
    }
}
public class VentanaLogin extends JFrame {


    /**
     * Método principal para ejecutar la aplicación (solo para pruebas).
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        // Simulación de inicialización de UsuarioService
        UsuarioService usuarioService = new UsuarioService();
        SwingUtilities.invokeLater(() -> new VentanaLogin(usuarioService).setVisible(true));
    }
}
