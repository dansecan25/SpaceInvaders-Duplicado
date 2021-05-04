package proyecto1.network;

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
                bw.write("Ingrese comando: \n");
                //dos.writeUTF("holi soy el server, ingrese un texto porfi: ");
                bw.flush();
                String command = br.readLine();
                switch (command) {
                    case "hola" : {
                        bw.write("gracias por escribir hola \n" );
                        bw.flush();
                        break;
                    }
                    case "fecha" : {
                        bw.write("hoy es" + new Date() + "\n");
                        bw.flush();
                        break;
                    }
                    default : {
                        bw.write("no se como hacer " + command + "\n");
                        bw.flush();
                    }

                }
                bw.write("usted escribio: " + command + "\n");
                bw.flush();
                //dos.writeUTF("usted escribio: " + text);
                Thread.sleep(500);
            } while(socket.isBound());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
