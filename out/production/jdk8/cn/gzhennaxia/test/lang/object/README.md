# java.lang.Object

## Methods

### clone

```java
protected native Object clone() throws CloneNotSupportedException;
```

#### 可见行

由 protected 修饰，所以只在 `java.lang` 包下，以及其直接子类中可见。

对于权限修饰符 protected 来说需要注意一点，如果子类和父类不在同一个包下，那么子类无法访问父类实例的 protected 方法！[Java protected 关键字详解 | 菜鸟教程](https://www.runoob.com/w3cnote/java-protected-keyword-detailed-explanation.html)

**e.g.1**

```java
package cn.gzhennaxia.test.lang.object;

public class ObjectTest {
  public static void main(String[] args) throws Exception {
    ObjectTest ot = new ObjectTest();
    ot.clone();

    Object o = new Object();
    // o.clone(); 编译错误
  }
}
```

**e.g.2**

```java
package cn.gzhennaxia.test.lang.object;

public class Test implements Cloneable {

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
```

```java
package cn.gzhennaxia.test.lang.object.p;

import cn.gzhennaxia.test.lang.object.Test;

public class ObjectTest extends Test {

    /**
     * 子类可以访问继承自父类的 protected 方法，
     */
    private void f() throws CloneNotSupportedException {
        clone();
    }

    /**
     * 但无法访问父类实例的 protected 方法。
     */
    private void f2() {
        Test5 t5 = new Test5();
//        t5.clone(); 编译错误
    }
}
```



#### 优缺点

使用 clone 方法的优点：

> [jdk源码分析（一）——Object类](https://www.jianshu.com/p/4791207253a0)

1. 速度快。clone 方法最终会调用 Object.clone() 方法，这是一个 native 方法，本质是内存块复制，所以在速度上比使用 new 创建对象要快。

2. 灵活。可以在运行时动态的获取对象的类型以及状态，从而创建一个对象。

使用 clone 方法的缺点：

1. 实现深拷贝较为困难，需要整个类继承系列的所有类都很好的实现 clone 方法。

2. 需要处理 CloneNotSupportedException 异常。Object 类中的 clone 方法被声明为可能会抛出 CloneNotSupportedException，因此在子类中，需要对这一异常进行处理。

因此，我们如果想使用clone方法的话，需要非常谨慎。事实上，《Effective Java》的作者 Joshua Bloch 建议我们不应该实现 Cloneable 接口，而应该使用拷贝构造器或者拷贝工厂。


拷贝构造方法相对 clone 方法的优势：
> [Java中的拷贝构造函数](https://www.imooc.com/article/298145)

1. 拷贝构造方法实现更简单。不需要实现 Cloneable 接口，也不需要处理 CloneNotSupportedException

2. clone 函数返回一个普通的 Object 类型的引用。还需要转成特定的类型。

3. 在 clone 方法中不能为 final 属性赋值，但是在拷贝构造方法中就可以。
  

拷贝工厂

```java
public class Test{
    
    private int a;
    
    private String b;
    
    public Test(int a, String b){
        this.a = a;
        this.b = b;
    }
    
    public static Test newInstance(Test test){
        return new Test(test.a, test.b);
    }
}
```

通过序列化实现深拷贝
> [为什么阿里Java手册推荐慎用 Object 的 clone 方法来拷贝对象](https://juejin.im/post/5d425230f265da039519d248)

```xml
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.8.1</version>
</dependency>
```

```java
public static void main(String[] args){
    Son son = new Son(1, "son");
    Parent parent = new Parent(1, "parent", son);
    
    // 序列化
    byte[] serialize = SerializationUtils.serialize(parent);
    // 反序列化
    Person newPerson = SerializationUtils.deserialize(serialize);
}
```



## notify() & notifyAll() & wait()

> [使用wait和notify | 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/1252599548343744/1306580911915042)
> 
> `synchronized` 解决了多线程**竞争**的问题，但并没有解决多线程**协调**的问题。
>
> [Java 基础 | Object 源码解析](https://zhuanlan.zhihu.com/p/77530300)
> 
> 为什么 wait() 必须在同步 (Synchronized) 方法/代码块中调用？
> 
> 答：调用 wait() 就是释放锁，释放锁的前提是必须要先获得锁，先获得锁才能释放锁。
> 
> 为什么 notify()、notifyAll() 必须在同步 (Synchronized) 方法/代码块中调用？
> 
> 答：notify()、notifyAll() 是将锁交给含有 wait() 方法的线程，让其继续执行下去，如果自身没有锁，怎么叫把锁交给其他线程呢。

- 必须在 synchronized 内部调用

- 必须在已获得的锁对象上调用
