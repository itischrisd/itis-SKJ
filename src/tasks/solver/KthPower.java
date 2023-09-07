package tasks.solver;

import runner.ClientRunner;

import java.math.BigInteger;

import static util.Logger.log;

public class KthPower implements TaskSolver {

    private final int powerForK;

    public KthPower(int powerForK) {
        this.powerForK = powerForK;
    }

    @Override
    public void solve() {
        String message = ClientRunner.clientInstance().passIncomingMessage();
        log("[CLIENT] Calculating biggest k for " + powerForK + "th power <= " + message);
        BigInteger kLimit = new BigInteger(message);
        BigInteger kToFind = BigInteger.ONE;
        while (kToFind.pow(powerForK).compareTo(kLimit) <= 0) kToFind = kToFind.add(BigInteger.ONE);
        kToFind = kToFind.subtract(BigInteger.ONE);
        log("[CLIENT] Calculated k: " + kToFind);
        ClientRunner.clientInstance().passOutgoingMessage(kToFind.toString());
    }
}
