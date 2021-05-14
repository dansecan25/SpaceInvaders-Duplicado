package proyecto1.protocolo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Protocol {
    // SERVER
    public static final String CMD_CLEAR = "CLEAR";
    public static final String CMD_CREATE = "CREATE"; // "CREATE ID POSX POSY"
    public static final String CMD_DRAW = "DRAW";
    public static final String CMD_MOVE = "MOVE";
    public static final String CMD_DESTROY = "DESTROY";

    // CLIENT
    public static final String CMD_START = "START";
    public static final String CMD_END = "END";
    //public static final String CMD_MOVE_LEFT = "MOVE_LEFT";
    //public static final String CMD_MOVE_RIGHT = "MOVE_RIGHT";
    public static final String CMD_SHOOT = "SHOOT";

    public static final String CMD_OK = "OK";
    public static final String CMD_ERROR = "ERROR";

    public static void writeMessage(BufferedWriter bw, String message) throws IOException {
        bw.write(message);
        bw.newLine();
        bw.flush();
    }

    public static void writeMessage(BufferedWriter bw, String message, String parameter) throws IOException {
        writeMessage(bw, message + " " + parameter);

    }

    public static String readMessage(BufferedReader br) throws IOException {
        String command = br.readLine();
        return command;
    }

    public static String[] readSplitMessage(BufferedReader br) throws IOException {
        String command = readMessage(br);
        return command.split(" ");
    }

}
