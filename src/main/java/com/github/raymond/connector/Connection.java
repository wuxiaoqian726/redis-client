package com.github.raymond.connector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Raymond
 */
public class Connection {

    private final Socket socket;
    private AtomicBoolean available = new AtomicBoolean(true);

    public Connection(InetSocketAddress inetSocketAddress, int timeout) {
        socket = new Socket();
        try {
            socket.bind(inetSocketAddress);
            socket.setSoTimeout(timeout);
            socket.setKeepAlive(true);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public String send(String command) {
        return "";
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    boolean available() {
        return available.get();
    }

    boolean tryToAcquire() {
        return available.compareAndSet(true, false);
    }

    void release() {
        available.set(true);
    }

}
