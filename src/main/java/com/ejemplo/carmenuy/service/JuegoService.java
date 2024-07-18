package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.dao.LocalidadDAO;
import com.ejemplo.carmenuy.dao.PartidaDAO;
import com.ejemplo.carmenuy.dao.PistaDAO;
import com.ejemplo.carmenuy.model.Detective;
import com.ejemplo.carmenuy.model.Grafo;
import com.ejemplo.carmenuy.model.Localidad;
import com.ejemplo.carmenuy.model.Nodo;
import com.ejemplo.carmenuy.model.Rango;
import com.ejemplo.carmenuy.model.Secuaz;
import com.ejemplo.carmenuy.tts.TTSManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JuegoService {
    private static final Logger LOGGER = Logger.getLogger(JuegoService.class.getName());
    private static final Random RANDOM = new Random();

    private Detective detective;
    private final Grafo grafo;
    private final PistaDAO pistaDAO;
    private final LocalidadDAO localidadDAO;
    private final PartidaDAO partidaDAO;
    private List<Secuaz> secuaces;
    private Nodo nodoActual;
    private Localidad localidadActual;
    private final NarrativaManager narrativaManager;
    private final TTSManager ttsManager;
    private final AccesibilidadManager accesibilidadManager;
    private final MisionManager misionManager;
    private Secuaz carmenSandiego;

    public JuegoService(Connection connection) throws SQLException {
        this.grafo = new Grafo();
        this.localidadDAO = new LocalidadDAO(connection);
        this.pistaDAO = new PistaDAO(connection);
        this.partidaDAO = new PartidaDAO(connection, this.grafo);
        this.narrativaManager = new NarrativaManager(this.grafo);
        this.ttsManager = new TTSManager();
        this.accesibilidadManager = new AccesibilidadManager(ttsManager);
        this.misionManager = new MisionManager(connection);
        inicializarJuego();
    }

    private void inicializarJuego() throws SQLException {
        accesibilidadManager.preguntarPreferenciaAudio();
        crearTablas();
        if (localidadDAO.obtenerTodasLasLocalidades().isEmpty()) {
            insertarDatosIniciales();
        }
        inicializarPersonajes();
    }

    private void crearTablas() throws SQLException {
        pistaDAO.crearTabla();
        localidadDAO.crearTabla();
        partidaDAO.crearTabla();
    }

    private void insertarDatosIniciales() throws SQLException {
        localidadDAO.insertarLocalidadesIniciales();
        pistaDAO.insertarPistasIniciales();
    }

    private void inicializarPersonajes() {
        this.detective = new Detective("Detective", "Apellido", Rango.DETECTIVE_JUNIOR, new Localidad("Montevideo", "Capital de Uruguay", -34.9011, -56.1645));
        this.secuaces = new ArrayList<>();
        inicializarSecuaces();
        inicializarLocalidadAleatoria();
    }

    private void inicializarSecuaces() {
        secuaces.add(new Secuaz("Betosecreto", "Escalador, experto en artes ninja y espionaje", 5));
        secuaces.add(new Secuaz("Ellabella", "Experta en estafas", 4));
        secuaces.add(new Secuaz("Mindy Ana Son", "Arqueóloga experta en gemas", 3));
        secuaces.add(new Secuaz("Moonabomber", "Experto en explosivos", 5));
        for (Secuaz secuaz : secuaces) {
            secuaz.setNodo(grafo.getNodoAleatorio());
        }
        carmenSandiego = new Secuaz("Carmen Sandiego", "Líder criminal", 10);
        carmenSandiego.setNodo(grafo.getNodoAleatorio());
    }

    private void inicializarLocalidadAleatoria() {
        try {
            List<Localidad> todasLasLocalidades = localidadDAO.obtenerTodasLasLocalidades();
            Localidad localidadInicial = todasLasLocalidades.get(RANDOM.nextInt(todasLasLocalidades.size()));
            this.nodoActual = grafo.getNodo(localidadInicial.getNombre());
            this.localidadActual = localidadInicial;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al inicializar la localidad aleatoria", e);
        }
    }

    public String obtenerPista() {
        return "Esta es una pista de ejemplo.";
    }

    public void responderNo() {
        // Implementación para responder No a una pregunta
    }

    public void responderSi() {
        // Implementación para responder Sí a una pregunta
    }

    public String obtenerNarrativaLocalidad() {
        return narrativaManager.generarNarrativa(localidadActual);
    }
}
