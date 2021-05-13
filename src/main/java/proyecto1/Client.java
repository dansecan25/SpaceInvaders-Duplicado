package proyecto1;

import javafx.application.Application;
import javafx.stage.Stage;
import proyecto1.Ventanas.VentanaDeJuego;
import proyecto1.Ventanas.VentanaPrincipal;
import proyecto1.network.ClientSession;
import javafx.application.Application;
import javafx.stage.Stage;
import proyecto1.Ventanas.VentanaPrincipal;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Application{
    public static String host;
    public static int port = 9000;

    public static void main(String[] args) throws IOException {

        host = "127.0.0.1";
        Socket clientSocket = new Socket(host, port);

        System.out.println("conectando al server . . . ");

        ClientSession clientSession = new ClientSession(clientSocket);

        Thread clientSessionThread = new Thread(clientSession);

        clientSessionThread.start();

        Scanner scanner = new Scanner(System.in);

        System.out.println("presione enter para terminar");


        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
        }

        launch(args);

        clientSocket.close();

        System.out.println("programa finalizad0");

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        VentanaPrincipal.ventana(primaryStage);
    }
}
