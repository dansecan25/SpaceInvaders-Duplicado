package proyecto1.Enemigos;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import proyecto1.Animaciones.currentClass;
import proyecto1.Hileras.HileraC;
import proyecto1.Hileras.HileraD;
import proyecto1.Imagenes.Imagenes;
import proyecto1.Usuario.NaveUsuario;
import proyecto1.Ventanas.VentanaDeJuego;
import proyecto1.protocolo.GraphicElements;
import proyecto1.protocolo.ImageWithProperties;
import proyecto1.protocolo.Protocol;

import java.util.Random;

/**
 * The type Nave enemiga.
 */
public class NaveEnemiga {
    Random random = new Random();
    private int posicionLista;
    private final Group ventana;
    private final Timeline comprobacion;
    private final ImageWithProperties nave;
    private boolean isBoss = false;
    private int puntosMorir = 5;
    private int vida;
    private final int shipID;
    private final String imageId = Protocol.generateId();

    /**
     * Instantiates a new Nave enemiga.
     *
     * @param x     the x
     * @param y     the y
     * @param juego the juego
     */
    public NaveEnemiga(int x, int y, Group juego,int shipID) {
        this.shipID = shipID;
        posicionLista = shipID;
        nave = spriteNaveAleatorio();
        GraphicElements.SINGLETON.addElement(nave);
        nave.move(x, y);
        //nave.setId("ufos");
        juego.getChildren().add(nave.getImage());
        vida = 1;
        comprobacion = new Timeline(new KeyFrame(Duration.millis(100), event -> colision()));
        comprobarColision();
        ventana = juego;
    }

    /**
     * Retorna la vida
     * @return vida: int
     */
    public int getVida(){
        return vida;
    }

    /**
     * Returns the ship ID.
     * @return shipID int
     */
    public int getID(){
        return shipID;
    }

    /**
     * Cambia la variable que indica la posicion de la nave en la lista
     * @param posicion: int
     */
    public void setPosicionLista(int posicion){
        this.posicionLista = posicion;
    }

    /**
     * Convierte un boss en una nave normal
     */
    public void toNave(){
        isBoss = false;
        nave.setImageType(Imagenes.IMG_UFO2);
        nave.move(nave.getPositionX() + 28, nave.getPositionY());
        vida = 1;
        puntosMorir = 5;
    }

    /**
     * Convierte una nave en un boss
     */
    public void toBoss(){
        int randomBossSprite = random.nextInt(4);
        switch (randomBossSprite){
            case 1 -> nave.setImageType(Imagenes.IMG_UFOBOSS1);
            case 2 -> nave.setImageType(Imagenes.IMG_UFOBOSS2);
            case 3 -> nave.setImageType(Imagenes.IMG_UFOBOSS3);
            default -> nave.setImageType(Imagenes.IMG_UFOBOSS4);
        }

        nave.move(nave.getPositionX() - 27, nave.getPositionY());


        int randomBonusHP = random.nextInt(4) + 1;
        vida = randomBonusHP;
        puntosMorir += 5 * randomBonusHP;
        isBoss = true;
    }

    /**
     * Comprueba si la nave es un boss
     * @return isBoss: boolean
     */
    public boolean esBoss(){ return isBoss;}

    /**
     * Asigna un sprite (imagen) aleatorio a la nave
     * @return sprite: ImageView
     */
    private ImageWithProperties spriteNaveAleatorio(){
        ImageWithProperties sprite;
        int spriteID = random.nextInt(3);
        switch (spriteID) {
            case 1 -> sprite = new ImageWithProperties(imageId, Imagenes.IMG_UFO1);
            case 2 -> sprite = new ImageWithProperties(imageId, Imagenes.IMG_UFO2);
            default -> sprite = new ImageWithProperties(imageId, Imagenes.IMG_UFO3);
        }
        return sprite;
    }

    /**
     * Detecta colisiones
     */
    private void colision(){

        for(NaveUsuario naveUsuario: VentanaDeJuego.getJugadores()){
            if (!naveUsuario.getDisparo().isVisible()){
                return;
            }
            if (this.nave.getImage().getBoundsInParent().intersects(naveUsuario.getDisparo().getBoundsInParent())){
                naveUsuario.setEstadoDisparo(true);
                vida -= 1;
                if (currentClass.getClase().equals("D")){
                    HileraD.ordenarNaves();
                }
                if (vida <= 0){
                    currentClass.getLista().borrarDato(this);
                    if(currentClass.getLista().tamanoLista()>0){
                        currentClass.reordenar(posicionLista);
                    }
                    ventana.getChildren().remove(nave);
                    comprobacion.stop();
                    VentanaDeJuego.updatePuntos(puntosMorir);
                    if(isBoss && (currentClass.getClase().equals("C") || currentClass.getClase().equals("E"))){
                        HileraC.cambiarJefe();
                        //ClaseE.cambiarJefe();
                    }
                }else if(currentClass.getClase().equals("D")){
                    HileraD.ordenarNaves();
                }
            }
        }
    }

    /**
     * Inicia el timeline que comprueba colisiones
     */
    private void comprobarColision(){
        comprobacion.setCycleCount(Timeline.INDEFINITE);
        comprobacion.play();
    }

    /**
     * Retorna la imagen de la nave
     * @return nave: ImageView
     */
    public ImageView getImagenNave(){ return nave.getImage(); }

    /**
     * Mueve la nave hacia la derecha
     */
    public void moveRight(int pixels){
        Timeline movimientoDerecha = new Timeline(new KeyFrame(Duration.millis(25),mover -> {
            if(!currentClass.getStopMovement()){
                nave.setX(nave.getX()+1);
            }

        }));
        movimientoDerecha.setCycleCount(pixels);
        movimientoDerecha.play();
    }
    /**
     * Mueve la nave hacia la izquierda
     */
    public void moveLeft(int pixels){
        Timeline movimientoIzquierda = new Timeline(new KeyFrame(Duration.millis(25),mover ->{
            if(!currentClass.getStopMovement()) {
                nave.setX(nave.getX() - 1);
            }
        }));
        movimientoIzquierda.setCycleCount(pixels);
        movimientoIzquierda.play();
    }

    /**
     * Mueve la nave hacia abajo
     */
    public void moveDown(int pixels){
        Timeline movimientoAbajo = new Timeline(new KeyFrame(Duration.millis(25),mover ->{
                if(!currentClass.getStopMovement()){
                    nave.setY(nave.getY()+1);
                }
        }));
        movimientoAbajo.setCycleCount(pixels);
        movimientoAbajo.play();
    }

    public void moveUp(int pixels){
        Timeline movimientoAbajo = new Timeline(new KeyFrame(Duration.millis(25),mover ->{
            if(!currentClass.getStopMovement()){
                nave.setY(nave.getY()-1);
            }
        }));
        movimientoAbajo.setCycleCount(pixels);
        movimientoAbajo.play();
    }
}
