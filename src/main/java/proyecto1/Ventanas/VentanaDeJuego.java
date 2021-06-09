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
import proyecto1.Animaciones.TreeEnemysAnimation;
import proyecto1.Animaciones.currentClass;
import proyecto1.Hileras.*;
import proyecto1.Imagenes.Fondo;
import proyecto1.Imagenes.Imagenes;
import proyecto1.Usuario.NaveUsuario;
import proyecto1.protocolo.GraphicElements;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Ventana de juego.
 */
public class VentanaDeJuego {
    private static boolean estado = true;
    public static int pts = 0;
    public static String clase = "Basic";
    private static final Text puntos = new Text();
    private static final Text cla = new Text();
    private static List<NaveUsuario> jugadores = new ArrayList<>();
    private static Stage GameStage;
    private static Stage stagePrincipal;
    private static int nivel = 1;
    private static Text nivelLabel = new Text();
    private static Group ventanaDeJuego;
    private static boolean gamePaused = true;
    /**
     * Iniciar ventana de juego.
     *
     * @param mainStage the main stage
     * @throws FileNotFoundException the file not found exception
     */
    public static void iniciarVentanaDeJuego(Stage mainStage) throws FileNotFoundException {
        stagePrincipal = mainStage;
        ventanaDeJuego= new Group();
        Scene gameScene = new Scene(ventanaDeJuego, 850, 700, Color.valueOf("#262934"));
        GameStage = new Stage();
        GameStage.setScene(gameScene);

        // Boton para destruir ventana secundaria
        Fondo.IniciarFondo(ventanaDeJuego);
        ImageView EXIT = new ImageView(Imagenes.getInstancia().getBotonExit());

        Button botonExit = new Button();
        botonExit.setOnAction(event -> {
            GameStage.close();
            mainStage.show();
        });
        botonExit.setLayoutX(765); //define la posicion en x del boton
        botonExit.setLayoutY(8); //posicion y
        botonExit.setGraphic(EXIT);
        botonExit.setWrapText(true);
        ventanaDeJuego.getChildren().add(botonExit);

        //setJugador(new NaveUsuario(ventanaDeJuego));
        GameStage.show(); //requerido para mostrar el stage


        new HileraC(ventanaDeJuego);
        Animacion.iniciarAnimacion(currentClass.getLista());
        setCLASE();

        crearClases(ventanaDeJuego, nivelLabel);
        String puntaje = Integer.toString(pts);
        puntos.setText(puntaje);
        puntos.setX(105);
        puntos.setY(50);
        puntos.setFill(Color.valueOf("#55d147"));
        double fontSize = 40;
        FontWeight fontWeight = FontWeight.BOLD;
        Font font1 = Font.font("Arial", fontWeight,fontSize);
        puntos.setFont(font1);
        cla.setText(clase);
        cla.setY(185);
        cla.setX(750);
        cla.setFill(Color.valueOf("#55d147"));
        double fontSize2 = 75;
        FontWeight fontWeight1 = FontWeight.BOLD;
        Font font2 = Font.font("Arial", fontWeight1,fontSize2);
        cla.setFont(font2);

        String nivelString = Integer.toString(nivel);
        nivelLabel.setText(nivelString);
        nivelLabel.setX(400);
        nivelLabel.setY(50);
        nivelLabel.setFill(Color.valueOf("#55d147"));
        nivelLabel.setFont(font1);



        ventanaDeJuego.getChildren().add(cla);
        ventanaDeJuego.getChildren().add(puntos);
        ventanaDeJuego.getChildren().add(nivelLabel);
    }
    private static void generarHilera(Group ventanaDeJuego){
        if (!estado) {
            Random rng = new Random();
            int hilera = rng.nextInt(4);
            System.out.println("Hilera: "+hilera);
            if (hilera == 0){ //hilera basic
                HileraArbolAVL naves = new HileraArbolAVL(ventanaDeJuego); //inicia la hilera Basic
                TreeEnemysAnimation.AnimationStart(ventanaDeJuego);
                setCLASE();
            }
            else if(hilera == 1){ //clase B
                HileraArbolBST.IniciarAnimacion(ventanaDeJuego); //inicia la hilera B
                TreeEnemysAnimation.AnimationStart(ventanaDeJuego);
                setCLASE();
            }
            else if(hilera == 2){ //clase C
                try {
                    new HileraC(ventanaDeJuego); //inicia la hilera C
                    Animacion.iniciarAnimacion(currentClass.getLista());
                    setCLASE();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    HileraE hileraE = new HileraE(ventanaDeJuego, 330, 300); //Inicia la hilera E
                    AnimacionClaseE animacionClaseE = new AnimacionClaseE(hileraE);
                    animacionClaseE.iniciarAnimacion();
                    setCLASE();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * Crear clases.
     *
     * @param ventanaDeJuego the ventana de juego
     */
    public static void crearClases(Group ventanaDeJuego, Text nivelLabel){
        //Hilo para generar las clases
        Timeline clasesAleatoriedad = new Timeline(new KeyFrame(Duration.seconds(1),a->{
                if (pts>5){
                    nivel = 2;
                }
                if (pts>=100){
                    nivel = 3;
                }
                if (pts>=200){
                    nivel = 4;
                }
                if (pts>=300){
                    nivel = 5;
                }
            String nivelString = Integer.toString(nivel);
            nivelLabel.setText(nivelString);
            Fondo.setFondo(nivel);
            cambiarNivel(nivel);

            if (currentClass.getLista() != null) {
                estado = true; //hay enemigos en la ventana
            }else{
                estado=false;
                generarHilera(ventanaDeJuego);
            }
        }));
        clasesAleatoriedad.setCycleCount(Timeline.INDEFINITE);
        clasesAleatoriedad.play();
    }
    public static List<NaveUsuario> getJugadores(){
        return jugadores;
    }
    public static void addJugador(NaveUsuario naveJugador){
        jugadores.add(naveJugador);
    }
    public static void updatePuntos(int suma){
        pts = pts+suma;
        String puntaje = Integer.toString(pts);
        puntos.setText(puntaje);
    }
    public static void setCLASE(){
        cla.setText(currentClass.getClase());
    }
    public static void cambiarNivel(int nivel){
        currentClass.setNivel(nivel);
    }
    public static void terminarJuego(char condicion){
        //Llamar ventana game over
        GameStage.close();
        stagePrincipal.show();
    }

    public static Group getVentanaDeJuego() {
        return ventanaDeJuego;
    }

    public static boolean getGamePaused() {
        return gamePaused;
    }

    public static void setGamePaused(boolean state) {
        gamePaused = state;
    }

    public static boolean isEstado() {
        return estado;
    }

    public static int getPts() {
        return pts;
    }

    public static String getClase() {
        return clase;
    }

    public static Text getPuntos() {
        return puntos;
    }

    public static Text getCla() {
        return cla;
    }

    public static Stage getGameStage() {
        return GameStage;
    }

    public static Stage getStagePrincipal() {
        return stagePrincipal;
    }

    public static int getNivel() {
        return nivel;
    }

    public static Text getNivelLabel() {
        return nivelLabel;
    }

    public static boolean isGamePaused() {
        return gamePaused;
    }
}
