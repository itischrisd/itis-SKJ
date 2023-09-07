package tasks.solver;

import runner.ClientRunner;

import static util.Logger.log;

public class ClientPort implements TaskSolver {

    private final int clientPort;

    public ClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    @Override
    public void solve() {
        log("[CLIENT] Sending client port number: " + clientPort);
        ClientRunner.clientInstance().passOutgoingMessage(String.valueOf(clientPort));
    }
}
