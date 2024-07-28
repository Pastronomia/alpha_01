package com.ejemplo.carmenuy.dao;

import com.ejemplo.carmenuy.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private final Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public void crearTabla() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "apellido TEXT NOT NULL, " +
                "contrasena TEXT NOT NULL, " +
                "rango TEXT NOT NULL DEFAULT 'DETECTIVE_JUNIOR', " +
                "capturas INTEGER DEFAULT 0, " +
                "progreso TEXT DEFAULT '', " +
                "UNIQUE(nombre, apellido));";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        }
    }

    public void insertarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, apellido, contrasena, rango, capturas, progreso) " +
                "VALUES (?, ?, ?, ?, ?, ?) ON CONFLICT(nombre, apellido) DO UPDATE SET " +
                "contrasena = excluded.contrasena, rango = excluded.rango, capturas = excluded.capturas, progreso = excluded.progreso";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, hashPassword(usuario.getContrasena())); // Asumiendo que existe un método para hashear la contraseña
            statement.setString(4, usuario.getRango());
            statement.setInt(5, usuario.getCapturas());
            statement.setString(6, usuario.getProgreso());
            statement.executeUpdate();
        }
    }

    private String hashPassword(String password) {
        // Implementar hashing de contraseña aquí
        return password; // Retornar la contraseña hasheada
    }

    public Usuario obtenerUsuarioPorNombre(String nombre) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE nombre = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Usuario(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("contrasena"),
                        resultSet.getString("rango"),
                        resultSet.getInt("capturas"),
                        resultSet.getString("progreso")
                );
            }
        }
        return null;
    }

    public void actualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, contrasena = ?, rango = ?, capturas = ?, progreso = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, hashPassword(usuario.getContrasena()));
            statement.setString(4, usuario.getRango());
            statement.setInt(5, usuario.getCapturas());
            statement.setString(6, usuario.getProgreso());
            statement.setInt(7, usuario.getId());
            statement.executeUpdate();
        }
    }

    public void eliminarUsuario(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
