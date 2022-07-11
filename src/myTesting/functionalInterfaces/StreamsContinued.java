package myTesting.functionalInterfaces;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamsContinued {

    public static int sumOfNumsStream() {
        return IntStream.rangeClosed(1,9).sum();
    }

    public static List<Integer> boxing() {
        return IntStream.rangeClosed(1, 25)
                //int
                .boxed()
                ///Integer
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        //Diffrent Ways to Create Streams
        Stream<String> namesStream = Stream.of("adam", "ryan", "eric");
        namesStream.forEach(System.out::println);

        Stream.iterate(1, x-> x*2) //Generates numbers
                .limit(10)
                .forEach(System.out::println);
        System.out.println();
        Supplier<Integer> intSupp = new Random()::nextInt;
        Stream.generate(intSupp) // creates items from a supplier
                .limit(1).forEach(System.out::println);
        System.out.println();

        //Numeric Streams
        System.out.println("Sum of nums 1-9 via streams: " + sumOfNumsStream());

        IntStream intStreamEx = IntStream.range(1,50);
        System.out.println("Total Range Count: " + intStreamEx.count());

        LongStream longStreamClosed = LongStream.rangeClosed(1,50);
        System.out.println("Total Closed Range Count: " + longStreamClosed.count());



    }
}
