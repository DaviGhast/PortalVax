package model;

import java.time.LocalDate;

public class Vaccinazione {
    private short id;
    private String vaccinoSomministrato;
    private LocalDate dataVaccinazione;
    private short idCentroVaccinale;
    private String codiceFiscale;

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

    public LocalDate getDataVaccinazione() {
        return dataVaccinazione;
    }

    public void setDataVaccinazione(LocalDate dataVaccinazione) {
        this.dataVaccinazione = dataVaccinazione;
    }

    public short getIdCentroVaccinale() {
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
