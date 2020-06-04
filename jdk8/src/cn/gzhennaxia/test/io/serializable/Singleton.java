package cn.gzhennaxia.test.io.serializable;

import java.io.Serializable;

/**
 * @author bo li
 * @date 2020-06-04 15:21
 */
public class Singleton implements Serializable {

    private volatile static Singleton singleton;

    private Singleton() {
    }

    /**
     * 使用双重校验锁实现单例模式
     *
     * @author bli@skystartrade.com
     * @date 2020-06-04 15:24
     */
    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    /**
     * 反序列化时会检查是否存在 readResolve() 方法，存在则调用它来实例化
     * 否则使用反射调用无参构造器实例化，因此可以通过创建 readResolve() 方法
     * 来保护单例在反序列化过程中不被破坏。
     *
     * @author bli@skystartrade.com
     * @date 2020-06-04 16:33
     */
    public Object readResolve() {
        return singleton;
    }
}
