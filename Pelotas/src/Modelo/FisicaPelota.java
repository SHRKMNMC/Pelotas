package Modelo;

import java.util.List;

public class FisicaPelota {

    public void actualizar(Pelota p, int anchoLimite, int altoLimite,
                           Habitacion habitacion, List<Pelota> todas) {

        mover(p, anchoLimite, altoLimite);
        manejarHabitacion(p, habitacion);
        manejarColisiones(p, todas);
    }

    private void mover(Pelota p, int ancho, int alto) {
        p.x += p.velocidadX;
        p.y += p.velocidadY;

        // Colisión con bordes
        if (p.x < 0) { p.x = 0; p.velocidadX *= -1; }
        else if (p.x + p.radio * 2 > ancho) {
            p.x = ancho - p.radio * 2;
            p.velocidadX *= -1;
        }

        if (p.y < 0) { p.y = 0; p.velocidadY *= -1; }
        else if (p.y + p.radio * 2 > alto) {
            p.y = alto - p.radio * 2;
            p.velocidadY *= -1;
        }
    }

    private void manejarHabitacion(Pelota p, Habitacion habitacion) {
        if (habitacion == null) return;

        boolean tocando = habitacion.contiene(p.x, p.y, p.radio);

        if (tocando && !p.dentroHabitacion) {
            if (habitacion.intentarEntrar(p)) {
                p.dentroHabitacion = true;
            } else {
                p.velocidadX *= -1;
                p.velocidadY *= -1;
                p.x += p.velocidadX * 2;
                p.y += p.velocidadY * 2;
            }
        } else if (!tocando && p.dentroHabitacion) {
            habitacion.salir(p);
            p.dentroHabitacion = false;
        }
    }

    private void manejarColisiones(Pelota p, List<Pelota> pelotas) {
        if (pelotas == null) return;

        for (Pelota otra : pelotas) {
            if (otra == p) continue;

            int dx = (otra.x + otra.radio) - (p.x + p.radio);
            int dy = (otra.y + otra.radio) - (p.y + p.radio);

            double distancia = Math.sqrt(dx * dx + dy * dy);
            if (distancia < p.radio + otra.radio) {

                // Intercambiar velocidades
                int tempX = p.velocidadX;
                int tempY = p.velocidadY;
                p.velocidadX = otra.velocidadX;
                p.velocidadY = otra.velocidadY;
                otra.velocidadX = tempX;
                otra.velocidadY = tempY;

                // Separar para evitar solapamiento
                double overlap = 0.5 * (p.radio + otra.radio - distancia + 1);
                p.x -= (int) (overlap * dx / distancia);
                p.y -= (int) (overlap * dy / distancia);
                otra.x += (int) (overlap * dx / distancia);
                otra.y += (int) (overlap * dy / distancia);
            }
        }
    }
}
