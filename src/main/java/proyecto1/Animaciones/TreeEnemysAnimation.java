package proyecto1.Animaciones;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.util.Duration;
import proyecto1.Trees.Node;
import proyecto1.Trees.Tree;

public class TreeEnemysAnimation {
    public static void AnimationStart(Group gameWindow){
        Tree Tree = currentClass.getTreecito();
        for(int i=0;i<6;i++){
            Node Ship = Tree.find(0);
            //Ship.get
//            TranslateTransition translateShip = new TranslateTransition();
//            translateShip.setByX(800);
//            translateShip.setDuration(Duration.millis(2000));
//            translateShip.setCycleCount(1);
//            translateShip.setAutoReverse(false);
//            translateShip.setNode(Ship.);
//            translateShip.play();
//            gameWindow.getChildren().add(Ship);
        }


    }
}
