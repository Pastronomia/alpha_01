package com.ejemplo.carmenuy.model;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
    private String nombre;
    private List<Nodo> conexiones;

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.conexiones = new ArrayList<>();
    }

    public void addConexion(Nodo nodo) {
        conexiones.add(nodo);
    }

    public List<Nodo> getConexiones() {
        return conexiones;
    }

    public String getNombre() {
        return nombre;
    }
}
