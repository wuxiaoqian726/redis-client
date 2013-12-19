package com.github.raymond.connector;

import com.github.raymond.exception.RedisClientException;
import com.github.raymond.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Raymond
 */
public class Connection {

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private final Socket socket;
    private AtomicBoolean available = new AtomicBoolean(true);

    public Connection(InetSocketAddress inetSocketAddress, int timeout) {
        socket = new Socket();
        try {
            socket.connect(inetSocketAddress);
            socket.setSoTimeout(timeout);
            socket.setKeepAlive(true);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public String send(String command) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(command.getBytes(DEFAULT_CHARSET));
            outputStream.flush();

            InputStream inputStream = socket.getInputStream();
            IOUtils.text(inputStream);

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            throw new RedisClientException(e);
        }
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
