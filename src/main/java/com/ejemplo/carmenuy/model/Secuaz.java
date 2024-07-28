package com.ejemplo.carmenuy.model;

import java.util.Objects;

/**
 * Representa a un secuaz en el juego Carmen Sandiego Uruguay.
 */
public class Secuaz {
    private int id;  // Agregado para el ID del secuaz
    private String nombre;
    private String habilidad;
    private int peligrosidad;
    private Localidad localidad;  // Agregado para la localidad del secuaz
    private boolean capturado; // Agregado para manejar la captura del secuaz

    /**
     * Constructor para crear un secuaz.
     *
     * @param id El identificador único del secuaz.
     * @param nombre El nombre del secuaz.
     * @param habilidad La habilidad o especialidad del secuaz.
     * @param peligrosidad Nivel de peligrosidad del secuaz, de 1 a 10.
     * @param localidad La localidad del secuaz.
     * @param capturado Indica si el secuaz ha sido capturado.
     */
    public Secuaz(int id, String nombre, String habilidad, int peligrosidad, Localidad localidad, boolean capturado) {
        this.id = id;
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        this.habilidad = Objects.requireNonNull(habilidad, "La habilidad no puede ser nula");
        this.peligrosidad = peligrosidad;
        this.localidad = localidad;
        this.capturado = capturado;
        validarPeligrosidad();
    }

    /**
     * Constructor para crear un secuaz sin un ID especificado, útil para cuando se crea un nuevo secuaz que aún no ha sido guardado en la base de datos.
     *
     * @param nombre El nombre del secuaz.
     * @param habilidad La habilidad o especialidad del secuaz.
     * @param peligrosidad Nivel de peligrosidad del secuaz, de 1 a 10.
     * @param localidad La localidad del secuaz.
     */
    public Secuaz(String nombre, String habilidad, int peligrosidad, Localidad localidad) {
        this(0, nombre, habilidad, peligrosidad, localidad, false);
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getDescripcion() {
        return "Habilidad: " + habilidad + ", Peligrosidad: " + peligrosidad;
    }

    @Override
    public String toString() {
        return String.format("Secuaz{id=%d, nombre='%s', habilidad='%s', peligrosidad=%d, localidad=%s, capturado=%b}",
                id, nombre, habilidad, peligrosidad, localidad != null ? localidad.getNombre() : "Sin localidad", capturado);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Secuaz secuaz = (Secuaz) o;
        return id == secuaz.id &&
                peligrosidad == secuaz.peligrosidad &&
                capturado == secuaz.capturado &&
                Objects.equals(nombre, secuaz.nombre) &&
                Objects.equals(habilidad, secuaz.habilidad) &&
                Objects.equals(localidad, secuaz.localidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, habilidad, peligrosidad, localidad, capturado);
    }
}
