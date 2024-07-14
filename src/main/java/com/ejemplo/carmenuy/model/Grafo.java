package com.ejemplo.carmenuy.model;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
    private List<Nodo> nodos;

    public Grafo() {
        nodos = new ArrayList<>();
    }

    public void addNodo(Nodo nodo) {
        if (nodo == null) throw new IllegalArgumentException("El nodo no puede ser nulo");
        nodos.add(nodo);
    }

    public Nodo getNodo(String nombre) {
        for (Nodo nodo : nodos) {
            if (nodo.getNombre().equals(nombre)) {
                return nodo;
            }
        }
        return null;
    }

    public Nodo getNodoAleatorio() {
        return nodos.isEmpty() ? null : nodos.get((int) (Math.random() * nodos.size()));
    }
}
