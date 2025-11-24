package Vista;

import Controlador.Controlador;
import Modelo.Pelota;
import Modelo.Habitacion;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.List;

public class View extends Canvas implements Runnable {
    private Thread thread;
    private boolean running;
    private Controlador controlador;
    private BufferStrategy bufferStrategy;

    public View() {
        setBackground(Color.BLACK);
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void start() {
        if (thread == null || !running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {
        running = false;
        try {
            if (thread != null) thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread = null;
    }

    @Override
    public void run() {
        createBufferStrategy(2);
        bufferStrategy = getBufferStrategy();

        while (running) {
            if (controlador == null) continue;

            List<Pelota> pelotas = controlador.obtenerPelotas();
            Habitacion habitacion = controlador.obtenerHabitacion();

            do {
                do {
                    Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
                    try {
                        g.setColor(Color.BLACK);
                        g.fillRect(0, 0, getWidth(), getHeight());

                        if (habitacion != null) {
                            g.setColor(Color.YELLOW);
                            g.fillRect(habitacion.getX(), habitacion.getY(),
                                    habitacion.getLado(), habitacion.getLado());
                            g.setColor(Color.ORANGE);
                            g.drawRect(habitacion.getX(), habitacion.getY(),
                                    habitacion.getLado(), habitacion.getLado());
                        }

                        if (pelotas != null) {
                            for (Pelota p : pelotas) {
                                g.setColor(p.getColor());
                                int diam = p.getRadio() * 2;
                                g.fillOval(p.getX(), p.getY(), diam, diam);
                            }
                        }
                    } finally {
                        g.dispose();
                    }
                } while (bufferStrategy.contentsRestored());

                bufferStrategy.show();
                Toolkit.getDefaultToolkit().sync();
            } while (bufferStrategy.contentsLost());

            try { Thread.sleep(16); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
