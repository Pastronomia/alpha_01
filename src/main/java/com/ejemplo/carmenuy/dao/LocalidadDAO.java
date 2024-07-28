package com.ejemplo.carmenuy.dao;

import com.ejemplo.carmenuy.model.Localidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocalidadDAO {
    private final Connection connection;

    public LocalidadDAO(Connection connection) {
        this.connection = connection;
    }

    public void crearTabla() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS localidades (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL UNIQUE, " +
                "descripcion TEXT NOT NULL, " +
                "latitud REAL NOT NULL, " +
                "longitud REAL NOT NULL);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        }
    }

    public void insertarLocalidad(Localidad localidad) throws SQLException {
        String sql = "INSERT INTO localidades (nombre, descripcion, latitud, longitud) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, localidad.getNombre());
            statement.setString(2, localidad.getDescripcion());
            statement.setDouble(3, localidad.getLatitud());
            statement.setDouble(4, localidad.getLongitud());
            statement.executeUpdate();
        }
    }

    public Localidad obtenerLocalidadPorId(int id) throws SQLException {
        String sql = "SELECT * FROM localidades WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Localidad(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("descripcion"),
                        resultSet.getDouble("latitud"),
                        resultSet.getDouble("longitud")
                );
            }
        }
        return null;
    }

    public List<Localidad> obtenerTodasLasLocalidades() throws SQLException {
        List<Localidad> localidades = new ArrayList<>();
        String sql = "SELECT * FROM localidades";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                localidades.add(new Localidad(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("descripcion"),
                        resultSet.getDouble("latitud"),
                        resultSet.getDouble("longitud")
                ));
            }
        }
        return localidades;
    }

    public void actualizarLocalidad(Localidad localidad) throws SQLException {
        String sql = "UPDATE localidades SET nombre = ?, descripcion = ?, latitud = ?, longitud = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, localidad.getNombre());
            statement.setString(2, localidad.getDescripcion());
            statement.setDouble(3, localidad.getLatitud());
            statement.setDouble(4, localidad.getLongitud());
            statement.setInt(5, localidad.getId());
            statement.executeUpdate();
        }
    }

    public void eliminarLocalidad(int id) throws SQLException {
        String sql = "DELETE FROM localidades WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public void insertarLocalidadesIniciales() throws SQLException {
        List<Localidad> localidadesIniciales = List.of(
                new Localidad("A", "Localidad A", -34.90328, -56.18816),
                new Localidad("B", "Localidad B", -31.3833, -57.9667),
                new Localidad("C", "Localidad C", -34.9667, -54.95),
                // Agregar todas las localidades necesarias hasta "AN"
                new Localidad("AN", "Localidad AN", -33.0, -55.0)
        );
        for (Localidad localidad : localidadesIniciales) {
            insertarLocalidad(localidad);
        }
    }
}
