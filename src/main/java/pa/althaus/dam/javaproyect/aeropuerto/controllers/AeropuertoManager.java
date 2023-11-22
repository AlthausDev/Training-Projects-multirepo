package pa.althaus.dam.javaproyect.aeropuerto.controllers;

public class AeropuertoManager {

    private static final String CODIGO_AEROPUERTO;

    static {
        // Lee el código del aeropuerto desde el archivo de recursos al inicializar la clase
        // Aquí debes implementar la lógica para leer el código del aeropuerto desde el archivo de recursos
        CODIGO_AEROPUERTO = leerCodigoAeropuertoDesdeRecursos();
    }

    // Método para determinar si un vuelo es de llegada
    public static boolean esVueloDeLlegada(String codigoVuelo) {
        // Lógica para determinar si el vuelo es de llegada basado en el código del aeropuerto
        return codigoVuelo.startsWith(CODIGO_AEROPUERTO);
    }

    // Método para determinar si un vuelo es de salida
    public static boolean esVueloDeSalida(String codigoVuelo) {
        // Lógica para determinar si el vuelo es de salida basado en el código del aeropuerto
        return !esVueloDeLlegada(codigoVuelo);
    }

    // Método ficticio para simular la lectura del código del aeropuerto desde los recursos
    private static String leerCodigoAeropuertoDesdeRecursos() {
        // Implementa la lógica real para leer el código del aeropuerto desde los recursos
        return "AEROPUERTO1";
    }
}
