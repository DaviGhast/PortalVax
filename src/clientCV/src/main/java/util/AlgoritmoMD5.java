package util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * La classe <code>AlgoritmoMD5</code>
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class AlgoritmoMD5 {
    /**
     * <code>Converti</code> è un metodo che data un password in chiaro,
     * la restituisce in criptazione MD5
     * @param password password passata dall'utente
     * @return password convertita
     */
    public static String converti(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(password.getBytes(Charset.forName("UTF-8")));
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                buffer.append(String.format("%x", array[i]));
            }
            return buffer.toString();

        } catch (NoSuchAlgorithmException exc) {
            return null;
        }
    }
}