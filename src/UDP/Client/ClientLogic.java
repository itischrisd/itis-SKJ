package UDP.Client;

import UDP.UDP_KOLO;

import java.math.BigInteger;
import java.util.LinkedList;

@SuppressWarnings("SameParameterValue")
public class ClientLogic {

    private static int CLIENT_PORT;

    public static void runClient() {
//        taskFlag();
        taskPower();
        taskPort();
        taskGCD();
        taskDigit();
        taskConcatenate();
        taskSum();
        receiveVictoryFlag();
    }

     private static void taskFlag() {
        ClientUDP.passOutgoingMessage(UDP_KOLO.FLAG);
    }

    private static void taskPower() {
        String message = ClientUDP.passIncomingMessage();
        log("Calculating biggest k for " + UDP_KOLO.POWER_FOR_K + "th power <= " + message);
        BigInteger kLimit = new BigInteger(message);
        BigInteger kToFind = BigInteger.ONE;
        while (kToFind.pow(UDP_KOLO.POWER_FOR_K).compareTo(kLimit) <= 0)
            kToFind = kToFind.add(BigInteger.ONE);
        kToFind = kToFind.subtract(BigInteger.ONE);
        log("Calculated k: " + kToFind);
        ClientUDP.passOutgoingMessage(kToFind.toString());
    }

    private static void taskPort() {
        log("Sending client port number: " + CLIENT_PORT);
        ClientUDP.passOutgoingMessage(String.valueOf(CLIENT_PORT));
    }

    private static void taskGCD() {
        LinkedList<BigInteger> bigIntsForGCD = new LinkedList<>();
        for (int i = 0; i < UDP_KOLO.NUMBERS_TO_GCD; i++)
            bigIntsForGCD.add(new BigInteger(ClientUDP.passIncomingMessage()));
        log("Calculating GCD for BigInt list: " + bigIntsForGCD);
        BigInteger gcd = bigIntsForGCD.get(0);
        for (int i = 1; i < bigIntsForGCD.size(); i++)
            gcd = gcd.gcd(bigIntsForGCD.get(i));
        log("Calculated GCD: " + gcd);
        ClientUDP.passOutgoingMessage(gcd.toString());
    }

    private static void taskDigit() {
        String message = ClientUDP.passIncomingMessage();
        String digitStr = Integer.toString(UDP_KOLO.DIGIT_TO_REMOVE);
        log("Removing digit " + digitStr + " from " + message);
        message = message.replaceAll(digitStr, "");
        log("String with removed digit: " + message);
        ClientUDP.passOutgoingMessage(message);
    }

    private static void taskConcatenate() {
        String message = ClientUDP.passIncomingMessage();
        log("Concatenating " + message + " times " + UDP_KOLO.TIMES_TO_CONCATENATE);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < UDP_KOLO.TIMES_TO_CONCATENATE; i++)
            sb.append(message);
        message = sb.toString();
        log("Concatenated: " + message);
        ClientUDP.passOutgoingMessage(message);
    }

    private static void taskSum() {
        LinkedList<BigInteger> bigIntsForSum = new LinkedList<>();
        for (int i = 0; i < UDP_KOLO.NUMBERS_TO_SUM; i++)
            bigIntsForSum.add(new BigInteger(ClientUDP.passIncomingMessage()));
        log("Calculating sum for BigInt list: " + bigIntsForSum);
        BigInteger sum = BigInteger.ZERO;
        for (BigInteger number : bigIntsForSum)
            sum = sum.add(number);
        log("Calculated sum: " + sum);
        ClientUDP.passOutgoingMessage(sum.toString());
    }

    private static void receiveVictoryFlag() {
        String message = ClientUDP.passIncomingMessage();
        log("Victory flag: " + message);
    }

    private static void log(String message) {
        System.out.println(message);
    }

    public static void setClientPort(int clientPort) {
        CLIENT_PORT = clientPort;
    }
}
