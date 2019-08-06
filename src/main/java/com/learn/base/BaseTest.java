package com.learn.base;

import com.learn.base.entity.Player;
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
    public void optionalTest02(){
        Optional<Team> lal = Optional.ofNullable(LAL);
        //lal.ifPresent(team -> System.out.println(team.getPlayers()));

        List<Player> players = lal.map(Team::getPlayers).orElseGet(Collections::emptyList);
        System.out.println(players);
    }
}
