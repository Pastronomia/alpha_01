package com.ejemplo.carmenuy.model;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa un nodo en el grafo, cada nodo es una localidad.
 */
public class Nodo {
    private final String nombre;
    private final List<Nodo> conexiones;
    private final Map<Nodo, Integer> distancias;
    private final double x; // Coordenadas para cálculo de distancia
    private final double y;
    private Localidad localidad;

    public Nodo(String nombre, double x, double y) {
        this.nombre = nombre;
        this.conexiones = new ArrayList<>();
        this.distancias = new HashMap<>();
        this.x = x;
        this.y = y;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Nodo> getConexiones() {
        return new ArrayList<>(conexiones);
    }

    public void addConexion(Nodo nodo, int distancia) {
        if (!conexiones.contains(nodo)) {
            conexiones.add(nodo);
            distancias.put(nodo, distancia);
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public int getDistanciaA(Nodo otroNodo) {
        return distancias.getOrDefault(otroNodo, -1);
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "nombre='" + nombre + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", localidad=" + (localidad != null ? localidad.getNombre() : "null") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nodo nodo = (Nodo) o;
        return nombre.equals(nodo.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }
}
