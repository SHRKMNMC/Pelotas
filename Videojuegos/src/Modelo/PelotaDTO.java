package Modelo;

import java.awt.Color;

/**
 * DTO (Data Transfer Object) para la clase Pelota.
 * Contiene únicamente datos, sin lógica de negocio.
 *
 * Se usa para transferir el estado de una Pelota desde el Modelo
 * hacia el Controlador o la Vista sin romper el MVC estricto.
 */
public class PelotaDTO {

    private final int x;
    private final int y;
    private final int radio;
    private final int velocidadX;
    private final int velocidadY;
    private final Color color;
    private final boolean dentroHabitacion;

    public PelotaDTO(int x, int y, int radio, int velocidadX, int velocidadY,
                     Color color, boolean dentroHabitacion) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
        this.color = color;
        this.dentroHabitacion = dentroHabitacion;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getRadio() { return radio; }
    public int getVelocidadX() { return velocidadX; }
    public int getVelocidadY() { return velocidadY; }
    public Color getColor() { return color; }
    public boolean isDentroHabitacion() { return dentroHabitacion; }
}
