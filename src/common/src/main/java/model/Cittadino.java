package model;

import java.io.Serializable;

public class Cittadino implements Serializable {
    private String codiceFiscale;
    private String nomeCittadino;
    private String cognomeCittadino;
    public Cittadino(String codiceFiscale, String nomeCittadino, String cognomeCittadino){
        this.codiceFiscale = codiceFiscale;
        this.nomeCittadino = nomeCittadino;
        this.cognomeCittadino = cognomeCittadino;
    }

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