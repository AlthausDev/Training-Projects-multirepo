package com.althaus.dev.ProcessMaster.network.client;

import java.io.*;
import java.net.*;

public class ClienteTCP extends Cliente {
    private String servidorHost;
    private int puerto;

    public ClienteTCP(String servidorHost, int puerto) {
        this.servidorHost = servidorHost;
        this.puerto = puerto;
    }

    @Override
    public void enviarMensaje(Object mensaje) {
        try (Socket socket = new Socket(servidorHost, puerto);
             ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream())) {

            salida.writeObject(mensaje);
            Object respuesta = entrada.readObject();
            System.out.println("Respuesta del servidor: " + respuesta.toString());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

