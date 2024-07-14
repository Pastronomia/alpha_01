package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.tts.TTSManager;
import com.ejemplo.carmenuy.ui.InputManager;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gestiona las funcionalidades de accesibilidad en el juego Carmen Sandiego Uruguay.
 * Proporciona opciones para activar o desactivar el audio y utiliza TTS (Text-to-Speech) para la salida de audio.
 */
public class AccesibilidadManager {
    private static final Logger logger = Logger.getLogger(AccesibilidadManager.class.getName());
    private final TTSManager ttsManager;
    private boolean audioActivo;

    /**
     * Constructor para crear una instancia de AccesibilidadManager.
     *
     * @param ttsManager El gestor de TTS para la salida de audio.
     */
    public AccesibilidadManager(TTSManager ttsManager) {
        this.ttsManager = ttsManager;
        this.audioActivo = true; // El audio está activo por defecto
    }

    /**
     * Pregunta al usuario si desea mantener el audio activo.
     * Utiliza el gestor de TTS para la salida de audio y el gestor de entrada para recibir la respuesta.
     */
    public void preguntarPreferenciaAudio() {
        try {
            ttsManager.speak("¿Desea mantener el audio activo? Presione H para Sí, J para No.");
            audioActivo = InputManager.obtenerRespuestaSiNo();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener la preferencia de audio del usuario", e);
        }
    }

    /**
     * Establece si el audio está activo o no.
     *
     * @param audioActivo true si el audio está activo, false en caso contrario.
     */
    public void setAudioActivo(boolean audioActivo) {
        this.audioActivo = audioActivo;
    }

    /**
     * Verifica si el audio está activo.
     *
     * @return true si el audio está activo, false en caso contrario.
     */
    public boolean isAudioActivo() {
        return audioActivo;
    }

    /**
     * Utiliza el gestor de TTS para hablar el mensaje proporcionado.
     * Solo hablará si el audio está activo.
     *
     * @param mensaje El mensaje que se debe hablar.
     */
    public void hablar(String mensaje) {
        if (audioActivo) {
            try {
                ttsManager.speak(mensaje);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error al usar TTS para hablar el mensaje", e);
            }
        }
    }
}
