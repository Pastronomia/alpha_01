package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.model.Localidad;
import com.ejemplo.carmenuy.model.Grafo;
import com.ejemplo.carmenuy.model.Nodo;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

public class NarrativaManager {
    private final Map<String, String> narrativas = new HashMap<>();
    private final Map<String, String> letrasPorLocalidad = new HashMap<>();
    private final Grafo grafo;

    public NarrativaManager(Grafo grafo) {
        this.grafo = grafo;
        inicializarNarrativas();
        inicializarLetrasPorLocalidad();
    }

    private void inicializarNarrativas() {
        narrativas.put("A", "Aquí comienza tu aventura.");
        narrativas.put("B", "Has llegado a un lugar misterioso.");
    }

    private void inicializarLetrasPorLocalidad() {
        letrasPorLocalidad.put("Montevideo", "A");
        letrasPorLocalidad.put("Salto", "B");
    }

    public String generarNarrativa(Localidad localidad) {
        var letra = letrasPorLocalidad.getOrDefault(localidad.getNombre(), "X");
        var narrativaBase = narrativas.getOrDefault(letra,
                "Te encuentras en " + localidad.getNombre() + " (Localidad " + letra + "). [Narrativa por defecto]");

        var nodoActual = grafo.getNodo(localidad.getNombre());
        var conexiones = nodoActual.getConexiones().stream()
                .map(Nodo::getNombre)
                .collect(Collectors.toList());

        var infoConexiones = "Desde aquí puedes viajar a: " + String.join(", ", conexiones) + ".";

        return narrativaBase + "\n" + infoConexiones;
    }
}
