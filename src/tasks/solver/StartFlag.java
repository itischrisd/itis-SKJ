package tasks.solver;

import runner.ClientRunner;

import static util.Logger.log;

public class StartFlag implements TaskSolver {

    private final String flag;

    public StartFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public void solve() {
        log("[CLIENT] Sending flag: " + flag);
        ClientRunner.clientInstance().passOutgoingMessage(flag);
    }
}
