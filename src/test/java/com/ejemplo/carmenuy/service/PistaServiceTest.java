// CarmenSandiegoUruguay/src/test/java/com/ejemplo/carmenuy/service/PistaServiceTest.java

package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.dao.PistaDAO;
import com.ejemplo.carmenuy.model.Pista;
import com.ejemplo.carmenuy.exception.PistaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PistaServiceTest {

    @Mock
    private PistaDAO mockPistaDAO;

    private PistaService pistaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pistaService = new PistaService(mockPistaDAO);
    }

    @Test
    void testEncriptarPista() {
        String pista = "Esta es una pista";
        String pistaEncriptada = pistaService.encriptarPista(pista);
        assertEquals("*st* *s *n* p*st*", pistaEncriptada);
    }
    public class PistaServiceTest {


        @Test
        void testDesencriptarPista() {
            String pistaEncriptada = "*st* *s *n* p*st*";
            String pistaDesencriptada = pistaService.desencriptarPista(pistaEncriptada);
            assertEquals("Esta es una pista", pistaDesencriptada);
        }

        @Test
        void testInsertarPistaEncriptada() throws SQLException {
            Pista pista = new Pista(1, "Esta es una pista", true);
            pistaService.insertarPistaEncriptada(pista);
            verify(mockPistaDAO).insertarPista(argThat(p -> p.getTexto().equals("*st* *s *n* p*st*")));
        }

        @Test
        void testObtenerPistaDesencriptadaPorId() throws SQLException {
            when(mockPistaDAO.obtenerPistaPorId(1)).thenReturn(new Pista(1, "*st* *s *n* p*st*", true));
            Pista pistaDesencriptada = pistaService.obtenerPistaDesencriptadaPorId(1);
            assertEquals("Esta es una pista", pistaDesencriptada.getTexto());
        }
    }
    public class PistaServiceTest {


        @Test
        void testCrearTablaPistas() throws SQLException {
            pistaService.crearTablaPistas();
            verify(mockPistaDAO).crearTabla();
        }

        @Test
        void testObtenerTodasLasPistas() throws SQLException {
            List<Pista> pistasMock = Arrays.asList(
                new Pista(1, "Pista 1", true),
                new Pista(2, "Pista 2", false)
            );
            when(mockPistaDAO.obtenerTodasLasPistas()).thenReturn(pistasMock);

            List<Pista> pistas = pistaService.obtenerTodasLasPistas();
            assertEquals(2, pistas.size());
            verify(mockPistaDAO).obtenerTodasLasPistas();
        }

        @Test
        void testActualizarPista() throws SQLException {
            Pista pista = new Pista(1, "Pista actualizada", true);
            pistaService.actualizarPista(pista);
            verify(mockPistaDAO).actualizarPista(pista);
        }

        @Test
        void testEliminarPista() throws SQLException {
            pistaService.eliminarPista(1);
            verify(mockPistaDAO).eliminarPista(1);
        }
    }
    public class PistaServiceTest {


        @Test
        void testInsertarPista() throws SQLException {
            Pista pista = new Pista(1, "Nueva pista", true);
            pistaService.insertarPista(pista);
            verify(mockPistaDAO).insertarPista(pista);
        }

        @Test
        void testObtenerPistaPorId() throws SQLException {
            Pista pistaEsperada = new Pista(1, "Pista de prueba", true);
            when(mockPistaDAO.obtenerPistaPorId(1)).thenReturn(pistaEsperada);
            Pista pistaObtenida = pistaService.obtenerPistaPorId(1);
            assertEquals(pistaEsperada, pistaObtenida);
        }

        @Test
        void testInsertarPistasIniciales() throws SQLException {
            pistaService.insertarPistasIniciales();
            verify(mockPistaDAO, times(5)).insertarPista(any(Pista.class));
        }

        @Test
        void testManejoDeExcepcionesEnInsertarPista() {
            Pista pista = new Pista(1, "Pista de prueba", true);
            doThrow(new SQLException("Error de prueba")).when(mockPistaDAO).insertarPista(any(Pista.class));
            assertThrows(PistaException.class, () -> pistaService.insertarPista(pista));
        }

        @Test
        void testEncriptarPistaConEntradaVacia() {
            String pistaEncriptada = pistaService.encriptarPista("");
            assertEquals("", pistaEncriptada);
        }

        @Test
        void testDesencriptarPistaConEntradaVacia() {
            String pistaDesencriptada = pistaService.desencriptarPista("");
            assertEquals("", pistaDesencriptada);
        }
    }
