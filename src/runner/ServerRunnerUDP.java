package runner;

import server.ServerTCP;
import server.ServerUDP;
import tasks.provider.*;

import java.util.LinkedList;
import java.util.List;

public class ServerRunnerUDP extends ServerRunner {

    private static final int serverPort = 2140;

    private static final String START_FLAG = "123456";
    private static final String VICTORY_FLAG = "456789";
    private static final int POWER_FOR_K = 7;
    private static final int NUMBERS_TO_GCD = 5;
    private static final int MINIMUM_GCD = 17;
    private static final int DIGIT_TO_REMOVE = 9;
    private static final int TIMES_TO_CONCATENATE = 4;
    private static final int NUMBERS_TO_SUM = 5;

    private static String clientIp;
    private static int clientPort;

    public static void run() {
        serverInstance = new ServerTCP(serverPort);
        serverInstance.makeConnection();

        TaskProvider flag = new StartFlag(START_FLAG);
        TaskProvider inform = new InformationAboutUDP();
        flag.provide();
        inform.provide();

        serverInstance.closeConnection();
        serverInstance = new ServerUDP(clientPort, clientIp);
        serverInstance.makeConnection();

        List<TaskProvider> tasks = new LinkedList<>();

        tasks.add(new KthPower(POWER_FOR_K));
        tasks.add(new ClientPort(serverInstance.getClientPort()));
        tasks.add(new RemoveDigit(DIGIT_TO_REMOVE));
        tasks.add(new GreatestCommonDivisioner(NUMBERS_TO_GCD, MINIMUM_GCD));
        tasks.add(new SumBigInts(NUMBERS_TO_SUM));
        tasks.add(new ConcatenateString(TIMES_TO_CONCATENATE));
        tasks.add(new VictoryFlag(VICTORY_FLAG));

        for (TaskProvider task : tasks) {
            boolean passed = task.provide();
            if (!passed)
                break;
        }

        serverInstance.closeConnection();
    }

    public static void setClientAddress(String ip, int port) {
        clientIp = ip;
        clientPort = port;
    }
}
