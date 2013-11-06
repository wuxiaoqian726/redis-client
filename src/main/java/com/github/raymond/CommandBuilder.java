package com.github.raymond;

/**
 * @author Raymond
 */
public class CommandBuilder {

    private static final String SPACE = " ";
    private final StringBuilder builder = new StringBuilder();

    public String build() {
        return builder.toString();
    }

    public CommandBuilder command(String command) {
        builder.append(command).append(SPACE);
        return this;
    }

    public CommandBuilder key(String key) {
        builder.append(key).append(SPACE);
        return this;
    }

    public CommandBuilder value(String value) {
        return this;
    }
}
