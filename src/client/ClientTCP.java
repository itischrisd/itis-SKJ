package client;

import connection.ConnectionTCP;

public class ClientTCP extends Client {

    private final String serverIp;
    private final int serverPort;
    private ConnectionTCP connectionTCP;

    public ClientTCP(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    @Override
    public void makeConnection() {
        connectionTCP = new ConnectionTCP(serverIp, serverPort);
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
        return connectionTCP.getLocalPort();
    }

    @Override
    public String getClientIp() {
        return connectionTCP.getLocalIp();
    }
}
