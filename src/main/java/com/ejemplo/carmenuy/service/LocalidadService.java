package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.dao.LocalidadDAO;
import com.ejemplo.carmenuy.model.Localidad;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocalidadService {
    private static final Logger LOGGER = Logger.getLogger(LocalidadService.class.getName());
    private final LocalidadDAO localidadDAO;

    public LocalidadService(LocalidadDAO localidadDAO) {
        this.localidadDAO = localidadDAO;
    }

    public void agregarLocalidad(Localidad localidad) {
        try {
            localidadDAO.insertarLocalidad(localidad);
            LOGGER.info("Localidad agregada con éxito: " + localidad.getNombre());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al agregar localidad: " + e.getMessage(), e);
            throw new RuntimeException("Error al agregar localidad", e);
        }
    }

    public List<Localidad> obtenerLocalidades() {
        try {
            return localidadDAO.obtenerTodasLasLocalidades();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener localidades: " + e.getMessage(), e);
            throw new RuntimeException("Error al obtener localidades", e);
        }
    }

    public void actualizarLocalidad(Localidad localidad) {
        try {
            localidadDAO.actualizarLocalidad(localidad);
            LOGGER.info("Localidad actualizada con éxito: " + localidad.getNombre());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar localidad: " + e.getMessage(), e);
            throw new RuntimeException("Error al actualizar localidad", e);
        }
    }

    public void eliminarLocalidad(int id) {
        try {
            localidadDAO.eliminarLocalidad(id);
            LOGGER.info("Localidad eliminada con éxito: ID " + id);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar localidad: " + e.getMessage(), e);
            throw new RuntimeException("Error al eliminar localidad", e);
        }
    }
}
