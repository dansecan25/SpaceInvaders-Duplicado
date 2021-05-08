package proyecto1;

import proyecto1.network.ClientSession;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
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

        String line = scanner.nextLine();

        clientSocket.close();

        System.out.println("programa finalizad0");

    }
}
