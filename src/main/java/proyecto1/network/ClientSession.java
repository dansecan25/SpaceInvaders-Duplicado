package proyecto1.network;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class ClientSession implements Runnable {
    Socket clientSocket;

    public ClientSession(Socket clientSocket){
        this.clientSocket = clientSocket;

    }

    @Override
    public void run() {
        OutputStream os = null;
        OutputStreamWriter osw;
        BufferedWriter bw;
        InputStream is = null;
        InputStreamReader isr;
        BufferedReader br;

        try {
            os = clientSocket.getOutputStream();
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);
            is = clientSocket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            do {
                bw.write("hola");
                bw.newLine();
                bw.flush();
                String line = br.readLine();
                System.out.println(line);
                
            } while(clientSocket.isConnected());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
