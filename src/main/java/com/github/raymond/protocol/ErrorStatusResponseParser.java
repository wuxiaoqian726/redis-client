package com.github.raymond.protocol;

import com.github.raymond.exception.RedisServerException;

/**
 * @author raymond
 */
public class ErrorStatusResponseParser implements ResponseParser<Void> {

    @Override
    public Void parse(String response) {
        throw new RedisServerException(response);
    }
}
