package tasks.solver;

import runner.ClientRunner;

import static util.Logger.log;

public class ConcatenateString implements TaskSolver {

    private final int timesToConcatenate;

    public ConcatenateString(int timesToConcatenate) {
        this.timesToConcatenate = timesToConcatenate;
    }

    @Override
    public void solve() {
        String message = ClientRunner.clientInstance().passIncomingMessage();
        log("[CLIENT] Concatenating " + message + " times " + timesToConcatenate);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < timesToConcatenate; i++)
            sb.append(message);
        message = sb.toString();
        log("[CLIENT] Concatenated: " + message);
        ClientRunner.clientInstance().passOutgoingMessage(message);
    }
}
