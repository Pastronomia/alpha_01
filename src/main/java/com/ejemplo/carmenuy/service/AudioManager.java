package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.tts.TTSManager;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gestiona los sonidos y la música en el juego Carmen Sandiego Uruguay.
 */
public class AudioManager {
    private static final Logger LOGGER = Logger.getLogger(AudioManager.class.getName());
    private final TTSManager ttsManager;
    private final Map<String, Clip> efectos;
    private Clip musicaFondo;
    private float volumenGeneral;

    /**
     * Constructor para crear una instancia de AudioManager.
     */
    public AudioManager() {
        this.ttsManager = new TTSManager();
        this.efectos = new HashMap<>();
        this.volumenGeneral = 1.0f; // Volumen inicial al máximo
        cargarEfectos();
    }

    /**
     * Carga los efectos de sonido iniciales.
     */
    private void cargarEfectos() {
        cargarEfecto("victoria", "sonidos/victoria.wav");
        cargarEfecto("fracaso", "sonidos/fracaso.wav");
        cargarEfecto("latido", "sonidos/latido.wav");
        // Agregar más efectos según sea necesario
    }

    /**
     * Carga un efecto de sonido desde un archivo.
     *
     * @param nombre El nombre del efecto.
     * @param ruta La ruta del archivo de sonido.
     */
    private void cargarEfecto(String nombre, String ruta) {
        try {
            File archivoAudio = new File(ruta);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(archivoAudio);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            efectos.put(nombre, clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            LOGGER.log(Level.SEVERE, "Error al cargar el efecto de sonido " + nombre + ": " + e.getMessage());
        }
    }

    /**
     * Reproduce un efecto de sonido.
     *
     * @param nombreEfecto El nombre del efecto a reproducir.
     */
    public void reproducirEfecto(String nombreEfecto) {
        Clip clip = efectos.get(nombreEfecto);
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            ajustarVolumen(clip);
            clip.start();
        } else {
            LOGGER.warning("Efecto de sonido no encontrado: " + nombreEfecto);
        }
    }

    /**
     * Reproduce una pista de música de fondo.
     *
     * @param nombrePista El nombre de la pista de música.
     */
    public void reproducirMusica(String nombrePista) {
        try {
            if (musicaFondo != null && musicaFondo.isRunning()) {
                musicaFondo.stop();
            }
            File archivoMusica = new File("sonidos/" + nombrePista + ".wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(archivoMusica);
            musicaFondo = AudioSystem.getClip();
            musicaFondo.open(audioInputStream);
            ajustarVolumen(musicaFondo);
            musicaFondo.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            LOGGER.log(Level.SEVERE, "Error al reproducir la música de fondo: " + e.getMessage());
        }
    }

    /**
     * Establece el volumen general para todos los sonidos.
     *
     * @param volumen El nuevo volumen general (entre 0.0 y 1.0).
     */
    public void setVolumenGeneral(float volumen) {
        this.volumenGeneral = Math.max(0.0f, Math.min(1.0f, volumen));
        ajustarVolumenTodosLosClips();
    }

    /**
     * Ajusta el volumen de todos los clips de sonido.
     */
    private void ajustarVolumenTodosLosClips() {
        for (Clip clip : efectos.values()) {
            ajustarVolumen(clip);
        }
        if (musicaFondo != null) {
            ajustarVolumen(musicaFondo);
        }
    }

    /**
     * Ajusta el volumen de un clip de sonido.
     *
     * @param clip El clip de sonido.
     */
    private void ajustarVolumen(Clip clip) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (range * volumenGeneral) + gainControl.getMinimum();
        gainControl.setValue(gain);
    }
}
