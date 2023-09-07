package tasks.provider;

import runner.ServerRunner;

import java.math.BigInteger;

import static util.Logger.log;
import static util.NumberGenerator.randomBigInt;

public class ConcatenateString implements TaskProvider {

    private final int timesToConcatenate;

    public ConcatenateString(int timesToConcatenate) {
        this.timesToConcatenate = timesToConcatenate;
    }

    @Override
    public boolean provide() {
        log("[SERVER] Starting string concatenation task");
        BigInteger bigIntToConcatenate = randomBigInt(ServerRunner.MIN, ServerRunner.MAX);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < timesToConcatenate; i++)
            sb.append(bigIntToConcatenate);
        String bigIntConcatenated = sb.toString();

        ServerRunner.serverInstance().passOutgoingMessage(bigIntToConcatenate.toString());
        String message = ServerRunner.serverInstance().passIncomingMessage();
        log("[SERVER] Expecting value: " + bigIntConcatenated);
        if (!message.equals(bigIntConcatenated)) {
            log("[SERVER] String concatenation task failed");
            ServerRunner.serverInstance().passOutgoingMessage("String not concatenated correctly!");
            return false;
        }
        log("[SERVER] Finished string concatenation task");
        return true;
    }
}
