package com.ejemplo.carmenuy.model;

import java.util.Objects;

public class Pista {
    private int id;
    private String texto;
    private boolean esCorrecta;

    public Pista(int id, String texto, boolean esCorrecta) {
        if (texto == null || texto.isEmpty()) {
            throw new IllegalArgumentException("El texto no puede ser nulo o vacío");
        }
        this.id = id;
        this.texto = texto;
        this.esCorrecta = esCorrecta;
    }

    public int getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public boolean isEsCorrecta() {
        return esCorrecta;
    }

    @Override
    public String toString() {
        return "Pista{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", esCorrecta=" + esCorrecta +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pista pista = (Pista) o;
        return id == pista.id &&
                esCorrecta == pista.esCorrecta &&
                texto.equals(pista.texto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, texto, esCorrecta);
    }
}
