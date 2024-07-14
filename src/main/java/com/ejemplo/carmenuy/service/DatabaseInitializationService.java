package com.ejemplo.carmenuy.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseInitializationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseInitializationService.class);
    private Properties config;

    public DatabaseInitializationService() {
        loadConfig();
    }

    private void loadConfig() {
        config = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("Unable to find config.properties");
            }
            config.load(input);
        } catch (IOException e) {
            LOGGER.error("Failed to load configuration", e);
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    public void initializeDatabase() {
        try (Connection conn = getConnection()) {
            createTables(conn);
            loadInitialData(conn);
        } catch (SQLException e) {
            LOGGER.error("Database initialization failed", e);
            throw new RuntimeException("Database initialization failed", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(config.getProperty("db.url"));
    }

    private void createTables(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            // Create 'localidades' table
            stmt.execute("CREATE TABLE IF NOT EXISTS localidades (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT NOT NULL, " +
                    "descripcion TEXT NOT NULL, " +
                    "latitud REAL, " +
                    "longitud REAL);");

            // Create 'usuarios' table
            stmt.execute("CREATE TABLE IF NOT EXISTS usuarios (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT NOT NULL, " +
                    "apellido TEXT NOT NULL, " +
                    "contrasena TEXT NOT NULL, " + // Consider storing passwords encrypted
                    "rango TEXT NOT NULL DEFAULT 'DETECTIVE JUNIOR', " +
                    "capturas INTEGER DEFAULT 0, " +
                    "progreso TEXT DEFAULT '');");

            // Create 'detectives' table (similar to 'usuarios', could be redundant)
            stmt.execute("CREATE TABLE IF NOT EXISTS detectives (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT NOT NULL, " +
                    "apellido TEXT NOT NULL, " +
                    "contrasena TEXT NOT NULL, " + // Consider storing passwords encrypted
                    "rango TEXT NOT NULL DEFAULT 'DETECTIVE JUNIOR', " +
                    "capturas INTEGER DEFAULT 0);");

            // Create 'secuaces' table
            stmt.execute("CREATE TABLE IF NOT EXISTS secuaces (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT NOT NULL, " +
                    "habilidad TEXT NOT NULL, " +
                    "peligrosidad INTEGER NOT NULL);");

            // Create 'pistas' table
            stmt.execute("CREATE TABLE IF NOT EXISTS pistas (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "localidad TEXT NOT NULL, " +
                    "numero TEXT NOT NULL, " +
                    "descripcion TEXT NOT NULL, " +
                    "esCorrecta INTEGER NOT NULL);");

            // Create 'misiones' table
            stmt.execute("CREATE TABLE IF NOT EXISTS misiones (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "titulo TEXT NOT NULL, " +
                    "descripcion TEXT, " +
                    "objetivo TEXT, " +
                    "completada BOOLEAN);");

            LOGGER.info("All tables created successfully.");
        }
    }

    private void loadInitialData(Connection conn) throws SQLException {
        // Example data to be loaded; this method should be implemented if needed
    }
}
