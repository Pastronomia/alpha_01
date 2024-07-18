package com.ejemplo.carmenuy.dao;

import com.ejemplo.carmenuy.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase de acceso a datos para los usuarios.
 */
public class UsuarioDAO {
    private final Connection conexion;

    public UsuarioDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Obtiene un usuario por su nombre.
     * @param nombre el nombre del usuario a buscar.
     * @return Usuario encontrado o null si no existe.
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
    public Usuario obtenerUsuarioPorNombre(String nombre) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE nombre = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, nombre);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("contrasena"),
                            rs.getString("rango"),
                            rs.getInt("capturas"),
                            rs.getString("progreso")
                    );
                }
            }
        }
        return null;
    }

    /**
     * Inserta un nuevo usuario en la base de datos.
     * @param usuario el usuario a insertar.
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
    public void insertarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, apellido, contrasena, rango, capturas, progreso) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, usuario.nombre());
            statement.setString(2, usuario.apellido());
            statement.setString(3, usuario.contrasena());
            statement.setString(4, usuario.rango());
            statement.setInt(5, usuario.capturas());
            statement.setString(6, usuario.progreso());
            statement.executeUpdate();
        }
    }

    /**
     * Actualiza los datos de un usuario existente.
     * @param usuario el usuario a actualizar.
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
    public void actualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, contrasena = ?, rango = ?, capturas = ?, progreso = ? WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, usuario.nombre());
            statement.setString(2, usuario.apellido());
            statement.setString(3, usuario.contrasena());
            statement.setString(4, usuario.rango());
            statement.setInt(5, usuario.capturas());
            statement.setString(6, usuario.progreso());
            statement.setInt(7, usuario.id());
            statement.executeUpdate();
        }
    }

    /**
     * Elimina un usuario de la base de datos por su ID.
     * @param id el ID del usuario a eliminar.
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
    public void eliminarUsuario(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
