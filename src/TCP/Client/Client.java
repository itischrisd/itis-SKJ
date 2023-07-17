package TCP.Client;

import TCP.CommunicationHandler;
import TCP.TCP_KOLO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private static Socket socket;
    private static CommunicationHandler communicationHandler;

    public static void main(String[] args) {
        makeConnection();
        useService();
        closeConnection();
    }

    private static void makeConnection() {

        try {
            InetAddress serverAddress = InetAddress.getByName(TCP_KOLO.TCP_SERVER_IP);
            socket = new Socket(serverAddress, TCP_KOLO.TCP_SERVER_PORT);
            communicationHandler = new CommunicationHandler(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void useService() {
        ClientLogic.setClientPort(socket.getLocalPort());
        ClientLogic.runClient();
    }

    private static void closeConnection() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String passIncomingMessage() {
        communicationHandler.pullMessage();
        String message = communicationHandler.getIncomingMessage();
        System.out.println("Received message: " + message);
        return message;
    }

    public static void passOutgoingMessage(String outgoingMessage) {
        communicationHandler.setOutgoingMessage(outgoingMessage);
        communicationHandler.pushMessage();
        System.out.println("Sent message: " + outgoingMessage);
    }
}
