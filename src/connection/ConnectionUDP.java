package connection;

import java.io.IOException;
import java.net.*;

import static util.Logger.log;
import static util.Logger.logException;

public class ConnectionUDP extends Connection {

    private DatagramSocket datagramSocket;
    private InetAddress receiverIp;
    private int receiverPort;

    public ConnectionUDP(String receiverIp, int receiverPort) {
        try {
            this.receiverIp = InetAddress.getByName(receiverIp);
            this.receiverPort = receiverPort;
            this.datagramSocket = new DatagramSocket();
        } catch (UnknownHostException | SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public ConnectionUDP() {
        try {
            this.datagramSocket = new DatagramSocket();
        } catch (SocketException e) {
            logException("[ERROR] While creating datagram socket", e);
        }
    }

    @Override
    public void pushMessage(String outgoingMessage) {
        try {
            String out = outgoingMessage + '\n';
            byte[] data = out.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, receiverIp, receiverPort);
            datagramSocket.send(packet);
        } catch (IOException e) {
            logException("[ERROR] While pushing message to " + this, e);
        }
        log("[SENT] " + outgoingMessage + " --TO-- " + receiverIp + ":" + receiverPort);
    }

    @Override
    public String pullMessage() {
        String incomingMessage = "";
        try {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            datagramSocket.receive(packet);
            incomingMessage = new String(packet.getData()).trim();
            receiverIp = packet.getAddress();
            receiverPort = packet.getPort();
        } catch (IOException e) {
            logException("[ERROR] While pulling message from " + this, e);
        }
        log("[RECEIVED] " + incomingMessage + " --FROM-- " + receiverIp + ":" + receiverPort);
        return incomingMessage;
    }

    @Override
    public void close() {
        datagramSocket.close();
    }

    @Override
    public int getLocalPort() {
        return datagramSocket.getLocalPort();
    }

    @Override
    public int getReceiverPort() {
        return datagramSocket.getPort();
    }

    @Override
    public String getLocalIp() {
        return datagramSocket.getLocalAddress().getHostAddress();
    }
}
