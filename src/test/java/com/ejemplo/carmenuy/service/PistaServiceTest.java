package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.dao.PistaDAO;
import com.ejemplo.carmenuy.model.Pista;
import com.ejemplo.carmenuy.exception.PistaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PistaServiceTest {

    @Mock
    private PistaDAO mockPistaDAO;

    @Mock
    private Connection mockConnection;

    private PistaService pistaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pistaService = new PistaService(mockConnection);
        pistaService.setPistaDAO(mockPistaDAO); // Aseguramos que el mockPistaDAO se use en el servicio
    }

    @Test
    void testInsertarPista() throws SQLException, PistaException {
        Pista pista = new Pista(1, 1, "1.1", "Esta es una pista", false); // Corregido con tipos de datos correctos
        pistaService.insertarPista(pista);
        verify(mockPistaDAO).insertarPista(pista);
    }

    @Test
    void testObtenerPistaPorId() throws SQLException, PistaException {
        Pista pistaEsperada = new Pista(1, 1, "1.1", "Esta es una pista", false); // Corregido con tipos de datos correctos
        when(mockPistaDAO.obtenerPistaPorId(1)).thenReturn(pistaEsperada);
        Pista pistaObtenida = pistaService.obtenerPistaPorId(1);
        assertEquals(pistaEsperada, pistaObtenida);
    }

    @Test
    void testCrearTablaPistas() throws SQLException, PistaException {
        pistaService.crearTablaPistas();
        verify(mockPistaDAO).crearTabla();
    }

    @Test
    void testObtenerTodasLasPistas() throws SQLException, PistaException {
        List<Pista> pistasMock = Arrays.asList(
                new Pista(1, 1, "1.1", "Pista 1", false), // Corregido con tipos de datos correctos
                new Pista(2, 2, "2.1", "Pista 2", false)  // Corregido con tipos de datos correctos
        );
        when(mockPistaDAO.obtenerTodasLasPistas()).thenReturn(pistasMock);

        List<Pista> pistas = pistaService.obtenerTodasLasPistas();
        assertEquals(2, pistas.size());
        verify(mockPistaDAO).obtenerTodasLasPistas();
    }

    @Test
    void testActualizarPista() throws SQLException, PistaException {
        Pista pista = new Pista(1, 1, "1.1", "Pista actualizada", false); // Corregido con tipos de datos correctos
        pistaService.actualizarPista(pista);
        verify(mockPistaDAO).actualizarPista(pista);
    }

    @Test
    void testEliminarPista() throws SQLException, PistaException {
        pistaService.eliminarPista(1);
        verify(mockPistaDAO).eliminarPista(1);
    }

    @Test
    void testInsertarPistasIniciales() throws SQLException, PistaException {
        pistaService.insertarPistasIniciales();
        verify(mockPistaDAO, times(40 * 5)).insertarPista(any(Pista.class));
    }

    @Test
    void testManejoDeExcepcionesEnInsertarPista() {
        Pista pista = new Pista(1, 1, "1.1", "Pista de prueba", false); // Corregido con tipos de datos correctos
        try {
            doThrow(new SQLException("Error de prueba")).when(mockPistaDAO).insertarPista(any(Pista.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        assertThrows(PistaException.class, () -> pistaService.insertarPista(pista));
    }
}
