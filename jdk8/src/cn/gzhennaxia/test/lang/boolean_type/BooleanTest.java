package cn.gzhennaxia.test.lang.boolean_type;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bo li
 * @date 2020-05-29 16:03
 */
public class BooleanTest {

    public static void main(String[] args) {
//        testAutoUnboxing();
        testAutoUnboxing2();
    }

    /**
     * //// 反编译后 ////
     * HashMap hashmap = new HashMap();
     * Boolean boolean1 = Boolean.valueOf(hashmap == null ? false : ((Boolean)hashmap.get("test")).booleanValue());
     *
     * @author bli@skystartrade.com
     * @date 2020-05-29 16:23
     */
    private static void testAutoUnboxing() {
        Map<String, Boolean> map = new HashMap<>(0);
        Boolean b = map != null ? map.get("test") : false;
        System.out.println(b); // java.lang.NullPointerException
    }

    private static void testAutoUnboxing2() {
        Map<String, Boolean> map = new HashMap<>(0);
        Boolean b = map != null ? map.get("test") : Boolean.FALSE;
        System.out.println(b); // null
    }
}
