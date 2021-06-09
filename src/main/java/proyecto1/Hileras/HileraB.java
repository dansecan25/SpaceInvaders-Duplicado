package proyecto1.Hileras;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.util.Duration;
import proyecto1.AbstractFactory.Factory;
import proyecto1.AbstractFactory.FactoryProvider;
import proyecto1.Animaciones.currentClass;
import proyecto1.Enemigos.NaveEnemiga;
import proyecto1.ListasEnlazadas.ListFactory;
import proyecto1.ListasEnlazadas.Lista;
import proyecto1.Ventanas.VentanaDeJuego;

import java.io.FileNotFoundException;

/**
 * Hilera de naves tipo B
 */
public class HileraB implements Hilera{
    private static final ListFactory listFactory = (ListFactory) FactoryProvider.getFactory("List");
    private static final Lista<NaveEnemiga> listaB;

    static {
        assert listFactory != null;
        listaB = listFactory.create("Doble");
    }

    private static NaveEnemiga naveAnt = null;
    public HileraB() {
        Group juego = VentanaDeJuego.getVentanaDeJuego();
        currentClass.setClass("B", listaB, null);
        listaB.agregarUltimo(new NaveEnemiga(110, 100, juego,0));
        listaB.agregarUltimo(new NaveEnemiga(220, 100, juego,1));
        listaB.agregarUltimo(new NaveEnemiga(330, 100, juego,2));
        listaB.agregarUltimo(new NaveEnemiga(440, 100, juego,3));
        listaB.agregarUltimo(new NaveEnemiga(550, 100, juego,4));
        cambioaBoss();
    }

    /**
     * Cambia alreatoriamente el boss
     */
    public static void cambioaBoss(){
        Timeline navesAleatorio = new Timeline(new KeyFrame(Duration.seconds(3),aleatorio ->{
            if(currentClass.getLista().tamanoLista()>0){
                int i=0;
                while(i!=currentClass.getLista().tamanoLista()-1){
                    NaveEnemiga comprobar = listaB.obtenerDato(i);
                    System.out.println("Nave a revisar: "+comprobar);
                    if(comprobar.esBoss()){
                        naveAnt = comprobar;
                        break;
                    }else i+=1;
                }
                double getRandom = Math.random()*(currentClass.getLista().tamanoLista()-1);
                int naveRandom = (int) getRandom;
                NaveEnemiga nuevoJefe = listaB.obtenerDato(naveRandom);
                nuevoJefe.toBoss();
                naveAnt.toNave();
                cambioaBoss();
            }
        }));
        navesAleatorio.setCycleCount(Timeline.INDEFINITE);
        navesAleatorio.play();
    }

}
