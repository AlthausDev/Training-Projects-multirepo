package org.althaus.dev.lambda.model;

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
    }
}
