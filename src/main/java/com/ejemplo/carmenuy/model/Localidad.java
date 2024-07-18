package com.ejemplo.carmenuy.model;

/**
 * Clase que representa una localidad en el juego Carmen Sandiego Uruguay.
 */
public class Localidad {
    private int id;
    private String nombre;
    private String descripcion;
    private double latitud;
    private double longitud;

    /**
     * Constructor completo de la clase Localidad.
     *
     * @param id El identificador único de la localidad.
     * @param nombre El nombre de la localidad.
     * @param descripcion La descripción de la localidad.
     * @param latitud La latitud de la localidad.
     * @param longitud La longitud de la localidad.
     */
    public Localidad(int id, String nombre, String descripcion, double latitud, double longitud) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    /**
     * Constructor para crear una localidad sin un ID especificado, útil para cuando se crea una nueva localidad que aún no ha sido guardada en la base de datos.
     *
     * @param nombre El nombre de la localidad.
     * @param descripcion La descripción de la localidad.
     * @param latitud La latitud de la localidad.
     * @param longitud La longitud de la localidad.
     */
    public Localidad(String nombre, String descripcion, double latitud, double longitud) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
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
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
