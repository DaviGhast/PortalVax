package model;

public class EventoAvverso {
    private short id;
    private String evento;
    private byte severità;
    private short idCittadino;
    private String note;

    public EventoAvverso(short id, String evento, byte severità, short idCittadino, String note){
        this.id = id;
        this.evento = evento;
        this.severità = severità;
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

    public byte getSeverità() {
        return severità;  }

    public void setSeverità(byte severità) {
        this.severità = severità; }

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
