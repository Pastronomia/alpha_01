package com.ejemplo.carmenuy.dao;

import com.ejemplo.carmenuy.model.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDeDatosManager {
    private Connection conexion;

    // Método para inicializar la base de datos
    public void inicializarBaseDeDatos() {
        try {
            // Asegúrate de que la ruta sea accesible y correcta
            conexion = DriverManager.getConnection("jdbc:sqlite:F:/SQLite/carmen_sandiego.db");
            System.out.println("Conexión a la base de datos SQLite establecida.");
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    // Método para crear tablas si no existen
    public void crearTablasSiNoExisten() {
        String sql = """
                CREATE TABLE IF NOT EXISTS usuario (
                    id INTEGER PRIMARY KEY,
                    nombre TEXT,
                    contrasena TEXT
                );
                """;
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.execute();
            System.out.println("Tabla de usuarios creada o verificada exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al crear tabla de usuarios: " + e.getMessage());
        }
    }

    // Método para obtener un usuario por nombre
    public Usuario obtenerUsuarioPorNombre(String nombre) {
        String sql = "SELECT * FROM usuario WHERE nombre = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("contrasena"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuario: " + e.getMessage());
        }
        return null;
    }

    // Método para insertar un usuario
    public void insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (nombre, contrasena) VALUES (?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getContrasena());
            stmt.executeUpdate();
            System.out.println("Usuario insertado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
        }
    }

    // Método para obtener la conexión
    public Connection getConexion() {
        return conexion;
    }
}
