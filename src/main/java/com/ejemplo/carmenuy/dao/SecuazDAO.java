package com.ejemplo.carmenuy.dao;

import com.ejemplo.carmenuy.model.Secuaz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SecuazDAO {
    private final Connection connection;

    public SecuazDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertarSecuaces(List<Secuaz> secuaces) throws SQLException {
        String sql = "INSERT INTO secuaces (nombre, habilidad, peligrosidad) VALUES (?, ?, ?)";
        connection.setAutoCommit(false); // Desactivar auto-commit para manejar la transacción manualmente
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Secuaz secuaz : secuaces) {
                if (!validarSecuaz(secuaz)) {
                    continue; // Saltar este secuaz si la validación falla
                }
                stmt.setString(1, secuaz.getNombre());
                stmt.setString(2, secuaz.getHabilidad());
                stmt.setInt(3, secuaz.getPeligrosidad());
                stmt.addBatch();
            }
            stmt.executeBatch();
            connection.commit(); // Confirmar todos los cambios si todos los lotes se insertan correctamente
        } catch (SQLException e) {
            connection.rollback(); // Revertir todos los cambios en caso de error
            throw e;
        } finally {
            connection.setAutoCommit(true); // Restaurar auto-commit
        }
    }

    private boolean validarSecuaz(Secuaz secuaz) {
        if (secuaz == null) {
            return false; // Verificación básica para asegurar que el objeto secuaz no sea nulo
        }
        return secuaz.getNombre() != null && !secuaz.getNombre().isEmpty() &&
                secuaz.getHabilidad() != null && !secuaz.getHabilidad().isEmpty() &&
                secuaz.getPeligrosidad() > 0; // Asumimos que la peligrosidad debe ser un valor positivo
    }
}
