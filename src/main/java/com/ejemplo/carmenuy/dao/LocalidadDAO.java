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
                "nombre TEXT NOT NULL, " +
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

    public void insertarLocalidadesIniciales() throws SQLException {
        List<Localidad> localidadesIniciales = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            localidadesIniciales.add(new Localidad(String.valueOf(c), "Descripción de " + c, 0.0, 0.0));
        }
        for (char c1 = 'A'; c1 <= 'Z'; c1++) {
            for (char c2 = 'A'; c2 <= 'Z'; c2++) {
                localidadesIniciales.add(new Localidad("" + c1 + c2, "Descripción de " + c1 + c2, 0.0, 0.0));
                if (localidadesIniciales.size() == 40) {
                    break;
                }
            }
        }
        for (Localidad localidad : localidadesIniciales) {
            insertarLocalidad(localidad);
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

    public Localidad obtenerLocalidadPorNombre(String nombre) throws SQLException {
        String sql = "SELECT id, nombre, descripcion, latitud, longitud FROM localidades WHERE nombre = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String descripcion = resultSet.getString("descripcion");
                    double latitud = resultSet.getDouble("latitud");
                    double longitud = resultSet.getDouble("longitud");
                    return new Localidad(id, nombre, descripcion, latitud, longitud);
                }
            }
        }
        return null;
    }
}
