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

    public boolean sonNodosAdyacentes(Nodo nodo1, Nodo nodo2) {
        return nodo1.getConexiones().contains(nodo2);
    }

    public double calcularDistancia(Nodo nodo1, Nodo nodo2) {
        // Implementación simplificada para calcular la distancia
        double dx = nodo1.getX() - nodo2.getX();
        double dy = nodo1.getY() - nodo2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
