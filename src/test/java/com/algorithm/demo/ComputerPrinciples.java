package com.algorithm.demo;

import org.junit.jupiter.api.Test;

public class ComputerPrinciples {

    @Test
    public void testErAdd()
    {
        byte a=(byte) 0b0001;
        byte b=(byte) 0b0000;

        System.out.println(a+b);
        System.out.println(b+a);
        System.out.println(a+a);
        System.out.println(a+a+a);
    }

}
