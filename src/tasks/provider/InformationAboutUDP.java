package tasks.provider;

import runner.ServerRunner;
import runner.ServerRunnerUDP;

import static util.Logger.log;

public class InformationAboutUDP implements TaskProvider {

    @Override
    public boolean provide() {
        log("[SERVER] Getting client UDP address");
        String message = ServerRunner.serverInstance().passIncomingMessage();
        String[] splitMessage = message.split(":");
        String IP = splitMessage[0];
        int PORT = Integer.parseInt(splitMessage[1]);
        ServerRunnerUDP.setClientAddress(IP, PORT);
        log("[SERVER] Finished getting UDP address");
        return true;
    }
}
