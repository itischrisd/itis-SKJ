package tasks.solver;

import runner.ClientRunner;

import java.math.BigInteger;
import java.util.LinkedList;

import static util.Logger.log;

public class SumBigInts implements TaskSolver {

    private final int numbersToSum;

    public SumBigInts(int numbersToSum) {
        this.numbersToSum = numbersToSum;
    }

    @Override
    public void solve() {
        LinkedList<BigInteger> bigIntsForSum = new LinkedList<>();
        for (int i = 0; i < numbersToSum; i++)
            bigIntsForSum.add(new BigInteger(ClientRunner.clientInstance().passIncomingMessage()));
        log("[CLIENT] Calculating sum for BigInt list: " + bigIntsForSum);
        BigInteger sum = BigInteger.ZERO;
        for (BigInteger number : bigIntsForSum)
            sum = sum.add(number);
        log("[CLIENT] Calculated sum: " + sum);
        ClientRunner.clientInstance().passOutgoingMessage(sum.toString());
    }
}
