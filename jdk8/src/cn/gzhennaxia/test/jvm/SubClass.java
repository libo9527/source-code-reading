package cn.gzhennaxia.test.jvm;

/**
 * 《深入理解Java虚拟机（第3版）》代码清单7-1
 * 被动使用类字段演示一：
 * 通过子类引用父类的静态字段，不会导致子类初始化
 *
 * @author bo li
 * @date 2020-07-02 10:12
 */
public class SubClass extends SuperClass {

    static {
        System.out.println("SubClass init!");
    }

}
