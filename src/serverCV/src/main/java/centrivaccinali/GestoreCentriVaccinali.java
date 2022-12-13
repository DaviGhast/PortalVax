package centrivaccinali;

import database.CentroVaccinaleDAO;
import database.CittadinoDAO;
import database.VaccinazioneDAO;
import model.*;

import java.util.ArrayList;

/**
 * la classe si occupa di definire le azioni disponibili agli Operatori dei centri vaccinali
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class GestoreCentriVaccinali {

    /**
     * Il Metodo <code>registraCentroVaccinale</code> controlla la validità delle informazioni e le inserisce nella base dati
     * @param centroVaccinale oggetto CentroVaccinale contenente tutte le informazioni
     * @return Oggetto Risposta valorizzato
     */
    public Risposta registraCentroVaccinale(CentroVaccinale centroVaccinale) {
        // creazione Oggetto Risposta non valorizzato
        Risposta risposta = new Risposta();
        // controllo se parametro in input è valorizzzato
        if (centroVaccinale != null) {
            // controllo se esiste già un CentroVaccinale con lo stesso nome
            if (CentroVaccinaleDAO.getByName(centroVaccinale.getNomeCentroVaccinale()).isEmpty()) {
                if (CentroVaccinaleDAO.insert(centroVaccinale))
                    risposta = new Risposta(Stato.GOOD, "Registrazione Centro Vaccinale eseguita");
                else
                    risposta = new Risposta(Stato.ERROR, "Registrazione Centro Vaccinale non eseguita");
            } else {
                // controllo se esiste già un CentroVaccinale con lo stesso nome nello stesso comune
                if (CentroVaccinaleDAO.getByComune(centroVaccinale.getComune()).isEmpty()) {
                    if (CentroVaccinaleDAO.insert(centroVaccinale))
                        risposta = new Risposta(Stato.GOOD,"Registrazione Centro Vaccinale eseguita");
                    else
                        risposta = new Risposta(Stato.ERROR,"Registrazione Centro Vaccinale non eseguita");
                } else {
                    risposta = new Risposta(Stato.BAD,"Centro Vaccinale già inserito");
                }
            }
        } else {
            risposta = new Risposta(Stato.BAD,"Oggetto Ricevuto Nullo");
        }
        return risposta;
    }

    /**
     * Il Metodo <code>registraVaccinato</code>
     * @param vaccinazione
     * @param cittadino
     * @return Oggetto Risposta valorizzato
     */
    public Risposta registraVaccinato(Vaccinazione vaccinazione, Cittadino cittadino){
        Risposta risposta = new Risposta();
        if (vaccinazione != null && cittadino != null) {
            if (VaccinazioneDAO.getByCodiceFiscale(cittadino.getCodiceFiscale()) != null) {
                if (CittadinoDAO.insert(cittadino) & VaccinazioneDAO.insert(vaccinazione)) {
                    risposta = new Risposta(Stato.GOOD, "Registrazione eseguita con Successo");
                }
                else {
                    risposta = new Risposta(Stato.ERROR, "Registrazione non andata a buon fine");
                    CittadinoDAO.delete(cittadino);
                    VaccinazioneDAO.delete(vaccinazione);
                }
            } else {
                risposta = new Risposta(Stato.BAD,"Vaccinazione già inserita");
            }
        } else {
            risposta = new Risposta(Stato.BAD,"Oggetto Ricevuto Nullo");
        }
        return risposta;
    }



}