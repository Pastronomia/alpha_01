package com.ejemplo.carmenuy.tts;

import java.util.concurrent.CompletableFuture;

/**
 * Interfaz que define los métodos para el gestor de síntesis de texto a voz (TTS).
 */
public interface ITTSManager {
    /**
     * Sintetiza el texto proporcionado en voz.
     *
     * @param text El texto a sintetizar.
     */
    void speak(String text);

    /**
     * Sintetiza el texto proporcionado en voz de forma asíncrona.
     *
     * @param text El texto a sintetizar.
     * @return Un CompletableFuture que se completa cuando la síntesis de texto a voz finaliza.
     */
    CompletableFuture<Void> speakAsync(String text);

    /**
     * Establece el volumen de la voz del sintetizador.
     *
     * @param volume El volumen deseado.
     */
    void setVolume(float volume);

    /**
     * Obtiene el volumen actual de la voz del sintetizador.
     *
     * @return El volumen actual.
     */
    float getVolume();

    /**
     * Establece la velocidad de la voz del sintetizador.
     *
     * @param speed La velocidad deseada.
     */
    void setSpeed(float speed);

    /**
     * Cierra el sintetizador de voz y libera los recursos.
     */
    void shutdown();
}
