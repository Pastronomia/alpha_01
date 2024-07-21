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
     * Método para encriptar una pista.
     *
     * @param pista La pista a encriptar.
     * @return La pista encriptada.
     */
    public String encriptarPista(String pista) {
        return pista.replaceAll("[aeiouAEIOU]", "*");
    }

    /**
     * Método para desencriptar una pista.
     *
     * @param pistaEncriptada La pista encriptada.
     * @return La pista desencriptada.
     */
    public String desencriptarPista(String pistaEncriptada) {
        return pistaEncriptada.replace("*", "a");
    }

    /**
     * Método para insertar una pista encriptada en la base de datos.
     *
     * @param pista La pista a insertar.
     * @throws SQLException Si ocurre un error al insertar la pista.
     */
    public void insertarPistaEncriptada(Pista pista) throws SQLException {
        try {
            String pistaEncriptada = encriptarPista(pista.getTexto());
            Pista pistaEncriptadaObj = new Pista(pista.getId(), pistaEncriptada, pista.getLocalidadId());
            pistaDAO.insertarPista(pistaEncriptadaObj);
        } catch (SQLException e) {
            LOGGER.severe("Error al insertar pista encriptada: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Método para obtener una pista desencriptada por su ID.
     *
     * @param id El ID de la pista a obtener.
     * @return La pista desencriptada.
     * @throws SQLException Si ocurre un error al obtener la pista.
     */
    public Pista obtenerPistaDesencriptadaPorId(int id) throws SQLException {
        try {
            Pista pistaEncriptada = pistaDAO.obtenerPistaPorId(id);
            if (pistaEncriptada == null) {
                throw new SQLException("Pista no encontrada con ID: " + id);
            }
            String pistaDesencriptada = desencriptarPista(pistaEncriptada.getTexto());
            return new Pista(pistaEncriptada.getId(), pistaDesencriptada, pistaEncriptada.getLocalidadId());
        } catch (SQLException e) {
            LOGGER.severe("Error al obtener pista desencriptada por ID: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Método para insertar pistas iniciales en la base de datos.
     *
     * @throws SQLException Si ocurre un error al insertar las pistas.
     */
    public void insertarPistasIniciales() throws SQLException {
        try {
            List<Pista> pistasIniciales = List.of(
                    new Pista("Pista 1", 1),
                    new Pista("Pista 2", 2),
                    new Pista("Pista 3", 3),
                    new Pista("Pista 4", 4),
                    new Pista("Pista 5", 5)
            );
            for (Pista pista : pistasIniciales) {
                pistaDAO.insertarPista(pista);
            }
        } catch (SQLException e) {
            LOGGER.severe("Error al insertar pistas iniciales: " + e.getMessage());
            throw e;
        }
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
