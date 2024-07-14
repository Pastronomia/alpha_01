package com.ejemplo.carmenuy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PistaDAO {
    private Connection connection;

    public PistaDAO(Connection connection) {
        this.connection = connection;
    }

    public void crearTabla() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS pistas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "descripcion TEXT NOT NULL, " +
                "localidad_id INTEGER, " +
                "FOREIGN KEY(localidad_id) REFERENCES localidades(id))";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        }
    }

    public void insertarPistasIniciales() throws SQLException {
        // Ejemplo de inserción de pistas
        String sql = "INSERT INTO pistas (descripcion, localidad_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "Pista en Montevideo");
            statement.setInt(2, 1);
            statement.addBatch();

            statement.setString(1, "Pista en Punta del Este");
            statement.setInt(2, 2);
            statement.addBatch();

            statement.executeBatch();
        }
    }
}
