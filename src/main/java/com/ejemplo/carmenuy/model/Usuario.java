package com.ejemplo.carmenuy.model;

/**
 * Clase modelo para un usuario, usando la característica de registros de Java.
 * Los registros proveen una manera sucinta de crear clases de datos inmutables.
 */
public record Usuario(int id, String nombre, String apellido, String contrasena, String rango, int capturas, String progreso) {
}
