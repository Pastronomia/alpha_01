package com.ejemplo.carmenuy.ui;

import com.ejemplo.carmenuy.service.UsuarioService;
import com.ejemplo.carmenuy.tts.TTSManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VentanaAyudaVisualTest {

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private TTSManager ttsManager;

    private VentanaAyudaVisual ventana;

    @BeforeEach
    void setUp() {
        ventana = new VentanaAyudaVisual(usuarioService);
        ventana.setTtsManager(ttsManager); // Asumiendo que has añadido un setter para ttsManager
    }

    @Test
    void testActivarTTS() {
        ventana.activarTTS();
        verify(ttsManager).speak("TTS Activado");
    }

    @Test
    void testDesactivarTTS() {
        ventana.desactivarTTS();
        verify(ttsManager).shutdown();
    }

    @Test
    void testMostrarVentanaLoginRegistro() {
        // Este test es más complicado de implementar debido a la naturaleza de Swing
        // Podrías considerar usar una biblioteca de testing de UI como AssertJ Swing
        // Por ahora, podemos verificar que el método no lanza excepciones
        ventana.mostrarVentanaLoginRegistro();
    }

    @Test
    void testInteraccionCompleta() {
        // Simular la interacción completa de activación de TTS y mostrar ventana de login/registro
        ventana.activarTTS();
        verify(ttsManager).speak("TTS Activado");

        ventana.mostrarVentanaLoginRegistro();
        // Aquí se podría verificar que la nueva ventana se muestra utilizando una biblioteca de testing de UI
    }
}
