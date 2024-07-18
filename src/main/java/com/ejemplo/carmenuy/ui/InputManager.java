package com.ejemplo.carmenuy.ui;

import java.util.Scanner;

/**
 * Clase que gestiona la entrada del usuario para el juego Carmen Sandiego Uruguay.
 */
public class InputManager {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Obtiene la respuesta del usuario como un booleano, donde 'H' es verdadero y 'J' es falso.
     *
     * @return true si el usuario presiona 'H', false si el usuario presiona 'J'.
     */
    public static boolean obtenerRespuestaSiNo() {
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            if ("H".equals(input)) {
                return true;
            } else if ("J".equals(input)) {
                return false;
            } else {
                System.out.println("Entrada no válida. Presione 'H' para Sí o 'J' para No.");
            }
        }
    }

    /**
     * Obtiene la entrada del usuario como una cadena.
     *
     * @return La cadena ingresada por el usuario.
     */
    public static String obtenerEntradaUsuario() {
        return scanner.nextLine().trim();
    }
}
