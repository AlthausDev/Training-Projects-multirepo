package com.althaus.dev.ProcessMaster.network.client;

import java.io.*;
import java.net.*;

public class ClienteUDP extends Cliente {
    private String servidorHost;
    private int puerto;

    public ClienteUDP(String servidorHost, int puerto) {
        this.servidorHost = servidorHost;
        this.puerto = puerto;
    }

    @Override
    public void enviarMensaje(Object mensaje) {
        try (DatagramSocket socket = new DatagramSocket()) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(mensaje);
            byte[] data = outputStream.toByteArray();
            DatagramPacket paquete = new DatagramPacket(data, data.length, InetAddress.getByName(servidorHost), puerto);
            socket.send(paquete);

            byte[] buffer = new byte[1024];
            DatagramPacket respuestaPaquete = new DatagramPacket(buffer, buffer.length);
            socket.receive(respuestaPaquete);
            ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(respuestaPaquete.getData()));
            Object respuesta = is.readObject();
            System.out.println("Respuesta del servidor: " + respuesta.toString());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
