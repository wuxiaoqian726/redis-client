package com.github.raymond.protocol;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author raymond
 */
public class ProtocolParserTest {

    ProtocolParser protocolParser = new ProtocolParser();

    @Test
    public void parseCommand() {
        Assert.assertEquals(protocolParser.parseCommand("get firstKey"), "*2\r\n$3\r\nget\r\n$8\r\nfirstKey\r\n");
    }
}
