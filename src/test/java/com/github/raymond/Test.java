package com.github.raymond;

/**
 * @author raymond
 */
public class Test {

    @org.junit.Test
    public void testSplitWithEmptySpace() {
        String command = "get firstKey";
        String[] arrays = command.split(" ");
        for (String array : arrays) {
            System.out.println(array);
        }
    }
}