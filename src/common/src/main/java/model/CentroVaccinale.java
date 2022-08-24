package model;

public class CentroVaccinale {
    /**
     * l' <code>id</code> univoco del centro vaccinale
     */
    private short id;
    /**
     * il <code>nomeCentroVaccinale</code> indica il nome del centro vaccinale
     */
    private String nomeCentroVaccinale;
    /**
     * il <code>qualificatoreIndirizzo</code> indica il tipo di luogo in cui e' situato l'indirizzo
     * */
    private String qualificatoreIndirizzo;
    /**
     *il <code>nomeIndirizzo</code> indica il nome della via
     */
    private String nomeIndirizzo;
    /**
     * il <code>comune</code> in cui e' siuato il centro vaccinale
     */
    private String comune;
    /**
     * la <code>siglaProvincia</code> indica sigla della provincia del centro vaccinale
     */
    private String siglaProvincia;
    /**
     * la <code>tipologia</code> indica la tipologia dei centro vaccinali
     */
    private String tipologia;
    /**
     *il <code>numeroCivico</code> indica il numero civico del centro vaccinale
     */
    private int numeroCivico;
    /**
     *il <code>cap</code> indica il codice postale del centro vaccinale
     */
    private int cap;

    /**
     * Costruttore per l'oggetto CetroVaccinale
     * @param id l'id della vaccinazione
     * @param nomeCentroVaccinale e' il nome del centro
     * @param qualificatoreIndirizzo e' il tipo di via
     * @param nomeIndirizzo e' il nome dell' indirizzo
     * @param comune e' il nome del comune
     * @param siglaProvincia e' la sigla della provincia
     * @param tipologia e' il tipo di struttura del centro
     * @param numeroCivico dice il numero civico
     * @param cap e' il codice postale
     */
    public CentroVaccinale(short id, String nomeCentroVaccinale, String qualificatoreIndirizzo, String nomeIndirizzo, int numeroCivico, String comune, String siglaProvincia, int cap, String tipologia){
        this.id = id;
        this.nomeCentroVaccinale = nomeCentroVaccinale;
        this.qualificatoreIndirizzo = qualificatoreIndirizzo;
        this.nomeIndirizzo = nomeIndirizzo;
        this.comune = comune;
        this.siglaProvincia = siglaProvincia;
        this.tipologia = tipologia;
        this.numeroCivico = numeroCivico;
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
     * @return qualificatoreIndirizzo il tipo di strada
     */
    public String getQualificatoreIndirizzo() {
        return qualificatoreIndirizzo;
    }

    /**
     * @param qualificatoreIndirizzo il tipo di strada
     */
    public void setQualificatoreIndirizzo(String qualificatoreIndirizzo) {
        this.qualificatoreIndirizzo = qualificatoreIndirizzo;
    }

    /**
     * @return nomeIndirizzo il nome dell'indirizzo
     */
    public String getNomeIndirizzo() {
        return nomeIndirizzo;
    }

    /**
     * @param nomeIndirizzo il nome dell'indirizzo
     */
    public void setNomeIndirizzo(String nomeIndirizzo) {
        this.nomeIndirizzo = nomeIndirizzo;
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
     * @return numeroCivico il numero civico del centroVaccinale
     */
    public int getNumeroCivico() {
        return numeroCivico;
    }

    /**
     * @param numeroCivico il numero civico  del centroVaccinale
     */

    public void setNumeroCivico(int numeroCivico) {
        this.numeroCivico = numeroCivico;
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

