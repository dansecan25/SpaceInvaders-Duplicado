package proyecto1;

import proyecto1.network.ClientSession;

import java.io.*;
import java.net.Socket;

public class Client {
    public static String host;
    public static int port = 9000;

    public static void main(String[] args) throws IOException {
        host = "127.0.0.1";
        Socket clientSocket = new Socket(host, port);

        do{
            ClientSession clientSession = new ClientSession(clientSocket);

            Thread clientSessionThread = new Thread(clientSession);

            clientSessionThread.start();

        } while(clientSocket.isBound());

        clientSocket.close();

    }
}
