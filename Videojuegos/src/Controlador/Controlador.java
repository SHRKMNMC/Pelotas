package Controlador;

import Modelo.Modelo;
import Modelo.Pelota;
import Modelo.PelotaControlable;
import Modelo.Habitacion;
import Vista.Vista;

import java.util.List;

public class Controlador {
    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.setControlador(this);

        // Botón agregar pelota automática
        vista.getControlPanel().addAgregarPelotaListener(e -> {
            int radio = vista.getControlPanel().getTamanioSeleccionado();
            int velocidad = vista.getControlPanel().getVelocidadSeleccionada();
            int ancho = vista.getPanelPelotas().getWidth();
            int alto = vista.getPanelPelotas().getHeight();

            modelo.inicializarHabitacion(ancho, alto);
            modelo.agregarPelota(radio, velocidad, ancho, alto);
        });

        // Botón agregar pelota controlable
        vista.getControlPanel().addAgregarControlableListener(e -> {
            int radio = vista.getControlPanel().getTamanioSeleccionado();
            int ancho = vista.getPanelPelotas().getWidth();
            int alto = vista.getPanelPelotas().getHeight();

            PelotaControlable pc = new PelotaControlable(ancho/2, alto/2, radio, java.awt.Color.RED);
            modelo.getPelotas().add(pc);
            pc.start(ancho, alto, modelo.getHabitacion(), modelo.getPelotas());

            // La vista captura el teclado
            vista.getPanelPelotas().addKeyListener(pc);
            vista.getPanelPelotas().setFocusable(true);
            vista.getPanelPelotas().requestFocus();
        });

        // Botón pausar
        vista.getControlPanel().addPausarListener(e -> {
            modelo.detenerPelotas();
            vista.getPanelPelotas().stop();
        });

        // Botón reanudar
        vista.getControlPanel().addReanudarListener(e -> {
            vista.getPanelPelotas().start();
            int ancho = vista.getPanelPelotas().getWidth();
            int alto = vista.getPanelPelotas().getHeight();

            List<Pelota> pelotas = modelo.getPelotas();
            Habitacion habitacion = modelo.getHabitacion();

            for (Pelota p : pelotas) {
                p.start(ancho, alto, habitacion, pelotas);
            }
        });
    }

    public List<Pelota> obtenerPelotas() {
        return modelo.getPelotas();
    }

    public Habitacion obtenerHabitacion() {
        return modelo.getHabitacion();
    }
}
