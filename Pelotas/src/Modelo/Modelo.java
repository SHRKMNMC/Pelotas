package Modelo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Modelo {
    private List<Pelota> pelotas;
    private Habitacion habitacion;

    public Modelo() {
        pelotas = new ArrayList<>();
    }

    public void inicializarHabitacion(int anchoPanel, int altoPanel) {
        if (habitacion == null) {
            int lado = Math.min(anchoPanel, altoPanel) / 3;
            int x = (anchoPanel - lado) / 2;
            int y = (altoPanel - lado) / 2;
            habitacion = new Habitacion(x, y, lado);
        }
    }

    public void agregarPelota(int radio, int velocidad, int anchoLimite, int altoLimite) {
        if (habitacion == null) inicializarHabitacion(anchoLimite, altoLimite);

        int x = (int) (Math.random() * (anchoLimite - radio * 2));
        int y = (int) (Math.random() * (altoLimite - radio * 2));
        int velocidadX = (int) (Math.random() * velocidad + 1) * (Math.random() < 0.5 ? 1 : -1);
        int velocidadY = (int) (Math.random() * velocidad + 1) * (Math.random() < 0.5 ? 1 : -1);
        Color color = new Color((int) (Math.random() * 256),
                (int) (Math.random() * 256),
                (int) (Math.random() * 256));

        Pelota p = new Pelota(x, y, radio, velocidadX, velocidadY, color);
        pelotas.add(p);
        p.start(anchoLimite, altoLimite, habitacion, pelotas);
    }

    public void detenerPelotas() {
        for (Pelota p : pelotas) {
            p.stop();
        }
    }

    public List<Pelota> getPelotas() {
        return pelotas;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }
}
