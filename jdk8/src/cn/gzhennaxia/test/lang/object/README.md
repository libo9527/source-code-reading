# java.lang.Object

## clone()

> [jdk源码分析（一）——Object类](https://www.jianshu.com/p/4791207253a0)

使用clone方法的优点：

（1）速度快。clone方法最终会调用Object.clone()方法，这是一个native方法，本质是内存块复制，所以在速度上比使用new创建对象要快。

（2）灵活。可以在运行时动态的获取对象的类型以及状态，从而创建一个对象。

当然，使用clone方法创建对象的缺点同样非常明显：

（1）实现深拷贝较为困难，需要整个类继承系列的所有类都很好的实现clone方法。

（2）需要处理CloneNotSupportedException异常。Object类中的clone方法被声明为可能会抛出CloneNotSupportedException，因此在子类中，需要对这一异常进行处理。

因此，我们如果想使用clone方法的话，需要非常谨慎。事实上，《Effective Java》的作者Joshua Bloch建议我们不应该实现Cloneable接口，而应该使用拷贝构造器或者拷贝工厂。

作者：自由水鸟
链接：https://www.jianshu.com/p/4791207253a0
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。