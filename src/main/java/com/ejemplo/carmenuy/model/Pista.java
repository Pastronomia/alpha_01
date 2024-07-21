package com.ejemplo.carmenuy.model;

/**
 * Clase que representa una pista en el juego Carmen Sandiego Uruguay.
 */
public class Pista {
    private int id;
    private String localidad;
    private String numero;
    private String descripcion;
    private boolean esCorrecta;

    /**
     * Constructor completo de la clase Pista.
     * @param id el identificador único de la pista.
     * @param localidad la localidad asociada a la pista.
     * @param numero el número de la pista.
     * @param descripcion el contenido de la pista.
     * @param esCorrecta indica si la pista es correcta.
     */
    public Pista(int id, String localidad, String numero, String descripcion, boolean esCorrecta) {
        this.id = id;
        this.localidad = localidad;
        this.numero = numero;
        this.descripcion = descripcion;
        this.esCorrecta = esCorrecta;
    }

    /**
     * Constructor para crear una pista sin un ID especificado, útil para cuando se crea una nueva pista que aún no ha sido guardada en la base de datos.
     * @param localidad la localidad asociada a la pista.
     * @param numero el número de la pista.
     * @param descripcion el contenido de la pista.
     * @param esCorrecta indica si la pista es correcta.
     */
    public Pista(String localidad, String numero, String descripcion, boolean esCorrecta) {
        this.localidad = localidad;
        this.numero = numero;
        this.descripcion = descripcion;
        this.esCorrecta = esCorrecta;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean esCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }
}
