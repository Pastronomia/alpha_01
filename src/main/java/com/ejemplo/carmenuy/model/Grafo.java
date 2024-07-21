package com.ejemplo.carmenuy.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase que representa un grafo donde cada nodo es una localidad.
 */
public class Grafo {
    private final List<Nodo> nodos;
    private final Random random;

    public Grafo() {
        this.nodos = new ArrayList<>();
        this.random = new Random();
    }

    public void agregarNodo(Nodo nodo) {
        nodos.add(nodo);
    }

    public List<Nodo> obtenerNodos() {
        return new ArrayList<>(nodos);
    }

    public boolean estanConectados(Nodo nodo1, Nodo nodo2) {
        return nodo1.getConexiones().contains(nodo2);
    }

    public void conectarNodos(Nodo nodo1, Nodo nodo2, int distancia) {
        nodo1.addConexion(nodo2, distancia);
        nodo2.addConexion(nodo1, distancia);
    }

    public List<Nodo> obtenerConexiones(Nodo nodo) {
        return new ArrayList<>(nodo.getConexiones());
    }

    public double obtenerDistancia(Nodo nodo1, Nodo nodo2) {
        return nodo1.getDistanciaA(nodo2);
    }

    public List<Nodo> obtenerRutaAleatoria(Nodo inicio, int pasos) {
        List<Nodo> ruta = new ArrayList<>();
        Nodo nodoActual = inicio;
        ruta.add(nodoActual);

        for (int i = 0; i < pasos; i++) {
            List<Nodo> conexiones = nodoActual.getConexiones();
            if (conexiones.isEmpty()) {
                break;
            }
            nodoActual = conexiones.get(random.nextInt(conexiones.size()));
            ruta.add(nodoActual);
        }

        return ruta;
    }

    public Nodo getNodoAleatorio() {
        if (nodos.isEmpty()) {
            throw new IllegalStateException("El grafo no tiene nodos");
        }
        return nodos.get(random.nextInt(nodos.size()));
    }

    public Nodo obtenerNodo(String nombre) {
        for (Nodo nodo : nodos) {
            if (nodo.getNombre().equals(nombre)) {
                return nodo;
            }
        }
        return null;
    }

    public boolean sonNodosAdyacentes(Nodo nodo1, Nodo nodo2) {
        return nodo1.getConexiones().contains(nodo2);
    }

    public double calcularDistancia(Nodo nodo1, Nodo nodo2) {
        return Math.sqrt(Math.pow(nodo1.getX() - nodo2.getX(), 2) + Math.pow(nodo1.getY() - nodo2.getY(), 2));
    }
}
