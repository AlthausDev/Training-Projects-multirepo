package org.example;

import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Introduzca la posición del número de la secuencia de Fibonacci que desea ver:");
        int position = sc.nextInt();
        System.out.println("Fibonacci en posición " + position + ": " + fibonacci(position));
    }

    private static long fibonacci(int position) {
        if (position == 0) return 0;
        if (position == 1) return 1;

        long i = 0, j = 1;

        for (int z = 1; z < position; z++) {
            j = i + (i = j); //
        }

        return j;
    }

}
