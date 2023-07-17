package TCP.Server;

import TCP.TCP_KOLO;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Random;

public class ServerLogic {

    private static final BigInteger victoryFlag = new BigInteger("37171");
    private static final int minimumGCD = 17;
    private static final BigInteger MIN = BigInteger.valueOf(100000);
    private static final BigInteger MAX = BigInteger.valueOf(10000000);
    private static final LinkedList<BigInteger> bigIntsForGCD = new LinkedList<>();
    private static final LinkedList<BigInteger> bigIntsForSum = new LinkedList<>();
    private static int CLIENT_PORT;
    private static BigInteger kLimit;
    private static BigInteger k;
    private static BigInteger gcdToFind;
    private static BigInteger bigIntToRemoveDigit;
    private static BigInteger bigIntWithRemoveDigit;
    private static BigInteger bigIntToConcatenate;
    private static String bigIntConcatenated;
    private static BigInteger sumToFind;

    public static void runService() {
        generateNumbers();
        printParameters();

        try {
            taskFlag();
            taskPower();
            taskPort();
            taskGCD();
            taskDigit();
            taskConcatenate();
            taskSum();
            sendVictory();
        } catch (InvalidArgumentException ignored) {
        }
    }

    private static void generateNumbers() {
        kLimit = randomBigInt();
        findMaxPower();

        for (int i = 0; i < TCP_KOLO.NUMBERS_TO_GCD; i++)
            bigIntsForGCD.add(randomBigInt().multiply(BigInteger.valueOf(minimumGCD)));
        findGCD();

        bigIntToRemoveDigit = randomBigInt();
        removeDigit();

        bigIntToConcatenate = randomBigInt();
        concatenateBigInteger();

        for (int i = 0; i < TCP_KOLO.NUMBERS_TO_SUM; i++)
            bigIntsForSum.add(randomBigInt());
        sumBigInts();
    }

    private static BigInteger randomBigInt() {
        Random random = new Random();
        BigInteger range = MAX.subtract(MIN);

        BigInteger randomValue;
        do {
            randomValue = new BigInteger(range.bitLength(), random);
        } while (randomValue.compareTo(range) >= 0);

        return randomValue.add(MIN);
    }

    private static void findMaxPower() {
        BigInteger kToFind = BigInteger.ONE;
        while (kToFind.pow(TCP_KOLO.POWER_FOR_K).compareTo(kLimit) <= 0)
            kToFind = kToFind.add(BigInteger.ONE);
        k = kToFind.subtract(BigInteger.ONE);
    }

    private static void findGCD() {
        BigInteger gcd = bigIntsForGCD.get(0);
        for (int i = 1; i < bigIntsForGCD.size(); i++)
            gcd = gcd.gcd(bigIntsForGCD.get(i));
        gcdToFind = gcd;
    }

    private static void removeDigit() {
        String digitStr = Integer.toString(TCP_KOLO.DIGIT_TO_REMOVE);
        String numberStr = bigIntToRemoveDigit.toString();
        numberStr = numberStr.replaceAll(digitStr, "");
        bigIntWithRemoveDigit = new BigInteger(numberStr);
    }

    private static void concatenateBigInteger() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TCP_KOLO.TIMES_TO_CONCATENATE; i++)
            sb.append(bigIntToConcatenate.toString());
        bigIntConcatenated = sb.toString();
    }

    private static void sumBigInts() {
        BigInteger sum = BigInteger.ZERO;
        for (BigInteger number : bigIntsForSum)
            sum = sum.add(number);
        sumToFind = sum;
    }

    private static void printParameters() {
        System.out.println("Parameters to solve:");

        System.out.println("Flag:\n" + TCP_KOLO.FLAG);

        System.out.println("Minimum random BigInt:\n" + MIN);
        System.out.println("Maximum random BigInt:\n" + MAX);

        System.out.println("x for k limit:\n" + kLimit);
        System.out.println("power:\n" + TCP_KOLO.POWER_FOR_K);
        System.out.println("max k:\n" + k);

        System.out.println("Amount of BigInts to GCD:\n" + TCP_KOLO.NUMBERS_TO_GCD);
        System.out.println("BigInts to GCD:");
        for (BigInteger bigInteger : bigIntsForGCD)
            System.out.println(bigInteger);
        System.out.println("GCD to find:\n" + gcdToFind);

        System.out.println("Digit to remove:\n" + TCP_KOLO.DIGIT_TO_REMOVE);
        System.out.println("BigInt to remove from:\n" + bigIntToRemoveDigit);
        System.out.println("BigInt with digit removed:\n" + bigIntWithRemoveDigit);

        System.out.println("Times to concatenate:\n" + TCP_KOLO.TIMES_TO_CONCATENATE);
        System.out.println("BigInt to concatenate:\n" + bigIntToConcatenate);
        System.out.println("BigInt concatenated:\n" + bigIntConcatenated);

        System.out.println("Amount of BigInts to sum:\n" + TCP_KOLO.NUMBERS_TO_SUM);
        System.out.println("BigInts to sum:");
        for (BigInteger bigInteger : bigIntsForSum)
            System.out.println(bigInteger);
        System.out.println("Sum to find:\n" + sumToFind);
    }

    private static void taskFlag() throws InvalidArgumentException {
        String message = Server.passIncomingMessage();
        if (!message.equals(TCP_KOLO.FLAG)) {
            Server.passOutgoingMessage("Flag not recognized!");
            throw new InvalidArgumentException(new String[]{"Incorrect flag"});
        }
    }

    private static void taskPower() throws InvalidArgumentException {
        Server.passOutgoingMessage(kLimit.toString());
        String message = Server.passIncomingMessage();
        if (!message.equals(k.toString())) {
            Server.passOutgoingMessage("Value " + message + " is not the maximum number that " + TCP_KOLO.POWER_FOR_K + "th power is <= " + kLimit + "!");
            throw new InvalidArgumentException(new String[]{"Incorrect k"});
        }
    }

    private static void taskPort() throws InvalidArgumentException {
        String message = Server.passIncomingMessage();
        if (!message.equals(Integer.toString(CLIENT_PORT))) {
            Server.passOutgoingMessage("Incorrect port number received!");
            throw new InvalidArgumentException(new String[]{"Incorrect port"});
        }
    }

    private static void taskGCD() throws InvalidArgumentException {
        for (BigInteger bigInteger : bigIntsForGCD)
            Server.passOutgoingMessage(bigInteger.toString());
        String message = Server.passIncomingMessage();
        if (!message.equals(gcdToFind.toString())) {
            Server.passOutgoingMessage("Incorrect GCD received!");
            throw new InvalidArgumentException(new String[]{"Incorrect gcd"});
        }
    }

    private static void taskDigit() throws InvalidArgumentException {
        Server.passOutgoingMessage(bigIntToRemoveDigit.toString());
        String message = Server.passIncomingMessage();
        if (!message.equals(bigIntWithRemoveDigit.toString())) {
            Server.passOutgoingMessage("Digit not removed correctly!");
            throw new InvalidArgumentException(new String[]{"Incorrect digit"});
        }
    }

    private static void taskConcatenate() throws InvalidArgumentException {
        Server.passOutgoingMessage(bigIntToConcatenate.toString());
        String message = Server.passIncomingMessage();
        if (!message.equals(bigIntConcatenated)) {
            Server.passOutgoingMessage("String not concatenated correctly!");
            throw new InvalidArgumentException(new String[]{"Incorrect concatenated"});
        }
    }

    private static void taskSum() throws InvalidArgumentException {
        for (BigInteger bigInteger : bigIntsForSum)
            Server.passOutgoingMessage(bigInteger.toString());
        String message = Server.passIncomingMessage();
        if (!message.equals(sumToFind.toString())) {
            Server.passOutgoingMessage("Incorrect sum received!");
            throw new InvalidArgumentException(new String[]{"Incorrect sum"});
        }
    }

    private static void sendVictory() {
        Server.passOutgoingMessage(String.valueOf(victoryFlag));
    }

    public static void setClientPort(int clientPort) {
        CLIENT_PORT = clientPort;
    }
}
