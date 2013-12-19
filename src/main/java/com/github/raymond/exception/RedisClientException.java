package com.github.raymond.exception;

/**
 * @author raymond
 */
public class RedisClientException extends RuntimeException {

    public RedisClientException(Exception e) {
        super(e);
    }

    public RedisClientException(String message) {
        super(message);
    }
}
