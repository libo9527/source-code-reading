package cn.gzhennaxia.test.lang.object;

/**
 * @author bo li
 * @date 2020-05-13 08:39
 */
public class Test1 {

    private static int a = 0;

    public static void fun() {
        for (int i = 0; i < 1000; i++)
            a++;
    }

    public static int getA() {
        return a;
    }

}
