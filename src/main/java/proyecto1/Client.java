package proyecto1;

import javafx.application.Application;
import javafx.stage.Stage;
import proyecto1.Ventanas.*;
import proyecto1.network.ClientSession;
import javafx.application.Application;
import javafx.stage.Stage;
import proyecto1.Ventanas.VentanaPrincipal;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Application {
//    public static String host;
//    public static int port = 9000;
//    public static Socket clientSocket = null;
//    public static ClientSession clientSession = null;

    public static void main(String[] args) throws IOException {

        if (args.length > 0) {
            ClientLoadMenuWindow.host = args[0];
        }

        if (args.length > 1) {
            ClientLoadMenuWindow.port = Integer.parseInt(args[1]);
        }

        //System.out.println(args[0]);
        //System.out.println(args[1]);

//        host = "127.0.0.1";
//        clientSocket = new Socket(host, port);
//
//        System.out.println("conectando al server . . . ");
//
//        clientSession = new ClientSession(clientSocket);
//
//        Thread clientSessionThread = new Thread(clientSession);
//
//        clientSessionThread.start();
//
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("presione enter para terminar");
//
//        //String line = scanner.nextLine();
//
//        while (scanner.hasNextLine()) {
//            String line = scanner.nextLine();
//        }

        launch(args);

//        clientSocket.close();

        System.out.println("programa finalizad0");

    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        new ClientMenuWindow(primaryStage);
    }
}
