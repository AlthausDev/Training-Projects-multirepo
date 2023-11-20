package pa.althaus.dam.javaproyect.aeropuerto.validator;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author samuelaa
 */
public class Validador {

    public boolean isPrefixValid(int prefix) {
        return prefix <= 999 && prefix >= 0;
    }

    public boolean isCompanyCodValid(String code) {
        return code.length() == 2 && code.toUpperCase().matches("[A-Z][A-Z0-9]");
    }

    public boolean isTlfValid(String tlf) {
        return tlf.matches("[0-9]{4,15}");
    }

    public boolean isFlyCodValid(String code) {

        if (isCompanyCodValid(code.substring(0, 1))) {
            String codVuelo = code.substring(2, code.length());
            return codVuelo.matches("[%d]{1,4}");
        } else {
            return false;
        }
    }

    public boolean isSpanish(String codMunicipio) {
        return codMunicipio.matches("[%d]{5}");
    }

    public boolean isOperatingDaysValid(String days) {
        if (days != null && days.length() <= 7) {
            return days.matches(
                    "[L]? "
                    + "[M]? "
                    + "[X]? "
                    + "[J]? "
                    + "[V]? "
                    + "[S]? "
                    + "[D]? "
                    + "{1,7}");
        } else {
            return false;
        }
    }

    public boolean isFechaOperatingDay(Date fecha, String operatingDays) {
        Format f = new SimpleDateFormat("E");
        if (operatingDays.contains(f.format(fecha))) {
            return true;
        } else {
            return false;
        }
    }

    // REVISAR
    public static int getDayNumberOld(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }
}
