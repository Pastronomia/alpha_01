package com.ejemplo.carmenuy.model;

import java.util.Objects;

/**
 * Representa a un secuaz en el juego Carmen Sandiego Uruguay.
 */
public class Secuaz {
    private String nombre;
    private String habilidad;
    private int peligrosidad;
    private Localidad localidad;  // Agregado para la localidad del secuaz
    private boolean capturado; // Agregado para manejar la captura del secuaz
    private Nodo nodo;  // Usando la clase Nodo definida anteriormente

    /**
     * Constructor para crear un secuaz.
     *
     * @param nombre El nombre del secuaz.
     * @param habilidad La habilidad o especialidad del secuaz.
     * @param peligrosidad Nivel de peligrosidad del secuaz, de 1 a 10.
     */
    public Secuaz(String nombre, String habilidad, int peligrosidad) {
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        this.habilidad = Objects.requireNonNull(habilidad, "La habilidad no puede ser nula");
        this.peligrosidad = peligrosidad;
        this.capturado = false; // Inicialmente no está capturado
        this.nodo = null; // Inicializa el nodo como null
        validarPeligrosidad();
    }

    /**
     * Valida que la peligrosidad esté dentro del rango permitido.
     */
    private void validarPeligrosidad() {
        if (peligrosidad < 1 || peligrosidad > 10) {
            throw new IllegalArgumentException("La peligrosidad debe estar entre 1 y 10");
        }
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        this.nombre = nombre.trim();
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        if (habilidad == null || habilidad.trim().isEmpty()) {
            throw new IllegalArgumentException("La habilidad no puede ser nula o vacía");
        }
        this.habilidad = habilidad.trim();
    }

    public int getPeligrosidad() {
        return peligrosidad;
    }

    public void setPeligrosidad(int peligrosidad) {
        if (peligrosidad < 1 || peligrosidad > 10) {
            throw new IllegalArgumentException("La peligrosidad debe estar entre 1 y 10");
        }
        this.peligrosidad = peligrosidad;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public boolean isCapturado() {
        return capturado;
    }

    public void setCapturado(boolean capturado) {
        this.capturado = capturado;
    }

    public Nodo getNodo() {
        return nodo;
    }

    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }

    @Override
    public String toString() {
        return String.format("Secuaz{nombre='%s', habilidad='%s', peligrosidad=%d, localidad=%s, capturado=%b, nodo=%s}",
                nombre, habilidad, peligrosidad, localidad != null ? localidad.getNombre() : "Sin localidad", capturado, nodo != null ? nodo.getNombre() : "Sin nodo");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Secuaz secuaz = (Secuaz) o;
        return peligrosidad == secuaz.peligrosidad &&
                capturado == secuaz.capturado &&
                Objects.equals(nombre, secuaz.nombre) &&
                Objects.equals(habilidad, secuaz.habilidad) &&
                Objects.equals(localidad, secuaz.localidad) &&
                Objects.equals(nodo, secuaz.nodo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, habilidad, peligrosidad, localidad, capturado, nodo);
    }
}
