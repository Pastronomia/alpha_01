// CarmenSandiegoUruguay/src/main/java/com/ejemplo/carmenuy/ui/VentanaCaptura.java

package com.ejemplo.carmenuy.ui;

import com.ejemplo.carmenuy.model.Detective;
import com.ejemplo.carmenuy.model.Secuaz;
import com.ejemplo.carmenuy.model.Rango;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;

/**
 * Ventana que muestra la información de captura exitosa de un secuaz.
 */
public class VentanaCaptura extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(VentanaCaptura.class.getName());

    private final JLabel lblMensaje;
    private final JLabel lblRango;
    private final JLabel lblSecuaz;
    private final JLabel lblImagen;
    private final JButton btnContinuar;

    /**
     * Constructor de la ventana de captura.
     *
     * @param detective El detective que realizó la captura.
     * @param secuaz El secuaz capturado.
     */
    public VentanaCaptura(Detective detective, Secuaz secuaz) {
        setTitle("Captura Exitosa");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        lblMensaje = new JLabel("¡Felicitaciones " + detective.getRango() + " " + detective.getNombre() + "!", SwingConstants.CENTER);
        lblMensaje.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblMensaje, BorderLayout.NORTH);

        lblRango = new JLabel("Has capturado al peligroso " + secuaz.getNombre() + " y has sido ascendido a " + detective.getRango(), SwingConstants.CENTER);
        lblRango.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblRango, BorderLayout.CENTER);

        lblSecuaz = new JLabel("Secuaz: " + secuaz.getNombre() + " - Habilidad: " + secuaz.getHabilidad() + " - Peligrosidad: " + secuaz.getPeligrosidad(), SwingConstants.CENTER);
        lblSecuaz.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblSecuaz, BorderLayout.SOUTH);

        lblImagen = new JLabel(new ImageIcon("ruta/a/la/imagen/medalla.png"), SwingConstants.CENTER);
        add(lblImagen, BorderLayout.WEST);

        btnContinuar = new JButton("Continuar");
        btnContinuar.addActionListener(this::onContinuarButtonClicked);
        add(btnContinuar, BorderLayout.SOUTH);
    }
    public class VentanaCaptura extends JFrame {


        private void onContinuarButtonClicked(ActionEvent e) {
            continuarJuego();
        }

        private void continuarJuego() {
            LOGGER.info("Continuando el juego después de la captura");
            SwingUtilities.invokeLater(() -> {
                VentanaJuego ventanaJuego = crearVentanaJuego();
                ventanaJuego.setVisible(true);
                dispose();
            });
        }

        protected VentanaJuego crearVentanaJuego() {
            return new VentanaJuego();
        }
    }
    public class VentanaCaptura extends JFrame {


        /**
         * Método principal para ejecutar la aplicación (solo para pruebas).
         *
         * @param args Argumentos de línea de comandos (no se utilizan).
         */
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                Detective detective = new Detective("Juan", "Pérez", Rango.DETECTIVE_JUNIOR);
                Secuaz secuaz = new Secuaz("MOONabomber", "Explosivos", 5);
                new VentanaCaptura(detective, secuaz).setVisible(true);
            });
        }
    }
    public class VentanaCaptura extends JFrame {
        // ... Código anterior ...

        /**
         * Método para mostrar la ventana de captura.
         *
         * @param detective El detective que realizó la captura.
         * @param secuaz El secuaz capturado.
         * @return La instancia de la ventana de captura.
         */
        public static VentanaCaptura mostrarVentanaCaptura(Detective detective, Secuaz secuaz) {
            VentanaCaptura ventanaCaptura = new VentanaCaptura(detective, secuaz);
            ventanaCaptura.setVisible(true);
            return ventanaCaptura;
        }

        /**
         * Método para cerrar la ventana de captura.
         */
        public void cerrarVentana() {
            this.dispose();


