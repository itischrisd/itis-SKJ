package tasks.solver;

import runner.ClientRunner;

import static util.Logger.log;

public class VictoryFlag implements TaskSolver {

    @Override
    public void solve() {
        String message = ClientRunner.clientInstance().passIncomingMessage();
        log("[CLIENT] Victory flag (without spaces): " + message);
    }
}
