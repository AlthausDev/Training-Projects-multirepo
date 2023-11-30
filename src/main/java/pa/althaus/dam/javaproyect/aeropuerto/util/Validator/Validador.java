package pa.althaus.dam.javaproyect.aeropuerto.util.Validator;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author samuelaa
 */
public class Validador {

    /**
     * Clase encargada de realizar validaciones en el contexto del aeropuerto.
     *
     * @author samuelaa
     */

    public boolean esPrefijoValido(int prefijo) {
        return prefijo <= 999 && prefijo >= 0;
    }

    public boolean esCodigoCompaniaValido(String codigo) {
        return codigo.length() == 2 && codigo.toUpperCase().matches("[A-Z][A-Z0-9]");
    }

    public boolean esTelefonoValido(String telefono) {
        return telefono.matches("[0-9]{4,15}");
    }

    public boolean esCodigoVueloValido(String codigo) {
        if (esCodigoCompaniaValido(codigo.substring(0, 1))) {
            String codVuelo = codigo.substring(2);
            return codVuelo.matches("[0-9]{1,4}");
        } else {
            return false;
        }
    }

    public boolean esCodigoMunicipioValido(String codMunicipio) {
        return codMunicipio.matches("[0-9]{5}");
    }

    public boolean sonDiasOperativosValidos(String dias) {
        if (dias != null && dias.length() <= 7) {
            return dias.matches("[L]?"
                    + "[M]?"
                    + "[X]?"
                    + "[J]?"
                    + "[V]?"
                    + "[S]?"
                    + "[D]?");
        } else {
            return false;
        }
    }

    //REVISAR
    public boolean esFechaDiaOperativo(Date fecha, String diasOperativos) {
        DateFormat formatoFecha = new SimpleDateFormat("E", new Locale("es", "ES"));
        String diasSemana = formatoFecha.format(fecha).toUpperCase();

        return diasOperativos.contains(diasSemana);
    }

}
