package com.ejemplo.carmenuy.model;

public class Pista {
    private int id;
    private int localidadId;
    private String numero;
    private String descripcion;
    private boolean esCorrecta;

    public Pista(int id, int localidadId, String numero, String descripcion, boolean esCorrecta) {
        this.id = id;
        this.localidadId = localidadId;
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

    public int getLocalidadId() {
        return localidadId;
    }

    public void setLocalidadId(int localidadId) {
        this.localidadId = localidadId;
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
