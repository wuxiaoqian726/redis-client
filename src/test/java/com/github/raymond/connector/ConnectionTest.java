package com.github.raymond.connector;

import org.junit.Assert;
import org.junit.Test;

import java.net.UnknownHostException;

/**
 * @author raymond
 */
public class ConnectionTest {

    String localhost = "127.0.0.1";

    @Test
    public void sendSetCommand() throws UnknownHostException {
        Connection connection = new Connection(localhost, 6379, 5000);
        String response = connection.send("*3\r\n$3\r\nSET\r\n$3\r\nkey\r\n$10\r\nfirstValue\r\n");
        Assert.assertEquals("+OK", response);
    }

    @Test
    public void sendGetCommand() throws UnknownHostException {
        Connection connection = new Connection(localhost, 6379, 5000);
        String command = "*2\r\n$3\r\nGET\r\n$3\r\nkey\r\n";
        String response = connection.send(command);
        Assert.assertEquals("$10\r\nfirstValue", response);
    }
}
