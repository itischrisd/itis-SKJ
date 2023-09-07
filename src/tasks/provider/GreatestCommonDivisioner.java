package tasks.provider;

import runner.ServerRunner;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import static util.Logger.log;
import static util.NumberGenerator.randomBigInt;

public class GreatestCommonDivisioner implements TaskProvider {

    private final int numbersToGCD;
    private final BigInteger minimumGCD;

    public GreatestCommonDivisioner(int numbersToGCD, int minimumGCD) {
        this.numbersToGCD = numbersToGCD;
        this.minimumGCD = BigInteger.valueOf(minimumGCD);
    }

    @Override
    public boolean provide() {
        log("[SERVER] Starting GCD task");
        List<BigInteger> bigIntsForGCD = new LinkedList<>();
        for (int i = 0; i < numbersToGCD; i++)
            bigIntsForGCD.add(randomBigInt(ServerRunner.MIN.multiply(minimumGCD), ServerRunner.MAX.multiply(minimumGCD)));
        BigInteger gcd = bigIntsForGCD.get(0);
        for (int i = 1; i < bigIntsForGCD.size(); i++)
            gcd = gcd.gcd(bigIntsForGCD.get(i));
        BigInteger gcdToFind = gcd;

        for (BigInteger bigInteger : bigIntsForGCD)
            ServerRunner.serverInstance().passOutgoingMessage(bigInteger.toString());
        log("[SERVER] Expecting value: " + gcdToFind);
        String message = ServerRunner.serverInstance().passIncomingMessage();
        if (!message.equals(gcdToFind.toString())) {
            log("[SERVER] GCD task failed");
            ServerRunner.serverInstance().passOutgoingMessage("Incorrect GCD received!");
            return false;
        }
        log("[SERVER] Finished GCD task");
        return true;
    }
}
