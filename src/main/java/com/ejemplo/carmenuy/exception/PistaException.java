package com.ejemplo.carmenuy.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Excepción lanzada cuando ocurre un error relacionado con las pistas.
 */
public class PistaException extends RuntimeException {
    /**
     * Constructor de la excepción con un mensaje detallado.
     *
     * @param message El mensaje detallado de la excepción.
     */
    public PistaException(String message) {
        super(message);
    }

    /**
     * Constructor de la excepción con un mensaje detallado y una causa.
     *
     * @param message El mensaje detallado de la excepción.
     * @param cause La causa de la excepción.
     */
    public PistaException(String message, Throwable cause) {
        super(message, cause);
    }
}

/**
 * Clase de prueba para la excepción PistaException.
 */
class PistaExceptionTest {
    /**
     * Prueba que verifica que la excepción PistaException se lanza correctamente.
     */
    @Test
    public void testPistaException() {
        Assertions.assertThrows(PistaException.class, () -> {
            throw new PistaException("Error en la pista.");
        });
    }

    /**
     * Prueba que verifica que la excepción PistaException se lanza correctamente con causa.
     */
    @Test
    public void testPistaExceptionWithCause() {
        Assertions.assertThrows(PistaException.class, () -> {
            throw new PistaException("Error en la pista.", new NullPointerException("Causa del error"));
        });
    }
}
