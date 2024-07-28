package com.ejemplo.carmenuy.model;

import java.util.List;

/**
 * Clase que representa una partida en el juego Carmen Sandiego Uruguay.
 */
public class Partida {
    private Detective detective;
    private Nodo nodoDetective;
    private Nodo nodoObjetivo;
    private Grafo grafo;
    private List<Secuaz> secuaces;

    public Partida(Detective detective, Nodo nodoDetective, Nodo nodoObjetivo, Grafo grafo) {
        this.detective = detective;
        this.nodoDetective = nodoDetective;
        this.nodoObjetivo = nodoObjetivo;
        this.grafo = grafo;
    }

    public Partida(Detective detective, List<Secuaz> secuaces, Grafo grafo) {
        this.detective = detective;
        this.secuaces = secuaces;
        this.grafo = grafo;
    }

    public Detective getDetective() {
        return detective;
    }

    public Nodo getNodoDetective() {
        return nodoDetective;
    }

    public Nodo getNodoObjetivo() {
        return nodoObjetivo;
    }

    public List<Secuaz> getSecuaces() {
        return secuaces;
    }

    public boolean sonNodosAdyacentes(Nodo nodo1, Nodo nodo2) {
        return grafo.sonNodosAdyacentes(nodo1, nodo2);
    }

    public double calcularDistancia(Nodo nodo1, Nodo nodo2) {
        return grafo.calcularDistancia(nodo1, nodo2);
    }

    public void moverDetective(Nodo nuevoNodo) {
        if (sonNodosAdyacentes(nodoDetective, nuevoNodo)) {
            nodoDetective = nuevoNodo;
            detective.setLocalidad(new Localidad(nuevoNodo.getNombre(), "Descripción", nuevoNodo.getX(), nuevoNodo.getY()));
        } else {
            throw new IllegalArgumentException("El nodo destino no es adyacente al nodo actual del detective.");
        }
    }

    public void capturarSecuaz(Secuaz secuaz) {
        if (secuaz.getLocalidad().equals(nodoDetective.getLocalidad())) {
            secuaz.setCapturado(true);
            detective.incrementarCapturas();
        }
    }
}
