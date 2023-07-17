package UDP.Client;

import UDP.UDP_KOLO;

@SuppressWarnings("SameParameterValue")
public class ClientHandshake {

    public static void handshake() {
        taskFlag();
        informServerAboutPort();
    }

    private static void taskFlag() {
        ClientTCP.passOutgoingMessage(UDP_KOLO.FLAG);
    }

    private static void informServerAboutPort() {
        String IP = UDP_KOLO.LOCAL_IP;
        int PORT = UDP_KOLO.LOCAL_UDP_PORT;
        ClientTCP.passOutgoingMessage(IP + ":" + PORT);
    }
}
