package com.github.raymond.connector;

import com.github.raymond.Configuration;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author raymond
 */
public class PoolConnectionManager {

    private final List<Connection> connections;
    private final Semaphore semaphore;
    private volatile boolean shutdown = false;

    public PoolConnectionManager(InetSocketAddress inetSocketAddress, Configuration configuration) {
        semaphore = new Semaphore(configuration.getSocketConnections(), true);
        connections = new ArrayList<Connection>(configuration.getSocketConnections());
        initializeConnection(inetSocketAddress, configuration);
    }

    private void initializeConnection(InetSocketAddress inetSocketAddress, Configuration configuration) {
        for (int i = 0; i < configuration.getSocketConnections(); i++) {
            connections.add(new Connection(inetSocketAddress, configuration.getSocketTimeout()));
        }
    }

    public Connection requestConnection() {
        if (this.shutdown)
            throw new IllegalStateException("connection pool already shutdown.");
        try {
            semaphore.acquire();
            Connection connection = lookupNextAvailableConnection();
            semaphore.release();
            return connection;
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    Connection lookupNextAvailableConnection() {
        for (Connection entry : connections) {
            if (entry.available() && entry.tryToAcquire())
                return entry;
        }
        throw new IllegalStateException("no available connection");
    }

    public void releaseConnection(Connection connection) {
        connection.release();
    }

    public void shutdown() {
        shutdown = true;
        for (Connection connection : connections) {
            connection.close();
        }
    }


}
