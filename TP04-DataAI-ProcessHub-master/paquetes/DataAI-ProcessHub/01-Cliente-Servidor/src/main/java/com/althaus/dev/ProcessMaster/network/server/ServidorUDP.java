package com.althaus.dev.ProcessMaster.network.server;

import java.io.*;
import java.net.*;

public class ServidorUDP {
    private int puerto;

    public ServidorUDP(int puerto) {
        this.puerto = puerto;
    }

    public void iniciarServidor() {
        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            System.out.println("Servidor UDP iniciado. Esperando conexiones...");

            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.receive(paquete);
                InetAddress direccionCliente = paquete.getAddress();
                int puertoCliente = paquete.getPort();

                ByteArrayInputStream bais = new ByteArrayInputStream(paquete.getData());
                ObjectInputStream is = new ObjectInputStream(bais);
                Object mensaje = is.readObject();
                // Procesar el mensaje recibido
                Object respuesta = procesarMensaje(mensaje);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream os = new ObjectOutputStream(baos);
                os.writeObject(respuesta);
                byte[] datosRespuesta = baos.toByteArray();
                DatagramPacket respuestaPaquete = new DatagramPacket(datosRespuesta, datosRespuesta.length, direccionCliente, puertoCliente);
                socket.send(respuestaPaquete);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Object procesarMensaje(Object mensaje) {
        // Lógica de procesamiento del mensaje recibido
        return "Respuesta del servidor UDP";
    }
}
