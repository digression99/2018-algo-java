import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Person {
    String name;
    int age;
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
}

class MyTask {
    private final int duration;
    public MyTask(int dur) {
        this.duration = dur;
    }
    public int calculate() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(duration * 1000);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
        return duration;
    }
}

public class TEST_20180516 {

    void collectionTest() {

        /*
        List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");

        myList.stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        System.out.println();

        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);

        System.out.println();

        IntStream.range(0, 4)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);

        System.out.println();

        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter : " + s);
                    return true;
                })
                .forEach(s -> System.out.println("for each : " + s));

        System.out.println();

        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter : " + s);
                    return s.startsWith("a");
                })
                .sorted((s1, s2) -> {
                    System.out.printf("sort : %s, %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .map(s -> {
                    System.out.println("map : " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach : " + s));
        */

        List<Person> persons = Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Pamela", 23),
                new Person("David", 12)
        );

        List<Person> filtered = persons
                .stream()
                .filter(p -> p.name.startsWith("P"))
                .collect(toList());
        System.out.println(filtered);

        Map<Integer, List<Person>> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(p -> p.age));

        System.out.println();

        personsByAge
                .forEach((age, p) -> System.out.format("age %s : %s\n", age, p));

        Double averageAge = persons
                .stream()
                .collect(Collectors.averagingInt(p -> p.age));
        System.out.println(averageAge);

        Map<Integer, String> map = persons
                .stream()
                .collect(Collectors.toMap(
                        p -> p.age,
                        p -> p.name,
                        (name1, name2) -> name1 + ";" + name2));

        System.out.println(map);
    }

    static void threadTest() {
        List<MyTask> tasks = IntStream.range(0, 10)
                .mapToObj(i -> new MyTask(i + 1))
                .collect(toList());

        useCompletableFuture(tasks);
    }

    public static void useCompletableFuture(List<MyTask> tasks) {
        long start = System.nanoTime();

        List<CompletableFuture<Integer>> futures = tasks
                .stream()
                .map(tsk -> CompletableFuture.supplyAsync(tsk::calculate))
                .collect(Collectors.toList());

        List<Integer> result = futures
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("processed %d tasks in %d millis \n", tasks.size(), duration);
        System.out.println(result);
    }

    public static void main(String[] args) {
//        threadTest();

        System.out.println("go");
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            try {
                // send data to aws.
                System.out.println("send data to aws.");
                TimeUnit.SECONDS.sleep(5L);
//                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 200;
        });
        System.out.println("end");

        cf.join();
        System.out.println("send data to server!");
    }
}
