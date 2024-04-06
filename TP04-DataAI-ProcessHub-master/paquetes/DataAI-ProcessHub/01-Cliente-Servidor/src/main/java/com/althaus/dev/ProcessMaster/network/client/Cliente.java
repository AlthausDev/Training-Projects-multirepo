package com.althaus.dev.ProcessMaster.network.client;

import com.althaus.dev.ProcessMaster.data.DatosPrueba;
import com.althaus.dev.ProcessMaster.model.Mensaje;
import com.althaus.dev.ProcessMaster.model.MiObjeto;
import com.althaus.dev.ProcessMaster.model.TipoDato;

import java.io.*;
import java.net.*;

public abstract class Cliente {

    private static final String HOST = "localhost";
    private static final int PUERTO_TCP = 12345;
    private static final int PUERTO_UDP = 9876;

    public static void main(String[] args) {
        try (
                Socket clienteSocketTCP = new Socket(HOST, PUERTO_TCP);
                ObjectOutputStream salidaTCP = new ObjectOutputStream(clienteSocketTCP.getOutputStream());
                ObjectInputStream entradaTCP = new ObjectInputStream(clienteSocketTCP.getInputStream());
                DatagramSocket clienteSocketUDP = new DatagramSocket()
        ) {
            // Comunicación TCP
            comunicacionTCP(salidaTCP, entradaTCP);

            // Comunicación UDP
            comunicacionUDP(clienteSocketUDP);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void comunicacionTCP(ObjectOutputStream salidaTCP, ObjectInputStream entradaTCP) throws IOException, ClassNotFoundException {
        // Envío del objeto al servidor TCP
        MiObjeto objeto = new MiObjeto("Mensaje desde el cliente TCP");
        salidaTCP.writeObject(objeto);
        System.out.println("Objeto enviado al servidor TCP.");

        // Recibiendo el objeto modificado del servidor TCP
        MiObjeto objetoModificado = (MiObjeto) entradaTCP.readObject();
        System.out.println("Objeto modificado recibido del servidor TCP: " + objetoModificado.getMensaje());

        // Envío de mensaje entero al servidor TCP
        Mensaje mensajeEntero = new Mensaje(TipoDato.ENTERO, 42);
        salidaTCP.writeObject(mensajeEntero);
        System.out.println("Mensaje entero enviado al servidor TCP.");

        // Recibiendo respuesta del servidor TCP
        Object respuestaEntero = entradaTCP.readObject();
        System.out.println("Respuesta recibida del servidor TCP: " + respuestaEntero);
    }

    private static void comunicacionUDP(DatagramSocket clienteSocketUDP) throws IOException, ClassNotFoundException {
        // Envío de datos de prueba al servidor UDP
        DatosPrueba datosUDP = new DatosPrueba(5);
        ByteArrayOutputStream salidaStream = new ByteArrayOutputStream();
        ObjectOutputStream salidaUDP = new ObjectOutputStream(salidaStream);
        salidaUDP.writeObject(datosUDP);

        byte[] bufferSalida = salidaStream.toByteArray();
        DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, InetAddress.getByName(HOST), PUERTO_UDP);
        clienteSocketUDP.send(paqueteSalida);
        System.out.println("Datos de prueba enviados al servidor UDP.");

        // Recibiendo los datos de prueba modificados del servidor UDP
        byte[] bufferEntrada = new byte[1024];
        DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
        clienteSocketUDP.receive(paqueteEntrada);

        ByteArrayInputStream entradaStream = new ByteArrayInputStream(paqueteEntrada.getData());
        ObjectInputStream entradaUDP = new ObjectInputStream(entradaStream);
        DatosPrueba datosModificadosUDP = (DatosPrueba) entradaUDP.readObject();
        System.out.println("Datos de prueba modificados recibidos del servidor UDP: " + datosModificadosUDP.getNumero());
    }

    public abstract void enviarMensaje(Object mensaje);
}
