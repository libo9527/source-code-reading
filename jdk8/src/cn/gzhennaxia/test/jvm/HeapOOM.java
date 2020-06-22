package cn.gzhennaxia.test.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * -Xms20m: 堆最大内存 20MB
 * -Xmx20m: 堆最小内存 20MB
 * -Xms20m -Xmx20m: 堆内存为不可动态扩展的 20MB
 * -XX:+HeapDumpOnOutOfMemoryError: 出现OOM时Dump出当前的内存堆转储快照
 *
 * @author bo li
 * @date 2020-06-22 09:52
 */
public class HeapOOM {

    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
