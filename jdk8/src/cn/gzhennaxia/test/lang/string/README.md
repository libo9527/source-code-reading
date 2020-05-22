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
分为高低位两部分，低位即 codePoint 的后 10 位(`Character.lowSurrogate(int codePoint)`)，高位则是剩下高位运算后的结果(`Character.highSurrogate(int codePoint)`)

