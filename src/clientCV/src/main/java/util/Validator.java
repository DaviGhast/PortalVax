package util;

import java.util.regex.Pattern;

public class Validator {

    public static boolean codiceFiscale(String string) {
        return Pattern.matches("^[A-Z0-9]{16}", string);
    }

    public static boolean nomeAndCognome(String string) {
        return Pattern.matches("^[a-zA-Z]{2,30}", string);
    }
}
