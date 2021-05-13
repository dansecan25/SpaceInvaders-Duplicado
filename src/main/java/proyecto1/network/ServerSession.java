package proyecto1.network;

import proyecto1.protocolo.Protocol;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Date;


public class ServerSession implements Runnable {
    private Socket socket;

    public ServerSession(Socket socket){
        this.socket = socket;

    }

    @Override
    public void run() {
        OutputStream os = null;
        //DataOutputStream dos;
        OutputStreamWriter osw;
        BufferedWriter bw;
        InputStream is = null;
        //DataInputStream dis;
        InputStreamReader isr;
        BufferedReader br;
        try {
            os = socket.getOutputStream();
            //dos = new DataOutputStream(os);
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);
            is = socket.getInputStream();
            //dis = new DataInputStream(is);
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);



            do {
                String[] completeCommand = Protocol.readSplitMessage(br);
                String command = completeCommand[0];
                System.out.println(command);

                switch (command) {
                    case Protocol.CMD_OK : {
                        break;
                    }

                    case Protocol.CMD_START : {
                        String ID = completeCommand[1];
                        System.out.println(command + " " + ID);
                        Protocol.writeMessage(bw, Protocol.CMD_OK, ID + " CREATED");
                        Protocol.writeMessage(bw, Protocol.CMD_CLEAR, ID);
                        Protocol.writeMessage(bw, Protocol.CMD_CREATE, ID);
                        break;
                    }

                    case Protocol.CMD_END: {
                        String ID = completeCommand[1];
                        System.out.println(command + " " + ID);
                        Protocol.writeMessage(bw, Protocol.CMD_OK, ID + " TERMINATED");
                        break;
                    }

                    case Protocol.CMD_MOVE_LEFT: {
                        Protocol.writeMessage(bw, Protocol.CMD_OK, "MOVED LEFT");
                        break;
                    }
                    case Protocol.CMD_MOVE_RIGHT: {
                        Protocol.writeMessage(bw, Protocol.CMD_OK, "MOVED RIGHT");
                        break;
                    }

                    default : {
                        Protocol.writeMessage(bw, Protocol.CMD_ERROR);
                    }

                }
            } while(socket.isBound());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}
