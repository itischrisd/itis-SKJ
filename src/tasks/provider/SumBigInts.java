package tasks.provider;

import runner.ServerRunner;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import static util.Logger.log;
import static util.NumberGenerator.randomBigInt;

public class SumBigInts implements TaskProvider {

    private final int numbersToSum;

    public SumBigInts(int numbersToSum) {
        this.numbersToSum = numbersToSum;
    }

    @Override
    public boolean provide() {
        log("[SERVER] Starting sum of Big Ints task");
        List<BigInteger> bigIntsForSum = new LinkedList<>();
        for (int i = 0; i < numbersToSum; i++)
            bigIntsForSum.add(randomBigInt(ServerRunner.MIN, ServerRunner.MAX));
        BigInteger sumToFind = BigInteger.ZERO;
        for (BigInteger number : bigIntsForSum)
            sumToFind = sumToFind.add(number);

        for (BigInteger bigInteger : bigIntsForSum)
            ServerRunner.serverInstance().passOutgoingMessage(bigInteger.toString());
        log("[SERVER] Expecting value: " + sumToFind);
        String message = ServerRunner.serverInstance().passIncomingMessage();
        if (!message.equals(sumToFind.toString())) {
            log("[SERVER] Sum of Big Ints task failed");
            ServerRunner.serverInstance().passOutgoingMessage("Incorrect sum received!");
            return false;
        }
        log("[SERVER] Finished sum of Big Ints task");
        return true;
    }
}
