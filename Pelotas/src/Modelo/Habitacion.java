package Modelo;

import java.awt.*;

public class Habitacion {
    private int x, y, lado;
    private Pelota ocupante;

    public Habitacion(int x, int y, int lado) {
        this.x = x;
        this.y = y;
        this.lado = lado;
    }

    public synchronized boolean intentarEntrar(Pelota p) {
        if (ocupante == null) {
            ocupante = p;
            return true;
        }
        return false;
    }

    public synchronized void salir(Pelota p) {
        if (ocupante == p) {
            ocupante = null;
        }
    }

    public boolean contiene(int px, int py, int radio) {
        return (px + radio > x && px + radio < x + lado &&
                py + radio > y && py + radio < y + lado);
    }



    public int getX() { return x; }
    public int getY() { return y; }
    public int getLado() { return lado; }
}
