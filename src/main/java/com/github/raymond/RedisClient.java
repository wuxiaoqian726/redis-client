package com.github.raymond;

import com.github.raymond.connector.PoolConnectionManager;
import com.github.raymond.protocol.ProtocolParser;

import java.net.InetSocketAddress;

/**
 * @author raymond
 */
public class RedisClient {
    private final PoolConnectionManager poolConnectionManager;
    private final CommandExecutor commandExecutor;
    private final CommandBuilder commandBuilder = new CommandBuilder();
    private final Configuration configuration = new Configuration();

    public RedisClient(String host, int port) {
        this.commandExecutor = new CommandExecutor();
        this.poolConnectionManager = new PoolConnectionManager(new InetSocketAddress(host, port), configuration);
        this.commandExecutor.setPoolConnectionManager(poolConnectionManager);
        this.commandExecutor.setProtocolParser(new ProtocolParser());
    }

    public void set(String key, String value) {
        commandExecutor.execute(commandBuilder.withCommand("set").withKey(key).withValue(value).build());
    }

    public String get(String key) {
        return "";
    }

    public void shutdown() {
        poolConnectionManager.shutdown();
    }

}
