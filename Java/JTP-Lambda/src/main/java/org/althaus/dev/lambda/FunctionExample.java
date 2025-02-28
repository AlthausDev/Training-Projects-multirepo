package org.althaus.dev.lambda;

import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionExample {

    public static void main(String[] args) {

        Function<String, String> f1 = param -> "Hello " + param;

        String result = f1.apply("World");
        System.out.println(result);

        Function<String, String> f2 = String::toUpperCase;

        result = f2.apply("World");
        System.out.println(result);

        BiFunction<String, String, String> f3 = (a, b) -> a + " " + b.toUpperCase();
        result = f3.apply("Hello", "World");
        System.out.println(result);

        BiFunction<String, String, Integer> f4 = (a, b) -> a.length() + b.length();
        System.out.println(f4.apply("Hello", "World"));

        BiFunction<String, String, String> f5 = String::concat;
        System.out.println(f5.apply("Hello", "World"));

        BiFunction<String, String, Integer> f6 = String::compareTo;
        System.out.println(f6.apply("Hello", "World"));
    }
}