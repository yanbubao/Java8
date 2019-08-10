package com.learn.stream;

import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author: yanzx
 * @date: 2019/08/10 18:09
 */
public class StreamTest {

    private Stream<String> stream = Stream.of("kobe", "james", "kd");

    @Test
    public void test() {
        String[] names = stream.toArray(String[]::new);
        Arrays.asList(names).forEach(System.out::println);
    }

    @Test
    public void test2() {
        List<String> list = stream.collect(Collectors.toList());

        list.forEach(System.out::println);
    }

    /**
     * 分析test2()中stream.collect方法：
     * <p>
     * collect方法存在重载方法
     * <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner);
     * supplier：供给类型函数接口 、accumulator：叠加器 、combiner：组合器
     * <p>
     * <p>
     * 第一个参数表示流处理完成后最终返回的数据类型！
     * 第二个参数叠加器表示对于流中每一个数据处理后叠加至一个结果中！
     * 第三个参数表示将参数二的结果组合至最终结果！
     */
    @Test
    public void test3() {
        List<String> result = stream.collect(
                ArrayList::new, ArrayList::add,
                // ArrayList::addAll
                (finallyResult, thisResult) -> finallyResult.addAll(thisResult));

        result.forEach(System.out::println);
    }

    /**
     * 和test3思路相同，collect还能传入Collectors.toCollection()，来指定使用LinkedList、Set...作为处理结果的数据类型！
     */
    @Test
    public void test4() {
        LinkedList<String> linkedList = stream.collect(Collectors.toCollection(LinkedList::new));

        linkedList.forEach(System.out::println);
    }

    @Test
    public void test5() {
        TreeSet<String> treeSet = stream.collect(Collectors.toCollection(TreeSet::new));

        System.out.println(treeSet.getClass());
        treeSet.forEach(System.out::println);
    }

    /**
     * append
     */
    @Test
    public void test6() {
        StringBuilder builder = stream.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
        // or
        System.out.println(stream.collect(Collectors.joining()));
    }

    @Test
    public void test7() {

        stream.map(String::toUpperCase).collect(Collectors.toList()).forEach(System.out::println);
    }


    /**
     * Stream.flatMap . flat:扁平化
     * 1,3,5; 3,5,7; 5,7,9; -> 1,3,5,3,5,7,5,7,9
     */
    @Test
    public void test8() {
        Stream<List<Integer>> listStream = Stream.of(
                Arrays.asList(1, 3, 5), Arrays.asList(3, 5, 7), Arrays.asList(5, 7, 9));

        listStream.flatMap(List::stream).map(temp -> temp * temp).forEach(System.out::println);
    }


    /**
     * Stream.concat . 合并流
     */
    @Test
    public void test9() {
        Stream<Integer> integerStream = Stream.of(1, 2);
        Stream<String> stringStream = Stream.of("3", "4");

        Stream<? extends Serializable> stream = Stream.concat(integerStream, stringStream);

        stream.collect(Collectors.toList()).forEach(temp -> System.out.println(temp.getClass()));
    }

    /**
     * Stream.generate
     */
    @Test
    public void test10() {
        Stream<UUID> stream = Stream.generate(UUID::randomUUID);

        stream.findFirst().ifPresent(System.out::println);
    }

    /**
     * Stream.iterate 返回一个无限的有序的的串行流！
     */
    @Test
    public void test11() {
        // Stream.iterate(1, temp -> temp + 2).forEach(System.out::println);

        // limit . 限制产生流的个数
        Stream.iterate(1, temp -> temp + 2).limit(6L).forEach(System.out::println);
    }

    /**
     * eg.
     * 定义流：Stream.iterate(1, temp -> temp + 2).limit(6L)
     * 找出该流中大于2的元素，将每个元素乘以2，忽略掉流中前两个元素，然后再取流中前两个元素，最终求元素sum.
     */
    @Test
    public void test12() {
        long sum = Stream.iterate(1, temp -> temp + 2).limit(6L)
                .filter(temp -> temp > 2)
                .mapToInt(temp -> temp * 2)
                .skip(2)
                .limit(2)
                .summaryStatistics().getSum();

        Assert.assertEquals(sum, 32);
    }

    /**
     * error .
     */
    @Test
    public void test13() {
        // error
        //IntStream.iterate(0, i -> (i + 1) % 2).distinct().limit(6).forEach(System.out::println);

        IntStream.iterate(0, i -> (i + 1) % 2).limit(6).distinct().forEach(System.out::println);
    }
}
