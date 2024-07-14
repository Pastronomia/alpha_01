package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.model.Grafo;
import com.ejemplo.carmenuy.model.Localidad;
import com.ejemplo.carmenuy.dao.LocalidadDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GrafoService {
    private static final Logger logger = LoggerFactory.getLogger(GrafoService.class);
    private static final int MIN_CONEXIONES = 4;  // Asegura un mínimo de 4 conexiones por nodo
    private static final int MAX_CONEXIONES = 6;  // Permite hasta 6 conexiones por flexibilidad
    private static final int MAX_DISTANCIA = 10;  // Distancia máxima de 10 km entre nodos

    private final Grafo grafo;
    private final LocalidadDAO localidadDAO;
    private final Random random;

    public GrafoService(LocalidadDAO localidadDAO) {
        this.localidadDAO = localidadDAO;
        this.grafo = new Grafo();
        this.random = new Random();
        inicializarGrafo();
    }

    private void inicializarGrafo() {
        try {
            List<Localidad> localidades = localidadDAO.obtenerTodasLasLocalidades();
            localidades.forEach(localidad -> grafo.agregarNodo(localidad.getNombre()));
            conectarNodos();
            logger.info("Grafo inicializado con éxito. Nodos: {}", grafo.obtenerNodos().size());
        } catch (SQLException e) {
            logger.error("Error al inicializar el grafo", e);
            throw new RuntimeException("Error al inicializar el grafo", e);
        }
    }

    private void conectarNodos() {
        List<String> nodos = new ArrayList<>(grafo.obtenerNodos());
        nodos.forEach(nodo -> {
            int conexiones = random.nextInt(MAX_CONEXIONES - MIN_CONEXIONES + 1) + MIN_CONEXIONES;
            for (int i = 0; i < conexiones; i++) {
                String nodoDestino = obtenerNodoDestinoAleatorio(nodo, nodos);
                if (nodoDestino != null && !grafo.estanConectados(nodo, nodoDestino)) {
                    int distancia = random.nextInt(MAX_DISTANCIA) + 1;
                    grafo.conectarNodos(nodo, nodoDestino, distancia);
                    logger.debug("Conectado {} con {} a una distancia de {}", nodo, nodoDestino, distancia);
                }
            }
        });
    }

    private String obtenerNodoDestinoAleatorio(String nodoOrigen, List<String> nodos) {
        List<String> nodosDisponibles = nodos.stream()
                .filter(nodo -> !nodo.equals(nodoOrigen) && !grafo.estanConectados(nodoOrigen, nodo))
                .collect(Collectors.toList());

        if (nodosDisponibles.size() < MIN_CONEXIONES) {
            throw new RuntimeException("No hay suficientes nodos disponibles para cumplir con el requisito mínimo de conexiones.");
        }

        return nodosDisponibles.get(random.nextInt(nodosDisponibles.size()));
    }

    public List<String> obtenerConexiones(String localidad) {
        return grafo.obtenerConexiones(localidad);
    }

    public String obtenerLocalidadAleatoria() {
        List<String> nodos = new ArrayList<>(grafo.obtenerNodos());
        return nodos.get(random.nextInt(nodos.size()));
    }

    public int obtenerDistancia(String localidad1, String localidad2) {
        return grafo.obtenerDistancia(localidad1, localidad2);
    }

    public List<String> obtenerRutaAleatoria(String inicio, int longitud) {
        return grafo.obtenerRutaAleatoria(inicio, longitud);
    }

    public void generarConexiones() {
        conectarNodos();
    }

    public void distribuirPistas() {
        try {
            List<Localidad> localidades = localidadDAO.obtenerTodasLasLocalidades();
            for (Localidad localidad : localidades) {
                int numeroDePistasFalsas = 3;
                generarPista(localidad, true); // Generar una pista correcta
                for (int i = 0; i < numeroDePistasFalsas; i++) {
                    generarPista(localidad, false); // Generar pistas falsas
                }
            }
            logger.info("Pistas distribuidas correctamente en todas las localidades.");
        } catch (SQLException e) {
            logger.error("Error al distribuir pistas", e);
            throw new RuntimeException("Error al distribuir pistas", e);
        }
    }

    private void generarPista(Localidad localidad, boolean esCorrecta) throws SQLException {
        String descripcionPista = esCorrecta ? "Pista correcta en " + localidad.getNombre() :
                "Pista falsa en " + localidad.getNombre();
        int esCorrectaInt = esCorrecta ? 1 : 0;
        localidadDAO.insertarPista(localidad.getId(), descripcionPista, esCorrectaInt);
    }
}
