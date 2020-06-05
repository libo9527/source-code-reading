package cn.gzhennaxia.test.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bo li
 * @date 2020-06-05 16:55
 */
public class JvmTest {

    public static void main(String[] args) throws InterruptedException {
        List list = new ArrayList<>();
        while (true) {
            list.add(new Object());
//            Thread.sleep(10);
        }
    }
}
