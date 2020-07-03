package cn.gzhennaxia.test.jvm;

/**
 * 《深入理解Java虚拟机（第3版）》代码清单4-6
 * staticObj、instanceObj、localObj存放在哪儿？
 * -Xmx10m -XX:+UseSerialGC -XX:-UseCompressedOops
 * <p>
 * JHSDB虽然名义上是JDK 9中才正式提供，
 * 但之前已经以sa-jdi.jar包里面的HSDB（可视化工具）
 * 和CLHSDB（命令行工具）的形式存在了很长一段时间。
 *
 * @author bo li
 * @date 2020-06-28 11:40
 */
public class JHSDB_TestCase {

    static class Test {
        static ObjectHolder staticObj = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done"); // 断点
        }
    }

    private static class ObjectHolder {
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.foo();
    }
}
