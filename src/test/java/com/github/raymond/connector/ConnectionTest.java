package com.github.raymond.connector;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * @author raymond
 */
public class ConnectionTest {

    @Test
    public void send() throws UnknownHostException {
        Connection connection = new Connection(new InetSocketAddress("127.0.0.1", 6379), 5000);
        connection.send("*2\r\n$3\r\nget\r\n$8\r\nfirstKey\r\n");
    }
}
