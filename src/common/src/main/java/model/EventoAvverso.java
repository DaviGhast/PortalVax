package model;

public class EventoAvverso {
    private short id;
    private String evento;
    private short severità;
    private short idCittadino;
    private String note;

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
