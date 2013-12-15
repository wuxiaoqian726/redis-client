package com.github.raymond.protocol;

/**
 * @author Raymond
 */
public class BulkResponseParser implements ResponseParser<String> {

    /*
    C: GET mykey
    S: $6\r\nfoobar\r\n
    */
    @Override
    public String parse(String response) {
        return null;
    }
}
