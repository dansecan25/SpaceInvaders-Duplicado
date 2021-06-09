package proyecto1.Hileras;

import javafx.scene.Group;
import proyecto1.AbstractFactory.FactoryProvider;
import proyecto1.Animaciones.currentClass;
import proyecto1.Enemigos.NaveEnemiga;
import proyecto1.ListasEnlazadas.ListFactory;
import proyecto1.ListasEnlazadas.Lista;
import proyecto1.Ventanas.VentanaDeJuego;

import java.io.FileNotFoundException;

/**
 * Hilera de naves tipo D
 */
public class HileraD implements Hilera{
    private final ListFactory listFactory = (ListFactory) FactoryProvider.getFactory("List");
    private final Lista<NaveEnemiga> listaNaves;

    {
        assert listFactory != null;
        listaNaves = listFactory.create("Doble");
    }

    public HileraD(){
        Group juego = VentanaDeJuego.getVentanaDeJuego();
        currentClass.setClass("D", listaNaves, null);
        listaNaves.agregarUltimo(new NaveEnemiga(110, 100, juego, 0));
        listaNaves.agregarUltimo(new NaveEnemiga(220, 100, juego, 1));
        listaNaves.agregarUltimo(new NaveEnemiga(330, 100, juego, 2));
        listaNaves.agregarUltimo(new NaveEnemiga(440, 100, juego, 3));
        listaNaves.agregarUltimo(new NaveEnemiga(550, 100, juego, 4));

        listaNaves.obtenerDato(0).toBoss();
        listaNaves.obtenerDato(1).toBoss();
        listaNaves.obtenerDato(2).toBoss();
        listaNaves.obtenerDato(3).toBoss();
        listaNaves.obtenerDato(4).toBoss();

        listaNaves.bubbleSort();
    }

    /**
     * Retorna la lista de naves
     * @return listaNaves: Lista<NaveEnemiga>
     */
    public Lista<NaveEnemiga> getListaNaves(){
        return listaNaves;
    }

    /**
     * Ordenar naves
     */
    public static void ordenarNaves(){
        double x;
        Lista<NaveEnemiga> lista = currentClass.getLista();
        assert lista != null;
        lista.bubbleSort();
        for (int i = 0; i < lista.tamanoLista();i++){
            if (lista.obtenerDato(i) != currentClass.getLista().obtenerDato(i)){
                x = currentClass.getLista().obtenerDato(i).getImagenNave().getX();
                lista.obtenerDato(i).setPosicionLista(i);
                lista.obtenerDato(i).getImagenNave().setX(x);
            }
        }
    }
}
