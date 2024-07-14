package com.ejemplo.carmenuy.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para inicializar y conectar a la base de datos de Carmen Sandiego.
 */
public class DatabaseInitialization {
    private static final Logger logger = Logger.getLogger(DatabaseInitialization.class.getName());
    private static final String URL = "jdbc:sqlite:F:/sqlite/carmen_sandiego.db";

    /**
     * Establece la conexión con la base de datos.
     * @return una conexión a la base de datos.
     */
    public static Connection getConnection() {
        try {
            // Cargar el driver de SQLite, si es necesario
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(URL);
            logger.log(Level.INFO, "Conexión establecida con la base de datos.");
            return connection;
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "No se encontró el driver de SQLite", e);
            throw new RuntimeException("Error al cargar el driver de SQLite", e);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al conectar con la base de datos", e);
            throw new RuntimeException("Error al conectar con la base de datos", e);
        }
    }

    /**
     * Método principal para verificar la conexión.
     */
    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.close();
                logger.log(Level.INFO, "Conexión cerrada exitosamente.");
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error al cerrar la conexión", e);
            }
        }
    }
}
