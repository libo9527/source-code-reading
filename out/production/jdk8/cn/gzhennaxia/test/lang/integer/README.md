
> [[译\]Java中整型的缓存机制](http://www.hollischuang.com/archives/1174)

自动装箱时，如果数字在 -128 至 127 之间时，会直接使用缓存中的对象，而不是重新创建一个对象。（只适用于自动装箱。使用构造函数创建对象不适用。）

最大值127可以通过`-XX:AutoBoxCacheMax=size`修改。

实际上这个功能在 Java 5 中引入的时候，范围是固定的 -128 至 +127。后来在Java 6 中，可以通过`java.lang.Integer.IntegerCache.high`设置最大值。

