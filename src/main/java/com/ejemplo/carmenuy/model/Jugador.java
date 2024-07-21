package com.ejemplo.carmenuy.model;

import java.util.Objects;

public class Jugador {
    private String nombre;
    private String contrasena;
    private Detective detective;

    public Jugador(String nombre, String contrasena, Localidad localidadInicial) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.detective = new Detective(nombre, "", Rango.DETECTIVE_JUNIOR, localidadInicial);
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Detective getDetective() {
        return detective;
    }

    public void setDetective(Detective detective) {
        this.detective = detective;
    }

    @Override
    public String toString() {
        return String.format("Jugador{nombre='%s', detective=%s}", nombre, detective);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return Objects.equals(nombre, jugador.nombre) &&
                Objects.equals(contrasena, jugador.contrasena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, contrasena);
    }
}
