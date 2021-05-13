package proyecto1.Ventanas;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import proyecto1.Animaciones.Animacion;
import proyecto1.Animaciones.AnimacionClaseE;
import proyecto1.Animaciones.currentClass;
import proyecto1.Hileras.*;
import proyecto1.Imagenes.Fondo;
import proyecto1.Imagenes.Imagenes;
import proyecto1.Usuario.NaveUsuario;
import proyecto1.protocolo.ImageWithProperties;

import java.io.FileNotFoundException;

public class ClientWindow {

    private static NaveUsuario jugador;
    private static ImageView userImage = new ImageView(Imagenes.getInstancia().getNaveUsuario());
    private static ImageWithProperties user = new ImageWithProperties("p1", userImage);

    private static Stage GameStage;
    private static Stage stagePrincipal;

    public static Group ventanaDeJuego = null;

    /**
     * Iniciar ventana de juego.
     *
     * @param mainStage the main stage
     * @throws FileNotFoundException the file not found exception
     */
    public  ClientWindow(Stage mainStage) throws FileNotFoundException {

        stagePrincipal = mainStage;
        ventanaDeJuego= new Group();
        Scene gameScene = new Scene(ventanaDeJuego, 850, 700, Color.valueOf("#262934"));
        GameStage = new Stage();
        GameStage.setScene(gameScene);

        Fondo.IniciarFondo(ventanaDeJuego);
        ImageView EXIT = new ImageView(Imagenes.getInstancia().getBotonExit());

        Button botonExit = new Button();

        botonExit.setOnAction(event -> {
            GameStage.close();
            mainStage.show();
        });

        botonExit.setLayoutX(765);
        botonExit.setLayoutY(8);
        botonExit.setGraphic(EXIT);
        botonExit.setWrapText(true);

        user.move(200, 300);

        ventanaDeJuego.getChildren().add(botonExit);
       // ventanaDeJuego.getChildren().add(user);
        user.addToGameWindow(ventanaDeJuego);




        GameStage.show();
    }

    public static void createNaveUsuario(String ID){
        //NaveUsuario naveUsuario = new NaveUsuario(ID, ventanaDeJuego));
    }

    public static ImageWithProperties getUserImage(){
        return user;
    }

    public static NaveUsuario getJugador(){
        return jugador;
    }

    public static void setJugador(NaveUsuario naveJugador){
        jugador = naveJugador;
    }

    public static void terminarJuego(char condicion){
        GameStage.close();
    }

}
