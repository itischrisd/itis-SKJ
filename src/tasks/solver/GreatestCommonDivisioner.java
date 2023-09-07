package tasks.solver;

import runner.ClientRunner;

import java.math.BigInteger;
import java.util.LinkedList;

import static util.Logger.log;

public class GreatestCommonDivisioner implements TaskSolver {

    private final int numbersToGcd;

    public GreatestCommonDivisioner(int numbersToGcd) {
        this.numbersToGcd = numbersToGcd;
    }

    @Override
    public void solve() {
        LinkedList<BigInteger> bigIntsForGCD = new LinkedList<>();
        for (int i = 0; i < numbersToGcd; i++)
            bigIntsForGCD.add(new BigInteger(ClientRunner.clientInstance().passIncomingMessage()));
        log("[CLIENT] Calculating GCD for BigInt list: " + bigIntsForGCD);
        BigInteger gcd = bigIntsForGCD.get(0);
        for (int i = 1; i < bigIntsForGCD.size(); i++)
            gcd = gcd.gcd(bigIntsForGCD.get(i));
        log("[CLIENT] Calculated GCD: " + gcd);
        ClientRunner.clientInstance().passOutgoingMessage(gcd.toString());
    }
}
