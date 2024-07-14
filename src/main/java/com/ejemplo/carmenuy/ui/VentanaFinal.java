// CarmenSandiegoUruguay/src/main/java/com/ejemplo/carmenuy/ui/VentanaFinal.java

package com.ejemplo.carmenuy.ui;

import com.ejemplo.carmenuy.model.Detective;
import com.ejemplo.carmenuy.model.Secuaz;
import com.ejemplo.carmenuy.model.Rango;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;

/**
 * Ventana final que muestra la información de la captura del último secuaz y finalización del juego.
 */
public class VentanaFinal extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(VentanaFinal.class.getName());

    private final JLabel lblMensaje;
    private final JLabel lblRango;
    private final JLabel lblSecuaz;
    private final JLabel lblImagen;
    private final JButton btnSalir;

    /**
     * Constructor de la ventana final.
     *
     * @param detective El detective que completó el juego.
     * @param secuaz El último secuaz capturado.
     */
    public VentanaFinal(Detective detective, Secuaz secuaz) {
        setTitle("Juego Completado");
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

        btnSalir = new JButton("Salir");
        btnSalir.addActionListener(this::onSalirButtonClicked);
        add(btnSalir, BorderLayout.SOUTH);
    }
    public class VentanaFinal extends JFrame {


        private void onSalirButtonClicked(ActionEvent e) {
            LOGGER.info("Saliendo del juego");
            System.exit(0);
        }
    }
    public class VentanaFinal extends JFrame {


        /**
         * Método principal para ejecutar la aplicación (solo para pruebas).
         *
         * @param args Argumentos de línea de comandos (no se utilizan).
         */
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                Detective detective = new Detective("Juan", "Pérez", Rango.DETECTIVE_JUNIOR);
                Secuaz secuaz = new Secuaz("MOONabomber", "Explosivos", 5);
                new VentanaFinal(detective, secuaz).setVisible(true);
            });
        }
    }
    public class VentanaFinal extends JFrame {


        /**
         * Método para mostrar la ventana final.
         *
         * @param detective El detective que completó el juego.
         * @param secuaz El último secuaz capturado.
         * @return La instancia de la ventana final.
         */
        public static VentanaFinal mostrarVentanaFinal(Detective detective, Secuaz secuaz) {
            VentanaFinal ventanaFinal = new VentanaFinal(detective, secuaz);
            ventanaFinal.setVisible(true);
            return ventanaFinal;
        }

        /**
         * Método para cerrar la ventana final.
         */
        public void cerrarVentana() {
            this.dispose();


