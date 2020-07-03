package cn.gzhennaxia.test.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 《深入理解Java虚拟机（第3版）》代码清单4-7
 * 内存占位符对象，一个OOMObject大约占64KB
 * -Xms100m -Xmx100m -XX:+UseSerialGC
 *
 * @author bo li
 * @date 2020-06-28 14:20
 */
public class JConsole_TestCase {

    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            // 稍作延时，令监视曲线的变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }
}
