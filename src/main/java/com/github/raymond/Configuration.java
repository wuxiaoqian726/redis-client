package com.github.raymond;

/**
 * @author raymond
 */
public class Configuration {

    private static final int DEFAULT_SOCKET_TIMEOUT = 10 * 1000;
    private static final int DEFAULT_SOCKET_CONNECTIONS = 20;
    private int socketTimeout = DEFAULT_SOCKET_TIMEOUT;
    private int socketConnections = DEFAULT_SOCKET_CONNECTIONS;

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(final int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public int getSocketConnections() {
        return socketConnections;
    }

    public void setSocketConnections(final int socketConnections) {
        this.socketConnections = socketConnections;
    }
}
