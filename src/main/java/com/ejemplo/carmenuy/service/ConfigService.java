package com.ejemplo.carmenuy.service;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servicio de configuración para el juego Carmen Sandiego Uruguay.
 * Carga y proporciona propiedades de configuración desde un archivo properties.
 */
public class ConfigService {
    private static final Logger LOGGER = Logger.getLogger(ConfigService.class.getName());
    private static final Properties properties = new Properties();

    static {
        cargarPropiedades();
    }

    /**
     * Carga las propiedades desde el archivo de configuración.
     */
    private static void cargarPropiedades() {
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            manejarErrorCargaPropiedades(e);
        }
    }

    /**
     * Obtiene el valor de una propiedad dado su clave.
     *
     * @param key La clave de la propiedad.
     * @return El valor de la propiedad.
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Obtiene el valor de una propiedad dado su clave, con un valor por defecto.
     *
     * @param key La clave de la propiedad.
     * @param defaultValue El valor por defecto si la propiedad no existe.
     * @return El valor de la propiedad o el valor por defecto.
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Verifica si una propiedad existe en el archivo de configuración.
     *
     * @param key La clave de la propiedad.
     * @return true si la propiedad existe, false en caso contrario.
     */
    public static boolean containsProperty(String key) {
        return properties.containsKey(key);
    }

    /**
     * Recarga las propiedades desde el archivo de configuración.
     *
     * @throws IOException si ocurre un error al leer el archivo.
     */
    public static void reloadProperties() throws IOException {
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            properties.clear();
            properties.load(input);
        }
    }

    /**
     * Obtiene el valor de una propiedad como entero.
     *
     * @param key La clave de la propiedad.
     * @return El valor de la propiedad como entero.
     * @throws NumberFormatException si el valor no es un entero válido.
     */
    public static int getIntProperty(String key) {
        String value = getProperty(key);
        if (value != null) {
            return Integer.parseInt(value);
        }
        throw new IllegalArgumentException("La propiedad " + key + " no existe");
    }

    /**
     * Obtiene el valor de una propiedad como booleano.
     *
     * @param key La clave de la propiedad.
     * @return El valor de la propiedad como booleano.
     */
    public static boolean getBooleanProperty(String key) {
        String value = getProperty(key);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        throw new IllegalArgumentException("La propiedad " + key + " no existe");
    }

    /**
     * Guarda una propiedad en el archivo de configuración.
     *
     * @param key La clave de la propiedad.
     * @param value El valor de la propiedad.
     * @throws IOException si ocurre un error al guardar la propiedad.
     */
    public static void setProperty(String key, String value) throws IOException {
        properties.setProperty(key, value);
        try (OutputStream output = new FileOutputStream("src/main/resources/config.properties")) {
            properties.store(output, null);
        }
    }

    /**
     * Maneja errores de carga de propiedades de configuración.
     *
     * @param e La excepción ocurrida durante la carga.
     */
    private static void manejarErrorCargaPropiedades(IOException e) {
        LOGGER.log(Level.SEVERE, "Error al cargar las propiedades de configuración", e);
    }
}
