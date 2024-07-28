package com.ejemplo.carmenuy.dao;

import com.ejemplo.carmenuy.model.Secuaz;
import com.ejemplo.carmenuy.model.Localidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SecuazDAO {
    private final Connection connection;
    private final LocalidadDAO localidadDAO;

    public SecuazDAO(Connection connection, LocalidadDAO localidadDAO) {
        this.connection = connection;
        this.localidadDAO = localidadDAO;
    }

    public void crearTabla() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS secuaces (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "habilidad TEXT NOT NULL, " +
                "peligrosidad INTEGER NOT NULL, " +
                "localidad_id INTEGER, " +
                "capturado INTEGER NOT NULL DEFAULT 0, " +
                "FOREIGN KEY(localidad_id) REFERENCES localidades(id));";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        }
    }

    public void insertarSecuaz(Secuaz secuaz) throws SQLException {
        if (secuazYaExiste(secuaz)) {
            System.out.println("Secuaz ya existe con el mismo nombre y habilidad.");
            return;
        }
        String sql = "INSERT INTO secuaces (nombre, habilidad, peligrosidad, localidad_id, capturado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, secuaz.getNombre());
            statement.setString(2, secuaz.getHabilidad());
            statement.setInt(3, secuaz.getPeligrosidad());
            if (secuaz.getLocalidad() != null) {
                statement.setInt(4, secuaz.getLocalidad().getId());
            } else {
                statement.setNull(4, java.sql.Types.INTEGER);
            }
            statement.setInt(5, secuaz.isCapturado() ? 1 : 0);
            statement.executeUpdate();
        }
    }

    private boolean secuazYaExiste(Secuaz secuaz) throws SQLException {
        String sql = "SELECT COUNT(*) FROM secuaces WHERE nombre = ? AND habilidad = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, secuaz.getNombre());
            statement.setString(2, secuaz.getHabilidad());
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public List<Secuaz> obtenerTodosLosSecuaces() throws SQLException {
        List<Secuaz> secuaces = new ArrayList<>();
        String sql = "SELECT s.id, s.nombre, s.habilidad, s.peligrosidad, s.capturado, l.id AS localidad_id, l.nombre AS localidad_nombre, l.descripcion AS localidad_descripcion, l.latitud, l.longitud " +
                "FROM secuaces s " +
                "LEFT JOIN localidades l ON s.localidad_id = l.id";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Localidad localidad = null;
                if (resultSet.getInt("localidad_id") != 0) {
                    localidad = new Localidad(
                            resultSet.getInt("localidad_id"),
                            resultSet.getString("localidad_nombre"),
                            resultSet.getString("localidad_descripcion"),
                            resultSet.getDouble("latitud"),
                            resultSet.getDouble("longitud")
                    );
                }
                secuaces.add(new Secuaz(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("habilidad"),
                        resultSet.getInt("peligrosidad"),
                        localidad,
                        resultSet.getInt("capturado") == 1
                ));
            }
        }
        return secuaces;
    }

    public List<Secuaz> obtenerSecuacesNoCapturados() throws SQLException {
        List<Secuaz> secuacesNoCapturados = new ArrayList<>();
        String sql = "SELECT s.id, s.nombre, s.habilidad, s.peligrosidad, s.capturado, l.id AS localidad_id, l.nombre AS localidad_nombre, l.descripcion AS localidad_descripcion, l.latitud, l.longitud " +
                "FROM secuaces s " +
                "LEFT JOIN localidades l ON s.localidad_id = l.id " +
                "WHERE s.capturado = 0";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Localidad localidad = null;
                if (resultSet.getInt("localidad_id") != 0) {
                    localidad = new Localidad(
                            resultSet.getInt("localidad_id"),
                            resultSet.getString("localidad_nombre"),
                            resultSet.getString("localidad_descripcion"),
                            resultSet.getDouble("latitud"),
                            resultSet.getDouble("longitud")
                    );
                }
                secuacesNoCapturados.add(new Secuaz(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("habilidad"),
                        resultSet.getInt("peligrosidad"),
                        localidad,
                        resultSet.getInt("capturado") == 1
                ));
            }
        }
        return secuacesNoCapturados;
    }

    public void actualizarSecuaz(Secuaz secuaz) throws SQLException {
        String sql = "UPDATE secuaces SET nombre = ?, habilidad = ?, peligrosidad = ?, localidad_id = ?, capturado = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, secuaz.getNombre());
            statement.setString(2, secuaz.getHabilidad());
            statement.setInt(3, secuaz.getPeligrosidad());
            if (secuaz.getLocalidad() != null) {
                statement.setInt(4, secuaz.getLocalidad().getId());
            } else {
                statement.setNull(4, java.sql.Types.INTEGER);
            }
            statement.setInt(5, secuaz.isCapturado() ? 1 : 0);
            statement.setInt(6, secuaz.getId());
            statement.executeUpdate();
        }
    }

    public void eliminarSecuaz(int id) throws SQLException {
        String sql = "DELETE FROM secuaces WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    private Secuaz crearSecuazDesdeResultSet(ResultSet resultSet) throws SQLException {
        Localidad localidad = null;
        if (resultSet.getInt("localidad_id") != 0) {
            localidad = new Localidad(
                    resultSet.getInt("localidad_id"),
                    resultSet.getString("localidad_nombre"),
                    resultSet.getString("localidad_descripcion"),
                    resultSet.getDouble("latitud"),
                    resultSet.getDouble("longitud")
            );
        }
        return new Secuaz(
                resultSet.getInt("id"),
                resultSet.getString("nombre"),
                resultSet.getString("habilidad"),
                resultSet.getInt("peligrosidad"),
                localidad,
                resultSet.getInt("capturado") == 1
        );
    }

    // Método para capturar secuaz
    public void capturarSecuaz(int secuazId) throws SQLException {
        String sql = "UPDATE secuaces SET capturado = 1 WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, secuazId);
            statement.executeUpdate();
        }
    }
}
