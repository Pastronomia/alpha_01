package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.model.*;
import com.ejemplo.carmenuy.dao.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MisionManager {
    private final MisionDAO misionDAO;
    private final LocalidadDAO localidadDAO;
    private Mision misionActual;
    private List<Secuaz> secuaces;
    private Secuaz carmenSandiego;
    private Detective detective;
    private List<Localidad> localidades;
    private boolean juegoTerminado;

    public MisionManager(Connection connection) {
        this.misionDAO = new MisionDAO(connection);
        this.localidadDAO = new LocalidadDAO(connection);
        this.localidades = cargarLocalidades();
        this.secuaces = inicializarSecuaces();
        this.carmenSandiego = inicializarCarmenSandiego();
        this.detective = inicializarDetective();
    }

    private List<Localidad> cargarLocalidades() {
        try {
            return localidadDAO.obtenerTodasLasLocalidades();
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar localidades", e);
        }
    }

    private Detective inicializarDetective() {
        if (localidades.isEmpty()) {
            throw new IllegalStateException("No hay localidades disponibles para inicializar el detective");
        }
        Random random = new Random();
        Localidad localidadInicial = localidades.get(random.nextInt(localidades.size()));
        return new Detective("Detective", "Apellido", Rango.DETECTIVE_JUNIOR, localidadInicial);
    }

    private List<Secuaz> inicializarSecuaces() {
        List<Secuaz> secuaces = new ArrayList<>();
        secuaces.add(new Secuaz("Betosecreto", "Escalador, experto en artes ninja y espionaje", 5));
        secuaces.add(new Secuaz("Ellabella", "Experta en estafas", 4));
        secuaces.add(new Secuaz("Mindy Ana Son", "Arqueóloga experta en gemas", 3));
        secuaces.add(new Secuaz("Moonabomber", "Experto en explosivos", 5));
        for (Secuaz secuaz : secuaces) {
            secuaz.setLocalidad(localidades.get(new Random().nextInt(localidades.size())));
        }
        return secuaces;
    }

    private Secuaz inicializarCarmenSandiego() {
        Secuaz carmenSandiego = new Secuaz("Carmen Sandiego", "Líder criminal", 10);
        carmenSandiego.setLocalidad(localidades.get(new Random().nextInt(localidades.size())));
        return carmenSandiego;
    }

    public void jugar() {
        while (!juegoTerminado) {
            // Implementar la lógica del juego aquí
            // Ejemplo: verificar si se captura a Carmen Sandiego o se complete una misión
            System.out.println("Jugando...");
        }
    }

    // Otros métodos necesarios para el funcionamiento completo del MisionManager
    public void iniciarNuevaMision() throws SQLException {
        List<Mision> misiones = misionDAO.obtenerTodasLasMisiones();
        if (!misiones.isEmpty()) {
            Random random = new Random();
            misionActual = misiones.get(random.nextInt(misiones.size()));
        } else {
            throw new IllegalStateException("No hay misiones disponibles");
        }
    }

    public Mision getMisionActual() {
        return misionActual;
    }

    public boolean verificarObjetivoMision(Secuaz secuazCapturado) {
        return misionActual != null && misionActual.getObjetivo().equals(secuazCapturado.getNombre());
    }

    public void completarMision() {
        if (misionActual != null) {
            misionActual.setCompletada(true);
            misionDAO.actualizarMision(misionActual);
        }
    }

    public String obtenerDescripcionMision() {
        return misionActual != null ? misionActual.getDescripcion() : "No hay misión activa";
    }
}
