package runner;

import client.ClientTCP;
import client.ClientUDP;
import tasks.solver.*;

import java.util.LinkedList;
import java.util.List;

public class ClientRunnerUDP extends ClientRunner {

    private static final String serverIpTCP = "127.0.0.1";
    private static final int serverPortTCP = 2140;

    private static final String FLAG = "123456";
    private static final int POWER_FOR_K = 7;
    private static final int NUMBERS_TO_GCD = 5;
    private static final int DIGIT_TO_REMOVE = 9;
    private static final int TIMES_TO_CONCATENATE = 4;
    private static final int NUMBERS_TO_SUM = 5;

    public static void run() {
        clientInstance = new ClientTCP(serverIpTCP, serverPortTCP);
        clientInstance.makeConnection();
        ClientUDP clientUDP = new ClientUDP();
        clientUDP.makeConnection();

        TaskSolver flag = new StartFlag(FLAG);
        TaskSolver inform = new InformationAboutUDP(clientInstance.getClientIp(), clientUDP.getClientPort());
        flag.solve();
        inform.solve();

        clientInstance.closeConnection();
        clientInstance = clientUDP;

        List<TaskSolver> tasks = new LinkedList<>();

        tasks.add(new KthPower(POWER_FOR_K));
        tasks.add(new ClientPort(clientInstance.getClientPort()));
        tasks.add(new RemoveDigit(DIGIT_TO_REMOVE));
        tasks.add(new GreatestCommonDivisioner(NUMBERS_TO_GCD));
        tasks.add(new SumBigInts(NUMBERS_TO_SUM));
        tasks.add(new ConcatenateString(TIMES_TO_CONCATENATE));
        tasks.add(new VictoryFlag());

        tasks.forEach(TaskSolver::solve);

        clientInstance.closeConnection();
    }
}
