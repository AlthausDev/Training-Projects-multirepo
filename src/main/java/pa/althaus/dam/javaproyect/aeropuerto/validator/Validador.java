package pa.althaus.dam.javaproyect.aeropuerto.validator;

/**
 * @author samuelaa
 */
public class Validador {

    public boolean prefixValid(int prefix) {
        return prefix <= 999 && prefix >= 0;
    }

    public boolean comCodValid(String code) {
        //codigo toUpperCase()
        return code.length() == 2 && code.matches("[A-Z][A-Z0-9]");
    }

    public boolean tlfValid(String tlf) {

        if (tlf.length() > 15) {
            return false;
        } else {
            //2 partes, la primera un String de 3 digitos
            // readListaCodigos .. equals()        
            //la segunda <= 12 digitos, minimo ¿8?
            String codigo = tlf.substring(0, 2);
            String numTlf = tlf.substring(3, tlf.length());
            int tlfSize = numTlf.length();

            try {
                Integer.valueOf(numTlf);
            } catch (NumberFormatException e) {

            }
        }         
        return tlf.matches("[0-9]{1,12}");
    }

    public boolean flyCodValid(String code) {
        
         if (comCodValid(code.substring(0, 1))){
             String codVuelo = code.substring(2, code.length());
             return codVuelo.matches("[%d]{1,4}");
         }  else{
                     return false;
         }   
    }

    public boolean operatingDaysValid() {
        return true;
    }
}
