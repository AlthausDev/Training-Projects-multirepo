/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package pa.althaus.dam.vitp.reflexiontest;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samuelaa
 */
public class VITP032ReflexionTest {

    public static void main(String[] args) {        
     
        try {
            Class<?> selfClazz = Class.forName("pa.althaus.dam.vitp.reflexiontest.Person");
           Constructor<?> ctor = selfClazz.getConstructor(String.class, String.class, int.class);
            Object p1 = ctor.newInstance("Robert", "Smith", 63);
            Method eq = selfClazz.getMethod("equals", Object.class);
            Object other = new Object();
            Person p2 = new Person ("Robert", "Smith", 63);
            
            Object isEqual = eq.invoke(p1, p2);
            System.out.println(isEqual);
            
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(VITP032ReflexionTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(VITP032ReflexionTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(VITP032ReflexionTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(VITP032ReflexionTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(VITP032ReflexionTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(VITP032ReflexionTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VITP032ReflexionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
