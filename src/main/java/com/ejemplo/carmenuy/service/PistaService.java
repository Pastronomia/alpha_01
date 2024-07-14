// CarmenSandiegoUruguay/src/main/java/com/ejemplo/carmenuy/service/PistaService.java
package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.dao.PistaDAO;
import com.ejemplo.carmenuy.model.Pista;
import com.ejemplo.carmenuy.model.PistaGeografia;
import com.ejemplo.carmenuy.model.PistaHistoria;
import com.ejemplo.carmenuy.model.PistaLeyenda;
import com.ejemplo.carmenuy.model.PistaTurismo;

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
}
public class PistaService {
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
}
public class PistaService {
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

    /**
     * Inserta las pistas iniciales en la base de datos.
     *
     * @throws SQLException Si ocurre un error al insertar las pistas iniciales.
     */
    public void insertarPistasIniciales() throws SQLException {
        try {
            insertarPista(new PistaGeografia(1, "Pista de Geografía 1", true));
            insertarPista(new PistaHistoria(2, "Pista de Historia 1", true));
            insertarPista(new PistaLeyenda(3, "Pista de Leyenda 1", true));
            insertarPista(new PistaGastronomia(4, "Pista de Gastronomía 1", true));
            insertarPista(new PistaTurismo(5, "Pista de Turismo 1", true));
            LOGGER.info("Pistas iniciales insertadas exitosamente.");
        } catch (SQLException e) {
            LOGGER.severe("Error al insertar pistas iniciales: " + e.getMessage());
            throw e;
        }
    }
}
public class PistaService {
    /**
     * Encripta una pista utilizando una lógica simple de sustitución.
     *
     * @param pista La pista a encriptar.
     * @return La pista encriptada.
     */
    public String encriptarPista(String pista) {
        return pista.replaceAll("[aeiou]", "*");
    }

    /**
     * Desencripta una pista utilizando la lógica inversa de la encriptación.
     *
     * @param pistaEncriptada La pista encriptada a desencriptar.
     * @return La pista desencriptada.
     */
    public String desencriptarPista(String pistaEncriptada) {
        return pistaEncriptada.replaceAll("\\*", "a");
    }

    /**
     * Inserta una nueva pista encriptada en la base de datos.
     *
     * @param pista La pista a insertar.
     * @throws SQLException Si ocurre un error al insertar la pista.
     */
    public void insertarPistaEncriptada(Pista pista) throws SQLException {
        try {
            String textoEncriptado = encriptarPista(pista.getTexto());
            pista.setTexto(textoEncriptado);
            pistaDAO.insertarPista(pista);
            LOGGER.info("Pista encriptada insertada: " + textoEncriptado);
        } catch (SQLException e) {
            LOGGER.severe("Error al insertar pista encriptada: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Obtiene una pista desencriptada por su ID.
     *
     * @param id El ID de la pista a obtener.
     * @return La pista desencriptada.
     * @throws SQLException Si ocurre un error al obtener la pista.
     */
    public Pista obtenerPistaDesencriptadaPorId(int id) throws SQLException {
        try {
            Pista pista = pistaDAO.obtenerPistaPorId(id);
            String textoDesencriptado = desencriptarPista(pista.getTexto());
            pista.setTexto(textoDesencriptado);
            return pista;
        } catch (SQLException e) {
            LOGGER.severe("Error al obtener pista desencriptada con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }
}
