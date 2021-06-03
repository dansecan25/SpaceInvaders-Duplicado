package proyecto1.Hileras;

import javafx.scene.Group;
import proyecto1.Animaciones.TreeEnemysAnimation;
import proyecto1.Animaciones.currentClass;
import proyecto1.Enemigos.NaveEnemiga;
import proyecto1.Trees.BinaryTree;

public class BSTtreeRow {
    public BSTtreeRow(Group gameWindow){
        //if id parent dead, the child younger goes up becomes parent not itself anymore, other stays same
        BinaryTree BSTtree = new BinaryTree();
        int x=380;
        int y=80;
        //Nave en el primer nivel
        BSTtree.add(new NaveEnemiga(x, y, gameWindow, 3));
        //naves en el segundo nivel
        BSTtree.add(new NaveEnemiga(x+120, y+80, gameWindow, 5));
        BSTtree.add(new NaveEnemiga(x-120, y+80, gameWindow, 1));
        //Naves en el tercer nivel
        //izquierda
        BSTtree.add(new NaveEnemiga(x-180, y+160, gameWindow, 0));
        BSTtree.add(new NaveEnemiga(x-60, y+160, gameWindow, 2));
        //derecha
        BSTtree.add(new NaveEnemiga(x+60, y+160, gameWindow, 4));
        BSTtree.add(new NaveEnemiga(x+180, y+160, gameWindow, 6));
        //Setea la clase actual
        currentClass.setClass("BST", null, BSTtree);
    }





}
