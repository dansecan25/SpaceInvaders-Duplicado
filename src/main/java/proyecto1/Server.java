package proyecto1;

import proyecto1.network.ServerSession;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    public static int port = 9000;
    public static ServerSocket serverSocket;


    public static void startServer() {

        Server server = new Server();

        Thread serverThread = new Thread(server);

        serverThread.start();

    }

    public static void stopServer() throws IOException {
        System.out.println("servidor terminado");
        serverSocket.close();
    }

    public static void main(String[] args) {
        startServer();

    }

    @Override
    public void run() {

        System.out.println("inicializando servidor en el puerto " + port);

        try {
            serverSocket = new ServerSocket(port);

            do{
                System.out.println("Esperando conexion . . .");

                Socket client = serverSocket.accept();

                System.out.println("Cliente conectado");

                ServerSession serverSession = new ServerSession(client);

                Thread serverSessionThread = new Thread(serverSession);

                serverSessionThread.start();
            } while(serverSocket.isBound());

            stopServer();

        } catch (IOException e) {
            System.out.println("Error al inicializar el Server: " + e.getMessage());
        }
    }
}
