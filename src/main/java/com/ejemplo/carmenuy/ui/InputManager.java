// CarmenSandiegoUruguay/src/main/java/com/ejemplo/carmenuy/ui/InputManager.java

package com.ejemplo.carmenuy.ui;

import java.util.Scanner;

public class InputManager {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Método para obtener la entrada del usuario.
     * Solo acepta las teclas H, J o K.
     *
     * @return La tecla ingresada por el usuario.
     */
    public static char obtenerEntradaUsuario() {
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.length() == 1 && (input.charAt(0) == 'H' || input.charAt(0) == 'J' || input.charAt(0) == 'K')) {
                return input.charAt(0);
            } else {
                System.out.println("Por favor, ingrese H, J o K.");
            }
        }
    }

    /**
     * Método para obtener una respuesta de sí o no del usuario.
     *
     * @return true si la respuesta es "sí", false si la respuesta es "no".
     */
    public static boolean obtenerRespuestaSiNo() {
        while (true) {
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if (respuesta.equals("si") || respuesta.equals("sí")) {
                return true;
            } else if (respuesta.equals("no")) {
                return false;
            }
            System.out.println("Por favor, responda 'Sí' o 'No'.");
        }
    }
}
public class InputManager {


    /**
     * Método para obtener el nombre de usuario.
     *
     * @return El nombre de usuario ingresado.
     */
    public static String obtenerNombreUsuario() {
        System.out.println("Ingrese su nombre de usuario:");
        return scanner.nextLine().trim();
    }

    /**
     * Método para obtener la contraseña del usuario.
     *
     * @return La contraseña ingresada.
     */
    public static String obtenerContrasena() {
        System.out.println("Ingrese su contraseña:");
        return scanner.nextLine().trim();
    }
}
public class InputManager {


    /**
     * Método para obtener una opción del menú principal.
     * Solo acepta las teclas M, N o O.
     *
     * @return La tecla ingresada por el usuario.
     */
    public static char obtenerOpcionMenuPrincipal() {
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.length() == 1 && (input.charAt(0) == 'M' || input.charAt(0) == 'N' || input.charAt(0) == 'O')) {
                return input.charAt(0);
            } else {
                System.out.println("Por favor, ingrese M, N o O.");
            }
        }
    }

    /**
     * Método para obtener una opción del menú de configuración.
     * Solo acepta las teclas A, B o C.
     *
     * @return La tecla ingresada por el usuario.
     */
    public static char obtenerOpcionMenuConfiguracion() {
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.length() == 1 && (input.charAt(0) == 'A' || input.charAt(0) == 'B' || input.charAt(0) == 'C')) {
                return input.charAt(0);
            } else {
                System.out.println("Por favor, ingrese A, B o C.");
            }
        }
    }
}
public class InputManager {


    /**
     * Método para confirmar una acción del usuario.
     *
     * @return true si el usuario confirma, false en caso contrario.
     */
    public static boolean confirmarAccion() {
        System.out.println("¿Está seguro de que desea realizar esta acción? (Sí/No)");
        return obtenerRespuestaSiNo();
    }

    /**
     * Método para finalizar la entrada del usuario.
     */
    public static void finalizarEntrada() {
        scanner.close();
        System.out.println("Entrada finalizada.");
    }
}
