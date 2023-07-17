package UDP.Client;

import UDP.CommunicationHandlerTCP;
import UDP.UDP_KOLO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientTCP {

    private static Socket socket;
    private static CommunicationHandlerTCP communicationHandlerTCP;

    public static void main(String[] args) {
        makeConnection();
        handshake();
        useService();
        closeConnection();
    }

    private static void makeConnection() {
        try {
            InetAddress serverAddress = InetAddress.getByName(UDP_KOLO.TCP_SERVER_IP);
            socket = new Socket(serverAddress, UDP_KOLO.TCP_SERVER_PORT);
            communicationHandlerTCP = new CommunicationHandlerTCP(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handshake() {
        ClientHandshake.handshake();
    }

    private static void useService() {
        ClientUDP.makeConnection();
        ClientUDP.useService();
        ClientUDP.closeConnection();
    }

    private static void closeConnection() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String passIncomingMessage() {
        communicationHandlerTCP.pullMessage();
        String message = communicationHandlerTCP.getIncomingMessage();
        System.out.println("Received message: " + message);
        return message;
    }

    public static void passOutgoingMessage(String outgoingMessage) {
        communicationHandlerTCP.setOutgoingMessage(outgoingMessage);
        communicationHandlerTCP.pushMessage();
        System.out.println("Sent message: " + outgoingMessage);
    }
}
