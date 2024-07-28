package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.dao.LocalidadDAO;
import com.ejemplo.carmenuy.dao.PistaDAO;
import com.ejemplo.carmenuy.dao.SecuazDAO;
import com.ejemplo.carmenuy.model.Localidad;
import com.ejemplo.carmenuy.model.Pista;
import com.ejemplo.carmenuy.model.Secuaz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JuegoService {
    private static final Logger LOGGER = Logger.getLogger(JuegoService.class.getName());
    private final PistaDAO pistaDAO;
    private final LocalidadDAO localidadDAO;
    private final SecuazDAO secuazDAO;
    private Localidad localidadActual;
    private Secuaz secuazActual;

    public JuegoService(PistaDAO pistaDAO, LocalidadDAO localidadDAO, SecuazDAO secuazDAO) {
        this.pistaDAO = pistaDAO;
        this.localidadDAO = localidadDAO;
        this.secuazDAO = secuazDAO;
        seleccionarSecuazAleatorio();
    }

    public void seleccionarSecuazAleatorio() {
        try {
            List<Secuaz> secuaces = secuazDAO.obtenerTodosLosSecuaces();
            Random random = new Random();
            secuazActual = secuaces.get(random.nextInt(secuaces.size()));
            localidadActual = secuazActual.getLocalidad();
            LOGGER.info("Secuaz seleccionado: " + secuazActual.getNombre() + " en " + localidadActual.getNombre());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al seleccionar el secuaz aleatorio", e);
            throw new RuntimeException("Error al seleccionar el secuaz aleatorio", e);
        }
    }

    public List<Pista> obtenerPistasDeLocalidadSecuaz() {
        try {
            if (localidadActual == null) {
                LOGGER.log(Level.WARNING, "La localidad actual no está definida.");
                return List.of();
            }
            List<Pista> pistas = pistaDAO.obtenerPistasPorLocalidad(localidadActual.getId());
            if (pistas.isEmpty()) {
                LOGGER.log(Level.WARNING, "No hay pistas disponibles para la localidad: " + localidadActual.getNombre());
                return List.of();
            }

            // Filtrar pistas correctas e incorrectas
            List<Pista> pistasCorrectas = new ArrayList<>();
            List<Pista> pistasIncorrectas = new ArrayList<>();
            for (Pista pista : pistas) {
                if (pista.esCorrecta()) {
                    pistasCorrectas.add(pista);
                } else {
                    pistasIncorrectas.add(pista);
                }
            }

            // Seleccionar una pista correcta y dos incorrectas aleatoriamente
            if (pistasCorrectas.isEmpty() || pistasIncorrectas.size() < 2) {
                LOGGER.log(Level.WARNING, "No hay suficientes pistas correctas o incorrectas.");
                return List.of();
            }

            Collections.shuffle(pistasIncorrectas);
            List<Pista> pistasSeleccionadas = new ArrayList<>();
            pistasSeleccionadas.add(pistasCorrectas.get(0));
            pistasSeleccionadas.add(pistasIncorrectas.get(0));
            pistasSeleccionadas.add(pistasIncorrectas.get(1));
            Collections.shuffle(pistasSeleccionadas);

            return pistasSeleccionadas;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener pistas de la localidad del secuaz", e);
            throw new RuntimeException("Error al obtener pistas de la localidad del secuaz", e);
        }
    }

    public boolean manejarSeleccionPista(Pista pista) {
        if (pista != null && pista.esCorrecta()) {
            try {
                localidadActual = localidadDAO.obtenerLocalidadPorId(pista.getLocalidadId());
                LOGGER.info("Pista correcta seleccionada. Nueva localidad: " + localidadActual.getNombre());
                return true;
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error al manejar la selección de pista", e);
                throw new RuntimeException("Error al manejar la selección de pista", e);
            }
        } else {
            LOGGER.info("Pista incorrecta seleccionada.");
        }
        return false;
    }

    public List<Localidad> obtenerTodasLasLocalidades() {
        try {
            return localidadDAO.obtenerTodasLasLocalidades();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener todas las localidades", e);
            throw new RuntimeException("Error al obtener todas las localidades", e);
        }
    }

    public void actualizarSecuaz(Secuaz secuaz) {
        try {
            secuazDAO.actualizarSecuaz(secuaz);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar el secuaz", e);
            throw new RuntimeException("Error al actualizar el secuaz", e);
        }
    }

    public void seleccionarNuevoSecuazNoCapturado() {
        try {
            List<Secuaz> secuacesDisponibles = secuazDAO.obtenerSecuacesNoCapturados();
            if (secuacesDisponibles.isEmpty()) {
                // Todos los secuaces han sido capturados, fin del juego
                // Implementa la lógica para terminar el juego aquí
                LOGGER.info("Todos los secuaces han sido capturados. Fin del juego.");
            } else {
                Random random = new Random();
                secuazActual = secuacesDisponibles.get(random.nextInt(secuacesDisponibles.size()));
                localidadActual = secuazActual.getLocalidad();
                LOGGER.info("Nuevo secuaz seleccionado: " + secuazActual.getNombre() + " en " + localidadActual.getNombre());
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al seleccionar un nuevo secuaz no capturado", e);
            throw new RuntimeException("Error al seleccionar un nuevo secuaz no capturado", e);
        }
    }

    public void capturarSecuazActual() {
        secuazActual.setCapturado(true);
        actualizarSecuaz(secuazActual);
        LOGGER.info("Secuaz capturado: " + secuazActual.getNombre());
    }

    public Localidad getLocalidadActual() {
        return localidadActual;
    }

    public Secuaz getSecuazActual() {
        return secuazActual;
    }
}
