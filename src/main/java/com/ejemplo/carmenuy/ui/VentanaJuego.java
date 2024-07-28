package com.ejemplo.carmenuy.ui;

import com.ejemplo.carmenuy.service.JuegoService;
import com.ejemplo.carmenuy.model.Pista;
import com.ejemplo.carmenuy.model.Usuario;
import com.ejemplo.carmenuy.model.Secuaz;
import com.ejemplo.carmenuy.model.Localidad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class VentanaJuego extends JFrame {
    private final JuegoService juegoService;
    private final JTextArea textArea;
    private List<Pista> pistasActuales;
    private final Usuario usuario;
    private int erroresConsecutivos = 0;
    private int localidadIndex = 0; // Índice para asignar localidades secuencialmente

    public VentanaJuego(JuegoService juegoService, Usuario usuario) {
        this.juegoService = juegoService;
        this.usuario = usuario;
        setTitle("Carmen Sandiego Uruguay");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Añadir el mensaje de salir
        JLabel salirLabel = new JLabel("Si desea abandonar el juego oprima la tecla escape", SwingConstants.RIGHT);
        salirLabel.setFont(new Font("Serif", Font.BOLD, 14));
        salirLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(salirLabel, BorderLayout.SOUTH);

        configurarAccionesTeclado();
        actualizarInterfaz();
    }

    private void configurarAccionesTeclado() {
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_H:
                        manejarSeleccionPista(0);
                        break;
                    case KeyEvent.VK_J:
                        manejarSeleccionPista(1);
                        break;
                    case KeyEvent.VK_K:
                        manejarSeleccionPista(2);
                        break;
                    case KeyEvent.VK_ESCAPE:
                        mostrarMenuSalida();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void manejarSeleccionPista(int indice) {
        if (pistasActuales != null && indice < pistasActuales.size()) {
            Pista pistaSeleccionada = pistasActuales.get(indice);
            boolean esCorrecta = pistaSeleccionada.esCorrecta();
            if (esCorrecta) {
                erroresConsecutivos = 0;
                mostrarMensaje("¡Has acertado!", "Felicitaciones, has encontrado una pista crucial.");
                juegoService.manejarSeleccionPista(pistaSeleccionada);
                if (juegoService.getLocalidadActual().equals(juegoService.getSecuazActual().getLocalidad())) {
                    mostrarVentanaCaptura();
                    juegoService.capturarSecuazActual();
                    juegoService.seleccionarNuevoSecuazNoCapturado();
                    asignarLocalidadSecuencial();
                }
            } else {
                erroresConsecutivos++;
                if (erroresConsecutivos >= 3) {
                    mostrarMensaje("Demasiados errores.", "Intenta nuevamente.");
                    erroresConsecutivos = 0;
                } else {
                    mostrarMensaje("Pista incorrecta", "Intenta nuevamente.");
                }
            }
            actualizarInterfaz();
        }
    }

    private void mostrarMenuSalida() {
        VentanaResultado.mostrarVentana("Opciones de Salida", "¿Qué desea hacer?\nH: Salir sin guardar\nJ: Proseguir\nK: Salir y guardar");
    }

    private void mostrarVentanaCaptura() {
        VentanaResultado.mostrarVentana("¡Captura Exitosa!", "¡Has capturado al secuaz " + juegoService.getSecuazActual().getNombre() + "!");
    }

    private void mostrarMensaje(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    private void actualizarInterfaz() {
        pistasActuales = juegoService.obtenerPistasDeLocalidadSecuaz();
        if (pistasActuales.isEmpty()) {
            return; // No mostrar nada si no hay pistas disponibles
        }

        StringBuilder sb = new StringBuilder();
        sb.append("_").append(usuario.getRango()).append(" ").append(usuario.getNombre()).append(",\n");
        sb.append("Ha llegado un mensaje de ACME, prioridad alfa 6, que dice que en un lugar se ha detectado actividad de:\n");

        Secuaz secuazActual = juegoService.getSecuazActual();
        sb.append(secuazActual.getNombre()).append("\n");
        sb.append("Especialidad: ").append(secuazActual.getHabilidad()).append("\n");
        sb.append("Peligrosidad: ").append(secuazActual.getPeligrosidad()).append("\n");
        sb.append("y todo apunta a:\n\n");

        // Mostrar las pistas obtenidas de la base de datos
        for (int i = 0; i < pistasActuales.size(); i++) {
            char tecla = (char) ('H' + i);
            if (tecla == 'I') {
                tecla = 'K';
            }
            sb.append(tecla).append(": ").append(pistasActuales.get(i).getDescripcion()).append("\n");
        }
        sb.append("\nElige tu pista con las teclas H, J o K.\n");
        sb.append("\n¡Recomendamos su seguimiento y captura urgente!\n");

        textArea.setText(sb.toString());
    }

    private void asignarLocalidadSecuencial() {
        List<Localidad> localidades = juegoService.obtenerTodasLasLocalidades();
        if (localidades.isEmpty()) {
            throw new IllegalStateException("No hay localidades disponibles.");
        }
        Secuaz secuazActual = juegoService.getSecuazActual();
        Localidad nuevaLocalidad = localidades.get(localidadIndex % localidades.size());
        secuazActual.setLocalidad(nuevaLocalidad);
        juegoService.actualizarSecuaz(secuazActual);
        localidadIndex++;
    }
}
