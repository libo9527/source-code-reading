package cn.gzhennaxia.test.io.serializable;

import java.io.*;

/**
 * @author bo li
 * @date 2020-06-04 10:00
 */
public class SerializableTest {

    public static void main(String[] args) {

        Test1 test1 = new Test1(1, "zhangsan");
        String fileName = "jdk8/src/cn/gzhennaxia/test/io/serializable/temp";

        serializable(test1, fileName);

        unserializable(fileName);
    }

    private static void unserializable(String fileName) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(fileName));
            Test1 o = (Test1) ois.readObject();
            System.out.println(o);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
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
