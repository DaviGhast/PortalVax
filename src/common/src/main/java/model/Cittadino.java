package model;

public class Cittadino {
    private String codiceFiscale;
    private String nomeCittadino;
    private String cognomeCittadino;

    public Cittadino() {
    }
    public String getCodiceFiscale() { return codiceFiscale;}

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getNomeCittadino() {
        return nomeCittadino;
    }

    public void setNomeCittadino(String nomeCittadino) {this.nomeCittadino= nomeCittadino; }

    public String getCognomeCittadino() {
        return cognomeCittadino;
    }

    public void setCognomeCittadino(String cognomeCittadino) {
        this.cognomeCittadino = cognomeCittadino;
    }
}