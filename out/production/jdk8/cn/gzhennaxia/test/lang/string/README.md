# String

> [JDK源码阅读-String](https://zhouyimian.github.io/2019/05/20/JDK%E6%BA%90%E7%A0%81%E9%98%85%E8%AF%BB-String/)
>
> [Java 源码分析 — String 的设计](https://www.jianshu.com/p/799c4459b808)

### code unit & code point

> [java里面code unit, code point怎么理解？](https://www.zhihu.com/question/35937819/answer/65194371)
>
> 在unicode的世界中，每种字符都有一个唯一的数字编号，这个数字编号就叫 unicode code point。目前code point的数值范围是0~0x10FFFF(需要 3 个字节)，
也就是一共可以表示1114112种不同的字符。由于code point的数值范围比较尴尬，直接用四字节的 int 类型来存储太浪费空间，直接用双字节的char类型来存储又存不下。
最终有个折衷方案，数值范围较小的code point用一个char存储，数字范围较大的code point用两个char来存储。所以一个char就叫一个code unit，而这种方案就叫utf-16。
> 
> Java中关于code point的具体处理方式，参见[Unicode surrogate programming with the Java language](https://link.zhihu.com/?target=http%3A//www.ibm.com/developerworks/library/j-unicode/)

code point: 码位

code unit: 码元

[UTF-16](https://zh.wikipedia.org/wiki/UTF-16) 中，位于BMP的一个码位对应一个码元，高于BMP的一个码位对应两个码元。

### Java中的String有没有长度限制

> [我说我精通字符串，面试官竟然问我Java中的String有没有长度限制！？|附视频讲解](http://www.hollischuang.com/archives/4839)
>
> 字符串有长度限制，在编译期，要求字符串常量池中的常量不能超过65535，并且在javac执行过程中控制了最大值为65534。
  在运行期，长度不能超过Int的范围，否则会抛异常。


## Constructor

### public String(int[] codePoints, int offset, int count) 

> [JDK源码阅读—String](https://zhouyimian.github.io/2019/05/20/JDK%E6%BA%90%E7%A0%81%E9%98%85%E8%AF%BB-String/)
> 
> isBmpCodePoint(int codePoint)，将数值右移16位，判断是否为0
> 
> 这里右移16位的原因是，Unicode编码中每个字符对应一个长度为16的二进制字符，这个范围内的字符称为BMP，
超出这个范围的叫做增补字符。一般我们使用的字符都是在BMP范围内的，一些特殊的字符，比如日本的字符等，就不属于BMP范围的。
在Java中，char是两个字节，正对应上BMP字符的长度(16个比特)，为了支持超出BMP范围的字符，
这些字符在Java中是以四个字节的形式进行存放的，isValidCodePoint方法就是用来判断是否是超出了BMP范围的字符，
如果是的话，需要额外增加一个两个字节(即增加最终字符串数组的一个长度，体现在n++上，n为最终数组的长度)。

根据 Unicode point 数组构造 String

> [UTF-16 - 维基百科](https://zh.wikipedia.org/zh-hans/UTF-16)
>
> 辅助平面（Supplementary Planes）中的码位，在UTF-16中被编码为**一对**16比特长的码元（即32位，4字节），称作*代理对*（Surrogate Pair），具体方法是：
>
> 1. 码位减去 `0x10000`，得到的值的范围为20比特长的 `0...0xFFFFF`。
> 2. 高位的10比特的值（值的范围为 `0...0x3FF`）被加上 `0xD800` 得到第一个码元或称作高位代理（high surrogate），值的范围是 `0xD800...0xDBFF`。由于高位代理比低位代理的值要小，所以为了避免混淆使用，Unicode标准现在称高位代理为**前导代理**（lead surrogates）。
> 3. 低位的10比特的值（值的范围也是 `0...0x3FF`）被加上 `0xDC00` 得到第二个码元或称作低位代理（low surrogate），现在值的范围是 `0xDC00...0xDFFF`。由于低位代理比高位代理的值要大，所以为了避免混淆使用，Unicode标准现在称低位代理为**后尾代理**（trail surrogates）。

其中的 Character.isBmpCodePoint(int codePoint) 用来判断 point 是否位于[基本多文种平面BMP](https://zh.wikipedia.org/wiki/Unicode%E5%AD%97%E7%AC%A6%E5%B9%B3%E9%9D%A2%E6%98%A0%E5%B0%84),
位于BMP的直接转换为 char，超出BMP的转化为 Surrogate(也就是增补字符，`Character.toSurrogates(c, v, j++);`)，
减掉最小增补字符码位(0x010000)后，分为高低位两部分，低位即 (codePoint - minSupplementaryCodePoint) 的后 10 位(`Character.lowSurrogate(int codePoint)`)，高位则是剩下高 10 位(`Character.highSurrogate(int codePoint)`)

## substring(int beginIndex, int endIndex)

> [JDK 6和JDK 7中 substring 的原理及区别](http://hollischuang.gitee.io/tobetopjavaer/#/basics/java-basic/substring?id=jdk-6和jdk-7中substring的原理及区别)

### JDK 6 中的 substring

在 jdk 6 中，String 类包含三个成员变量：`char value[]`， `int offset`，`int count`。他们分别用来存储真正的字符数组，数组的第一个位置索引以及字符串中包含的字符个数。

当调用 substring 方法的时候，会创建一个新的 string 对象，但是这个 string 的 value 值仍然指向堆中的**同一个字符数组**。这两个对象中只有 count 和 offset 的值是不同的。

如果字符串长度较长，当使用 substring 进行切割的时候只需要很短的一段。这可能导致性能问题，因为需要的只是一小段字符序列，但是却引用了整个字符串（因为这个字符数组一直在被引用，无法被回收，就可能导致**内存泄露**）。

关于 JDK 6 中 subString 的使用不当会导致内存泄漏已经被官方记录在 Java Bug Database 中：

![leak](http://www.hollischuang.com/wp-content/uploads/2016/03/leak.png)

> 内存泄露：在计算机科学中，内存泄漏指由于疏忽或错误造成程序未能释放已经不再使用的内存。内存泄漏并非指内存在物理上的消失，而是应用程序分配某段内存后，由于设计错误，导致在释放该段内存之前就失去了对该段内存的控制，从而造成了内存的浪费。

### JDK 7 中的substring

在 jdk 7 中，substring 方法会在堆内存中创建一个新的数组。

![string-substring-jdk7](http://www.programcreek.com/wp-content/uploads/2013/09/string-substring-jdk71-650x389.jpeg)

```java
//JDK 7
public String(char value[], int offset, int count) {
    //check boundary
    this.value = Arrays.copyOfRange(value, offset, offset + count);
}

public String substring(int beginIndex, int endIndex) {
    //check boundary
    int subLen = endIndex - beginIndex;
    return new String(value, beginIndex, subLen);
}
```

JDK 7 中的 subString 方法，使用`Arrays.copyOfRange()`将旧字符串的字符数组中的字符选择性的 copy 到新字符串的字符数组中，避免对老字符串的字符数组的引用。从而解决了内存泄露问题。

### JDK 8 中的 substring

```java
public String substring(int beginIndex, int endIndex) {
  if (beginIndex < 0) {
    throw new StringIndexOutOfBoundsException(beginIndex);
  }
  if (endIndex > value.length) {
    throw new StringIndexOutOfBoundsException(endIndex);
  }
  int subLen = endIndex - beginIndex;
  if (subLen < 0) {
    throw new StringIndexOutOfBoundsException(subLen);
  }
  return ((beginIndex == 0) && (endIndex == value.length)) ? this
    : new String(value, beginIndex, subLen);
}
```

