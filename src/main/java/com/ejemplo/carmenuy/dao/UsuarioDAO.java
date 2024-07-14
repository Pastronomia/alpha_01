package com.ejemplo.carmenuy.dao;

import com.ejemplo.carmenuy.database.DatabaseInitialization;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO para manejar las operaciones de la base de datos relacionadas con los detectives.
 */
public class DetectiveDAO {
    private static final Logger logger = Logger.getLogger(DetectiveDAO.class.getName());
    private Connection connection;

    /**
     * Constructor del DAO de detectives. Utiliza la conexión centralizada desde DatabaseInitialization.
     */
    public DetectiveDAO() {
        this.connection = DatabaseInitialization.getConnection();
    }

    /**
     * Actualiza el rango de los detectives basado en el número de capturas.
     */
    public void actualizarRangoDetective() {
        String sql = "UPDATE detectives SET rango = CASE " +
                "WHEN capturas >= 4 THEN 'INSPECTOR' " +
                "WHEN capturas >= 3 THEN 'DETECTIVE JEFE' " +
                "WHEN capturas >= 2 THEN 'DETECTIVE EFICIENTE' " +
                "WHEN capturas >= 1 THEN 'DETECTIVE APRENDIZ' " +
                "ELSE 'DETECTIVE JUNIOR' END";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar los rangos de los detectives: " + e.getMessage(), e);
        }
    }

    /**
     * Método adicional para manejar otros CRUD o operaciones específicas de detectives...
     */

    /**
     * Cierra los recursos de forma segura.
     *
     * @param recursos Los recursos a cerrar.
     */
    private void cerrarRecursos(AutoCloseable... recursos) {
        for (AutoCloseable recurso : recursos) {
            if (recurso != null) {
                try {
                    recurso.close();
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Error al cerrar recurso", e);
                }
            }
        }
    }
}
