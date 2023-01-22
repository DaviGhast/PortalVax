package model;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * E' il modello della vaccinazione
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class Vaccinazione implements Serializable {
    private short id;
    private String vaccinoSomministrato;
    private LocalDate dataVaccinazione;
    private short idCentroVaccinale;
    private String codiceFiscale;
    /**
     * Costruttore per l'oggetto Vaccinazione
     * @param id l'id
     * @param vaccinoSomministrato e' il tipo di vaccino somministarto
     * @param dataVaccinazione e' la data di vaccinazione
     * @param idCentroVaccinale e' l'id del centro vaccinale
     * @param codiceFiscale e' il codice fiscale
     */
    public Vaccinazione(short id, String vaccinoSomministrato, LocalDate dataVaccinazione, short idCentroVaccinale,
                           String codiceFiscale){
        this.id = id;
        this.vaccinoSomministrato = vaccinoSomministrato;
        this.dataVaccinazione = dataVaccinazione;
        this.idCentroVaccinale = idCentroVaccinale;
        this.codiceFiscale = codiceFiscale;
    }

    public Vaccinazione(){

    }
    /**
     * Il Metodo <code>getId</code> è il metodo che recupera l'id
     * @return ritorna il valore del id
     */
    public short getId() {
        return id;
    }
    /**
     * Il Metodo <code>setId</code> è il metodo che salva l'id
     * @param id short contenente l'id
     */
    public void setId(short id) {
        this.id = id;
    }
    /**
     * Il Metodo <code>getVaccinoSomministrato</code> è il metodo che recupera il tipo di vaccino somministrato
     * @return ritorna il valore del vaccino somministrato
     */
    public String getVaccinoSomministrato() {
        return vaccinoSomministrato;
    }
    /**
     * Il Metodo <code>setVaccinoSomministrato</code> è il metodo che salva il tipo di vaccino somministrato
     * @param vaccinoSomministrato stringa contenente il valore del vaccino somministrato
     */
    public void setVaccinoSomministrato(String vaccinoSomministrato) {
        this.vaccinoSomministrato = vaccinoSomministrato; }
    /**
     * Il Metodo <code>getDataVaccinazione</code> è il metodo che recupera la data di vaccinazione
     * @return ritorna il valore della data di vaccinazione
     */
    public LocalDate getDataVaccinazione() {
        return dataVaccinazione;
    }
    /**
     * Il Metodo <code>setDataVaccinazione</code> è il metodo che salva la data di vaccinazione
     * @param dataVaccinazione contiene la data di vaccinazione
     */
    public void setDataVaccinazione(LocalDate dataVaccinazione) {
        this.dataVaccinazione = dataVaccinazione;
    }
    /**
     * Il Metodo <code>getIdCentroVaccinale</code> è il metodo che recupera l'id del centro vaccinale
     * @return ritorna il valore del id del centro vaccinale
     */
    public short getIdCentroVaccinale() {
        return idCentroVaccinale;
    }
    /**
     * Il Metodo <code>setIdCentroVaccinale</code> è il metodo che salva l'id del centro vaccinale
     * @param idCentroVaccinale short contenente l'id del centro vaccinale
     */
    public void setIdCentroVaccinale(short idCentroVaccinale) {
        this.idCentroVaccinale = idCentroVaccinale;
    }
    /**
     * Il Metodo <code>getCodiceFiscale</code> è il metodo che recupera il codice fiscale
     * @return ritorna il valore del codice fiscale
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }
    /**
     * Il Metodo <code>setCodiceFiscale</code> è il metodo che salva il codice fiscale
     * @param codiceFiscale stringa contenente il codice fiscale
     */
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
}
