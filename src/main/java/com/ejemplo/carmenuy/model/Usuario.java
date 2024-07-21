package com.ejemplo.carmenuy.model;

public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String contrasena;

    // Constructor con tres argumentos String
    public Usuario(String nombre, String apellido, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
