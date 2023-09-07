import runner.ClientRunnerTCP;
import runner.ClientRunnerUDP;
import runner.ServerRunnerTCP;
import runner.ServerRunnerUDP;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        runParallelTCP();
        runParallelUDP();

        ServerRunnerTCP.run();
        ServerRunnerUDP.run();

        ClientRunnerTCP.run();
        ClientRunnerUDP.run();
    }

    private static void runParallelTCP() {
        List<Thread> threads = new LinkedList<>();
        threads.add(new Thread(ServerRunnerTCP::run));
        threads.add(new Thread(ClientRunnerTCP::run));
        threads.forEach(Thread::start);
    }

    private static void runParallelUDP() {
        List<Thread> threads = new LinkedList<>();
        threads.add(new Thread(ServerRunnerUDP::run));
        threads.add(new Thread(ClientRunnerUDP::run));
        threads.forEach(Thread::start);
    }
}
