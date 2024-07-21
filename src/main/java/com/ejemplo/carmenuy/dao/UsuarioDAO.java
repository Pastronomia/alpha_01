package com.ejemplo.carmenuy.dao;

import com.ejemplo.carmenuy.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private Connection conexion;

    public UsuarioDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // Método para obtener un usuario por nombre y contraseña
    public Usuario obtenerUsuarioPorNombreYContrasena(String nombre, String contrasena) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE nombre = ? AND contrasena = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, nombre);
            statement.setString(2, contrasena);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getString("nombre"), rs.getString("apellido"), rs.getString("contrasena"));
            }
        }
        return null;
    }

    // Método para insertar un nuevo usuario
    public void insertarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nombre, apellido, contrasena) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getContrasena());
            statement.executeUpdate();
        }
    }

    // Método para actualizar un usuario existente
    public void actualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET nombre = ?, apellido = ?, contrasena = ? WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getContrasena());
            statement.setInt(4, usuario.getId());
            statement.executeUpdate();
        }
    }

    // Método para eliminar un usuario
    public void eliminarUsuario(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
