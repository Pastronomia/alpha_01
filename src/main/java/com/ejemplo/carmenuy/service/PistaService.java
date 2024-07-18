package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.dao.PistaDAO;
import com.ejemplo.carmenuy.model.Pista;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Servicio que maneja las operaciones relacionadas con las pistas en el juego Carmen Sandiego Uruguay.
 */
public class PistaService {
    private static final Logger LOGGER = Logger.getLogger(PistaService.class.getName());
    private final PistaDAO pistaDAO;

    /**
     * Constructor del servicio de pistas.
     *
     * @param connection Conexión a la base de datos.
     */
    public PistaService(Connection connection) {
        this.pistaDAO = new PistaDAO(connection);
    }

    /**
     * Crea la tabla de pistas en la base de datos.
     *
     * @throws SQLException Si ocurre un error al crear la tabla.
     */
    public void crearTablaPistas() throws SQLException {
        try {
            pistaDAO.crearTabla();
            LOGGER.info("Tabla de pistas creada exitosamente.");
        } catch (SQLException e) {
            LOGGER.severe("Error al crear la tabla de pistas: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Inserta una nueva pista en la base de datos.
     *
     * @param pista La pista a insertar.
     * @throws SQLException Si ocurre un error al insertar la pista.
     */
    public void insertarPista(Pista pista) throws SQLException {
        try {
            pistaDAO.insertarPista(pista);
            LOGGER.info("Pista insertada: " + pista.getTexto());
        } catch (SQLException e) {
            LOGGER.severe("Error al insertar pista: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Obtiene una pista por su ID.
     *
     * @param id El ID de la pista a obtener.
     * @return La pista encontrada.
     * @throws SQLException Si ocurre un error al obtener la pista.
     */
    public Pista obtenerPistaPorId(int id) throws SQLException {
        try {
            return pistaDAO.obtenerPistaPorId(id);
        } catch (SQLException e) {
            LOGGER.severe("Error al obtener pista con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    /**
     * Obtiene todas las pistas de la base de datos.
     *
     * @return Lista de todas las pistas.
     * @throws SQLException Si ocurre un error al obtener las pistas.
     */
    public List<Pista> obtenerTodasLasPistas() throws SQLException {
        try {
            return pistaDAO.obtenerTodasLasPistas();
        } catch (SQLException e) {
            LOGGER.severe("Error al obtener todas las pistas: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Actualiza una pista existente en la base de datos.
     *
     * @param pista La pista a actualizar.
     * @throws SQLException Si ocurre un error al actualizar la pista.
     */
    public void actualizarPista(Pista pista) throws SQLException {
        try {
            pistaDAO.actualizarPista(pista);
            LOGGER.info("Pista actualizada: " + pista.getTexto());
        } catch (SQLException e) {
            LOGGER.severe("Error al actualizar pista: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Elimina una pista de la base de datos por su ID.
     *
     * @param id El ID de la pista a eliminar.
     * @throws SQLException Si ocurre un error al eliminar la pista.
     */
    public void eliminarPista(int id) throws SQLException {
        try {
            pistaDAO.eliminarPista(id);
            LOGGER.info("Pista eliminada con ID: " + id);
        } catch (SQLException e) {
            LOGGER.severe("Error al eliminar pista con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }
}
