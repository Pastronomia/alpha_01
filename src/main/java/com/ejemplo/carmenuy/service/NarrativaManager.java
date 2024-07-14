
package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.model.Localidad;
import com.ejemplo.carmenuy.model.Grafo;
import com.ejemplo.carmenuy.model.Nodo;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;
import com.ejemplo.carmenuy.model.Localidad;




public class NarrativaManager {
    private final Map<String, String> narrativas;
    private final Map<String, String> letrasPorLocalidad;
    private final Grafo grafo;

    public NarrativaManager(Grafo grafo) {
        this.grafo = grafo;
        narrativas = new HashMap<>();
        letrasPorLocalidad = new HashMap<>();
        inicializarNarrativas();
        inicializarLetrasPorLocalidad();
    }

    public String generarNarrativa(Localidad localidad) {
        String letra = letrasPorLocalidad.getOrDefault(localidad.getNombre(), "X");
        String narrativaBase = narrativas.getOrDefault(letra,
                "Te encuentras en " + localidad.getNombre() + " (Localidad " + letra + "). [Narrativa por defecto]");

        Nodo nodoActual = grafo.getNodo(localidad.getNombre());
        List<String> conexiones = nodoActual.getConexiones().stream()
                .map(Nodo::getNombre)
                .collect(Collectors.toList());

        String infoConexiones = "Desde aquí puedes viajar a: " + String.join(", ", conexiones) + ".";

        return narrativaBase + "\n" + infoConexiones;
    }
}
