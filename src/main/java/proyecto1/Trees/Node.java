package proyecto1.Trees;


import proyecto1.Enemigos.NaveEnemiga;

/**
 * Node class.
 */
public class Node {
    public NaveEnemiga element;
    public Node left;
    public Node right;
    int height;

    /**
     * Constructor 1
     * @param element element
     */
    public Node(NaveEnemiga element) {
        this(element,null,null);
    }

    /**
     * Constructor 2
     * @param element element
     * @param left left node
     * @param right right node
     */
    public Node(NaveEnemiga element, Node left, Node right){
        this.element = element;
        this.left = left;
        this.right = right;
    }



}
