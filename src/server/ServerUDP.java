package server;

import connection.ConnectionUDP;

public class ServerUDP extends Server {

    private final String clientIp;
    private ConnectionUDP connectionUDP;

    public ServerUDP(int clientPort, String clientIp) {
        super(clientPort);
        this.clientIp = clientIp;
    }

    @Override
    public void makeConnection() {
        connectionUDP = new ConnectionUDP(clientIp, port);
    }

    @Override
    public void closeConnection() {
        connectionUDP.close();
    }

    @Override
    public String passIncomingMessage() {
        return connectionUDP.pullMessage();
    }

    @Override
    public void passOutgoingMessage(String outgoingMessage) {
        connectionUDP.pushMessage(outgoingMessage);
    }

    @Override
    public int getClientPort() {
        return port;
    }
}