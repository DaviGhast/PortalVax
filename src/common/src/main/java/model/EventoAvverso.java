package model;

import java.io.Serializable;

public class EventoAvverso implements Serializable {
    private short id;
    private String evento;
    private byte severita;
    private short idCittadino;
    private String note;

    public EventoAvverso(short id, String evento, byte severita, short idCittadino, String note){
        this.id = id;
        this.evento = evento;
        this.severita = severita;
        this.idCittadino = idCittadino;
        this.note = note;
    }

    public EventoAvverso(){
    }
    public short getId() {
        return id;
    }

    public void setId(short id) { this.id = id;}

    public String getEvento() {
        return evento;  }

    public void setEvento(String evento) {
        this.evento = evento; }

    public byte getSeverita() {
        return severita;  }

    public void setSeverita(byte severita) {
        this.severita = severita; }

    public short getIdCittadino() {
        return idCittadino;  }

    public void setIdCittadino(short idCittadino) {
        this.idCittadino = idCittadino;
    }
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note; }
}
