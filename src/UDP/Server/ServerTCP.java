package UDP.Server;

import UDP.CommunicationHandlerTCP;
import UDP.UDP_KOLO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {

    private static Socket socket;
    private static CommunicationHandlerTCP communicationHandlerTCP;

    public static void main(String[] args) {
        makeConnection();
        handshake();
        provideService();
        closeConnection();
    }

    private static void makeConnection() {
        try {
            ServerSocket welcomeSocket = new ServerSocket(UDP_KOLO.TCP_SERVER_PORT);
            socket = welcomeSocket.accept();
            communicationHandlerTCP = new CommunicationHandlerTCP(socket);
            welcomeSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handshake() {
        ServerHandshake.handshake();
    }

    private static void provideService() {
        ServerUDP.makeConnection();
        ServerUDP.provideService();
        ServerUDP.closeConnection();
    }

    public static void closeConnection() {
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