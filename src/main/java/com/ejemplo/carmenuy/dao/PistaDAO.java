// Path: src/main/java/com/ejemplo/carmenuy/dao/PistaDAO.java
package com.ejemplo.carmenuy.dao;

import com.ejemplo.carmenuy.model.Pista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PistaDAO {
    private final Connection connection;

    public PistaDAO(Connection connection) {
        this.connection = connection;
    }

    public void crearTabla() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS pistas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                localidad_id INTEGER NOT NULL,
                numero TEXT NOT NULL,
                descripcion TEXT NOT NULL,
                esCorrecta BOOLEAN NOT NULL,
                FOREIGN KEY(localidad_id) REFERENCES localidades(id)
            );
            """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        }
    }

    public void insertarPista(Pista pista) throws SQLException {
        if (pistaYaExiste(pista)) {
            System.out.println("Pista ya existe con el mismo número y localidad_id.");
            return; // No continuar con la inserción
        }
        String sql = "INSERT INTO pistas (localidad_id, numero, descripcion, esCorrecta) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pista.getLocalidadId());
            statement.setString(2, pista.getNumero());
            statement.setString(3, pista.getDescripcion());
            statement.setBoolean(4, pista.esCorrecta());
            statement.executeUpdate();
        }
    }

    public void actualizarPista(Pista pista) throws SQLException {
        String sql = "UPDATE pistas SET localidad_id = ?, numero = ?, descripcion = ?, esCorrecta = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pista.getLocalidadId());
            statement.setString(2, pista.getNumero());
            statement.setString(3, pista.getDescripcion());
            statement.setBoolean(4, pista.esCorrecta());
            statement.setInt(5, pista.getId());
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

    public Pista obtenerPistaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM pistas WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Pista(
                        resultSet.getInt("id"),
                        resultSet.getInt("localidad_id"),
                        resultSet.getString("numero"),
                        resultSet.getString("descripcion"),
                        resultSet.getBoolean("esCorrecta")
                );
            }
        }
        return null;
    }

    public List<Pista> obtenerPistasPorLocalidad(int localidadId) throws SQLException {
        List<Pista> pistas = new ArrayList<>();
        String sql = "SELECT * FROM pistas WHERE localidad_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, localidadId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                pistas.add(new Pista(
                        resultSet.getInt("id"),
                        resultSet.getInt("localidad_id"),
                        resultSet.getString("numero"),
                        resultSet.getString("descripcion"),
                        resultSet.getBoolean("esCorrecta")
                ));
            }
        }
        return pistas;
    }

    public List<Pista> obtenerTodasLasPistas() throws SQLException {
        List<Pista> pistas = new ArrayList<>();
        String sql = "SELECT * FROM pistas";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                pistas.add(new Pista(
                        resultSet.getInt("id"),
                        resultSet.getInt("localidad_id"),
                        resultSet.getString("numero"),
                        resultSet.getString("descripcion"),
                        resultSet.getBoolean("esCorrecta")
                ));
            }
        }
        return pistas;
    }

    public void insertarPistasIniciales() throws SQLException {
        List<Pista> pistasIniciales = List.of(
                new Pista(0, 1, "1A", "Primera pista en localidad 1", true),
                new Pista(0, 2, "1B", "Primera pista en localidad 2", false)
        );
        for (Pista pista : pistasIniciales) {
            insertarPista(pista);
        }
    }

    private boolean pistaYaExiste(Pista pista) throws SQLException {
        String sql = "SELECT COUNT(*) FROM pistas WHERE localidad_id = ? AND numero = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pista.getLocalidadId());
            statement.setString(2, pista.getNumero());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
}
