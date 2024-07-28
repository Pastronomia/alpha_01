package com.ejemplo.carmenuy.exception;

/**
 * Excepción personalizada para manejar errores relacionados con las pistas.
 */
public class PistaException extends Exception {
    public PistaException(String mensaje) {
        super(mensaje);
    }

    public PistaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
