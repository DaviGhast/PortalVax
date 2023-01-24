package model;

import java.io.Serializable;
/**
 * E' il modello dei report dei eventi avversi
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class ReportEventoAvverso implements Serializable {
    /**
     * l'evento
     */
    private String evento;
    /**
     * e' il numero delle segnalazioni
     */
    private int numeroSegnalazioni;
    /**
     * e' la severita media
     */
    private double severitaMedia;
    /**
     * Costruttore per l'oggetto ReportEventoAvverso
     * @param evento l'evento
     * @param numeroSegnalazioni e' il numero delle segnalazioni
     * @param severitaMedia e' la severita media
     */
    public ReportEventoAvverso(String evento, int numeroSegnalazioni, double severitaMedia) {
        this.evento = evento;
        this.numeroSegnalazioni = numeroSegnalazioni;
        this.severitaMedia = severitaMedia;
    }
    /**
     * Il Metodo <code>getEvento</code> è il metodo che recupera l'evento
     * @return ritorna il valore del'evento
     */
    public String getEvento() {
        return evento;
    }
    /**
     * Il Metodo <code>setEvento</code> è il metodo che salva l'evento
     * @param evento stringa contenente il valore dell'evento
     */
    public void setEvento(String evento) {
        this.evento = evento;
    }
    /**
     * Il Metodo <code>getNumeroSegnalazioni</code> è il metodo che recupera il numero delle segnalazioni
     * @return ritorna il numero delle segnalazioni
     */
    public int getNumeroSegnalazioni() {
        return numeroSegnalazioni;
    }
    /**
     * Il Metodo <code>setNumeroSegnalazioni</code> è il metodo che salva il numero delle segnalazioni
     * @param numeroSegnalazioni intero contenente il numero delle segnalazioni
     */
    public void setNumeroSegnalazioni(int numeroSegnalazioni) {
        this.numeroSegnalazioni = numeroSegnalazioni;
    }
    /**
     * Il Metodo <code>getSeveritaMedia</code> è il metodo che recupera il valore della severita media
     * @return ritorna il valore della severitra media
     */
    public double getSeveritaMedia() {
        return severitaMedia;
    }
    /**
     * Il Metodo <code>setSeveritaMedia</code> è il metodo che salva il valore della severita media
     * @param severitaMedia double contenente il valore della severita media
     */
    public void setSeveritaMedia(double severitaMedia) {
        this.severitaMedia = severitaMedia;
    }
}
