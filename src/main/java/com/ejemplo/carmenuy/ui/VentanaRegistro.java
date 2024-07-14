//CarmenSandiegoUruguay/src/main/java/com/ejemplo/carmenuy/ui/VentanaRegistro.java

package com.ejemplo.carmenuy.ui;

import com.ejemplo.carmenuy.service.UsuarioService;
import com.ejemplo.carmenuy.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Ventana de registro de usuario para el juego Carmen Sandiego Uruguay.
 */
public class VentanaRegistro extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(VentanaRegistro.class.getName());

    private final JTextField txtNombre;
    private final JTextField txtApellido;
    private final JPasswordField txtContrasena;
    private final JButton btnRegistrar;
    private final JButton btnCancelar;
    private final UsuarioService usuarioService;

    /**
     * Constructor de la ventana de registro.
     *
     * @param usuarioService Servicio para manejar la lógica de usuarios.
     */
    public VentanaRegistro(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        setTitle("Registro");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Registro de Usuario", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCampos = new JPanel(new GridLayout(3, 2));

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        JLabel lblApellido = new JLabel("Apellido:");
        txtApellido = new JTextField();
        JLabel lblContrasena = new JLabel("Contraseña:");
        txtContrasena = new JPasswordField();

        panelCampos.add(lblNombre);
        panelCampos.add(txtNombre);
        panelCampos.add(lblApellido);
        panelCampos.add(txtApellido);
        panelCampos.add(lblContrasena);
        panelCampos.add(txtContrasena);

        add(panelCampos, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());

        btnRegistrar = new JButton("Registrar");
        btnCancelar = new JButton("Cancelar");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnCancelar);

        add(panelBotones, BorderLayout.SOUTH);

        btnRegistrar.addActionListener(this::onRegistrarButtonClicked);
        btnCancelar.addActionListener(e -> mostrarVentanaLogin());
    }
}
public class VentanaRegistro extends JFrame {


    private void onRegistrarButtonClicked(ActionEvent e) {
        try {
            registrarUsuario();
        } catch (SQLException ex) {
            LOGGER.severe("Error durante el registro de usuario: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Error al registrar usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarUsuario() throws SQLException {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String contrasena = new String(txtContrasena.getPassword());

        if (nombre.isEmpty() || apellido.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuario = new Usuario(nombre, apellido, contrasena);
        usuarioService.insertarUsuario(usuario);

        LOGGER.info("Usuario registrado exitosamente: " + nombre + " " + apellido);
        JOptionPane.showMessageDialog(this, "Registro exitoso");
        mostrarVentanaLogin();
    }
}
public class VentanaRegistro extends JFrame {


    private void mostrarVentanaLogin() {
        SwingUtilities.invokeLater(() -> {
            VentanaLogin ventanaLogin = new VentanaLogin(usuarioService);
            ventanaLogin.setVisible(true);
            dispose();
        });
    }
}
public class VentanaRegistro extends JFrame {


    /**
     * Método principal para ejecutar la aplicación (solo para pruebas).
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
                UsuarioService usuarioService = new UsuarioService(connection);
                new VentanaRegistro(usuarioService).setVisible(true);
            } catch (SQLException e) {
                LOGGER.severe("Error al conectar con la base de datos: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
