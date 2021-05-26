package proyecto1.network;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import proyecto1.Ventanas.ClientWindow;
import proyecto1.protocolo.GraphicElements;
import proyecto1.protocolo.ImageWithProperties;
import proyecto1.protocolo.Protocol;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class ClientSession implements Runnable, EventHandler<MouseEvent> {
    Socket clientSocket;
    BufferedWriter bw;
    double posicionX = 0;
    double lastPosicionX = 0;
    String myId;
    String myLaserId;
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

        myId = generateId();
        myLaserId = generateId();
        long lastSentTime = 0;

        try {
            os = clientSocket.getOutputStream();
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);
            is = clientSocket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            Protocol.writeMessage(bw, Protocol.CMD_START, myId + " " + myLaserId);

            System.out.println("mi id es " + myId);

            ClientWindow.ventanaDeJuego.setOnMouseMoved(this);
            ClientWindow.ventanaDeJuego.setOnMouseClicked(this);

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
                    case Protocol.CMD_CLEAR -> {
                        GraphicElements.SINGLETON.clearElements();
                        break;
                    }
                    case Protocol.CMD_CREATE -> {
//                        String id = completeCommand[1];
//
//                        Platform.runLater(
//                                () -> {
//
//                                    ImageWithProperties naveUsuario = GraphicElements.createElement(id, Imagenes.IMG_NAVEUSUARIO);
//                                    VentanaDeJuego.getVentanaDeJuego().getChildren().add(naveUsuario.getImage());
//                                    GraphicElements.addElement(naveUsuario);
//
//
//                                });
//                        break;
                    }

//                    case Protocol.CMD_DRAW -> {
//                        Protocol.writeMessage(bw, Protocol.CMD_OK, "DRAWN");
//                        break;
//                    }
                        case Protocol.CMD_MOVE -> {

                            String id = completeCommand[1];
                            String imageType = completeCommand[2];
                            double newX = Double.parseDouble(completeCommand[3]);
                            double newY = Double.parseDouble(completeCommand[4]);


                            Platform.runLater(
                                    () -> {
                                        ImageWithProperties imageWithProperties = GraphicElements.SINGLETON.findElement(id);

                                        if (imageWithProperties == null) {
                                            System.out.println("se creo elemento");
                                            imageWithProperties = GraphicElements.SINGLETON.createElement(id, imageType);
                                            GraphicElements.SINGLETON.addElement(imageWithProperties);
                                            ClientWindow.ventanaDeJuego.getChildren().add(imageWithProperties.getImage());
                                            ClientWindow.ventanaDeJuego.getChildren().add(imageWithProperties.getIdLabel());
                                        }

                                        imageWithProperties.move(newX, newY);

                                    });

                            break;
                        }
                    }
                }
                if (lastPosicionX != posicionX && System.currentTimeMillis() - lastSentTime > 200) {
                    Protocol.writeMessage(bw, Protocol.CMD_MOVE, myId + " " + posicionX);
                    lastSentTime = System.currentTimeMillis();
                    lastPosicionX = posicionX;
                }


                //Thread.sleep(100);
            } while(clientSocket.isConnected());
//
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void handle(MouseEvent event) {
        posicionX = event.getX();
        if (event.getClickCount() > 0) {
            System.out.println("se dio click");
            try {
                Protocol.writeMessage(bw, Protocol.CMD_SHOOT, myLaserId + " " + GraphicElements.SINGLETON.findElement(myId).getPositionX());
                System.out.println("laser enviado");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //System.out.println(posicionX);
    }

    public static String generateId() {
        Random random = new Random();
        int value = random.nextInt();
        return String.valueOf(value);
    }

}
