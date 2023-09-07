package server;

public abstract class Server {

    protected int port;

    public Server(int port) {
        this.port = port;
    }

    public abstract void makeConnection();

    public abstract void closeConnection();

    public abstract String passIncomingMessage();

    public abstract void passOutgoingMessage(String outgoingMessage);

    public abstract int getClientPort();
}
