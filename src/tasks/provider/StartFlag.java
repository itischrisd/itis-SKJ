package tasks.provider;

import runner.ServerRunner;

import static util.Logger.log;

public class StartFlag implements TaskProvider {

    private final String flag;

    public StartFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public boolean provide() {
        log("[SERVER] Starting flag task");
        log("[SERVER] Expecting value: " + flag);
        String message = ServerRunner.serverInstance().passIncomingMessage();
        if (!message.equals(flag)) {
            log("[SERVER] Flag task failed");
            ServerRunner.serverInstance().passOutgoingMessage("Flag not recognized!");
            return false;
        }
        log("[SERVER] Finished flag task");
        return true;
    }
}
