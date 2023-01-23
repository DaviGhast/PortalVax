package util;

/**
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class FixInput {

    public static String tuttePrimeLettereMaiuscole(String string){
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

    public static String aggiungiSpazi(String string){
        String[] caratteri = {".",",",":","?","!"};
        for (String carattere: caratteri) {
            if (string.contains(carattere)) {
                int index = string.indexOf(carattere);
                if (string.charAt(index+1) != ' ') {
                    String strPre = string.substring(0,index+1);
                    String strSuf = string.substring(index+1);
                    string = strPre + " " + strSuf;
                }
            }
        }
        return string;
    }

}
