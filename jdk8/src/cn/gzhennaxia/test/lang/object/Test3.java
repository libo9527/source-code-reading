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
     * 《effective java》一书中提到了一种简单的解决办法：
     * <p>
     * （1）把某个非零的常数值，比如17，保存在一个名为result的int类型的变量中。
     * <p>
     * （2）对于对象中的每个关键域f（equals方法中涉及的每个域，如果equals方法中没有涉及，则一定要排除在外），完成如下步骤：
     * <p>
     * a.为该域计算int类型的散列码c：
     * <p>
     * i.如果该域是boolean类型，则计算(f?1:0)。
     * <p>
     * ii.如果该域是byte、char、short或者int类型，则计算(int)f。
     * <p>
     * iii.如果该域是long类型，则计算(int)(f^(f>>>32))。
     * <p>
     * iv.如果该域是float类型，则计算Float.floatToIntBits(f)。
     * <p>
     * v.如果该域是double类型，则计算Double.doubleToLongBits(f)，然后按照步骤iii中所述，为得到的long类型值计算散列值。
     * <p>
     * vi.如果该域是一个对象引用，并且该类的equals方法通过递归的调用equals的方式来比较这个域，则同样为这个域递归的调用hashCode。如果这个域的值为null，则返回0。
     * <p>
     * vii.如果该域是一个数组，则要把每一个元素当作单独的域来处理，也就是说，递归地应用上述规则，对每个重要元素计算一个散列码，然后根据步骤2.b中的做法把这些散列值组合起来。也可以使用Arrays.hashCode方法。
     * <p>
     * b.按照下面的公式，把步骤（2）a中计算得到的散列码c合并到result中：
     * <p>
     * result = 31 * result + c。
     * <p>
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
