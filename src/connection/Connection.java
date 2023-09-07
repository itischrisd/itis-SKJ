package connection;

import java.io.BufferedReader;
import java.io.DataOutputStream;

public abstract class Connection {

    protected String declaredAddress;
    protected DataOutputStream outToClient;
    protected BufferedReader inFromClient;

    public abstract void pushMessage(String outgoingMessage);

    public abstract String pullMessage();

    public abstract void close();

    abstract public int getLocalPort();

    abstract public int getReceiverPort();

    abstract public String getLocalIp();

    @Override
    public String toString() {
        return declaredAddress;
    }
}
