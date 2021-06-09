package proyecto1.Hileras;

import javafx.scene.Group;
import proyecto1.AbstractFactory.FactoryProvider;
import proyecto1.Animaciones.currentClass;
import proyecto1.Enemigos.NaveEnemiga;
import proyecto1.ListasEnlazadas.ListFactory;
import proyecto1.ListasEnlazadas.Lista;
import proyecto1.Ventanas.VentanaDeJuego;

/**
 * Hilera tipo C.
 */
public class HileraC implements Hilera{
    private static final ListFactory listFactory = (ListFactory) FactoryProvider.getFactory("List");
    /**
     * Iniciar clase c.
     */
    public HileraC(){
        Group juego = VentanaDeJuego.getVentanaDeJuego();
        Lista<NaveEnemiga> listaNaves = listFactory.create("Circular");
        currentClass.setClass("C", listaNaves, null);
        listaNaves.agregarUltimo(new NaveEnemiga(110, 100, juego, 0));
        listaNaves.agregarUltimo(new NaveEnemiga(220, 100, juego, 1));
        listaNaves.agregarUltimo(new NaveEnemiga(330, 100, juego, 2));
        listaNaves.agregarUltimo(new NaveEnemiga(440, 100, juego, 3));
        listaNaves.agregarUltimo(new NaveEnemiga(550, 100, juego, 4));

        NaveEnemiga naveBoss = listaNaves.obtenerDato(2);
        naveBoss.toBoss();

        //Animacion.iniciarAnimacion(listaNaves);
    }

    /**
     * Cambia el jefe en la hilera
     */
    public static void cambiarJefe(){
        assert listFactory != null;
        Lista<NaveEnemiga> lista = listFactory.create("Circular");
        lista = currentClass.getLista();

        double pos = Math.random()*lista.tamanoLista();
        int posicion =  (int) pos;
        if (lista.tamanoLista()==1){
            posicion = 0;
        }
        //arreglar naveBoss se vuelve null
        NaveEnemiga naveBoss = lista.obtenerDato(posicion);
        if(naveBoss!=null) {
            naveBoss.toBoss();
            naveBoss.setPosicionLista(posicion);
        }
    }

}
