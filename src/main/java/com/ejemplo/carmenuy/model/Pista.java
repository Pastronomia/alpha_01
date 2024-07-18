package com.ejemplo.carmenuy.model;

/**
 * Clase que representa una pista en el juego Carmen Sandiego Uruguay.
 */
public class Pista {
    private int id;
    private String texto;
    private int localidadId;

    /**
     * Constructor completo de la clase Pista.
     * @param id el identificador único de la pista.
     * @param texto el contenido de la pista.
     * @param localidadId el identificador de la localidad asociada a la pista.
     */
    public Pista(int id, String texto, int localidadId) {
        this.id = id;
        this.texto = texto;
        this.localidadId = localidadId;
    }

    /**
     * Constructor para crear una pista sin un ID especificado, útil para cuando se crea una nueva pista que aún no ha sido guardada en la base de datos.
     * @param texto el contenido de la pista.
     * @param localidadId el identificador de la localidad asociada a la pista.
     */
    public Pista(String texto, int localidadId) {
        this.texto = texto;
        this.localidadId = localidadId;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getLocalidadId() {
        return localidadId;
    }

    public void setLocalidadId(int localidadId) {
        this.localidadId = localidadId;
    }
}
