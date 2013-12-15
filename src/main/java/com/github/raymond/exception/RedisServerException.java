package com.github.raymond.exception;

/**
 * @author raymond
 */
public class RedisServerException extends RuntimeException {

    public RedisServerException(String message) {
        super(message);
    }
}