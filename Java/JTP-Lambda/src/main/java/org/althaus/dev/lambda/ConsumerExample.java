package org.althaus.dev.lambda;

import org.althaus.dev.lambda.model.User;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ConsumerExample {
    public static void main(String[] args) {

        Consumer <Date> consumer = fecha -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println(sdf.format(fecha));
        };

        consumer.accept(new Date());

        BiConsumer<String, Integer> biConsumer = (name, age) -> {
            System.out.println("Name: " + name + ", Age: " + age);
        };

        biConsumer.accept("John", 25);

        Consumer<String> consumer2 = System.out::println;
        consumer2.accept("Hello");

        List<String> nombres = Arrays.asList("Pepe", "Antonio", "Luis");
        nombres.forEach(consumer2);

        Supplier<User> newUserSupplier = User::new;
        User user = newUserSupplier.get();

        BiConsumer<User, String> asignarNombre = User::setName;
        asignarNombre.accept(user, "Pepe");
        System.out.println(user.getName());

        Supplier<String> supplier = () -> "Hello";
        System.out.println(supplier.get());
    }
}