package proyecto1.Hileras;

import javafx.scene.Group;
import proyecto1.AbstractFactory.FactoryProvider;
import proyecto1.Animaciones.TreeEnemysAnimation;
import proyecto1.Animaciones.currentClass;
import proyecto1.Enemigos.NaveEnemiga;
import proyecto1.Trees.BinaryTree;
import proyecto1.Trees.TreeFactory;
import proyecto1.Ventanas.VentanaDeJuego;

public class HileraArbolBST implements Hilera{

    public HileraArbolBST(){
        Group juego = VentanaDeJuego.getVentanaDeJuego();
        //if id parent dead, the child younger goes up becomes parent not itself anymore, other stays same
        TreeFactory factory = (TreeFactory) FactoryProvider.getFactory("Tree");
        assert factory != null;
        BinaryTree BSTtree = (BinaryTree) factory.create("Binary");
        int x=380;
        int y=80;
        //Nave en el primer nivel
        BSTtree.add(new NaveEnemiga(x, y, juego, 3));
        //naves en el segundo nivel
        BSTtree.add(new NaveEnemiga(x+120, y+80, juego, 5));
        BSTtree.add(new NaveEnemiga(x-120, y+80, juego, 1));
        //Naves en el tercer nivel
        //izquierda
        BSTtree.add(new NaveEnemiga(x-180, y+160, juego, 0));
        BSTtree.add(new NaveEnemiga(x-60, y+160, juego, 2));
        //derecha
        BSTtree.add(new NaveEnemiga(x+60, y+160, juego, 4));
        BSTtree.add(new NaveEnemiga(x+180, y+160, juego, 6));
        //Setea la clase actual
        currentClass.setClass("BST", null, BSTtree);
    }





}
