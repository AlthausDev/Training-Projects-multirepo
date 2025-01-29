package org.example;

import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    private static String palabra1, palabra2;

    public static void main(String[] args) {
        System.out.println("Introduzca la primera palabra");
        palabra1 = sc.nextLine();
        System.out.println("Introduzca la segunda palabra");
        palabra2 = sc.nextLine();

        System.out.println(anagrama(palabra1, palabra2));
    }

    private static boolean anagrama(String palabra1, String palabra2) {

        String filtro1 = palabra1.toLowerCase().replaceAll("[^a-z]", "");
        String filtro2 = palabra2.toLowerCase().replaceAll("[^a-z]", "");

        if (filtro1.length() != filtro2.length()) {
            return false;
        }

        char[] arr1 = filtro1.toCharArray();
        char[] arr2 = filtro2.toCharArray();

        java.util.Arrays.sort(arr1);
        java.util.Arrays.sort(arr2);

        return java.util.Arrays.equals(arr1, arr2);
    }

}