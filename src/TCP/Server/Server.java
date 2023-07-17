package TCP.Server;

import TCP.CommunicationHandler;
import TCP.TCP_KOLO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static Socket socket;
    private static CommunicationHandler communicationHandler;

    public static void main(String[] args) {
        makeConnection();
        provideService();
        closeConnection();
    }

    private static void makeConnection() {
        try {
            ServerSocket welcomeSocket = new ServerSocket(TCP_KOLO.TCP_SERVER_PORT);
            socket = welcomeSocket.accept();
            communicationHandler = new CommunicationHandler(socket);
            welcomeSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void provideService() {
        ServerLogic.setClientPort(socket.getPort());
        ServerLogic.runService();
    }

    public static void closeConnection() {
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