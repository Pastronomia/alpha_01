package com.ejemplo.carmenuy.exception;

/**
 * Excepción lanzada cuando una localidad no se encuentra en la base de datos.
 */
public class LocalidadNotFoundException extends RuntimeException {
    public LocalidadNotFoundException(String message) {
        super(message);
    }
}
