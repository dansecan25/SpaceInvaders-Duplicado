package proyecto1.Trees;

import proyecto1.Enemigos.NaveEnemiga;

/**
 * Tree interface.
 */
public interface Tree {
    void add(NaveEnemiga nave);
    void delete(int shipID);
    Node getRoot();
    int getBalance(Node node);
    boolean isEmpty();
    boolean contains(int shipID);
    Node find(int id);
    int getSize();
}
