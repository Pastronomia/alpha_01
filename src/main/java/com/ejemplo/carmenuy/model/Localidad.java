package com.ejemplo.carmenuy.model;

public class Localidad {
    private int id;
    private String nombre;
    private String descripcion;
    private double latitud;
    private double longitud;

    // Constructor sin ID para facilitar la creación de localidades sin necesidad de conocer el ID de antemano
    public Localidad(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.latitud = 0;  // Valor predeterminado si no se especifica
        this.longitud = 0; // Valor predeterminado si no se especifica
    }

    // Constructor completo
    public Localidad(int id, String nombre, String descripcion, double latitud, double longitud) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    // Getters y setters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getLatitud() { return latitud; }
    public double getLongitud() { return longitud; }
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setLatitud(double latitud) { this.latitud = latitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }
}
