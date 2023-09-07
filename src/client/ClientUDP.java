package client;

import connection.ConnectionUDP;

public class ClientUDP extends Client {

    private static ConnectionUDP connectionUDP;

    @Override
    public void makeConnection() {
        connectionUDP = new ConnectionUDP();
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
        return connectionUDP.getLocalPort();
    }

    @Override
    public String getClientIp() {
        return connectionUDP.getLocalIp();
    }
}
