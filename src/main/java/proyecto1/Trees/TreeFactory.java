package proyecto1.Trees;

public class TreeFactory {
    public Tree createTree(String treeType){
        if(treeType == null){
            return null;
        }else if(treeType.equalsIgnoreCase("Binary")){
            return new BinaryTree();
        }else if(treeType.equalsIgnoreCase("AVL")){
            return new AVLTree();
        }
        return null;
    }
}
