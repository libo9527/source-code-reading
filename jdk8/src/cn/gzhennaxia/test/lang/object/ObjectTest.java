package cn.gzhennaxia.test.lang.object;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author bo li
 * @date 2020-05-12 16:27
 */
public class ObjectTest {

    public static void main(String[] args) throws Exception {

//        testGetClass();
//        testGetClass1();

//        testHashCode();
        testClone();
    }

    /**
     * getClass() 方法返回的对象是运行时的类对象。
     *
     * @author bli@skystartrade.com
     * @date 2020-05-12 19:26
     */
    private static void testGetClass() {
        printClassNameOfList(new ArrayList<>());  // java.util.ArrayList
        printClassNameOfList(new LinkedList<>()); // java.util.LinkedList
    }

    private static void printClassNameOfList(List list) {
        System.out.println(list.getClass().getName());
    }

    /**
     * https://stackoverflow.com/questions/30918889/how-to-understand-the-object-getclass-method
     * <p>
     * 使用 getClass() 方法返回的类对象来调用原对象的静态方法时，该静态方法是加了同步锁的，<br>
     * 而且使用的锁就是 getClass() 返回的这个类对象。
     * </p><p>
     * 即: {@code Test1.fun() } 会变成下面这样
     * {@code  public static void fun() {               }
     * {@code       synchronized (Test1.class) {        }
     * {@code           ...                             }
     * {@code       }                                   }
     * {@code  }                                        }
     * </p><p>
     * 线程同步和线程安全的区别：
     * 线程安全是表示一种状态，在这种状态下程序中的共享数据是一致的，不会引发数据错误。
     * 线程同步是实现线程安全的一种方法。
     * </p>
     *
     * @author bli@skystartrade.com
     * @date 2020-05-12 19:26
     */
    private static void testGetClass1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // 非线程安全
//        new Thread(() -> {
//            Test1.fun();
//            System.out.println(Test1.getA()); // 1308
//        }).start();
//
//        new Thread(() -> {
//            Test1.fun();
//            System.out.println(Test1.getA()); // 1325
//        }).start();


        // 线程安全
        Object o = new Test1();
        Class<?> aClass = o.getClass();
        new Thread(() -> {
            try {
                Method fun = aClass.getMethod("fun");
                Method getA = aClass.getMethod("getA");
                fun.invoke(o);
                Object a = getA.invoke(o);
                System.out.println(a); // 1000
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }).start();

        new Thread(() -> {
            try {
                Method fun = aClass.getMethod("fun");
                Method getA = aClass.getMethod("getA");
                fun.invoke(o);
                Object a = getA.invoke(o);
                System.out.println(a); // 2000
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }).start();

    }

    /**
     * 规范：重写 equals 方法时也要重写 hashCode 方法，且应该满足如下关系
     * o1.equals(o2) => o1.hashCode() == o2.hashCode()
     * !o1.equals(o2) <= o1.hashCode() != o2.hashCode()
     * <br>
     * 实际中应该尽量使得 o1.equals(o2) <=> o1.hashCode() == o2.hashCode()
     *
     * @author bli@skystartrade.com
     * @date 2020-05-13 10:49
     */
    private static void testHashCode() {
        // Test2 重写了 equals 方法但没重写 hashCode 方法，
        // 所以 equals 为 true 的两个 Test2 对象在 HashMap 中被认为是 unequal 的。
        // HashMap 中为了提高性能，判断两个对象是否相等使用的是 hashCode 方法，而不是 equals 方法。
        // 因为 hashCode 返回的是 Integer，对 Integer 做位操作是非常高效的。
        Test2 o1 = new Test2(1, "aaa");
        Test2 o2 = new Test2(1, "aaa");
        HashMap<Test2, Float> map = new HashMap<>(2);
        map.put(o1, 95.27f);
        System.out.println(o1.equals(o2)); // true
        System.out.println(o1.hashCode()); // 1639705018
        System.out.println(o2.hashCode()); // 1627674070
        System.out.println(map.get(o2)); // null

        Test3 o3 = new Test3(1, "aaa");
        Test3 o4 = new Test3(1, "aaa");
        HashMap<Test3, Float> map1 = new HashMap<>(2);
        map1.put(o3, 95.27f);
        System.out.println(o3.equals(o4)); // true
        System.out.println(o3.hashCode()); // 96849
        System.out.println(o4.hashCode()); // 96849
        System.out.println(map1.get(o4)); // 95.27
    }

    /**
     * 必须实现 Cloneable 接口，否则报 CloneNotSupportedException
     *
     * @author bli@skystartrade.com
     * @date 2020-05-15 10:17
     */
    private static void testClone() throws CloneNotSupportedException {
//        Test4 o1 = new Test4(); // Test4 没有实现 Cloneable 接口
//        Object o2 = o1.clone();
//        System.out.println(o2);// java.lang.CloneNotSupportedException

        Test5 o3 = new Test5();
        Object o4 = o3.clone();
        System.out.println(o4);// cn.gzhennaxia.test.lang.object.Test5@7a7b0070
    }

    private static void testClone2() throws CloneNotSupportedException {
        Test5 o = new Test5();
        Object o1 = o.clone();
        System.out.println(o1);// java.lang.CloneNotSupportedException
    }
}
