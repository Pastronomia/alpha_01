// JuegoService.java
package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.dao.PistaDAO;
import com.ejemplo.carmenuy.model.Pista;
import com.ejemplo.carmenuy.model.Localidad;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JuegoService {
    private static final Logger LOGGER = Logger.getLogger(JuegoService.class.getName());
    private final PistaDAO pistaDAO;
    private Localidad localidadActual;

    public JuegoService(PistaDAO pistaDAO) {
        this.pistaDAO = pistaDAO;
    }

    public List<Pista> obtenerTresPistasAlAzar(Localidad localidad) {
        try {
            List<Pista> pistas = pistaDAO.obtenerPistasPorLocalidad(localidad.getNombre());
            Collections.shuffle(pistas);
            return pistas.subList(0, Math.min(3, pistas.size()));
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener pistas", e);
        }
        return Collections.emptyList();
    }

    public void manejarSeleccionPista(Pista pistaSeleccionada) {
        if (pistaSeleccionada.esCorrecta()) {
            hablar("¡Correcto! La pista es correcta.");
            // Lógica para avanzar en el juego
        } else {
            hablar("Lo siento, la pista es incorrecta.");
            // Lógica para manejar una pista incorrecta
        }
    }

    private void hablar(String mensaje) {
        // Implementación para hablar el mensaje
    }

    public Localidad obtenerLocalidadActual() {
        return localidadActual;
    }

    public void setLocalidadActual(Localidad localidad) {
        this.localidadActual = localidad;
    }
}
