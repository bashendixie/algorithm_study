package com.algorithm.demo;

import com.algorithm.demo.dl4jexamples.LinearDataClassifier;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 测试运算符
 */
public class operator {

    @Test
    public void test_1()
    {
        System.out.println(6 / 4);      // 求除数 =1
        System.out.println(6 % 4);      // 求余数 =2

        //左移运算符 = 左侧的数字 * 2的右侧数字的次方
        System.out.println(6 << 2);     // 6*2*2 = 24
        System.out.println(7 << 2);     // 7*2*2 = 28
        System.out.println(6 << 3);     // 6*2*2*2 = 48
        System.out.println(7 << 3);     // 7*2*2*2 = 56

        //右移运算符 = 左侧的数字 / 2的右侧数字的次方
        System.out.println(6 >> 2);     // 6/4 = 1
        System.out.println(7 >> 2);     // 7/4 = 1
        System.out.println(6 >> 3);     // 6/8 = 0
        System.out.println(7 >> 3);     // 7/8 = 0
    }

    @Test
    public void test_2()
    {
        System.out.println(6 & 3);      // 6%4 = 2
        System.out.println(7 & 3);      // 7%4 = 3
        System.out.println(7 & 5);      // 7%8 = 7
        System.out.println(7 & 7);      // 7%8 = 7
    }

    @Test
    public void test_3()
    {
        System.out.println(6 | 3);      // 6%4 = 2
        System.out.println(7 | 3);      // 7%4 = 3
        System.out.println(7 | 5);      // 7%8 = 7
        System.out.println(7 | 7);      // 7%8 = 7

        Integer i = 100;
        Integer j = 100;
        System.out.print(i == j); //true

        Integer i1 = 128;
        Integer j1 = 128;
        System.out.print(i1 == j1); //false
    }

    @Test
    public void test_4() throws Exception
    {
        LinearDataClassifier.test();
    }
}
