package tasks.provider;

import runner.ServerRunner;

import java.math.BigInteger;

import static util.Logger.log;
import static util.NumberGenerator.randomBigInt;

public class KthPower implements TaskProvider {

    private final int powerForK;

    public KthPower(int powerForK) {
        this.powerForK = powerForK;
    }

    @Override
    public boolean provide() {
        log("[SERVER] Starting k power task");
        BigInteger kLimit = randomBigInt(ServerRunner.MIN, ServerRunner.MAX);
        BigInteger kToFind = BigInteger.ONE;
        while (kToFind.pow(powerForK).compareTo(kLimit) <= 0)
            kToFind = kToFind.add(BigInteger.ONE);
        BigInteger k = kToFind.subtract(BigInteger.ONE);

        ServerRunner.serverInstance().passOutgoingMessage(kLimit.toString());
        log("[SERVER] Expecting value: " + k);
        String message = ServerRunner.serverInstance().passIncomingMessage();
        if (!message.equals(k.toString())) {
            log("[SERVER] K power task failed");
            ServerRunner.serverInstance().passOutgoingMessage(
                    "Value " + message + " is not the maximum number that " + k + "th power is <= " + kLimit + "!");
            return false;
        }
        log("[SERVER] Finished k power task");
        return true;
    }
}
