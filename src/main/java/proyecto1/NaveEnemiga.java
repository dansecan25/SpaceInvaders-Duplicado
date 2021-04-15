package proyecto1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * The type Nave enemiga.
 */
public class NaveEnemiga {
    int posicionLis;
    Group ventana;
    /**
     * The Nave.
     */
    ImageView nave;

    private int vida;

    /**
     * Instantiates a new Nave enemiga.
     *
     * @param x     the x
     * @param y     the y
     * @param juego the juego
     */
    public NaveEnemiga(int x, int y, Group juego,int pos) {
        this.nave = new ImageView(Imagenes.getInstancia().getUfo1());
        nave.setX(x);
        nave.setY(y);
        nave.setId("ufos");
        juego.getChildren().add(nave);
        this.posicionLis=pos;
        this.vida = 1;
        Animacion.iniciarAnimacion(this.nave);
        comprobarColision();
        ventana = juego;

    }
    public void toBoss(){
        this.nave = new ImageView(Imagenes.getInstancia().getUfoBoss());
        this.vida += 2;
    }

    private void colision(){
        if (this.nave.getBoundsInParent().intersects(VentanaDeJuego.getJugador().getDisparo().getBoundsInParent())){
            VentanaDeJuego.getJugador().setEstadoDisparo(true);
            this.vida -= 1;
            if (this.vida <= 0){
                currentClass.getLista().borrarPosicion(this.posicionLis);
                ventana.getChildren().remove(this.nave);
            }
        }
    }
    private void comprobarColision(){
        Timeline comprobacion = new Timeline(new KeyFrame(Duration.millis(100), event -> colision()));
        comprobacion.setCycleCount(Timeline.INDEFINITE);
        comprobacion.play();
    }
    public ImageView getImagenNave(){
        return nave;
    }
}
