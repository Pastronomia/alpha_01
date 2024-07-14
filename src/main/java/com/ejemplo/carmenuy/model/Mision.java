package com.ejemplo.carmenuy.model;

import java.util.Objects;

public class Mision {
    private int id;
    private String titulo;
    private String descripcion;
    private String objetivo;
    private boolean completada;

    // Constructor existente
    public Mision(int id, String titulo, String descripcion, String objetivo) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.completada = false; // Valor predeterminado
    }

    // Nuevo constructor que incluye el parámetro completada
    public Mision(int id, String titulo, String descripcion, String objetivo, boolean completada) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.completada = completada;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    @Override
    public String toString() {
        return "Mision{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", objetivo='" + objetivo + '\'' +
                ", completada=" + completada +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mision mision = (Mision) o;
        return id == mision.id &&
                completada == mision.completada &&
                Objects.equals(titulo, mision.titulo) &&
                Objects.equals(descripcion, mision.descripcion) &&
                Objects.equals(objetivo, mision.objetivo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descripcion, objetivo, completada);
    }
}
