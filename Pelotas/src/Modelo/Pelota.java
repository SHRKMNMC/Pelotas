package Modelo;

import Modelo.PelotaDTO;
import java.awt.*;
import java.util.List;

public class Pelota implements Runnable {

    int x, y, radio;
    int velocidadX, velocidadY;
    boolean dentroHabitacion = false;

    private Color color;
    private boolean running;
    private Thread thread;

    private int anchoLimite, altoLimite;
    private Habitacion habitacion;
    private List<Pelota> pelotas;

    private final FisicaPelota fisica;

    public Pelota(int x, int y, int radio, int velocidadX, int velocidadY, Color color) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
        this.color = color;
        this.fisica = new FisicaPelota();
    }

    public void start(int ancho, int alto, Habitacion habitacion, List<Pelota> pelotas) {
        if (thread == null || !running) {
            this.anchoLimite = ancho;
            this.altoLimite = alto;
            this.habitacion = habitacion;
            this.pelotas = pelotas;

            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {
        running = false;
        if (thread != null) {
            try { thread.join(); } catch (Exception ignored) {}
            thread = null;
        }
    }

    @Override
    public void run() {
        while (running) {
            fisica.actualizar(this, anchoLimite, altoLimite, habitacion, pelotas);
            try { Thread.sleep(16); } catch (InterruptedException ignored) {}
        }
    }

    public Color getColor() { return color; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getRadio() { return radio; }
    public int getVelocidadX() { return velocidadX; }
    public int getVelocidadY() { return velocidadY; }
    public boolean isDentroHabitacion() { return dentroHabitacion; }

    public void setVelocidadX(int vx) { velocidadX = vx; }
    public void setVelocidadY(int vy) { velocidadY = vy; }

    // >>> NUEVO: CREAR UN DTO DE ESTA PELOTA <<<
    public PelotaDTO toDTO() {
        return new PelotaDTO(
                x,
                y,
                radio,
                velocidadX,
                velocidadY,
                color,
                dentroHabitacion
        );
    }
}
