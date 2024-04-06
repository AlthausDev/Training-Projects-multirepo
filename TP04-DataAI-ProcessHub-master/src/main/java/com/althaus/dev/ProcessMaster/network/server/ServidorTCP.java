package com.althaus.dev.ProcessMaster.network.server;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ServidorTCP {
    private int puerto;

    public ServidorTCP(int puerto) {
        this.puerto = puerto;
    }

    public void iniciarServidor() {
        ExecutorService pool = Executors.newFixedThreadPool(10);

        try (ServerSocket servidorSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor TCP iniciado. Esperando conexiones...");

            while (true) {
                Socket clienteSocket = servidorSocket.accept();
                pool.execute(new ManejadorClienteTCP(clienteSocket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ManejadorClienteTCP implements Runnable {
        private Socket clienteSocket;

        public ManejadorClienteTCP(Socket socket) {
            this.clienteSocket = socket;
        }

        @Override
        public void run() {
            try (ObjectInputStream entrada = new ObjectInputStream(clienteSocket.getInputStream());
                 ObjectOutputStream salida = new ObjectOutputStream(clienteSocket.getOutputStream())) {

                Object mensaje = entrada.readObject();
                // Procesar el mensaje recibido
                Object respuesta = procesarMensaje(mensaje);
                salida.writeObject(respuesta);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        private Object procesarMensaje(Object mensaje) {
            // Lógica de procesamiento del mensaje recibido
            return "Respuesta del servidor TCP";
        }
    }
}
