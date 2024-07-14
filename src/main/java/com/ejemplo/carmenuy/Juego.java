package com.ejemplo.carmenuy;

import com.ejemplo.carmenuy.tts.TTSManager;
import com.ejemplo.carmenuy.tts.ITTSManager;
import com.ejemplo.carmenuy.service.UsuarioService;
import com.ejemplo.carmenuy.model.Usuario;
import com.ejemplo.carmenuy.dao.BaseDeDatosManager; // Corregir la importación

import java.sql.SQLException;
import java.util.Scanner;

public class Juego {
    private ITTSManager ttsManager;
    private UsuarioService usuarioService;
    private boolean juegoTerminado;

    public Juego() {
        this.ttsManager = new TTSManager();
        BaseDeDatosManager baseDeDatosManager = new BaseDeDatosManager();
        baseDeDatosManager.inicializarBaseDeDatos();
        baseDeDatosManager.crearTablasSiNoExisten();
        this.usuarioService = new UsuarioService(baseDeDatosManager.getConexion());
        this.juegoTerminado = false;
    }

    public void iniciarJuego() {
        ttsManager.speak("Bienvenido al juego Carmen Sandiego. Por favor, ingrese su nombre de usuario y contraseña.");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        // Validar credenciales
        try {
            Usuario usuarioObj = usuarioService.obtenerUsuarioPorNombre(usuario);
            if (usuarioObj != null && usuarioObj.getContrasena().equals(contrasena)) {
                ttsManager.speak("Bienvenido, " + usuario + ". Elija una opción usando las teclas H, J o K.");
                mostrarOpciones();
            } else {
                ttsManager.speak("Credenciales inválidas. Por favor, intente de nuevo.");
                iniciarJuego(); // Reintentar login
            }
        } catch (SQLException e) {
            ttsManager.speak("Error al validar las credenciales. Por favor, intente de nuevo.");
            iniciarJuego(); // Reintentar login
        }
    }

    public void mostrarOpciones() {
        Scanner scanner = new Scanner(System.in);
        String opcion = "";

        while (!opcion.equals("h") && !opcion.equals("j") && !opcion.equals("k")) {
            ttsManager.speak("Elija una opción usando las teclas H, J o K. H para apagar, J para subir volumen, K para bajar volumen.");
            opcion = scanner.nextLine().toLowerCase();

            if (!opcion.equals("h") && !opcion.equals("j") && !opcion.equals("k")) {
                ttsManager.speak("Solo puedes elegir entre las teclas H, J, K.");
            }
        }

        procesarOpcion(opcion);
    }

    private void procesarOpcion(String opcion) {
        switch (opcion) {
            case "h":
                finalizarJuego();
                break;
            case "j":
                ttsManager.speak("Has seleccionado subir el volumen.");
                // Lógica para subir el volumen
                break;
            case "k":
                ttsManager.speak("Has seleccionado bajar el volumen.");
                // Lógica para bajar el volumen
                break;
            default:
                ttsManager.speak("Opción inválida.");
                mostrarOpciones();
                break;
        }
    }

    public void finalizarJuego() {
        ttsManager.speak("Gracias por jugar. Hasta la próxima.");
        ttsManager.shutdown();
        juegoTerminado = true;
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.iniciarJuego();
    }
}
