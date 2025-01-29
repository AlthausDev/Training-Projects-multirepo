package org.example;

import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Introduzca una frase");
        System.out.println(esPalindromo(sc.nextLine()));
    }

    private static boolean esPalindromo(String s) {

        s = s.toLowerCase().replaceAll("[^a-z]", "");
        return s.contentEquals(new StringBuilder(s).reverse());
    }
}