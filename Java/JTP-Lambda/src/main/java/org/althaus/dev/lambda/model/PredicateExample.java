package org.althaus.dev.lambda.model;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class PredicateExample {
    public static void main(String[] args) {
        Predicate<String> predicate = s -> s.length() > 5;
        System.out.println(predicate.test("Hello"));

        Predicate<String> predicate2 = Predicate.isEqual("Hello");
        System.out.println(predicate2.test("Hello"));

        Predicate<Object> predicate3 = Predicate.isEqual("Hello").negate();
        System.out.println(predicate3.test("Hello"));

        Predicate<Object> predicate4 = Predicate.isEqual("Hello").or(Predicate.isEqual("World"));
        System.out.println(predicate4.test("Hello"));

        Predicate<Object> predicate5 = Predicate.isEqual("Hello").and(Predicate.isEqual("World"));
        System.out.println(predicate5.test("Hello"));

        BiPredicate<String, String> t3 = String::equals;
        System.out.println(t3.test("Hello", "Hello"));

        BiPredicate<String, String> t4 = String::equalsIgnoreCase;
        System.out.println(t4.test("Hello", "hello"));

        BiPredicate<String, String> t5 = String::startsWith;
        System.out.println(t5.test("Hello", "H"));

        BiPredicate<Integer, Integer> t6 = Integer::equals;
        System.out.println(t6.test(5, 5));

        BiPredicate<Integer, Integer> t7 = (i, j) -> i > j;
        System.out.println(t7.test(5, 6));
    }
}
