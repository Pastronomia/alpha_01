// CarmenSandiegoUruguay/src/main/java/com/ejemplo/carmenuy/ui/VentanaAyudaVisual.java

package com.ejemplo.carmenuy.ui;

import com.ejemplo.carmenuy.service.UsuarioService;
import com.ejemplo.carmenuy.tts.TTSManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;

/**
 * Ventana que pregunta al usuario si necesita ayuda visual para jugar.
 */
public class VentanaAyudaVisual extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(VentanaAyudaVisual.class.getName());

    private final JButton btnSi;
    private final JButton btnNo;
    private final JLabel lblPregunta;
    private final UsuarioService usuarioService;
    private TTSManager ttsManager;

    /**
     * Constructor de la ventana de ayuda visual.
     *
     * @param usuarioService Servicio de usuario para manejar la lógica de negocio.
     */
    public VentanaAyudaVisual(UsuarioService usuarioService) {
        this(usuarioService, new TTSManager());
    }

    /**
     * Constructor de la ventana de ayuda visual con TTSManager inyectado.
     *
     * @param usuarioService Servicio de usuario para manejar la lógica de negocio.
     * @param ttsManager Gestor de Text-to-Speech.
     */
    public VentanaAyudaVisual(UsuarioService usuarioService, TTSManager ttsManager) {
        this.usuarioService = usuarioService;
        this.ttsManager = ttsManager;

        setTitle("Ayuda Visual");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        lblPregunta = new JLabel("¿Necesitas ayuda visual para jugar?", SwingConstants.CENTER);
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblPregunta, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());

        btnSi = new JButton("Sí");
        btnNo = new JButton("No");

        panelBotones.add(btnSi);
        panelBotones.add(btnNo);

        add(panelBotones, BorderLayout.SOUTH);

        btnSi.addActionListener(this::onSiButtonClicked);
        btnNo.addActionListener(this::onNoButtonClicked);
    }
    public class VentanaAyudaVisual extends JFrame {


        private void onSiButtonClicked(ActionEvent e) {
            activarTTS();
            mostrarVentanaLoginRegistro();
        }

        private void onNoButtonClicked(ActionEvent e) {
            desactivarTTS();
            mostrarVentanaLoginRegistro();
        }

        // Cambiado a public para testing
        public void activarTTS() {
            ttsManager.speak("TTS Activado");
            LOGGER.info("TTS Activado");
        }

        // Cambiado a public para testing
        public void desactivarTTS() {
            ttsManager.shutdown();
            LOGGER.info("TTS Desactivado");
        }
    }
    public class VentanaAyudaVisual extends JFrame {


        // Cambiado a protected para testing
        protected void mostrarVentanaLoginRegistro() {
            SwingUtilities.invokeLater(() -> {
                VentanaLogin ventanaLogin = crearVentanaLogin();
                ventanaLogin.setVisible(true);
                dispose();
            });
        }

        // Método separado para facilitar el testing
        protected VentanaLogin crearVentanaLogin() {
            return new VentanaLogin(usuarioService);
        }
    }
    public class VentanaAyudaVisual extends JFrame {


        // Setter para TTSManager, útil para testing
        public void setTtsManager(TTSManager ttsManager) {
            this.ttsManager = ttsManager;
        }

        /**
         * Método principal para ejecutar la aplicación.
         *
         * @param args Argumentos de línea de comandos (no se utilizan).
         */
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new VentanaAyudaVisual(null).setVisible(true));


