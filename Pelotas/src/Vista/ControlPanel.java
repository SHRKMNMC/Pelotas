package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    private JButton btnAgregarPelota;
    private JButton btnAgregarControlable;
    private JButton btnPausar;
    private JButton btnReanudar;
    private JComboBox<Integer> comboTamanio;
    private JComboBox<Integer> comboVelocidad;

    public ControlPanel() {
        setLayout(new GridLayout(0, 1, 5, 5));

        btnAgregarPelota = new JButton("Agregar Pelota");
        btnAgregarControlable = new JButton("Agregar Pelota Controlable");
        btnPausar = new JButton("Pausar");
        btnReanudar = new JButton("Reanudar");

        comboTamanio = new JComboBox<>(new Integer[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100});
        comboVelocidad = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});

        add(new JLabel("Tamaño:"));
        add(comboTamanio);
        add(new JLabel("Velocidad:"));
        add(comboVelocidad);
        add(btnAgregarPelota);
        add(btnAgregarControlable);
        add(btnPausar);
        add(btnReanudar);
    }

    // Solo se exponen métodos para registrar listeners
    public void addAgregarPelotaListener(ActionListener listener) {
        btnAgregarPelota.addActionListener(listener);
    }

    public void addAgregarControlableListener(ActionListener listener) {
        btnAgregarControlable.addActionListener(listener);
    }

    public void addPausarListener(ActionListener listener) {
        btnPausar.addActionListener(listener);
    }

    public void addReanudarListener(ActionListener listener) {
        btnReanudar.addActionListener(listener);
    }

    public int getTamanioSeleccionado() {
        return (Integer) comboTamanio.getSelectedItem();
    }

    public int getVelocidadSeleccionada() {
        return (Integer) comboVelocidad.getSelectedItem();
    }
}
