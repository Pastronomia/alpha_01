// CarmenSandiegoUruguay/src/main/java/com/ejemplo/carmenuy/ui/VentanaJuego.java

package com.ejemplo.carmenuy.ui;

import com.ejemplo.carmenuy.service.JuegoService;
import com.ejemplo.carmenuy.model.Pista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class VentanaJuego extends JFrame {
    private final JLabel lblNarrativa;
    private final JLabel lblLocalidad;
    private final JTextArea txtPista;
    private final JButton btnOpcion1;
    private final JButton btnOpcion2;
    private final JButton btnOpcion3;
    private final JuegoService juegoService;

    public VentanaJuego(JuegoService juegoService) {
        this.juegoService = juegoService;
        setTitle("Carmen Sandiego Uruguay");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior para la narrativa y la localidad
        JPanel panelSuperior = new JPanel(new GridLayout(2, 1));
        lblNarrativa = new JLabel(juegoService.obtenerNarrativaLocalidad(), SwingConstants.CENTER);
        lblNarrativa.setFont(new Font("Arial", Font.BOLD, 18));
        panelSuperior.add(lblNarrativa);

        lblLocalidad = new JLabel("Localidad: " + juegoService.getLocalidadActual().getNombre(), SwingConstants.CENTER);
        lblLocalidad.setFont(new Font("Arial", Font.BOLD, 16));
        panelSuperior.add(lblLocalidad);
        add(panelSuperior, BorderLayout.NORTH);

        // Panel central para la pista
        JPanel panelCentral = new JPanel(new BorderLayout());
        txtPista = new JTextArea("Pistas disponibles:");
        txtPista.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPista.setEditable(false);
        txtPista.setLineWrap(true);
        txtPista.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtPista);
        panelCentral.add(scrollPane, BorderLayout.CENTER);
        add(panelCentral, BorderLayout.CENTER);

        // Panel inferior para las opciones
        JPanel panelInferior = new JPanel(new GridLayout(1, 3));
        btnOpcion1 = new JButton("Opción 1 (H)");
        btnOpcion2 = new JButton("Opción 2 (J)");
        btnOpcion3 = new JButton("Opción 3 (K)");
        panelInferior.add(btnOpcion1);
        panelInferior.add(btnOpcion2);
        panelInferior.add(btnOpcion3);
        add(panelInferior, BorderLayout.SOUTH);

        configurarAccionesBotones();
        configurarAtajosTeclado();
        actualizarInterfaz();
    }
}
public class VentanaJuego extends JFrame {


    private void configurarAccionesBotones() {
        btnOpcion1.addActionListener(e -> procesarRespuesta(0));
        btnOpcion2.addActionListener(e -> procesarRespuesta(1));
        btnOpcion3.addActionListener(e -> procesarRespuesta(2));
    }

    private void configurarAtajosTeclado() {
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('H'), "opcion1");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('J'), "opcion2");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('K'), "opcion3");

        getRootPane().getActionMap().put("opcion1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnOpcion1.doClick();
            }
        });
        getRootPane().getActionMap().put("opcion2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnOpcion2.doClick();
            }
        });
        getRootPane().getActionMap().put("opcion3", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnOpcion3.doClick();
            }
        });
    }
}
public class VentanaJuego extends JFrame {


    private void procesarRespuesta(int opcion) {
        List<Pista> pistas = juegoService.obtenerPistasParaLocalidad();
        if (opcion >= 0 && opcion < pistas.size()) {
            Pista pistaSeleccionada = pistas.get(opcion);
            boolean esCorrecta = juegoService.verificarRespuesta(pistaSeleccionada);
            if (esCorrecta) {
                JOptionPane.showMessageDialog(this, "¡Respuesta correcta!");
                juegoService.moverDetectiveALocalidadAleatoria();
            } else {
                JOptionPane.showMessageDialog(this, "Respuesta incorrecta. Inténtalo de nuevo.");
            }
            actualizarInterfaz();
        }
    }

    private void actualizarInterfaz() {
        lblNarrativa.setText(juegoService.obtenerNarrativaLocalidad());
        lblLocalidad.setText("Localidad: " + juegoService.getLocalidadActual().getNombre());
        List<Pista> pistas = juegoService.obtenerPistasParaLocalidad();

        StringBuilder pistasTexto = new StringBuilder("Pistas disponibles:\n\n");
        for (int i = 0; i < pistas.size(); i++) {
            pistasTexto.append(pistas.get(i).getNumero()).append("\n");
        }
        txtPista.setText(pistasTexto.toString());

        btnOpcion1.setText("Opción 1 (H): " + pistas.get(0).getNumero());
        btnOpcion2.setText("Opción 2 (J): " + pistas.get(1).getNumero());
        btnOpcion3.setText("Opción 3 (K): " + pistas.get(2).getNumero());
    }
}
public class VentanaJuego extends JFrame {
    // ... Código anterior ...

    /**
     * Método principal para ejecutar la aplicación (solo para pruebas).
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        // Simulación de inicialización de JuegoService
        JuegoService juegoService = new JuegoService();
        SwingUtilities.invokeLater(() -> new VentanaJuego(juegoService).setVisible(true));
    }
}
