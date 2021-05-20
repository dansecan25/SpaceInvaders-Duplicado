package proyecto1.Hileras;

import javafx.scene.Group;
import proyecto1.Enemigos.NaveEnemiga;
import proyecto1.Trees.AVLTree;
import proyecto1.Trees.BinaryTree;

public class HileraArbolBST {
    public static void IniciarAnimacion(Group juego){
        //if id parent dead, the child younger goes up becomes parent not itself anymore, other stays same
        BinaryTree arbolitoBST = new BinaryTree();
        int x=380;
        int y=80;
        //Nave en el primer nivel
        arbolitoBST.add(new NaveEnemiga(x, y, juego, 3));
        //naves en el segundo nivel
        arbolitoBST.add(new NaveEnemiga(x+120, y+80, juego, 5));
        arbolitoBST.add(new NaveEnemiga(x-120, y+80, juego, 1));
        //Naves en el tercer nivel
        //izquierda
        arbolitoBST.add(new NaveEnemiga(x-180, y+160, juego, 0));
        arbolitoBST.add(new NaveEnemiga(x-60, y+160, juego, 2));
        //derecha
        arbolitoBST.add(new NaveEnemiga(x+60, y+160, juego, 4));
        arbolitoBST.add(new NaveEnemiga(x+180, y+160, juego, 6));
        //Setea la clase actual
    }





}
