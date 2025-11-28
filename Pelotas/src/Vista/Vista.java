package Vista;

import Controlador.Controlador;

import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {
    private View panelPelotas;
    private ControlPanel controlPanel;
    private DataPanel dataPanel;
    private Controlador controlador;

    public Vista() {
        setTitle("Juego de Pelotas");
        setSize(700, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        panelPelotas = new View();
        controlPanel = new ControlPanel();
        dataPanel = new DataPanel();

        JScrollPane scrollPane = new JScrollPane(dataPanel);
        scrollPane.setPreferredSize(new Dimension(200, 100));

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1; gbc.gridheight = 1;
        gbc.weightx = 0; gbc.weighty = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        add(scrollPane, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0; gbc.weighty = 1; gbc.fill = GridBagConstraints.VERTICAL;
        add(controlPanel, gbc);

        gbc.gridx = 1; gbc.gridy = 0; gbc.gridheight = 2; gbc.weightx = 1; gbc.weighty = 1; gbc.fill = GridBagConstraints.BOTH;
        add(panelPelotas, gbc);

        setVisible(true);
        panelPelotas.start();
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
        panelPelotas.setControlador(controlador);
    }

    public View getPanelPelotas() { return panelPelotas; }
    public ControlPanel getControlPanel() { return controlPanel; }
    public DataPanel getDataPanel() { return dataPanel; }
}
