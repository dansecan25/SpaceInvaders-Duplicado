package proyecto1.network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ClientSession implements Runnable {
    private Socket socket;

    public ClientSession(Socket socket){
        this.socket = socket;

    }

    @Override
    public void run() {
        OutputStream os = null;
        try {
            os = socket.getOutputStream();
            do {
                os.write("hola soy el server\n".getBytes());
                Thread.sleep(500);
            } while(socket.isBound());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
