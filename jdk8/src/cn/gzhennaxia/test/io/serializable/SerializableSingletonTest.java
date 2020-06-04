package cn.gzhennaxia.test.io.serializable;

import java.io.*;

/**
 * @author bo li
 * @date 2020-06-04 15:27
 */
public class SerializableSingletonTest {

    /**
     * 序列化会破坏单例模式
     *
     * @author bli@skystartrade.com
     * @date 2020-06-04 15:33
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String filePath = "jdk8/src/cn/gzhennaxia/test/io/serializable/temp";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
        oos.writeObject(Singleton.getInstance());

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        Singleton singleton = (Singleton) ois.readObject();
        System.out.println(singleton == Singleton.getInstance()); // false
    }
}
