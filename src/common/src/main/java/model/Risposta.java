package model;

import java.io.Serializable;
/**
 * E' il modello della risposta
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class Risposta implements Serializable {
    /**
     * tipo di stato
     * @see Stato
     */
    private Stato stato;
    /**
     * oggetto messaggio
     */
    private String message;
    /**
     * oggetto generico
     */
    private Object object;

    /**
     * Costruttore vuoto oggetto Risposta
     */
    public Risposta() {

    }
    /**
     * Il Metodo <code>getStato</code> è il metodo che recupera lo stato
     * @return ritorna il valore dello stato
     */
    public Stato getStato() {
        return stato;
    }
    /**
     * Il Metodo <code>setStato</code> è il metodo che salva lo stato
     * @param stato stringa contenente ilm valore dello stato
     */
    public void setStato(Stato stato) {
        this.stato = stato;
    }
    /**
     * Il Metodo <code>getMessage</code> è il metodo che recupera il messaggio
     * @return ritorna il valore del messaggio
     */
    public String getMessage() {
        return message;
    }
    /**
     * Il Metodo <code>setMessage</code> è il metodo che salva il messaggio
     * @param message stringa contenente il messaggio
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * Il Metodo <code>getObject</code> è il metodo che recupera l'oggetto'
     * @return ritorna il valore dell'oggetto
     */
    public Object getObject() {
        return object;
    }
    /**
     * Il Metodo <code>setObject</code> è il metodo che salva l'oggetto
     * @param object stringa contenente l'oggetto
     */
    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * Metodo che crea la risposta
     * @param stato contiene lo stato
     * @param message contiene il messaggio
     * @param object contiene l'oggetto
     */
    public Risposta(Stato stato, String message, Object object) {
        this.stato = stato;
        this.message = message;
        this.object = object;
    }

    /**
     * Metodo che crea la risposta
     * @param stato contiene lo stato
     * @param message contiene il messaggio
     */
    public Risposta(Stato stato, String message) {
        this.stato = stato;
        this.message = message;
    }

    /**
     * Metodo che crea la risposta
     * @param stato contiene lo stato
     * @param object contiene l'oggetto
     */
    public Risposta(Stato stato, Object object) {
        this.stato = stato;
        this.object = object;
    }
}

