package com.althaus.dev.ProcessMaster;

import com.althaus.dev.ProcessMaster.network.client.Cliente;
import com.althaus.dev.ProcessMaster.network.client.ClienteTCP;
import com.althaus.dev.ProcessMaster.network.client.ClienteUDP;

import java.util.Scanner;

public class ClientMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al cliente.");

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Enviar mensaje al servidor");
            System.out.println("2. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    enviarMensaje(scanner);
                    break;
                case 2:
                    System.out.println("Saliendo del cliente.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }

    private static void enviarMensaje(Scanner scanner) {
        System.out.println("Por favor, seleccione el tipo de protocolo (TCP/UDP): ");
        String protocolo = scanner.nextLine().toUpperCase();

        // Solicitar al usuario los datos a enviar al servidor
        System.out.println("Ingrese los datos a enviar al servidor: ");
        String datos = scanner.nextLine();

        // Crear instancia del cliente según el protocolo seleccionado
        Cliente client;
        if (protocolo.equals("TCP")) {
            client = new ClienteTCP("localhost", 12345);
        } else if (protocolo.equals("UDP")) {
            client = new ClienteUDP("localhost", 9876);
        } else {
            System.out.println("Protocolo no válido.");
            return;
        }

        // Enviar los datos al servidor y recibir la respuesta
        client.enviarMensaje(datos);
    }
}
