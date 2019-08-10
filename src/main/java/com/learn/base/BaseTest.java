package com.learn.base;

import com.learn.base.entity.Player;
import com.learn.base.entity.SubClass;
import com.learn.base.entity.Team;
import org.junit.Test;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author: yanzx
 * @date: 2019/08/04 16:55
 */
public class BaseTest {

    static {
        players = Arrays.asList("Kobe", "James", "Jordan");
        kobe = new Player("kobe", 24);
        james = new Player("james", 23);

        LAL = new Team(
                "洛杉矶湖人队",
                Arrays.asList(new Player("kobe", 24), new Player("james", 23)));
    }

    private static Team LAL;

    private static Player kobe, james;

    private static List<String> players;

    /**
     * stream simple demo
     */
    @Test
    public void stream01() {
        players.stream().map(String::toUpperCase).forEach(System.out::println);
    }

    /**
     * function simple demo
     * 使用String::toUpperCase这种调用实例方法，是由一个String类型的对象调用toUpperCase方法的
     */
    @Test
    public void function01() {
        Function<String, String> function = String::toUpperCase;
        System.out.println(function.apply(players.get(0)));
    }

    @Test
    public void function02() {
        System.out.println(computer("JAMES", str -> str.substring(0, 1), String::toUpperCase));
    }

    private String computer(String name, Function<String, String> f1, Function<String, String> f2) {
        return f1.compose(f2).apply(name);
    }


    /**
     * @param targetAge
     * @param players
     * @return List<Player>
     */
    private List<Player> filterByAge(Integer targetAge, List<Player> players) {
        BiFunction<Integer, List<Player>, List<Player>> function =
                (age, list) -> list.stream().filter(player -> age.equals(player.getAge())).collect(Collectors.toList());

        return function.apply(targetAge, players);
    }

    @Test
    public void biFunctionTest() {
        List<Player> result = filterByAge(23, Arrays.asList(kobe, james));
        result.forEach(System.out::println);
    }


    /**
     * constructor ref
     */
    @Test
    public void constructorRefTest() {
        Supplier<Player> supplier = Player::new;
        System.out.println(supplier.get().getName());
    }


    /**
     * 推荐使用Optional.ifPresent进行函数式编写程序，避免编写 if(null == xx) else ..
     */
    @Test
    public void optionalTest01() throws RainException {
        Optional<String> optional = Optional.of("rain..");
        optional.ifPresent(System.out::println);

        Optional<Object> empty = Optional.empty();

        System.out.println(empty.orElse("hua la la.."));

        System.out.println(empty.orElseGet(() -> kobe.getName()));

        empty.orElseThrow(RainException::new);
    }

    @Test
    public void optionalTest02() {
        Optional<Team> lal = Optional.ofNullable(LAL);
        //lal.ifPresent(team -> System.out.println(team.getPlayers()));

        List<Player> players = lal.map(Team::getPlayers).orElseGet(Collections::emptyList);
        System.out.println(players);
    }

    /**
     * 注意:
     * 类::实例方法，实例方法一定被一个对象所调用！
     * <p>
     * default void sort(Comparator<? super E> c) {...}
     * Comparator -> int compare(T o1, T o2);
     * <p>
     * sort方法要求传入带有两个参数的lambda，但我们的方法引用指向的函数只有一个参数 ？？
     * 因为调用::后面compareByAge的对象为lambda的第一个参数（p1），
     * 如果lambda中有多个参数，那么后面的参数(p2 p3 p4)为 xx.compareByAge(yy) 中的参数yy
     */

    @Test
    public void methodReference() {
        Player p1 = new Player("a", 25);
        Player p2 = new Player("b", 24);
        Player p3 = new Player("b", 26);

        List<Player> players = Arrays.asList(p1, p2, p3);

        players.sort(Player::compareByAge);
        players.forEach(System.out::println);
        System.out.println("******************************************");

    }

    @Test
    public void defaultMethod(){
        SubClass subClass = new SubClass();
        subClass.defaultMethod();
    }
}
