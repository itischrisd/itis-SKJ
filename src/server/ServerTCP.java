package server;

import connection.ConnectionTCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.Logger.logException;

public class ServerTCP extends Server {

    private static ConnectionTCP connectionTCP;

    public ServerTCP(int serverPort) {
        super(serverPort);
    }

    @Override
    public void makeConnection() {
        try {
            ServerSocket welcomeSocket = new ServerSocket(port);
            Socket socket = welcomeSocket.accept();
            connectionTCP = new ConnectionTCP(socket);
            welcomeSocket.close();
        } catch (IOException e) {
            logException("[ERROR] While receiving incoming connection", e);
        }
    }

    @Override
    public void closeConnection() {
        connectionTCP.close();
    }

    @Override
    public String passIncomingMessage() {
        return connectionTCP.pullMessage();
    }

    @Override
    public void passOutgoingMessage(String outgoingMessage) {
        connectionTCP.pushMessage(outgoingMessage);
    }

    @Override
    public int getClientPort() {
        return connectionTCP.getReceiverPort();
    }
}
