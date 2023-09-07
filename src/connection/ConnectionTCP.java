package connection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import static util.Logger.log;
import static util.Logger.logException;

public class ConnectionTCP extends Connection {

    private Socket socket;

    public ConnectionTCP(String host, int port) {
        try {
            socket = new Socket(host, port);
            inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outToClient = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            logException("[ERROR] While creating streams with " + host + ":" + port, e);
        }
        declaredAddress = socket.getInetAddress().getHostAddress() + ":" + port;
    }

    public ConnectionTCP(Socket socket) {
        this.socket = socket;
        declaredAddress = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
        try {
            inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outToClient = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            logException("[ERROR] While creating streams with " + declaredAddress, e);
        }
    }

    @Override
    public void pushMessage(String outgoingMessage) {
        try {
            outToClient.writeBytes(outgoingMessage + '\n');
            outToClient.flush();
        } catch (IOException e) {
            logException("[ERROR] While pushing message to " + this, e);
        }
        log("[SENT] " + outgoingMessage + " --TO-- " + declaredAddress);
    }

    @Override
    public String pullMessage() {
        String incomingMessage = null;
        try {
            incomingMessage = inFromClient.readLine();
        } catch (IOException e) {
            logException("[ERROR] While pulling message from " + this, e);
        }
        log("[RECEIVED] " + incomingMessage + " --FROM-- " + declaredAddress);
        return incomingMessage;
    }

    @Override
    public void close() {
        try {
            inFromClient.close();
            outToClient.close();
            socket.close();
        } catch (IOException e) {
            logException("[ERROR] While closing connection with " + this, e);
        }
    }

    @Override
    public int getLocalPort() {
        return socket.getLocalPort();
    }

    @Override
    public int getReceiverPort() {
        return socket.getPort();
    }

    public String getLocalIp() {
        return socket.getLocalAddress().getHostAddress();
    }

}
