package cn.gzhennaxia.test.io.externalizable;

import java.io.*;

/**
 * @author bo li
 * @date 2020-06-04 10:31
 */
public class ExternalizableTest {

    public static void main(String[] args) {
        Test1 test1 = new Test1(1, "lisi");

        String fileName = "jdk8/src/cn/gzhennaxia/test/io/externalizable/temp";

        serializable(test1, fileName);

        unserializable(fileName);
    }

    /**
     * 如果 Test1 没有空构造器，则会报：
     * java.io.InvalidClassException: cn.gzhennaxia.test.io.externalizable.Test1; no valid constructor
     *
     * @author bli@skystartrade.com
     * @date 2020-06-04 14:37
     */
    private static void unserializable(String fileName) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(fileName));
            Test1 o = (Test1) ois.readObject();
            System.out.println(o);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                assert ois != null;
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void serializable(Test1 test1, String fileName) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(test1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert oos != null;
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
