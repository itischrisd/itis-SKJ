package UDP;

import java.io.*;
import java.net.Socket;

public class CommunicationHandlerTCP {

    private final DataOutputStream outToClient;
    private final BufferedReader inFromClient;
    private String incomingMessage;
    private String outgoingMessage;

    public CommunicationHandlerTCP(Socket socket) {
        incomingMessage = "";
        outgoingMessage = "";
        try {
            inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outToClient = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void pushMessage() {
        try {
            outToClient.writeBytes(outgoingMessage + '\n');
            outToClient.flush();
        } catch (IOException e) {
            System.err.println("Sending failed - connection closed by client");
            incomingMessage = "EXIT";
        }
    }

    public void pullMessage() {
        try {
            incomingMessage = inFromClient.readLine();
        } catch (IOException e) {
            System.err.println("Receiving failed - connection closed by client");
            incomingMessage = "EXIT";
        }
    }

    public String getIncomingMessage() {
        return incomingMessage;
    }

    public void setOutgoingMessage(String outgoingMessage) {
        this.outgoingMessage = outgoingMessage;
    }
}
