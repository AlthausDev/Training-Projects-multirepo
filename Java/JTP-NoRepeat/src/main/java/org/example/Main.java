package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Introduzca una cadena de caracteres");
        System.out.println(noRepetidos(sc.nextLine()));
    }

    private static String noRepetidos(String palabra) {

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        for (Character c : palabra.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (Character c : palabra.toCharArray()) {
            if (map.get(c) == 1) {
                return c.toString();
            }
        }

        return "No hay caracteres no repetidos";
    }
}