package com.ejemplo.carmenuy.model;

import java.util.Objects;
import java.util.Random;

/**
 * Representa a Carmen Sandiego (CSD) en el juego.
 * Esta clase contiene información sobre el personaje principal.
 */
public class CSD {
    private static final String NOMBRE = "Carmen Sandiego";
    private static final String DESCRIPCION = "Extremadamente inteligente y escurridiza";
    private static final String HABILIDAD_ESPECIAL = "Poder escapar a menos que esté en el mismo nodo del grafo";

    /**
     * Constructor para crear una instancia de CSD.
     */
    public CSD() {
        // No se necesita hacer nada en el constructor
    }

    /**
     * Obtiene el nombre de Carmen Sandiego.
     *
     * @return El nombre de Carmen Sandiego.
     */
    public String getNombre() {
        return NOMBRE;
    }

    /**
     * Obtiene la descripción de Carmen Sandiego.
     *
     * @return La descripción de Carmen Sandiego.
     */
    public String getDescripcion() {
        return DESCRIPCION;
    }

    /**
     * Obtiene la habilidad especial de Carmen Sandiego.
     *
     * @return La habilidad especial de Carmen Sandiego.
     */
    public String getHabilidadEspecial() {
        return HABILIDAD_ESPECIAL;
    }

    /**
     * Genera una pista falsa sobre la ubicación de Carmen Sandiego.
     *
     * @return Una cadena con una pista falsa.
     */
    public String generarPistaFalsa() {
        String[] ciudades = {"Montevideo", "Punta del Este", "Colonia del Sacramento"};
        String ciudadAleatoria = ciudades[new Random().nextInt(ciudades.length)];
        int horasAleatorias = new Random().nextInt(5) + 1;
        return "Carmen Sandiego fue vista en " + ciudadAleatoria + " hace " + horasAleatorias + " horas.";
    }

    @Override
    public String toString() {
        return String.format("CSD{nombre='%s', descripcion='%s', habilidadEspecial='%s'}",
                NOMBRE, DESCRIPCION, HABILIDAD_ESPECIAL);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true; // Todas las instancias de CSD son iguales
    }

    @Override
    public int hashCode() {
        return Objects.hash(NOMBRE, DESCRIPCION, HABILIDAD_ESPECIAL);
    }
}
