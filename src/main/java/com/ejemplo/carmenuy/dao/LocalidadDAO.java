package com.ejemplo.carmenuy.dao;

import com.ejemplo.carmenuy.model.Localidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocalidadDAO {
    private Connection connection;

    public LocalidadDAO(Connection connection) {
        this.connection = connection;
    }

    public void crearTabla() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS localidades (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "descripcion TEXT, " +
                "latitud DOUBLE, " +
                "longitud DOUBLE);";
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

    public List<Localidad> obtenerTodasLasLocalidades() throws SQLException {
        List<Localidad> localidades = new ArrayList<>();
        String sql = "SELECT id, nombre, descripcion, latitud, longitud FROM localidades";
        try (PreparedStatement statement = this.connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                double latitud = resultSet.getDouble("latitud");
                double longitud = resultSet.getDouble("longitud");
                localidades.add(new Localidad(id, nombre, descripcion, latitud, longitud));
            }
        }
        return localidades;
    }
}
