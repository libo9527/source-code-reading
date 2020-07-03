package cn.gzhennaxia.test.jvm;

/**
 * 《深入理解Java虚拟机（第3版）》代码清单7-2
 * 被动使用类字段演示二：
 * 通过数组定义来引用类，不会触发此类的初始化
 *
 * @author bo li
 * @date 2020-07-02 10:12
 */
public class SuperClass {

    static {
        System.out.println("SuperClass init!");
    }

    public static int value = 123;
}
