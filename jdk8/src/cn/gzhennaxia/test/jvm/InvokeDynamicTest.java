package cn.gzhennaxia.test.jvm;

import java.lang.invoke.*;
import java.lang.reflect.Field;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * 《深入理解Java虚拟机（第3版）》代码清单8-16
 * @author bo li
 * @date 2020-07-09 14:31
 */
class Test {
    class GrandFather {
        void thinking() {
            System.out.println("i am grandfather");
        }
    }

    class Father extends GrandFather {
        @Override
        void thinking() {
            System.out.println("i am father");
        }
    }

    class Son extends Father {
        @Override
        void thinking() {
            try {
                MethodType mt = MethodType.methodType(void.class);
                MethodHandle mh = lookup().findSpecial(GrandFather.class,
                        "thinking", mt, getClass());
                mh.invoke(this);
            } catch (Throwable e) {
            }
        }


        void thinking2() {
            try {
                MethodType mt = MethodType.methodType(void.class);
                Field lookupImpl = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                lookupImpl.setAccessible(true);
                MethodHandle mh = ((MethodHandles.Lookup) lookupImpl.get(null)).findSpecial(GrandFather.class, "thinking", mt, GrandFather.class);
                mh.invoke(this);
            } catch (Throwable e) {
            }
        }
    }

    public static void main(String[] args) {
        (new Test().new Son()).thinking();
    }
}
