package com.github.raymond;

import com.github.raymond.connector.Connection;
import com.github.raymond.connector.PoolConnectionManager;
import com.github.raymond.protocol.ProtocolParser;

/**
 * @author raymond
 */
public class CommandExecutor {

    private ProtocolParser protocolParser;
    private PoolConnectionManager poolConnectionManager;

    public Object execute(String command) {
        Connection connection = poolConnectionManager.requestConnection();
        String response = connection.send(protocolParser.parseCommand(command));
        poolConnectionManager.releaseConnection(connection);
        return protocolParser.parseResponse(response);
    }

    public void setProtocolParser(final ProtocolParser protocolParser) {
        this.protocolParser = protocolParser;
    }

    public void setPoolConnectionManager(final PoolConnectionManager poolConnectionManager) {
        this.poolConnectionManager = poolConnectionManager;
    }
}
