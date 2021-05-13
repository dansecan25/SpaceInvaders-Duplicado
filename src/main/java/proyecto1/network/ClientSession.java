package proyecto1.network;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import proyecto1.Imagenes.Imagenes;
import proyecto1.Usuario.NaveUsuario;
import proyecto1.Ventanas.ClientWindow;
import proyecto1.Ventanas.VentanaDeJuego;
import proyecto1.protocolo.ImageWithProperties;
import proyecto1.protocolo.Protocol;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class ClientSession implements Runnable {
    Socket clientSocket;
    BufferedWriter bw;
    //public ImageView user;

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

            Protocol.writeMessage(bw, Protocol.CMD_START, "P1");

            System.out.println(" se envio comando start");

            do {
//                String line = Protocol.readMessage(br);
//                System.out.println(line);

                if (is.available() > 0) {
                    String[] completeCommand = Protocol.readSplitMessage(br);
                    String command = completeCommand[0];

                    switch (command) {
//                    case Protocol.CMD_OK -> {
//                        System.out.println(command);
//                        break;
//                    }
//                    case Protocol.CMD_CLEAR -> {
//                        String ID = completeCommand[1];
//                        System.out.println(command + " " + ID);
//                        Protocol.writeMessage(bw, Protocol.CMD_OK, "CLEARED");
//                        break;
//                    }
//                    case Protocol.CMD_CREATE -> {
//                        String ID = completeCommand[1];
//                        System.out.println(command + " " + ID);
//                        Platform.runLater(
//                                () -> {
//                                    user = new ImageView(Imagenes.getInstancia().getNaveUsuario());
//                                    user.setX(300);
//                                    user.setY(300);
//                                    ClientWindow.ventanaDeJuego.getChildren().add(user);
//                                }
//                        );
//                        Protocol.writeMessage(bw, Protocol.CMD_OK, ID + " CREATED");
//                        break;
//                    }
//                    case Protocol.CMD_DRAW -> {
//                        Protocol.writeMessage(bw, Protocol.CMD_OK, "DRAWN");
//                        break;
//                    }
                        case Protocol.CMD_MOVE -> {
                            double newX = Double.parseDouble(completeCommand[1]);
                            double newY = Double.parseDouble(completeCommand[2]);

                            ImageWithProperties user = ClientWindow.getUserImage();
                            user.move(newX - 50, newY);
                            break;
//                    }
//                    default -> {
//                        Protocol.writeMessage(bw, Protocol.CMD_ERROR);
                        }
                    }
                }
            } while(clientSocket.isConnected());
//
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
