package com.ejemplo.carmenuy.model;

public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String contrasena;
    private String rango;
    private int capturas;
    private String progreso;

    public Usuario(int id, String nombre, String apellido, String contrasena, String rango, int capturas, String progreso) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.rango = rango;
        this.capturas = capturas;
        this.progreso = progreso;
    }

    public Usuario(String nombre, String apellido, String contrasena, String rango, int capturas, String progreso) {
        this(0, nombre, apellido, contrasena, rango, capturas, progreso);
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public int getCapturas() {
        return capturas;
    }

    public void setCapturas(int capturas) {
        this.capturas = capturas;
    }

    public String getProgreso() {
        return progreso;
    }

    public void setProgreso(String progreso) {
        this.progreso = progreso;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", rango='" + rango + '\'' +
                ", capturas=" + capturas +
                ", progreso='" + progreso + '\'' +
                '}';
    }
}
