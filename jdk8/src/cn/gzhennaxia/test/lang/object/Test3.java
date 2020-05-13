package cn.gzhennaxia.test.lang.object;

/**
 * @author bo li
 * @date 2020-05-13 12:26
 */
public class Test3 {

    private int id;
    private String name;

    Test3(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Test3) {
            Test3 test3 = (Test3) obj;
            return this.id == test3.id && this.name.equals(test3.name);
        }
        return false;
    }

    /**
     * 《Effective Java》一书中提到了一种简单的解决办法：
     * <p>
     * （1）把某个非零的常数值，比如17，保存在一个名为 result 的 int 类型的变量中。
     * （2）对于对象中的每个关键域 f(equals 方法中涉及的每个域，如果 equals 方法中没有涉及，则一定要排除在外)，完成如下步骤：
     * </p><p>
     *     a. 为该域计算 int 类型的散列码 c：
     *        i. 如果 f 是 boolean 类型，则 {@code c = f ? 1 : 0}
     *       ii. 如果 f 是 byte、char、short 或者 int 类型，则 {@code c = (int)f}
     *      iii. 如果 f 是 long 类型，则 {@code c = (int)(f ^ (f >>> 32))}
     *       iv. 如果 f 是 float 类型，则 {@code c = Float.floatToIntBits(f)}
     *        v. 如果 f 是 double 类型，则 {@code f = Double.doubleToLongBits(f)}，然后按照步骤 iii 中所述，为得到的 long 类型值计算散列值
     *       vi. 如果 f 是引用类型，并且该类的 equals 方法通过递归的调用 equals 的方式来比较这个域，则同样为这个域递归的调用 hashCode。如果这个域的值为 null，则返回 0。
     *      vii. 如果 f 是一个数组，则要把每一个元素当作单独的域来处理，也就是说，递归地应用上述规则，对每个重要元素计算一个散列码，然后根据步骤 2.b 中的做法把这些散列值组合起来。也可以使用 Arrays.hashCode 方法。
     * </p><p>
     *     b. 按照下面的公式，把步骤 2.a 中计算得到的散列码 c 合并到 result 中：
     *        {@code result = 31 * result + c}
     * </p><p>
     * (3)返回result。
     * <p>
     * 作者：自由水鸟
     * 链接：https://www.jianshu.com/p/4791207253a0
     * 来源：简书
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @author bli@skystartrade.com
     * @date 2020-05-13 12:25
     */
    @Override
    public int hashCode() {
        int result = 17;
        int c = 0;
        c += this.id;
        c += this.name.hashCode();
        return 31 * result + c;
    }
}
