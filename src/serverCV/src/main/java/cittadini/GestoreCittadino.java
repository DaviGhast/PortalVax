package cittadini;

import database.*;
import model.*;

import java.util.ArrayList;
public class GestoreCittadino {

    /**
     * inserisce un cittadino
     * @param cittadino il cittadino
     */
    public boolean inserisciCittadino(Cittadino cittadino) {
        return CittadinoDAO.insert(cittadino);
    }

    /**
     * cancella un cittadino
     * @param cittadino il cittadino
     */
    public boolean cancellaCittadino(Cittadino cittadino){
        return CittadinoDAO.delete(cittadino);
    }

    /**
     * aggiorna un cittadino
     * @param cittadino il cittadino
     */
    public boolean aggiornaCittadino(Cittadino cittadino){
        return CittadinoDAO.update(cittadino);
    }

    /**
     * legge ogni riga dalla tabella cittadino.sql e per ognuna di essa istanzia oggetti di tipo cittadino
     * @return listaCittadini e' una lista di Cittadini
     */
    public ArrayList<Cittadino> getCittadini(){
        ArrayList<Cittadino> listaCittadini = CittadinoDAO.getAll();
        return listaCittadini;
    }

    /**
     * inserisce un cittadino registrato
     * @param cittadino il cittadino
     */
    public boolean inserisciCittadinoRegistrato(CittadinoRegistrato cittadino) {
        return CittadinoRegistratoDAO.insert(cittadino);
    }

    /**
     * cancella un cittadino registrato
     * @param cittadino il cittadino
     */
    public boolean cancellaCittadinoRegistrato(CittadinoRegistrato cittadino){
        return CittadinoRegistratoDAO.delete(cittadino);
    }

    /**
     * aggiorna un cittadino registrato
     * @param cittadino il cittadino
     */
    public boolean aggiornaCittadinoRegistrato(CittadinoRegistrato cittadino){
        return CittadinoRegistratoDAO.update(cittadino);
    }

    /**
     * legge ogni riga dalla tabella cittadino_registrato.sql e per ognuna di essa istanzia oggetti di tipo cittadino_registrato
     * @return listaCittadiniRegistarti e' una lista di CentriVaccinali
     */
    public ArrayList<CittadinoRegistrato> getCittadinoRegistrati(){
        ArrayList<CittadinoRegistrato> listaCittadiniRegistrati = CittadinoRegistratoDAO.getAll();
        return listaCittadiniRegistrati;
    }

    /**
     * il metodo si occupa di cercare il cittadino di interesse tramite il codice fiscale all'interno della tabella
     * @param codice il codice fiscale
     * @return listacittadiniRegistrati contiene i cittadini registrati trovati
     */
    public ArrayList<CittadinoRegistrato> searchCittadinoByCodiceFiscale(String codice) {
        ArrayList<CittadinoRegistrato> listaCittadiniRegistrati = CittadinoRegistratoDAO.getByCodiceFiscale(codice);
        return listaCittadiniRegistrati;
    }

    /**
     * inserisce un cittadino vaccinato
     * @param cittadino il cittadino
     */
    public boolean inserisciCittadinoVaccinato(Vaccinazione cittadino) {
        return VaccinazioneDAO.insert(cittadino);
    }

    /**
     * aggiorna un cittadino vaccinato
     * @param cittadino il cittadino
     */
    public boolean aggiornaCittadinoVaccinato(Vaccinazione cittadino){
        return VaccinazioneDAO.update(cittadino);
    }

    /**
     * cancella un cittadino Vaccinato
     * @param cittadino il cittadino
     */
    public boolean cancellaCittadinoVaccinato(Vaccinazione cittadino){
        return VaccinazioneDAO.delete(cittadino);
    }

    /**
     * il metodo si occupa di cercare il cittadino vaccinato di interesse tramite il codice fiscale all'interno della tabella
     * @param codice il codice fiscale
     * @return listacittadiniVaccinati contiene i cittadini vaccinati trovati
     */
    public ArrayList<Vaccinazione> searchCittadinoVaccinatoByCodiceFiscale(String codice) {
        ArrayList<Vaccinazione> listaCittadiniVaccinati = VaccinazioneDAO.getByCodiceFiscale(codice);
        return listaCittadiniVaccinati;
    }

    /**
     * il metodo si occupa di cercare il cittadino vaccinato di interesse tramite l'id del centro all'interno della tabella
     * @param idCentro l'id del centro
     * @return listacittadini contiene i cittadini vaccinati trovati
     */
    public ArrayList<Vaccinazione> searchCittadinoVaccinatoByIdCentro(short idCentro) {
        ArrayList<Vaccinazione> listaCittadiniVaccinati = VaccinazioneDAO.getByIdCentro(idCentro);
        return listaCittadiniVaccinati;
    }

    /**
     * legge ogni riga dalla tabella vaccinazione.sql e per ognuna di essa istanzia oggetti di tipo vaccinazione
     * @return listaCittadiniVaccinati e' una lista di Cittadini Vaccinati
     */
    public ArrayList<Vaccinazione> getCittadiniVaccinati(){
        ArrayList<Vaccinazione> listaCittadiniVaccinati = VaccinazioneDAO.getAll();
        return listaCittadiniVaccinati;
    }
    /**
     * inserisce un evento avverso
     * @param evento il cittadino
     */
    public boolean inserisciEventoAvverso(EventoAvverso evento) {
        return EventoAvversoDAO.insert(evento);
    }

    /**
     * il metodo si occupa di cercare l'evento avverso di interesse tramite l'id del cittadino all'interno della tabella
     * @param codice l'id del cittadino
     * @return listaEventiAvversi contiene gli eventi avversi trovati
     */
    public EventoAvverso searchEventoAvversoByIdCittadino(String codice) {
        EventoAvverso listaEvenetiAvversi = EventoAvversoDAO.getByIdCittadino(codice);

        return listaEvenetiAvversi;
    }


    /**
     * inserisce un evento avverso
     * @param evento l'evento avverso
     */
    public boolean inserisciEventoAvverso(EventoAvverso evento) {
        return EventoAvversoDAO.insert(evento);
    }

}
