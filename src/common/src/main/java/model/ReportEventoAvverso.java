package model;

public class ReportEventoAvverso {
    private String evento;
    private int numeroSegnalazioni;
    private double severitaMedia;

    public ReportEventoAvverso(String evento, int numeroSegnalazioni, double severitaMedia) {
        this.evento = evento;
        this.numeroSegnalazioni = numeroSegnalazioni;
        this.severitaMedia = severitaMedia;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public int getNumeroSegnalazioni() {
        return numeroSegnalazioni;
    }

    public void setNumeroSegnalazioni(int numeroSegnalazioni) {
        this.numeroSegnalazioni = numeroSegnalazioni;
    }

    public double getSeveritaMedia() {
        return severitaMedia;
    }

    public void setSeveritaMedia(double severitaMedia) {
        this.severitaMedia = severitaMedia;
    }
}
