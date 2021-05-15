package proyecto1.network;

import javafx.application.Platform;
import proyecto1.Imagenes.Imagenes;
import proyecto1.Usuario.LaserTask;
import proyecto1.Usuario.NaveUsuario;
import proyecto1.Ventanas.ClientWindow;
import proyecto1.Ventanas.VentanaDeJuego;
import proyecto1.protocolo.GraphicElements;
import proyecto1.protocolo.ImageWithProperties;
import proyecto1.protocolo.Protocol;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
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

            GraphicElements lastState = new GraphicElements();

            long lastSentTime = 0;


            do {

              //  List<NaveUsuario> jugadores = VentanaDeJuego.getJugadores();

                if ( System.currentTimeMillis() - lastSentTime > 200) {
                    for (ImageWithProperties element: GraphicElements.SINGLETON.getElements()) {

                        double posicionX = element.getPositionX();
                        double posicionY = element.getPositionY();

                        ImageWithProperties lastStateElement = lastState.findElement(element.getId());
                        if (lastStateElement == null || lastStateElement.getPositionX() != posicionX || lastStateElement.getPositionY() != posicionY){
                            Protocol.writeMessage(bw, Protocol.CMD_MOVE, element.getId() + " " + element.getImageType() + " " + posicionX + " " + posicionY);
                            lastStateElement = new ImageWithProperties(element.getId(), element.getImageType(), posicionX, posicionY);
                            lastState.removeElement(element.getId());
                            lastState.addElement(lastStateElement);

                        }
                    }
                    lastSentTime = System.currentTimeMillis();
                }


//
//                for (NaveUsuario naveUsuario: jugadores){
//
//                    double posicionX = naveUsuario.getPosicionX();
//                    double posicionY = naveUsuario.getPosicionY();
//                    Protocol.writeMessage(bw, Protocol.CMD_MOVE, naveUsuario.getId() + " " + posicionX + " " + posicionY);
//
//                }

                if (is.available() > 0) {
                    String[] completeCommand = Protocol.readSplitMessage(br);
                    String command = completeCommand[0];
                    //System.out.println(command);

                    switch (command) {
//                    case Protocol.CMD_OK : {
//                        break;
//                    }
//
                        case Protocol.CMD_START : {

                            String id = completeCommand[1];
                            String laserId = completeCommand[2];

                            Platform.runLater(
                                () -> {
                                    ImageWithProperties naveUsuario = GraphicElements.SINGLETON.createElement(id, Imagenes.IMG_NAVEUSUARIO);
                                    naveUsuario.move(200, 600);

                                    ImageWithProperties laser = GraphicElements.SINGLETON.createElement(laserId, Imagenes.IMG_LASER);
                                    naveUsuario.move(200, 550);
                                    laser.getImage().setVisible(false);

                                    VentanaDeJuego.getVentanaDeJuego().getChildren().add(naveUsuario.getImage());
                                    VentanaDeJuego.getVentanaDeJuego().getChildren().add(laser.getImage());
                                    GraphicElements.SINGLETON.addElement(naveUsuario);
                                    GraphicElements.SINGLETON.addElement(laser);
                                //NaveUsuario naveUsuario = new NaveUsuario(id, VentanaDeJuego.getVentanaDeJuego());


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
                        case Protocol.CMD_MOVE: {

                            String ID = completeCommand[1];
                            double posicionX = Double.parseDouble(completeCommand[2]);

                            Platform.runLater(
                                    () -> {
                                        ImageWithProperties naveUsuario = GraphicElements.SINGLETON.findElement(ID);
                                        if (naveUsuario != null) {
                                            naveUsuario.move(posicionX - 50, naveUsuario.getPositionY());
                                        }

                                    });
                        break;
                        }

                    case Protocol.CMD_SHOOT: {
                        String ID = completeCommand[1];
                        double posicionX = Double.parseDouble(completeCommand[2]);
                        System.out.println("aqui estamos intentando un shoot");
                        ImageWithProperties laser = GraphicElements.SINGLETON.findElement(ID);
                        if (laser == null ){
                            laser = GraphicElements.SINGLETON.createElement(ID, Imagenes.IMG_LASER);
                            GraphicElements.SINGLETON.addElement(laser);

                        }

                        System.out.println("la posicion del laser es" + laser.getPositionY());

                       if (laser.getPositionY() == 550.0 || laser.getPositionY() == 0.0) {
                           System.out.println("pew pew");
                            laser.setPositionX(posicionX);
                            LaserTask laserTask = new LaserTask(laser);
                            Thread laserThread = new Thread(laserTask);
                            laserThread.start();
                        }
                        break;
                    }
//
//                    default : {
//                        Protocol.writeMessage(bw, Protocol.CMD_ERROR);
//                    }

                    }
                }


            } while(socket.isBound());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}
