package proyecto1.Hileras;

import javafx.scene.Group;
import proyecto1.Animaciones.currentClass;
import proyecto1.Enemigos.NaveEnemiga;
import proyecto1.Trees.AVLTree;



public class HileraArbolAVL {
    public HileraArbolAVL(Group juego){
        //usar https://www.cs.usfca.edu/~galles/visualization/AVLtree.html para visualizar movimiento, util
        //guardar naves en NaveEnemiga
        //el AVL obtiene los ids para ordenar
        //yo moverlas respecto avl eso si
        int x=380;
        int y=80;
        AVLTree arbolAVL = new AVLTree();
        //Nave en el primer nivel
        arbolAVL.add(new NaveEnemiga(x, y, juego, 3));
        //naves en el segundo nivel
        arbolAVL.add(new NaveEnemiga(x+120, y+80, juego, 5));
        arbolAVL.add(new NaveEnemiga(x-120, y+80, juego, 1));
        //Naves en el tercer nivel
        //izquierda
        arbolAVL.add(new NaveEnemiga(x-180, y+160, juego, 0));
        arbolAVL.add(new NaveEnemiga(x-60, y+160, juego, 2));
        //derecha
        arbolAVL.add(new NaveEnemiga(x+60, y+160, juego, 4));
        arbolAVL.add(new NaveEnemiga(x+180, y+160, juego, 6));
        //Setea la clase actual
        currentClass.setClass("AVL", null, arbolAVL);

    }
}
