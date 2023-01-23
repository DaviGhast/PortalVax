package model;

import java.io.Serializable;
/**
 * E' il modello dei eventi avversi
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class EventoAvverso implements Serializable {
    private short id;
    private String evento;
    private byte severita;
    private String idCittadino;
    private String note;
    /**
     * Costruttore per l'oggetto EventoAvverso
     * @param id l'id del'user
     * @param evento e' l'event
     * @param severita e' la severita
     * @param idCittadino è l'id del cittadino
     * @param note sono le note
     */
    public EventoAvverso(short id, String evento, byte severita, String idCittadino, String note){
        this.id = id;
        this.evento = evento;
        this.severita = severita;
        this.idCittadino = idCittadino;
        this.note = note;
    }

    public EventoAvverso(){
    }
    /**
     * Il Metodo <code>getId</code> è il metodo che recupera l'id dell'evento avverso
     * @return ritorna il valore dell'id
     */
    public short getId() {
        return id;
    }
    /**
     * Il Metodo <code>setId</code> è il metodo che salva l'id dell'evento avverso
     * @param id short  contenente l'id
     */
    public void setId(short id) { this.id = id;}
    /**
     * Il Metodo <code>getEvento</code> è il metodo che recupera l'evento
     * @return ritorna il valore dell'evento
     */
    public String getEvento() {
        return evento;  }
    /**
     * Il Metodo <code>setEvento</code> è il metodo che salva levento
     * @param evento stringa contenente l'evento
     */
    public void setEvento(String evento) {
        this.evento = evento; }
    /**
     * Il Metodo <code>getSeverità</code> è il metodo che recupera la severità
     * @return ritorna il valore della severità
     */
    public byte getSeverita() {
        return severita;  }
    /**
     * Il Metodo <code>setSeverità</code> è il metodo che salva la severità
     * @param severita contiene i dati della severita
     */
    public void setSeverita(byte severita) {
        this.severita = severita; }
    /**
     * Il Metodo <code>getIdCittadino</code> è il metodo che recupera l'id del cittadino
     * @return ritorna il valore del'id del cittadino
     */
    public String getIdCittadino() {
        return idCittadino;  }
    /**
     * Il Metodo <code>setIdCittadino</code> è il metodo che salva l'id del citadino
     * @param idCittadino contiene l'id del cittadino
     */
    public void setIdCittadino(String idCittadino) {
        this.idCittadino = idCittadino;
    }
    /**
     * Il Metodo <code>getNote</code> è il metodo che recupera le note
     * @return ritorna il valore delle note
     */
    public String getNote() {
        return note;
    }
    /**
     * Il Metodo <code>setNote</code> è il metodo che salva le note
     * @param note contiene il valore delle note
     */
    public void setNote(String note) {
        this.note = note; }
}
