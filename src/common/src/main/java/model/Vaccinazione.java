package model;

public class Vaccinazione {
    private short id;
    private String vaccinoSomministrato;
    private String dataVaccinazione;
    private short idCentroVaccinale;
    private String codiceFiscale;

    public Vaccinazione(){

    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getVaccinoSomministrato() {
        return vaccinoSomministrato;
    }

    public void setVaccinoSomministrato(String vaccinoSomministrato) {
        this.vaccinoSomministrato = vaccinoSomministrato; }

    public String getDataVaccinazione() {
        return dataVaccinazione;
    }

    public void setDataVaccinazione(String dataVaccinazione) {
        this.dataVaccinazione = dataVaccinazione;
    }

    public short getIdCentroVaccinaled() {
        return idCentroVaccinale;
    }

    public void setIdCentroVaccinale(short idCentroVaccinale) {
        this.idCentroVaccinale = idCentroVaccinale;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
}
