package model;

import java.io.Serializable;
/**
 * E' il modello dei cittadini registrati
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class CittadinoRegistrato implements Serializable {
    /**
     * l'id del'user
     */
    /**
     * oggetto String
     */private String userId;
    /**
     * e' la mail
     */
    /**
     * oggetto String
     */private String email;
    /**
     * e' la password
     */
    /**
     * oggetto String
     */private String password;
    /**
     * è il codice fiscale
     */
    /**
     * oggetto String
     */private String codiceFiscale;

    /**
     * Costruttore per l'oggetto CittadinoRegistarto
     * @param userId l'id del'user
     * @param email e' la mail
     * @param password e' la password
     * @param codiceFiscale è il codice fiscale
     */
    public CittadinoRegistrato(String userId, String email, String password, String codiceFiscale){
        this.userId = userId.toLowerCase();
        this.email = email.toLowerCase();
        this.password = password;
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * Costruttore vuoto per l'oggetto CittadinoRegistarto
     */
    public CittadinoRegistrato() {
    }
    /**
     * Il Metodo <code>getUserId</code> è il metodo che recupera l'id dell'user
     * @return ritorna il valore del id dell'user
     */
    public String getUserId() {
        return userId.toLowerCase();
    }
    /**
     * Il Metodo <code>setUserId</code> è il metodo che salva l'id dell'user
     * @param userId stringa contenente l'id dell'user
     */
    public void setUserId(String userId) {
        this.userId = userId.toLowerCase();
    }
    /**
     * Il Metodo <code>getEmail</code> è il metodo che recupera la mail
     * @return ritorna il valore della mail
     */
    public String getEmail() {
        return email.toLowerCase();
    }
    /**
     * Il Metodo <code>setEmail</code> è il metodo che salva la mail
     * @param email stringa contenente la mail
     */
    public void setEmail(String email) { this.email = email.toLowerCase(); }
    /**
     * Il Metodo <code>getPassword</code> è il metodo che recupera la password
     * @return ritorna il valore della password
     */
    public String getPassword() {
        return password;
    }
    /**
     * Il Metodo <code>setPassword</code> è il metodo che salva la password
     * @param password stringa contenente la password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Il Metodo <code>getCodiceFiscale</code> è il metodo che recupera il codice fiscale
     * @return ritorna il valore del codice fiscale
     */
    public String getCodiceFiscale() { return codiceFiscale; }
    /**
     * Il Metodo <code>setCodiceFiscale</code> è il metodo che salva il codice fiscale
     * @param codiceFiscale stringa contenente il codice fiscale
     */
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }


}
