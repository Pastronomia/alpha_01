package com.ejemplo.carmenuy.model;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Clase para definir constantes utilizadas en la aplicación.
 */
public final class Constants {
    private Constants() {
        // Constructor privado para evitar instanciación
    }

    // Configuración de la base de datos
    public static final String DATABASE_URL = "jdbc:sqlite:database.db";
    public static final String DRIVER_NAME = "org.sqlite.JDBC";

    // Rutas base
    private static final Path AUDIO_BASE_PATH = Paths.get("sonidos");
    private static final Path IMAGE_BASE_PATH = Paths.get("imagenes");

    // Mensajes de audio
    public static final Path AUDIO_BIENVENIDA = AUDIO_BASE_PATH.resolve("voz/bienvenida.mp3");
    public static final Path AUDIO_LOGIN_EXITOSO = AUDIO_BASE_PATH.resolve("voz/login_exitoso.mp3");
    public static final Path AUDIO_LOGIN_FALLIDO = AUDIO_BASE_PATH.resolve("voz/login_fallido.mp3");

    // Mensajes de texto
    public static final String MENSAJE_BIENVENIDA = "Bienvenido al juego de Carmen Sandiego en Uruguay.";
    public static final String MENSAJE_LOGIN_EXITOSO = "Login exitoso. Bienvenido de nuevo.";
    public static final String MENSAJE_LOGIN_FALLIDO = "Login fallido. Por favor, inténtelo de nuevo.";

    // Rutas de imágenes
    public static final Path IMAGEN_MEDALLA = IMAGE_BASE_PATH.resolve("medalla.png");

    // Rutas de efectos de sonido
    public static final Path SONIDO_CORRECTO = AUDIO_BASE_PATH.resolve("efectos/correcto.mp3");
    public static final Path SONIDO_INCORRECTO = AUDIO_BASE_PATH.resolve("efectos/incorrecto.mp3");

    // Rutas de música
    public static final Path MUSICA_FONDO = AUDIO_BASE_PATH.resolve("musica/fondo.mp3");
}
