package model;

import java.io.Serializable;
/**
 * E' il modello dei Cittadini
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */

public class Cittadino implements Serializable {
    /**
     * il codcie fiscale
     */
    /**
     * oggetto String
     */
    private String codiceFiscale;
    /**
     * e' il nome del cittadino
     */
    /**
     * oggetto String
     */
    private String nomeCittadino;
    /**
     * e' il cognome del cittadino
     */
    /**
     * oggetto String
     */
    private String cognomeCittadino;
    /**
     * Costruttore per l'oggetto Cittadino
     * @param codiceFiscale il codcie fiscale
     * @param nomeCittadino e' il nome del cittadino
     * @param cognomeCittadino e' il cognome del cittadino
     */
    public Cittadino(String codiceFiscale, String nomeCittadino, String cognomeCittadino){
        this.codiceFiscale = codiceFiscale;
        this.nomeCittadino = nomeCittadino;
        this.cognomeCittadino = cognomeCittadino;
    }
    /**
     * Costruttore vuoto per l'oggetto Cittadino
     */
    public Cittadino() {
    }

    /**
     * Il Metodo <code>getCodiceFiscale</code> è il metodo che recupera il codice fiscale
     * @return ritorna il valore del codice fiscale
     */
    public String getCodiceFiscale() { return codiceFiscale;}
    /**
     * Il Metodo <code>setCodiceFiscale</code> è il metodo che salva il codice fiscale
     * @param codiceFiscale stringa contenente il codice fiscale
     */
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
    /**
     * Il Metodo <code>getNomeCittadino</code> è il metodo che recupera il nome del cittadino
     * @return ritorna il valore del nome del cittadino
     */
    public String getNomeCittadino() {
        return nomeCittadino;
    }
    /**
     * Il Metodo <code>setNomeCittadino</code> è il metodo che salva il nome del cittadino
     * @param nomeCittadino stringa contenente il nome del cittadino
     */
    public void setNomeCittadino(String nomeCittadino) {this.nomeCittadino= nomeCittadino; }
    /**
     * Il Metodo <code>getCognomeCittadino</code> è il metodo che recupera il cognome del cittadino
     * @return ritorna il valore del cognome del cittadino
     */
    public String getCognomeCittadino() {
        return cognomeCittadino;
    }
    /**
     * Il Metodo <code>setCognomeCittadino</code> è il metodo che salva il cognome del cittadino
     * @param cognomeCittadino stringa contenente il cognome del cittadino
     */
    public void setCognomeCittadino(String cognomeCittadino) {
        this.cognomeCittadino = cognomeCittadino;
    }
}