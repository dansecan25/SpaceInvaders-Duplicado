package proyecto1;

import proyecto1.network.ClientSession;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static int port = 9000;

    public static void main(String[] args) {
        System.out.println("inicializando servidor en el puerto " + port);
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);

            do{
                System.out.println("Esperando conexion . . .");

                Socket client = serverSocket.accept();

                System.out.println("Cliente conectado");

                ClientSession clientSession = new ClientSession(client);

                Thread clientSessionThread = new Thread(clientSession);

                clientSessionThread.start();
            } while(serverSocket.isBound());
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error al inicializar el Server: " + e.getMessage());
        }

        System.out.println("servidor terminado");
    }
}
