package com.ejemplo.carmenuy.model;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
    private String nombre;
    private double x, y; // Coordenadas para calcular distancia
    private List<Nodo> conexiones;

    public Nodo(String nombre, double x, double y) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
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

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
