package proyecto1.network;

import javafx.application.Platform;
import proyecto1.Usuario.NaveUsuario;
import proyecto1.Ventanas.VentanaDeJuego;
import proyecto1.protocolo.Protocol;

import java.io.*;
import java.net.Socket;
import java.util.List;


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

                List<NaveUsuario> jugadores = VentanaDeJuego.getJugadores();

                for (NaveUsuario naveUsuario: jugadores){

                    double posicionX = naveUsuario.getPosicionX();
                    double posicionY = naveUsuario.getPosicionY();
                    Protocol.writeMessage(bw, Protocol.CMD_MOVE, posicionX + " " + posicionY);

                }

                if (is.available() > 0) {
                    String[] completeCommand = Protocol.readSplitMessage(br);
                    String command = completeCommand[0];
                    System.out.println(command);

                    switch (command) {
//                    case Protocol.CMD_OK : {
//                        break;
//                    }
//
                        case Protocol.CMD_START : {

                            String id = completeCommand[1];

                            Platform.runLater(
                                () -> {
                                NaveUsuario naveUsuario = new NaveUsuario(id, VentanaDeJuego.getVentanaDeJuego());
                                VentanaDeJuego.addJugador(naveUsuario);
                                });



                            System.out.println("llego el id " + id);

//                        System.out.println(command + " " + ID);
//                        //ClientWindow.createNaveUsuario(ID);
//                        //Protocol.writeMessage(bw, Protocol.CMD_OK, ID + " CREATED");
//                        //Protocol.writeMessage(bw, Protocol.CMD_CLEAR, ID);
//                        Protocol.writeMessage(bw, Protocol.CMD_CREATE, "p1");
//                        Protocol.writeMessage(bw, Protocol.CMD_MOVE, "200  200");
                            break;
                        }
//
//                    case Protocol.CMD_END: {
//                        String ID = completeCommand[1];
//                        System.out.println(command + " " + ID);
//                        Protocol.writeMessage(bw, Protocol.CMD_OK, ID + " TERMINATED");
//                        break;
//                    }
//
//                    case Protocol.CMD_MOVE_LEFT: {
//                        Protocol.writeMessage(bw, Protocol.CMD_OK, "MOVED LEFT");
//                        break;
//                    }
//                    case Protocol.CMD_MOVE_RIGHT: {
//                        Protocol.writeMessage(bw, Protocol.CMD_OK, "MOVED RIGHT");
//                        break;
//                    }
//                    case Protocol.CMD_SHOOT: {
//                        Protocol.writeMessage(bw, Protocol.CMD_OK, "SHOT");
//                        break;
//                    }
//
//                    default : {
//                        Protocol.writeMessage(bw, Protocol.CMD_ERROR);
//                    }

                    }
                }

                Thread.sleep(50);
            } while(socket.isBound());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}
