package util;

/**
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class FixInput {

    public static String fixString(String string){
        String[] array = string.split(" ");
        StringBuffer newString = new StringBuffer();
        for (String str: array) {
            newString.append(str.substring(0, 1).toUpperCase() +
                    str.substring(1).toLowerCase());
            newString.append(" ");
        }
        newString.deleteCharAt(newString.length()-1);
        return newString.toString();
    }

}
