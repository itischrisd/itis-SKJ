package tasks.solver;

import runner.ClientRunner;

import static util.Logger.log;

public class RemoveDigit implements TaskSolver {

    private final int digitToRemove;

    public RemoveDigit(int digitToRemove) {
        this.digitToRemove = digitToRemove;
    }

    @Override
    public void solve() {
        String message = ClientRunner.clientInstance().passIncomingMessage();
        String digitStr = Integer.toString(digitToRemove);
        log("[CLIENT] Removing digit " + digitStr + " from " + message);
        message = message.replaceAll(digitStr, "");
        log("[CLIENT] String with removed digit: " + message);
        ClientRunner.clientInstance().passOutgoingMessage(message);
    }
}
