package com.ejemplo.carmenuy.dao;

import com.ejemplo.carmenuy.model.Mision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MisionDAO {
    private static final Logger logger = LoggerFactory.getLogger(MisionDAO.class);
    private final Connection connection;

    public MisionDAO(Connection connection) {
        this.connection = connection;
    }

    public void crearTabla() {
        String sql = """
                CREATE TABLE IF NOT EXISTS Misiones (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    titulo TEXT NOT NULL,
                    descripcion TEXT,
                    objetivo TEXT,
                    completada BOOLEAN
                )""";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            logger.info("Tabla de misiones creada o verificada exitosamente.");
        } catch (SQLException e) {
            logger.error("Error al crear tabla de misiones: ", e);
        }
    }

    public void insertarMision(Mision mision) {
        if (!validarMision(mision)) {
            return;
        }
        String sql = "INSERT INTO Misiones (titulo, descripcion, objetivo, completada) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, mision.getTitulo());
            pstmt.setString(2, mision.getDescripcion());
            pstmt.setString(3, mision.getObjetivo());
            pstmt.setBoolean(4, mision.isCompletada());
            pstmt.executeUpdate();
            logger.info("Misión insertada exitosamente.");
        } catch (SQLException e) {
            logger.error("Error al insertar misión: ", e);
        }
    }

    public Mision obtenerMisionPorId(int id) {
        String sql = "SELECT * FROM Misiones WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Mision(rs.getInt("id"), rs.getString("titulo"), rs.getString("descripcion"), rs.getString("objetivo"), rs.getBoolean("completada"));
                }
            }
        } catch (SQLException e) {
            logger.error("Error al obtener misión por ID: ", e);
        }
        return null;
    }

    public List<Mision> obtenerTodasLasMisiones() {
        List<Mision> misiones = new ArrayList<>();
        String sql = "SELECT * FROM Misiones";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                misiones.add(new Mision(rs.getInt("id"), rs.getString("titulo"), rs.getString("descripcion"), rs.getString("objetivo"), rs.getBoolean("completada")));
            }
            logger.info("Misiones obtenidas exitosamente.");
        } catch (SQLException e) {
            logger.error("Error al obtener todas las misiones: ", e);
        }
        return misiones;
    }

    public void actualizarMision(Mision mision) {
        if (!validarMision(mision)) {
            return;
        }
        String sql = "UPDATE Misiones SET titulo = ?, descripcion = ?, objetivo = ?, completada = ? WHERE id = ?";
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, mision.getTitulo());
                pstmt.setString(2, mision.getDescripcion());
                pstmt.setString(3, mision.getObjetivo());
                pstmt.setBoolean(4, mision.isCompletada());
                pstmt.setInt(5, mision.getId());
                pstmt.executeUpdate();
                connection.commit();
                logger.info("Misión actualizada exitosamente.");
            } catch (SQLException e) {
                connection.rollback();
                logger.error("Error al actualizar misión, se hizo rollback.", e);
            }
        } catch (SQLException e) {
            logger.error("Error al manejar la transacción", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.error("Error al restaurar auto-commit", e);
            }
        }
    }

    public void eliminarMision(int id) {
        String sql = "DELETE FROM Misiones WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            logger.info("Misión eliminada exitosamente.");
        } catch (SQLException e) {
            logger.error("Error al eliminar misión: ", e);
        }
    }

    private boolean validarMision(Mision mision) {
        if (mision == null) {
            logger.error("La misión no puede ser nula.");
            return false;
        }
        if (mision.getTitulo() == null || mision.getTitulo().trim().isEmpty()) {
            logger.error("El título de la misión no puede ser nulo o vacío.");
            return false;
        }
        if (mision.getDescripcion() == null || mision.getDescripcion().trim().isEmpty()) {
            logger.error("La descripción no puede ser nula o vacía.");
            return false;
        }
        if (mision.getObjetivo() == null || mision.getObjetivo().trim().isEmpty()) {
            logger.error("El objetivo no puede ser nulo o vacío.");
            return false;
        }
        return true;
    }
}
