
> [自动拆箱导致空指针异常](http://www.hollischuang.com/archives/435)

三目运算符的语法规范。参见 [jls-15.25](http://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.25)，摘要如下：

> If the second and third operands have the same type (which may be the null type), then that is the type of the conditional expression.
>
>   If one of the second and third operands is of primitive type T, and the type of the other is the result of applying boxing conversion (§5.1.7) to T, then the type of the conditional expression is T.
>
> 如果第二第三操作数一个是基本类型，另一个是相应第包装类型，那么表达式的结果会拆箱成基本类型！(并且，由反编译结果可以看出是先将包装类型拆箱成基本类型，然后再进行三木运算的。)
>
>  If one of the second and third operands is of the null type and the type of the other is a reference type, then the type of the conditional expression is that reference type.

```java
Map<String, Boolean> map = new HashMap<>(0);
Boolean b = map != null ? map.get("test") : false;
System.out.println(b); // java.lang.NullPointerException

Boolean b2 = map != null ? map.get("test") : Boolean.FALSE;
System.out.println(b2); // null
```

```java
Map<String, Boolean> map = new HashMap<>(0);
Boolean b = map != null ? map.get("test") : false;

//// 反编译后 //// 
HashMap hashmap = new HashMap();
Boolean boolean1 = Boolean.valueOf(hashmap == null ? false : ((Boolean)hashmap.get("test")).booleanValue()); 
```