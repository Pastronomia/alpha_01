package com.ejemplo.carmenuy.model;

public class Secuaz {
    private String nombre;
    private String habilidad;
    private int peligrosidad;
    private Localidad localidad;
    private Nodo nodo;  // Nodo asociado para la navegación en el grafo
    private boolean capturado;

    public Secuaz(String nombre, String habilidad, int peligrosidad) {
        this.nombre = nombre;
        this.habilidad = habilidad;
        this.peligrosidad = peligrosidad;
        this.localidad = null;
        this.nodo = null;
        this.capturado = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public int getPeligrosidad() {
        return peligrosidad;
    }

    public void setPeligrosidad(int peligrosidad) {
        this.peligrosidad = peligrosidad;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public Nodo getNodo() {
        return nodo;
    }

    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }

    public boolean isCapturado() {
        return capturado;
    }

    public void setCapturado(boolean capturado) {
        this.capturado = capturado;
    }
}
