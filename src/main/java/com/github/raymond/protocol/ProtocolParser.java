package com.github.raymond.protocol;


import com.github.raymond.exception.RedisClientException;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Raymond
 */
public class ProtocolParser {

    private static final String CRLF = "\r\n";
    private static final Map<Character, ResponseParser<?>> RESPONSE_PARSER_MAP = new HashMap<Character, ResponseParser<?>>() {
        {
            put('+', new SuccessStatusResponseParser());
            put('-', new ErrorStatusResponseParser());
            put(':', new NumberResponseParser());
            put('$', new BulkResponseParser());
            put('*', new MultipleBulkResponseParser());
        }
    };

    public String parseCommand(String command) {
        if (StringUtils.isBlank(command)) throw new RedisClientException("empty command");

        String[] tokens = command.split(" ");
        StringBuilder builder = new StringBuilder();
        builder.append("*").append(tokens.length).append(CRLF);
        for (String token : tokens) {
            builder.append("$").append(token.length()).append(CRLF);
            builder.append(token).append(CRLF);
        }
        return builder.toString();
    }

    public Object parseResponse(String response) {
        char[] chars = response.toCharArray();
        return RESPONSE_PARSER_MAP.get(response.toCharArray()[0]).parse(String.valueOf(Arrays.copyOfRange(chars, 1, chars.length - 1)));
    }


}
