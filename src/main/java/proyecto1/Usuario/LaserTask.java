package proyecto1.Usuario;

import javafx.application.Platform;
import proyecto1.Imagenes.Imagenes;
import proyecto1.protocolo.GraphicElements;
import proyecto1.protocolo.ImageWithProperties;

public class LaserTask implements Runnable {
    private ImageWithProperties laser = null;
    private double posicionX;
    private  double posicionY;

    public LaserTask(ImageWithProperties laser) {
        this.laser = laser;
        posicionX = laser.getPositionX();
        posicionY = laser.getPositionY();
    }

    @Override
    public void run() {
        System.out.println("hay laser amix");
        laser.getImage().setVisible(true);
        while (posicionY >= 75) {
            Platform.runLater(
                    () -> {
                        laser.move(posicionX + 50, posicionY);
                    });
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            posicionY--;
        }
        laser.getImage().setVisible(false);
        laser.move(posicionX, 550);
        System.out.println("listisimo");
    }
}
