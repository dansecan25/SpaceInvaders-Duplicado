package proyecto1.Hileras;

import javafx.scene.Group;
import proyecto1.AbstractFactory.Factory;
import proyecto1.Ventanas.VentanaDeJuego;
import java.util.Random;
import proyecto1.Animaciones.currentClass;

public class EnemyFactory implements Factory<Hilera> {
    Random random = new Random();
    public Hilera create(String type) {
        Group ventanaDeJuego = VentanaDeJuego.getVentanaDeJuego();
        int intType = Integer.parseInt(type);
        currentClass.setHileraE(null);
        if (intType < 30 && !VentanaDeJuego.getGamePaused()){
            int hilera = random.nextInt(6);
            System.out.println("Hilera: " + hilera);
            if (hilera == 0){ //hilera basic
                return new HileraC(); //inicia la hilera Basic
            }
            else if(hilera == 1){ //clase A
                return new HileraC(); //inicia la hilera A
            }
            else if(hilera == 2){ //clase B
                return new HileraC(); //inicia la hilera B
            }
            else if(hilera == 3){ //clase C
                return new HileraC(); //inicia la hilera C
            }
            else if (hilera == 4){ //Clase D
                return new HileraC(); //inicia la hilera D
            }
            else{
                HileraE hileraE = new HileraE(ventanaDeJuego, 330, 300); //Inicia la hilera E
                currentClass.setHileraE(hileraE);
                return hileraE;
            }
        } else if (intType >= 30 && !VentanaDeJuego.getGamePaused()){
            int arbol = random.nextInt(2);
            if (arbol == 0){
                return new AVLTreeRow();
            } else{
                return new BSTtreeRow();
            }
        }
        return null;
    }
}
