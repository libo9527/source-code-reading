package cn.gzhennaxia.test.jvm;

/**
 * @author bo li
 * @date 2020-07-02 10:13
 */
public class NotInitialization {

    public static void main(String[] args) {
//        System.out.println(SubClass.value);
//        SuperClass[] classes = new SuperClass[10];
        System.out.println(ConstClass.HELLOWORLD);
    }
}
