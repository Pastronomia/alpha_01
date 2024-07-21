package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.model.Grafo;
import com.ejemplo.carmenuy.model.Localidad;
import com.ejemplo.carmenuy.model.Nodo;

public class GrafoService {
    private final Grafo grafo;

    public GrafoService(Grafo grafo) {
        this.grafo = grafo;
    }

    public void agregarLocalidad(Localidad localidad) {
        Nodo nodo = convertirLocalidadANodo(localidad);
        grafo.agregarNodo(nodo);
    }

    public void conectarLocalidades(Localidad loc1, Localidad loc2, int distancia) {
        Nodo nodo1 = convertirLocalidadANodo(loc1);
        Nodo nodo2 = convertirLocalidadANodo(loc2);
        if (nodo1 != null && nodo2 != null) {
            grafo.conectarNodos(nodo1, nodo2, distancia);
        }
    }

    private Nodo convertirLocalidadANodo(Localidad localidad) {
        return new Nodo(localidad.getNombre(), localidad.getLatitud(), localidad.getLongitud());
    }
}
