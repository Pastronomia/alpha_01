package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.dao.PistaDAO;
import com.ejemplo.carmenuy.model.Pista;
import com.ejemplo.carmenuy.exception.PistaException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PistaService {
    private PistaDAO pistaDAO;

    public PistaService(Connection connection) {
        this.pistaDAO = new PistaDAO(connection);
    }

    public void setPistaDAO(PistaDAO pistaDAO) {
        this.pistaDAO = pistaDAO;
    }

    public void insertarPista(Pista pista) throws PistaException {
        try {
            pistaDAO.insertarPista(pista);
        } catch (SQLException e) {
            throw new PistaException("Error al insertar pista: " + e.getMessage(), e);
        }
    }

    public Pista obtenerPistaPorId(int id) throws PistaException {
        try {
            return pistaDAO.obtenerPistaPorId(id);
        } catch (SQLException e) {
            throw new PistaException("Error al obtener pista por ID: " + e.getMessage(), e);
        }
    }

    public void crearTablaPistas() throws PistaException {
        try {
            pistaDAO.crearTabla();
        } catch (SQLException e) {
            throw new PistaException("Error al crear tabla de pistas: " + e.getMessage(), e);
        }
    }

    public List<Pista> obtenerTodasLasPistas() throws PistaException {
        try {
            return pistaDAO.obtenerTodasLasPistas();
        } catch (SQLException e) {
            throw new PistaException("Error al obtener todas las pistas: " + e.getMessage(), e);
        }
    }

    public void actualizarPista(Pista pista) throws PistaException {
        try {
            pistaDAO.actualizarPista(pista);
        } catch (SQLException e) {
            throw new PistaException("Error al actualizar pista: " + e.getMessage(), e);
        }
    }

    public void eliminarPista(int id) throws PistaException {
        try {
            pistaDAO.eliminarPista(id);
        } catch (SQLException e) {
            throw new PistaException("Error al eliminar pista: " + e.getMessage(), e);
        }
    }

    public void insertarPistasIniciales() throws PistaException {
        try {
            pistaDAO.insertarPistasIniciales();
        } catch (SQLException e) {
            throw new PistaException("Error al insertar pistas iniciales: " + e.getMessage(), e);
        }
    }
}
