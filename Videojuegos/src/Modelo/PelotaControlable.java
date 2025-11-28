package Modelo;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PelotaControlable extends Pelota implements KeyListener {

    private final double aceleracionMax = 2;   // aceleración máxima por ciclo
    private final int velocidadMax = 5;       // velocidad máxima
    private double aceleracionActual = 0.3;    // empieza lenta al primer empujón

    private int ax = 0; // dirección deseada X
    private int ay = 0; // dirección deseada Y

    public PelotaControlable(int x, int y, int radio, Color color) {
        super(x, y, radio, 0, 0, color);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: ay = -1; break;
            case KeyEvent.VK_DOWN: ay = 1; break;
            case KeyEvent.VK_LEFT: ax = -1; break;
            case KeyEvent.VK_RIGHT: ax = 1; break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN: ay = 0; break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT: ax = 0; break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void actualizarVelocidad() {
        // Si hay input, aumentar aceleración gradual hasta el máximo
        if (ax != 0 || ay != 0) {
            if (aceleracionActual < aceleracionMax) {
                aceleracionActual += 0.1; // sube despacio cada ciclo
            }

            // Aplicar aceleración en X
            velocidadX += ax * aceleracionActual;
            if (velocidadX > velocidadMax) velocidadX = velocidadMax;
            if (velocidadX < -velocidadMax) velocidadX = -velocidadMax;

            // Aplicar aceleración en Y
            velocidadY += ay * aceleracionActual;
            if (velocidadY > velocidadMax) velocidadY = velocidadMax;
            if (velocidadY < -velocidadMax) velocidadY = -velocidadMax;

        } else {
            // No hay input → mantener velocidad actual (sin fricción)
            aceleracionActual = 0.2; // resetea aceleración inicial para el próximo empujón
        }
    }
}
