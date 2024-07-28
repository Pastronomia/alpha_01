package com.ejemplo.carmenuy.model;

import java.util.Objects;

/**
 * Representa a un Detective en el juego Carmen Sandiego Uruguay.
 * Esta clase contiene información sobre el detective, sus capacidades y su ubicación actual.
 */
public class Detective {
    private final String nombre;
    private final String apellido;
    private Rango rango;
    private int movimientos;
    private int capturas; // Número de capturas realizadas
    private Localidad localidad; // Localidad actual del detective

    /**
     * Constructor para crear una instancia de Detective.
     *
     * @param nombre El nombre del detective.
     * @param apellido El apellido del detective.
     * @param rango El rango inicial del detective.
     * @param localidad La localidad inicial del detective.
     * @throws IllegalArgumentException si algún parámetro es nulo o vacío.
     */
    public Detective(String nombre, String apellido, Rango rango, Localidad localidad) {
        this.nombre = validarString(nombre, "El nombre no puede ser nulo o vacío");
        this.apellido = validarString(apellido, "El apellido no puede ser nulo o vacío");
        this.rango = Objects.requireNonNull(rango, "El rango no puede ser nulo");
        this.localidad = Objects.requireNonNull(localidad, "La localidad no puede ser nula");
        this.movimientos = calcularMovimientosIniciales(rango);
        this.capturas = 0;
    }

    private String validarString(String valor, String mensajeError) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensajeError);
        }
        return valor.trim();
    }

    public void incrementarCapturas() {
        this.capturas++;
        verificarAscenso();
    }

    private void verificarAscenso() {
        if (this.capturas >= 3 && this.rango == Rango.DETECTIVE_JUNIOR) {
            setRango(Rango.DETECTIVE_APRENDIZ);
        } else if (this.capturas >= 6 && this.rango == Rango.DETECTIVE_APRENDIZ) {
            setRango(Rango.DETECTIVE_EFICIENTE);
        } else if (this.capturas >= 10 && this.rango == Rango.DETECTIVE_EFICIENTE) {
            setRango(Rango.DETECTIVE_JEFE);
        } else if (this.capturas >= 15 && this.rango == Rango.DETECTIVE_JEFE) {
            setRango(Rango.INSPECTOR);
        }
    }

    private static int calcularMovimientosIniciales(Rango rango) {
        return switch (rango) {
            case DETECTIVE_JUNIOR -> 5;
            case DETECTIVE_APRENDIZ -> 4;
            case DETECTIVE_EFICIENTE -> 3;
            case DETECTIVE_JEFE -> 2;
            case INSPECTOR -> 1;
            default -> throw new IllegalArgumentException("Rango no reconocido");
        };
    }

    public void setRango(Rango rango) {
        this.rango = rango;
        this.movimientos = calcularMovimientosIniciales(rango);
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    // Getters para acceder a los atributos de la clase
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Rango getRango() {
        return rango;
    }

    public int getMovimientos() {
        return movimientos;
    }

    public int getCapturas() {
        return capturas;
    }

    @Override
    public String toString() {
        return String.format("Detective{nombre='%s', apellido='%s', rango=%s, movimientos=%d, capturas=%d, localidad=%s}",
                nombre, apellido, rango, movimientos, capturas, localidad.getNombre());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Detective detective = (Detective) o;
        return movimientos == detective.movimientos &&
                capturas == detective.capturas &&
                nombre.equals(detective.nombre) &&
                apellido.equals(detective.apellido) &&
                rango == detective.rango &&
                Objects.equals(localidad, detective.localidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, rango, movimientos, capturas, localidad);
    }
}
