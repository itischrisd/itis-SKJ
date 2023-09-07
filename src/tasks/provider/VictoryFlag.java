package tasks.provider;

import runner.ServerRunner;

import static util.Logger.log;

public class VictoryFlag implements TaskProvider {

    private final String victoryFlag;

    public VictoryFlag(String victoryFlag) {
        this.victoryFlag = victoryFlag;
    }

    @Override
    public boolean provide() {
        log("[SERVER] Sending victory flag");
        ServerRunner.serverInstance().passOutgoingMessage(victoryFlag);
        return true;
    }
}
