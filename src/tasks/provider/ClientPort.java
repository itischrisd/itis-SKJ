package tasks.provider;

import runner.ServerRunner;

import static util.Logger.log;

public class ClientPort implements TaskProvider {

    private final int clientPort;

    public ClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    @Override
    public boolean provide() {
        log("[SERVER] Starting client port task");
        String message = ServerRunner.serverInstance().passIncomingMessage();
        log("[SERVER] Expecting value: " + clientPort);
        if (!message.equals(Integer.toString(clientPort))) {
            log("[SERVER] Client port task failed");
            ServerRunner.serverInstance().passOutgoingMessage("Incorrect port number received!");
            return false;
        }
        log("[SERVER] Finished client port task");
        return true;
    }
}
