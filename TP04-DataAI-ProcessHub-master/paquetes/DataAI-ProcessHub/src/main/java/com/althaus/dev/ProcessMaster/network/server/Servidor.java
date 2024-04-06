package com.althaus.dev.ProcessMaster.network.server;

import com.althaus.dev.ProcessMaster.data.DatosPrueba;
import com.althaus.dev.ProcessMaster.model.Mensaje;
import com.althaus.dev.ProcessMaster.model.TipoDato;
import com.althaus.dev.ProcessMaster.model.MiObjeto;

import java.io.*;
import java.net.*;

public class Servidor {

    private static final int PUERTO_TCP = 12345;
    private static final int PUERTO_UDP = 9876;

    public static void main(String[] args) {
        try (ServerSocket servidorTCP = new ServerSocket(PUERTO_TCP);
             DatagramSocket servidorUDP = new DatagramSocket(PUERTO_UDP)) {

            System.out.println("Servidor iniciado. Esperando conexiones...");

            while (true) {
                manejarConexionTCP(servidorTCP);
                manejarConexionUDP(servidorUDP);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void manejarConexionTCP(ServerSocket servidorTCP) {
        try (Socket clienteSocket = servidorTCP.accept();
             ObjectInputStream entradaTCP = new ObjectInputStream(clienteSocket.getInputStream());
             ObjectOutputStream salidaTCP = new ObjectOutputStream(clienteSocket.getOutputStream())) {

            System.out.println("Cliente TCP conectado.");

            MiObjeto objetoRecibido = (MiObjeto) entradaTCP.readObject();
            System.out.println("Objeto recibido del cliente TCP: " + objetoRecibido.getMensaje());

            objetoRecibido.setMensaje("Mensaje modificado por el servidor TCP");

            salidaTCP.writeObject(objetoRecibido);
            System.out.println("Objeto modificado enviado al cliente TCP.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void manejarConexionUDP(DatagramSocket servidorUDP) {
        try {
            byte[] bufferUDP = new byte[1024];
            DatagramPacket paqueteUDP = new DatagramPacket(bufferUDP, bufferUDP.length);
            servidorUDP.receive(paqueteUDP);

            ByteArrayInputStream entradaStream = new ByteArrayInputStream(paqueteUDP.getData());
            ObjectInputStream entradaUDP = new ObjectInputStream(entradaStream);

            DatosPrueba datosRecibidos = (DatosPrueba) entradaUDP.readObject();
            System.out.println("Datos de prueba recibidos del cliente UDP: " + datosRecibidos.getNumero());

            datosRecibidos.setNumero(datosRecibidos.getNumero() * 2);

            ByteArrayOutputStream salidaStream = new ByteArrayOutputStream();
            ObjectOutputStream salidaUDP = new ObjectOutputStream(salidaStream);
            salidaUDP.writeObject(datosRecibidos);

            byte[] bufferSalida = salidaStream.toByteArray();
            DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length,
                    paqueteUDP.getAddress(), paqueteUDP.getPort());
            servidorUDP.send(paqueteSalida);
            System.out.println("Datos de prueba modificados enviados al cliente UDP.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Object manejarMensaje(Mensaje mensaje) {
        Object respuesta = null;

        switch (mensaje.getTipoDato()) {
            case ENTERO:
                int valorEntero = (int) mensaje.getContenido();
                //respuesta = /* Realizar operaciones necesarias */;
                break;
            case DOBLE:
                double valorDouble = (double) mensaje.getContenido();
                //respuesta = /* Realizar operaciones necesarias */;
                break;
            case CADENA:
                String cadena = (String) mensaje.getContenido();
                //respuesta = /* Realizar operaciones necesarias */;
                break;
        }

        return respuesta;
    }
}
