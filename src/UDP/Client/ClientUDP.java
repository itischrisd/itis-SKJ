package UDP.Client;

import UDP.CommunicationHandlerUDP;
import UDP.UDP_KOLO;

import java.io.IOException;
import java.net.DatagramSocket;

public class ClientUDP {

    private static DatagramSocket datagramSocket;
    private static CommunicationHandlerUDP communicationHandlerUDP;

    public static void makeConnection() {
        try {
            datagramSocket = new DatagramSocket(UDP_KOLO.LOCAL_UDP_PORT);
            communicationHandlerUDP = new CommunicationHandlerUDP(datagramSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void useService() {
        ClientLogic.setClientPort(datagramSocket.getLocalPort());
        ClientLogic.runClient();
    }

    public static void closeConnection() {
        datagramSocket.close();
    }

    public static String passIncomingMessage() {
        communicationHandlerUDP.pullMessage();
        String message = communicationHandlerUDP.getIncomingMessage();
        System.out.println("Received message: " + message);
        return message;
    }

    public static void passOutgoingMessage(String outgoingMessage) {
        communicationHandlerUDP.setOutgoingMessage(outgoingMessage);
        communicationHandlerUDP.pushMessage();
        System.out.println("Sent message: " + outgoingMessage);
    }
}
