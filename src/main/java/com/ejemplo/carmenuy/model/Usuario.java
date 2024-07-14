package com.ejemplo.carmenuy.dao;

import com.ejemplo.carmenuy.model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {
    private static final Logger logger = Logger.getLogger(UsuarioDAO.class.getName());
    private final Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
        try {
            crearTabla();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al crear la tabla de usuarios", e);
        }
    }

    private void crearTabla() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "apellido TEXT NOT NULL, " +
                "contrasena TEXT NOT NULL)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void insertarUsuario(Usuario usuario) {
        String hashedPassword = BCrypt.hashpw(usuario.getContrasena(), BCrypt.gensalt());
        String sql = "INSERT INTO usuarios (nombre, apellido, contrasena) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellido());
            pstmt.setString(3, hashedPassword);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            manejarError("Error al insertar usuario", e);
        }
    }

    public Usuario obtenerUsuarioPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("contrasena"));
                }
            }
        } catch (SQLException e) {
            manejarError("Error al obtener usuario por ID", e);
        }
        return null;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("contrasena")));
            }
        } catch (SQLException e) {
            manejarError("Error al obtener todos los usuarios", e);
        }
        return usuarios;
    }

    private void manejarError(String mensaje, SQLException e) {
        logger.log(Level.SEVERE, mensaje, e);
    }
}
