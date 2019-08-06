Java8新特性笔记

高阶函数：如果一个函数接受一个函数作为参数，或者将一个函数作为返回值，那么该函数就叫做高阶函数。

一、lambda:用于表示匿名函数或闭包的一种运算符。
*** 传递行为，而不仅仅是值！


二、函数式接口：java.lang.FunctionalInterface
条件：
01.有且只有一个抽象方法的接口
02.有@FunctionInterface注解
03.没有@FunctionInterface注解，但有且只有一个抽象方法的接口

04.如果接口中含有覆写了java.lang.Object中的方法，那么编译器不会认为此方法为函数式接口中的一个方法，因为所有实现该函数式接口的类都继承自Object类。

实现：可以使用lambda表达式、方法引用、构造方法引用来创造函数式接口的实例。

注意：在将函数作为一等公民的编程语言中，lambda表达式是类型是函数，如python等语言。
但是！在Java中lambda的类型的对象！必须依附于一类特别的对象类型——函数式接口。

java.util.function.* 下为Java8提供的所有函数式接口，BiFunction: Bidirectional（双向的)等等！


三、stream
基本概念：
两组纬度，串行流和并行流、中间流和节点流。、
流要操作的数据，数据源source




四、java.util.Optional<T>

Optional是一个容器对象，可能包含null！

推荐使用Optional.ifPresent进行函数式编写程序，避免编写 if(null == xx) else ..

注意：Optional没有实现序列化接口！因此不要用Optional最为成员变量或函数参数，一般只作为函数返回值使用！