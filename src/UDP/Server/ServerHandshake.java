package UDP.Server;

import UDP.UDP_KOLO;
import com.sun.javaws.exceptions.InvalidArgumentException;

@SuppressWarnings("SameParameterValue")
public class ServerHandshake {

    public static void handshake() {
        try {
            taskFlag();
            getClientInfo();
        } catch (InvalidArgumentException ignored) {
        }
    }

    private static void taskFlag() throws InvalidArgumentException {
        String message = ServerTCP.passIncomingMessage();
        if (!message.equals(UDP_KOLO.FLAG)) {
            ServerTCP.passOutgoingMessage("Flag not recognized!");
            throw new InvalidArgumentException(new String[]{"Incorrect flag"});
        }
    }

    private static void getClientInfo() {
        String message = ServerTCP.passIncomingMessage();
        String[] splitMessage = message.split(":");
        String IP = splitMessage[0];
        int PORT = Integer.parseInt(splitMessage[1]);
        ServerUDP.setClient(IP, PORT);
    }
}
