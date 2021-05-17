package proyecto1.Trees;

import proyecto1.Enemigos.NaveEnemiga;

/**
 * AVL Tree data structure.
 */
public class AVLTree implements Tree{

    private Node root;

    /**
     * Constructor
     */
    public AVLTree(){ this.root = null; }

    /**
     * Checks if the tree is empty.
     * @return Boolean empty
     */
    public boolean isEmpty() { return this.root == null; }

    /**
     * Updates the height of a node.
     * @param node node
     */
    private void updateHeight(Node node){
        node.height = 1 + Math.max(height(node.left),height(node.right));
    }

    /**
     * Returns the height of a node.
     * @param node node
     * @return int height
     */
    private int height(Node node) {
        return node == null ? -1 : node.height;
    }

    /**
     * Returns the balance of a node.
     * @param node node
     * @return int balance
     */
    public int getBalance(Node node){
        return (node == null) ? 0 : height(node.right) - height(node.left);
    }

    /**
     * Finds the most left child.
     * @param node node
     * @return left
     */
    private Node mostLeftChild(Node node){
        Node current = node;
        while(current.left != null){
            current = current.left;
        }
        return current;
    }

    /**
     * Right rotation
     * @param node node
     * @return x
     */
    private Node rotateRight(Node node){
        Node x = node.left;
        Node z = node.right;
        x.right = node;
        node.left = z;
        updateHeight(node);
        updateHeight(x);
        return x;
    }

    /**
     * Left rotation
     * @param node node
     * @return x
     */
    private Node rotateLeft(Node node){
        Node x = node.right;
        Node z = x.left;
        x.left = node;
        node.right = z;
        updateHeight(node);
        updateHeight(x);
        return x;
    }

    /**
     * Rebalance a node
     * @param z node
     * @return node
     */
    private Node rebalance(Node z){
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1){
            if (height(z.right.right) <= height(z.right.left)) {
                z.right = rotateRight(z.right);
            }
            z = rotateLeft(z);
        }else if (balance < -1){
            if (height(z.left.left) <= height(z.left.right)) {
                z.left = rotateLeft(z.left);
            }
            z = rotateRight(z);
        }
        return z;
    }

    /**
     * Adds an element to the tree.
     * @param nave Ship
     */
    public void add(NaveEnemiga nave){
        this.root = this.add(root,nave);
    }
    private Node add(Node root,NaveEnemiga nave){
        if (root == null){
            return new Node(nave);
        } else if(root.element.getID() > nave.getID()){
            root.left = add(root.left,nave);
        } else if(root.element.getID() < nave.getID()){
            root.right = add(root.right,nave);
        }else{
            throw new RuntimeException("IDs de Naves duplicados.");
        }
        return rebalance(root);
    }

    /**
     * Deletes an element from the tree.
     * @param elementID int
     */
    public void delete(int elementID){
        root = this.delete(root,elementID);
    }
    private Node delete(Node root,int elementID){
        if (root == null) {
            return null;
        } else if (root.element.getID() > elementID) {
            root.left = delete(root.left, elementID);
        } else if (root.element.getID() < elementID) {
            root.right = delete(root.right, elementID);
        } else {
            if (root.left == null || root.right == null) {
                root = (root.left == null) ? root.right : root.left;
            } else {
                Node mostLeftChild = mostLeftChild(root.right);
                root.element = mostLeftChild.element;
                root.right = delete(root.right, root.element.getID());
            }
        }
        if (root != null) {
            root = rebalance(root);
        }
        return root;
    }

    /**
     * Finds an element in the tree.
     * @param elementID int
     * @return element Node
     */
    public Node find(int elementID) {
        Node current = root;
        while (current != null) {
            if (current.element.getID() == elementID) {
                break;
            }
            current = current.element.getID() < elementID ? current.right : current.left;
        }
        return current;
    }

    /**
     * Checks if an element is contained in the tree.
     * @param elementID int
     * @return isContained boolean
     */
    public boolean contains(int elementID) {
        Node current = root;
        while (current != null) {
            if (current.element.getID() == elementID) {
                return true;
            }
            current = current.element.getID() < elementID ? current.right : current.left;
        }
        return false;
    }
    /**
     * Returns the root of the tree.
     * @return root
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Returns the height of the tree.
     * @return height
     */
    public int getSize(){
        if(root == null){
            return -1;
        }
        return mostLeftChild(root).height;
    }

}