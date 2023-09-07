package tasks.solver;

import runner.ClientRunner;

import static util.Logger.log;

public class InformationAboutUDP implements TaskSolver {

    private final String ip;
    private final int port;

    public InformationAboutUDP(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void solve() {
        log("[CLIENT] Sending UDP address: " + ip + ":" + port);
        ClientRunner.clientInstance().passOutgoingMessage(ip + ":" + port);
    }
}
