package proyecto1.Trees;

import proyecto1.Enemigos.NaveEnemiga;

/**
 * Binary Tree data structure.
 */
public class BinaryTree implements Tree{

    private Node root;

    /**
     * Constructor
     */
    public BinaryTree(){
        this.root = null;
    }

    /**
     * Checks if the tree is empty.
     * @return Boolean empty
     */
    public boolean isEmpty(){
        return root == null;
    }

    /**
     * Checks if an element is contained in the tree
     * @param elementID NaveEnemiga
     * @return isContained Boolean
     */
    public boolean contains(int elementID){
        return this.contains(elementID,root);
    }
    private Boolean contains(int elementID, Node node){
        if (node == null){
            return false;
        }else{
            int compareResult = elementID - node.element.getID();
            if (compareResult < 0){
                return contains(elementID,node.left);
            } else if (compareResult > 0){
                return contains(elementID,node.right);
            }else{
                return true;
            }
        }
    }

    /**
     * Finds an element in the tree
     * @param elementID element
     * @return Node node
     */
    public Node find(int elementID){
        return this.find(elementID,root);
    }

    /**
     * Returns tree size
     * @return tree size
     */
    @Override
    public int getSize() {
        return getSize(root);
    }
    private int getSize(Node node){
        return node == null ? 0 : getSize(node.left) + 1 + getSize(node.right);
    }

    private Node find(int elementID, Node node){
        if (this.isEmpty()){
            throw new RuntimeException("Empty Tree");
        }else{
            int compareResult = elementID - node.element.getID();

            if (compareResult < 0){
                return find(elementID,node.left);
            } else if (compareResult > 0){
                return find(elementID,node.right);
            }else{
                return node;
            }
        }
    }

    /**
     * Adds an element to the tree.
     * @param element element
     */
    public void add(NaveEnemiga element){
        root = this.add(element,root);
    }
    private Node add(NaveEnemiga element, Node current){
        if (current == null)
            return new Node(element);

        int compareResult = element.getID() - current.element.getID();

        if (compareResult < 0)
            current.left = this.add(element,current.left);
        else if (compareResult > 0)
            current.right = this.add(element,current.right);

        return current;
    }

    /**
     * Deletes an element from the tree.
     * @param elementID element
     */
    public void delete(int elementID){
        root = this.delete(root,elementID);
    }
    private Node delete(Node node,int elementID){
        if (node == null) {
            return null;
        }

        if (elementID == node.element.getID()) {
            if (node.left == null && node.right == null) {
                return null;
            }

            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }

            Node first = findFirstNode(node.right);
            node = first;
            node.right = delete(node.right, first.element.getID());
            return node;
        }
        if (elementID < node.element.getID()) {
            node.left = delete(node.left, elementID);
            return node;
        }

        node.right = delete(node.right, elementID);
        return node;
    }

    /**
     * Finds the first node in the tree.
     * @param root root
     * @return Node first
     */
    private Node findFirstNode(Node root) {
        Node current = root;
        while (current.left != null){
            current = current.left;
        }
        return current;
    }
    public Node getRoot(){
        return root;
    }
    public int getBalance(Node node){
        return -202;
    }
}
