package runner;

import client.Client;

public abstract class ClientRunner {
    protected static Client clientInstance;

    public static Client clientInstance() {
        return clientInstance;
    }
}
