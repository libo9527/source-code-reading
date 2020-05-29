package cn.gzhennaxia.test.lang.object.p;

import cn.gzhennaxia.test.lang.object.Test5;

/**
 * @author bo li
 * @date 2020-05-26 10:51
 */
public class ObjectTest2 extends Test5 {

    /**
     * 子类可以访问继承自父类的 protected 方法，
     *
     * @author bli@skystartrade.com
     * @date 2020-05-26 11:05
     */
    private void f() throws CloneNotSupportedException {
        clone();
//        System.identityHashCode()
    }

    /**
     * 但无法访问父类实例的 protected 方法。
     *
     * @author bli@skystartrade.com
     * @date 2020-05-26 11:06
     */
    private void f2() {
        Test5 t5 = new Test5();
//        t5.clone(); 编译错误
    }
}
