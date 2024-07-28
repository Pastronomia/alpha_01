package com.ejemplo.carmenuy.dao;

import com.ejemplo.carmenuy.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioDAOTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    private UsuarioDAO usuarioDAO;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        usuarioDAO = new UsuarioDAO(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    }

    @Test
    void testObtenerUsuarioPorNombre() throws SQLException {
        String nombre = "testNombre";

        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("nombre")).thenReturn(nombre);
        when(mockResultSet.getString("apellido")).thenReturn("testApellido");
        when(mockResultSet.getString("contrasena")).thenReturn("testContrasena");
        when(mockResultSet.getString("rango")).thenReturn("DETECTIVE_JUNIOR");
        when(mockResultSet.getInt("capturas")).thenReturn(0);
        when(mockResultSet.getString("progreso")).thenReturn("");

        Usuario usuario = usuarioDAO.obtenerUsuarioPorNombre(nombre);

        assertNotNull(usuario);
        assertEquals(nombre, usuario.getNombre());
    }

    @Test
    void testObtenerUsuarioPorNombreNoExistente() throws SQLException {
        String nombre = "usuarioNoExistente";

        when(mockResultSet.next()).thenReturn(false);

        Usuario usuario = usuarioDAO.obtenerUsuarioPorNombre(nombre);

        assertNull(usuario);
    }

    @Test
    void testInsertarUsuario() throws SQLException {
        Usuario usuario = new Usuario("testNombre", "testApellido", "testContrasena", "DETECTIVE_JUNIOR", 0, "");

        usuarioDAO.insertarUsuario(usuario);

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testActualizarUsuario() throws SQLException {
        Usuario usuario = new Usuario(1, "testNombre", "testApellido", "testContrasena", "DETECTIVE_JUNIOR", 0, "");

        usuarioDAO.actualizarUsuario(usuario);

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testEliminarUsuario() throws SQLException {
        usuarioDAO.eliminarUsuario(1);

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }
}
