package com.ejemplo.carmenuy.dao;

import com.ejemplo.carmenuy.model.Pista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class PistaDAO {
    private Connection connection;

    public PistaDAO(Connection connection) {
        this.connection = connection;
    }

    public void crearTabla() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS pistas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "texto TEXT NOT NULL, " +
                "localidad_id INTEGER, " +
                "FOREIGN KEY(localidad_id) REFERENCES localidades(id))";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        }
    }

    public void insertarPista(Pista pista) throws SQLException {
        String sql = "INSERT INTO pistas (texto, localidad_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pista.getTexto());
            statement.setInt(2, pista.getLocalidadId());
            statement.executeUpdate();
        }
    }

    public void insertarPistasIniciales() throws SQLException {
        // Ejemplo de cómo podrían insertarse pistas iniciales
        List<Pista> pistasIniciales = new ArrayList<>();
        pistasIniciales.add(new Pista("Pista 1", 1));
        pistasIniciales.add(new Pista("Pista 2", 2));
        for (Pista pista : pistasIniciales) {
            insertarPista(pista);
        }
    }

    public Pista obtenerPistaPorId(int id) throws SQLException {
        String sql = "SELECT id, texto, localidad_id FROM pistas WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Pista(
                        resultSet.getInt("id"),
                        resultSet.getString("texto"),
                        resultSet.getInt("localidad_id")
                );
            }
        }
        return null;
    }

    public List<Pista> obtenerTodasLasPistas() throws SQLException {
        List<Pista> pistas = new ArrayList<>();
        String sql = "SELECT id, texto, localidad_id FROM pistas";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                pistas.add(new Pista(
                        resultSet.getInt("id"),
                        resultSet.getString("texto"),
                        resultSet.getInt("localidad_id")
                ));
            }
        }
        return pistas;
    }

    public void actualizarPista(Pista pista) throws SQLException {
        String sql = "UPDATE pistas SET texto = ?, localidad_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pista.getTexto());
            statement.setInt(2, pista.getLocalidadId());
            statement.setInt(3, pista.getId());
            statement.executeUpdate();
        }
    }

    public void eliminarPista(int id) throws SQLException {
        String sql = "DELETE FROM pistas WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
