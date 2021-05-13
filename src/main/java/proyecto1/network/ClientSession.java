package proyecto1.network;

import proyecto1.protocolo.Protocol;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class ClientSession implements Runnable {
    Socket clientSocket;
    BufferedWriter bw;

    public ClientSession(Socket clientSocket){
        this.clientSocket = clientSocket;

    }

    @Override
    public void run() {
        OutputStream os = null;
        OutputStreamWriter osw;
        //BufferedWriter bw;
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
                //Protocol.writeMessage(bw, Protocol.CMD_START, "P1");
                String line = Protocol.readMessage(br);
                System.out.println(line);
                
            } while(clientSocket.isConnected());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    public void clientWriteMessage(String message) throws IOException {
        Protocol.writeMessage(bw, message);
    }

    public void clientWriteMessage(String message, String parameter) throws IOException {
        Protocol.writeMessage(bw, message, parameter);
    }
}
