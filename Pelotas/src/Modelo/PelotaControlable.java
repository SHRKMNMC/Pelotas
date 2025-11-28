package Modelo;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PelotaControlable extends Pelota implements KeyListener {
    private int velocidad = 5;

    public PelotaControlable(int x, int y, int radio, Color color) {
        super(x, y, radio, 0, 0, color); // empieza sin velocidad
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: setVelocidadY(-velocidad); break;
            case KeyEvent.VK_DOWN: setVelocidadY(velocidad); break;
            case KeyEvent.VK_LEFT: setVelocidadX(-velocidad); break;
            case KeyEvent.VK_RIGHT: setVelocidadX(velocidad); break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN: setVelocidadY(0); break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT: setVelocidadX(0); break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
