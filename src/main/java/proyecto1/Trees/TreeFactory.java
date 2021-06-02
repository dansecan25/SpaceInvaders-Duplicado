package proyecto1.Trees;

import proyecto1.AbstractFactory.Factory;

public class TreeFactory implements Factory<Tree> {
    public Tree create(String treeType){
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
