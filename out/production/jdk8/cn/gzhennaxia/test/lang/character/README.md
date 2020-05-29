# Charactor

## Method

### isValidCodePoint

> [Code Point | Unicode](http://www.unicode.org/glossary/#code_point)

判断制定的 Unicode 码位是否有效

```java
public static boolean isValidCodePoint(int codePoint) {
    // Optimized(v.    持乐观态度；使最优化) form of:
    //     codePoint >= MIN_CODE_POINT && codePoint <= MAX_CODE_POINT
    // MIN_CODE_POINT = 0x000000;
    // MAX_CODE_POINT = 0X10FFFF;
    // (MAX_CODE_POINT + 1) >>> 16 = 0X11 = 17
    // codePoint >= MIN_CODE_POINT = 0，假设 codePoint < 0，则 codePoint 的最高位(符号位)是1
    // 所以，codePoint >>> 16 一定 >= 0X1000 = 4096 > 17，也就是说如果 codePoint < 0，则
    // codePoint >>> 16 一定大于 ((MAX_CODE_POINT + 1) >>> 16)
    int plane = codePoint >>> 16;
    return plane < ((MAX_CODE_POINT + 1) >>> 16);
}
```

### toSurrogates 

> [UTF-16 | Wiki](https://zh.wikipedia.org/wiki/UTF-16)
>
> **从U+10000到U+10FFFF的码位**
>
> 辅助平面（Supplementary Planes）中的码位，在UTF-16中被编码为**一对**16比特长的码元（即32位，4字节），称作*代理对*（Surrogate Pair），具体方法是：
>
> 1. 码位减去 `0x10000`，得到的值的范围为20比特长的 `0...0xFFFFF`。
> 2. 高位的10比特的值（值的范围为 `0...0x3FF`）被加上 `0xD800` 得到第一个码元或称作高位代理（high surrogate），值的范围是 `0xD800...0xDBFF`。由于高位代理比低位代理的值要小，所以为了避免混淆使用，Unicode标准现在称高位代理为**前导代理**（lead surrogates）。
> 3. 低位的10比特的值（值的范围也是 `0...0x3FF`）被加上 `0xDC00` 得到第二个码元或称作低位代理（low surrogate），现在值的范围是 `0xDC00...0xDFFF`。由于低位代理比高位代理的值要大，所以为了避免混淆使用，Unicode标准现在称低位代理为**后尾代理**（trail surrogates）

将[辅助平面](https://zh.wikipedia.org/wiki/UTF-16)上的码位转化为[代理对](https://zh.wikipedia.org/wiki/UTF-16)

```java
static void toSurrogates(int codePoint, char[] dst, int index) {
  // We write elements "backwards" to guarantee all-or-nothing
  // 低位代理
  dst[index+1] = lowSurrogate(codePoint);
  // 高位代理
  dst[index] = highSurrogate(codePoint);
}
```

### lowSurrogate

将增补字符的低位转化为[后尾代理](https://zh.wikipedia.org/wiki/UTF-16)

```java
public static char lowSurrogate(int codePoint) {
  // `& 0x3ff` 是为了取后 10 位
  // MIN_LOW_SURROGATE: '/uDC00'
  return (char) ((codePoint & 0x3ff) + MIN_LOW_SURROGATE);
}
```

### highSurrogate

```java
public static char highSurrogate(int codePoint) {
  // MIN_HIGH_SURROGATE = '\uD800';
  // MIN_SUPPLEMENTARY_CODE_POINT = 0x010000;
  return (char) ((codePoint >>> 10)
                 + (MIN_HIGH_SURROGATE - (MIN_SUPPLEMENTARY_CODE_POINT >>> 10)));
}
```

为什么不是

```java
(codePoint >>> 10) - (MIN_SUPPLEMENTARY_CODE_POINT >>> 10) + (MIN_HIGH_SURROGATE);
```

