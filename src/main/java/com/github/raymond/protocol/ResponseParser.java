package com.github.raymond.protocol;

/**
 * @author Raymond
 */
public interface ResponseParser<T> {
    T parse(String response);
}
