package UDP.Server;

import UDP.CommunicationHandlerUDP;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerUDP {

    private static String CLIENT_IP;
    private static int CLIENT_PORT;

    private static DatagramSocket datagramSocket;
    private static CommunicationHandlerUDP communicationHandlerUDP;

    public static void setClient(String IP, int PORT) {
        CLIENT_IP = IP;
        CLIENT_PORT = PORT;
    }

    public static void makeConnection() {
        try {
            datagramSocket = new DatagramSocket();
            communicationHandlerUDP = new CommunicationHandlerUDP(datagramSocket);
            communicationHandlerUDP.setPORT(CLIENT_PORT);
            communicationHandlerUDP.setIPAddress(InetAddress.getByName(CLIENT_IP));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void provideService() {
        ServerLogic.setClientPort(CLIENT_PORT);
        ServerLogic.runService();
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
        communicationHandlerUDP.setOutgoingMessage(outgoingMessage + '\n');
        communicationHandlerUDP.pushMessage();
        System.out.println("Sent message: " + outgoingMessage);
    }
}