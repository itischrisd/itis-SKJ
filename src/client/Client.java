package client;

public abstract class Client {

    public abstract void makeConnection();

    public abstract void closeConnection();

    public abstract String passIncomingMessage();

    public abstract void passOutgoingMessage(String outgoingMessage);

    public abstract int getClientPort();

    public abstract String getClientIp();
}
