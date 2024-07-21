package com.ejemplo.carmenuy.dao;

import com.ejemplo.carmenuy.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que gestiona la conexión a la base de datos y las operaciones relacionadas con los usuarios.
 */
public class BaseDeDatosManager {
    private static final Logger LOGGER = Logger.getLogger(BaseDeDatosManager.class.getName());
    private final Connection connection;

    public BaseDeDatosManager(Connection connection) {
        this.connection = connection;
    }

    public void crearTablaUsuarios() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "apellido TEXT NOT NULL, " +
                "contrasena TEXT NOT NULL, " +
                "rango TEXT NOT NULL DEFAULT 'DETECTIVE JUNIOR', " +
                "capturas INTEGER DEFAULT 0, " +
                "progreso TEXT DEFAULT '', " +
                "UNIQUE(nombre, apellido)" +
                ")";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        }
    }

    public void insertarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, apellido, contrasena, rango, capturas, progreso) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getContrasena());
            statement.setString(4, usuario.getRango());
            statement.setInt(5, usuario.getCapturas());
            statement.setString(6, usuario.getProgreso());
            statement.executeUpdate();
        }
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
            statement.setString(3, usuario.getContrasena());
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

    // Método para inicializar la base de datos y crear las tablas necesarias
    public void inicializarBaseDeDatos() throws SQLException {
        try {
            crearTablaUsuarios();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al inicializar la base de datos", e);
            throw e;
        }
    }
}
