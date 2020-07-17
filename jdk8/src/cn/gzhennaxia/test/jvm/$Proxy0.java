package cn.gzhennaxia.test.jvm;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;


/**
 * 《深入理解Java虚拟机（第3版）》代码清单9-2
 *
 * @author bo li
 * @date 2020-07-10 15:06
 */
public final class $Proxy0 extends Proxy
        implements DynamicProxyTest.IHello {
    private static Method m3;
    private static Method m1;
    private static Method m0;
    private static Method m2;

    public $Proxy0(InvocationHandler paramInvocationHandler)
            throws {
        super(paramInvocationHandler);
    }

    public final void sayHello()
            throws {
        try {
            this.h.invoke(this, m3, null);
            return;
        } catch (RuntimeException localRuntimeException) {
            throw localRuntimeException;
        } catch (Throwable localThrowable) {
            throw new UndeclaredThrowableException(localThrowable);
        }
    }

    // 此处由于版面原因，省略equals()、hashCode()、toString()3个方法的代码
    // 这3个方法的内容与sayHello()非常相似。
    static {
        try {
            m3 = Class.forName("org.fenixsoft.bytecode.DynamicProxyTest$IHello").getMethod("sayHello", new Class[0]);
            m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[]{Class.forName("java.lang.Object")});
            m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
            m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
            return;
        } catch (NoSuchMethodException localNoSuchMethodException) {
            throw new NoSuchMethodError(localNoSuchMethodException.getMessage());
        } catch (ClassNotFoundException localClassNotFoundException) {
            throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
        }
    }
}