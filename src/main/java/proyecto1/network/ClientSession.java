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
//                String line = Protocol.readMessage(br);
//                System.out.println(line);

                String[] completeCommand = Protocol.readSplitMessage(br);
                String command = completeCommand[0];

                switch (command) {
                    case Protocol.CMD_OK -> {
                        System.out.println(command);
                        break;
                    }
                    case Protocol.CMD_CLEAR -> {
                        String ID = completeCommand[1];
                        System.out.println(command + " " + ID);
                        Protocol.writeMessage(bw, Protocol.CMD_OK, "CLEARED");
                        break;
                    }
                    case Protocol.CMD_CREATE -> {
                        String ID = completeCommand[1];
                        System.out.println(command + " " + ID);
                        Protocol.writeMessage(bw, Protocol.CMD_OK, ID + " CREATED");
                        break;
                    }
                    case Protocol.CMD_DRAW -> {
                        Protocol.writeMessage(bw, Protocol.CMD_OK, "DRAWN");
                        break;
                    }
                    case Protocol.CMD_MOVE -> {
                        Protocol.writeMessage(bw, Protocol.CMD_OK, "MOVED");
                        break;
                    }
                    default -> {
                        Protocol.writeMessage(bw, Protocol.CMD_ERROR);
                    }
                }
                
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
