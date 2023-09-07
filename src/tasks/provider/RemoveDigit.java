package tasks.provider;

import runner.ServerRunner;

import java.math.BigInteger;

import static util.Logger.log;
import static util.NumberGenerator.randomBigInt;

public class RemoveDigit implements TaskProvider {

    private final int digitToRemove;

    public RemoveDigit(int digitToRemove) {
        this.digitToRemove = digitToRemove;
    }

    @Override
    public boolean provide() {
        log("[SERVER] Staring remove digit task");
        BigInteger bigIntToRemoveDigit = randomBigInt(ServerRunner.MIN, ServerRunner.MAX);
        String digitStr = Integer.toString(digitToRemove);
        String numberStr = bigIntToRemoveDigit.toString();
        numberStr = numberStr.replaceAll(digitStr, "");
        BigInteger bigIntWithRemovedDigit = new BigInteger(numberStr);

        ServerRunner.serverInstance().passOutgoingMessage(bigIntToRemoveDigit.toString());
        log("[SERVER] Expecting value: " + bigIntWithRemovedDigit);
        String message = ServerRunner.serverInstance().passIncomingMessage();
        if (!message.equals(bigIntWithRemovedDigit.toString())) {
            log("[SERVER] Remove digit task failed");
            ServerRunner.serverInstance().passOutgoingMessage("Digit not removed correctly!");
            return false;
        }
        log("[SERVER] Finished remove digit task");
        return true;
    }
}
