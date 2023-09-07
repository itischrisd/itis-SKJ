package runner;


import server.Server;

import java.math.BigInteger;

public abstract class ServerRunner {

    public static final BigInteger MIN = BigInteger.valueOf(100000);
    public static final BigInteger MAX = BigInteger.valueOf(10000000);
    protected static Server serverInstance;

    public static Server serverInstance() {
        return serverInstance;
    }
}
