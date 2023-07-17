package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class CommunicationHandlerUDP {

    private final DatagramSocket datagramSocket;
    private String incomingMessage;
    private String outgoingMessage;
    private byte[] receiveData = new byte[1024];
    private InetAddress IPAddress;
    private int PORT;

    public CommunicationHandlerUDP(DatagramSocket datagramSocket) {
        incomingMessage = "";
        outgoingMessage = "";
        this.datagramSocket = datagramSocket;
    }

    public void pushMessage() {
        try {
            byte[] sendData = outgoingMessage.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, PORT);
            datagramSocket.send(sendPacket);
        } catch (IOException e) {
            System.err.println("Sending failed - connection closed by client");
            incomingMessage = "EXIT";
        }
    }

    public void pullMessage() {
        try {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            datagramSocket.receive(receivePacket);
            incomingMessage = new String(receivePacket.getData()).trim();
            IPAddress = receivePacket.getAddress();
            PORT = receivePacket.getPort();
            receiveData = new byte[1024];
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

    public void setIPAddress(InetAddress IPAddress) {
        this.IPAddress = IPAddress;
    }

    public void setPORT(int PORT) {
        this.PORT = PORT;
    }
}
