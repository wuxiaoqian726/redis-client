package com.github.raymond.connector;

import com.github.raymond.exception.RedisClientException;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

    public Connection(String host, int port, int timeout) {
        try {
            socket = new Socket(host, port);
            socket.setSoTimeout(timeout);
            socket.setKeepAlive(true);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public String send(String command) {
        String response = StringUtils.EMPTY;
        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(command.getBytes(DEFAULT_CHARSET));
            outputStream.flush();

            //TODO:how to read all the response without empty space
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            socket.getInputStream().read(bytes);
            response = new String(bytes);

            inputStream.close();

        } catch (IOException e) {
            throw new RedisClientException(e);
        }
        return response.trim();
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
