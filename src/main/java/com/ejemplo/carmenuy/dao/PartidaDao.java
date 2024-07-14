package com.ejemplo.carmenuy.dao;

import com.ejemplo.carmenuy.model.Partida;
import com.ejemplo.carmenuy.model.Nodo;
import com.ejemplo.carmenuy.model.Detective;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PartidaDAO {
    private Connection connection;
    private Grafo grafo;

    public PartidaDAO(Connection connection, Grafo grafo) {
        this.connection = connection;
        this.grafo = grafo;
    }

    public void guardarPartida(Partida partida) throws SQLException {
        String sql = "INSERT INTO partidas (detective_nombre, nodo_detective, nodo_objetivo) VALUES (?, ?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, partida.getDetective().getNombre());
            statement.setString(2, partida.getNodoDetective().getNombre());
            statement.setString(3, partida.getNodoObjetivo().getNombre());
            statement.executeUpdate();
        }
    }

    public Partida cargarUltimaPartida() throws SQLException {
        Partida partida = null;
        String sql = "SELECT * FROM partidas ORDER BY id DESC LIMIT 1";
        try (PreparedStatement statement = this.connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                String detectiveNombre = resultSet.getString("detective_nombre");
                String nodoDetectiveNombre = resultSet.getString("nodo_detective");
                String nodoObjetivoNombre = resultSet.getString("nodo_objetivo");

                Nodo nodoDetective = grafo.getNodo(nodoDetectiveNombre);
                Nodo nodoObjetivo = grafo.getNodo(nodoObjetivoNombre);
                Detective detective = new Detective(detectiveNombre, "", Rango.DETECTIVE_JUNIOR); // Asumiendo que el apellido no es importante aquí

                partida = new Partida(detective, nodoDetective, nodoObjetivo);
            }
        }
        return partida;
    }

    public void crearTabla() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS partidas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "detective_nombre TEXT, " +
                "nodo_detective TEXT, " +
                "nodo_objetivo TEXT);";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.execute();
        }
    }
}
