package model;

import java.io.Serializable;
/**
 * E' il modello dei CentriVaccinali
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class CentroVaccinale implements Serializable {
    /**
     * l' <code>id</code> univoco del centro vaccinale
     */
    private short id;
    /**
     * il <code>nomeCentroVaccinale</code> indica il nome del centro vaccinale
     */
    /**
     * oggetto String
     */
    private String nomeCentroVaccinale;
    /**
     *il <code>indirizzo</code> indica l'indirizzo (qualificatore via/v.le/pzza, nome, numero civico)
     */
    /**
     * oggetto String
     */
    private String indirizzo;
    /**
     * il <code>comune</code> in cui e' siuato il centro vaccinale
     */
    /**
     * oggetto String
     */
    private String comune;
    /**
     * la <code>siglaProvincia</code> indica sigla della provincia del centro vaccinale
     */
    /**
     * oggetto String
     */
    private String siglaProvincia;
    /**
     * la <code>tipologia</code> indica la tipologia dei centro vaccinali
     */
    /**
     * oggetto String
     */
    private String tipologia;
    /**
     *il <code>cap</code> indica il codice postale del centro vaccinale
     */
    private int cap;

    /**
     * Costruttore per l'oggetto CetroVaccinale
     * @param id l'id della vaccinazione
     * @param nomeCentroVaccinale e' il nome del centro
     * @param indirizzo e' l'indirizzo (qualificatore via/v.le/pzza, nome, numero civico)
     * @param comune e' il nome del comune
     * @param siglaProvincia e' la sigla della provincia
     * @param tipologia e' il tipo di struttura del centro
     * @param cap e' il codice postale
     */
    public CentroVaccinale(short id, String nomeCentroVaccinale, String indirizzo, String comune,
                           String siglaProvincia, String tipologia, int cap){
        this.id = id;
        this.nomeCentroVaccinale = nomeCentroVaccinale;
        this.indirizzo = indirizzo;
        this.comune = comune;
        this.siglaProvincia = siglaProvincia;
        this.tipologia = tipologia;
        this.cap = cap;
    }
    /**
     *costruttore vuoto
     */
    public CentroVaccinale(){
    }

    /**
     * @return id Id CentroVaccinale
     */
    public short getId() {
        return id;
    }

    /**
     * @param id id CentroVaccinale
     */
    public void setId(short id) {
        this.id = id;
    }

    /**
     * @return nomeCentroVaccinale  il nome del CentroVaccinale
     */

    public String getNomeCentroVaccinale() {
        return nomeCentroVaccinale;
    }

    /**
     * @param nomeCentroVaccinale il nome del CentroVaccinale
     */
    public void setNomeCentroVaccinale(String nomeCentroVaccinale) {
        this.nomeCentroVaccinale = nomeCentroVaccinale;
    }

    /**
     * @return indirizzo l'indirizzo (qualificatore via/v.le/pzza, nome, numero civico)
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * @param indirizzo l'indirizzo (qualificatore via/v.le/pzza, nome, numero civico)
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * @return comune il nome del comune
     */
    public String getComune() {
        return comune;
    }

    /**
     * @param comune il nome del comune
     */
    public void setComune(String comune) {
        this.comune = comune;
    }

    /**
     *
     * @return siglaProvincia la sigla della provincia
     */
    public String getSiglaProvincia() {
        return siglaProvincia;
    }

    /**
     * @param siglaProvincia la sigla della provincia
     */
    public void setSiglaProvincia(String siglaProvincia) {
        this.siglaProvincia = siglaProvincia;
    }

    /**
     * @return tipologia la tipologia del centroVaccinale
     */
    public String getTipologia() {
        return tipologia;
    }

    /**
     * @param tipologia la tipologia del centroVaccinale
     */
    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    /**
     * @return cap il codice postale del centroVaccinale
     */
    public int getCap() {
        return cap;
    }

    /**
     * @param cap il codice postale del centroVaccinale
     */
    public void setCap(int cap) {
        this.cap = cap;
    }

}

