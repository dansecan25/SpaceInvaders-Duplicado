package proyecto1.Hileras;

import javafx.scene.Group;
import proyecto1.Animaciones.currentClass;
import proyecto1.Enemigos.NaveEnemiga;
import proyecto1.Trees.AVLTree;
import proyecto1.Ventanas.VentanaDeJuego;


public class AVLTreeRow implements Hilera{
    public AVLTreeRow(){
        Group gameWindow = VentanaDeJuego.getVentanaDeJuego();
        int x=380;
        int y=80;
        AVLTree AVLtree = new AVLTree();
        //Nave en el primer nivel
        AVLtree.add(new NaveEnemiga(x, y, gameWindow, 3));
        //naves en el segundo nivel
        AVLtree.add(new NaveEnemiga(x+120, y+80, gameWindow, 5));
        AVLtree.add(new NaveEnemiga(x-120, y+80, gameWindow, 1));
        //Naves en el tercer nivel
        //izquierda
        AVLtree.add(new NaveEnemiga(x-180, y+160, gameWindow, 0));
        AVLtree.add(new NaveEnemiga(x-60, y+160, gameWindow, 2));
        //derecha
        AVLtree.add(new NaveEnemiga(x+60, y+160, gameWindow, 4));
        AVLtree.add(new NaveEnemiga(x+180, y+160, gameWindow, 6));
        //Setea la clase actual
        currentClass.setClass("AVL", null, AVLtree);

    }
}
