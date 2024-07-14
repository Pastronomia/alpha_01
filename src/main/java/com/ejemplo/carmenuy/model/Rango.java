package com.ejemplo.carmenuy.model;

public enum Rango {
    DETECTIVE_JUNIOR("Detective Junior"),
    DETECTIVE_APRENDIZ("Detective Aprendiz"),
    DETECTIVE_SENIOR("Detective Senior"),  // Cambiado de EFICIENTE a SENIOR
    DETECTIVE_JEFE("Detective Jefe"),
    INSPECTOR("Inspector");

    private final String descripcion;

    Rango(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return this.descripcion;
    }
}
