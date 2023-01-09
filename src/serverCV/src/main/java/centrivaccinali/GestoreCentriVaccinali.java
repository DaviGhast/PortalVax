package centrivaccinali;

import database.*;
import model.*;

import java.util.ArrayList;
import java.util.Objects;

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
            if (centroVaccinale.getId() == 0){
                centroVaccinale.setId((short) CentroVaccinaleDAO.nextID());
            }
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
            if (vaccinazione.getId() == 0){
                vaccinazione.setId((short) VaccinazioneDAO.nextID());
            }
            if (VaccinazioneDAO.getByCodiceFiscale(cittadino.getCodiceFiscale()) != null) {
                if (VaccinazioneDAO.insert(vaccinazione)) {
                    if (CittadinoDAO.insert(cittadino)) {
                        risposta = new Risposta(Stato.GOOD, "Registrazione eseguita con Successo");
                    } else {
                        risposta = new Risposta(Stato.ERROR, "Registrazione non andata a buon fine");
                        VaccinazioneDAO.delete(vaccinazione);
                    }
                }
            } else {
                risposta = new Risposta(Stato.BAD,"Vaccinazione già inserita");
            }
        } else {
            risposta = new Risposta(Stato.BAD,"Oggetto Ricevuto Nullo");
        }
        return risposta;
    }

    public Risposta cercaCentroVaccinale(String nomeCentroVaccinale) {
        Risposta risposta = new Risposta();
        if (!nomeCentroVaccinale.isEmpty()) {
            ArrayList<CentroVaccinale> centriVaccinali = CentroVaccinaleDAO.getByName(nomeCentroVaccinale);
            if (!centriVaccinali.isEmpty()) {
                risposta = new Risposta(Stato.GOOD, centriVaccinali);
            } else {
                risposta = new Risposta(Stato.ERROR, "");
            }
        } else {
            risposta = new Risposta(Stato.BAD, "");
        }
        return risposta;
    }

    public Risposta cercaCentroVaccinale(String comune, String tipologia) {
        Risposta risposta = new Risposta();
        if (!comune.isEmpty()) {
            ArrayList<CentroVaccinale> centriVaccinali = CentroVaccinaleDAO.getByComune(comune);
            if (!centriVaccinali.isEmpty()) {
                if (!tipologia.isEmpty()) {
                    risposta = new Risposta(Stato.GOOD, searchCentroByTipologia(centriVaccinali, tipologia));
                } else {
                    risposta = new Risposta(Stato.GOOD, centriVaccinali);
                }
            } else {
                risposta = new Risposta(Stato.ERROR, "");
            }
        } else {
            risposta = new Risposta(Stato.BAD, "");
        }
        return risposta;
    }

    /**
     * il metodo si occupa di cercare il centro vaccinale di interesse tramite tipologia all'interno della tabella
     * @param tipologia il nome della tipologia
     * @return listarisultati contiene i centri vaccinali trovati
     */
    public ArrayList<CentroVaccinale> searchCentroByTipologia(ArrayList<CentroVaccinale> listaCentriVaccinali, String tipologia) {
        ArrayList<CentroVaccinale> listaRisultati = new ArrayList<>();
        for (CentroVaccinale centroVaccinale: listaCentriVaccinali) {
            if (centroVaccinale.getTipologia().toLowerCase().contains(tipologia.toLowerCase()))
                listaRisultati.add(centroVaccinale);
        }
        return listaRisultati;
    }

    public Risposta visulizzaInfoCentroVaccinale(CentroVaccinale centroVaccinale) {
        Risposta risposta = new Risposta();
        ArrayList<EventoAvverso> eventiAvversiCentro = new ArrayList<EventoAvverso>();
        if (!Objects.equals(centroVaccinale, new CentroVaccinale())) {
            ArrayList<Vaccinazione> vaccinazioni = VaccinazioneDAO.getByIdCentro(centroVaccinale.getId());
            if (!vaccinazioni.isEmpty()) {
                for (Vaccinazione vaccinazione: vaccinazioni) {
                    CittadinoRegistrato cittadinoRegistrato = CittadinoRegistratoDAO.getByCodiceFiscale(vaccinazione.getCodiceFiscale());
                    if (!Objects.equals(cittadinoRegistrato, new CittadinoRegistrato())) {
                        ArrayList<EventoAvverso> eventiAvversiCittadino = EventoAvversoDAO.getByIdCittadino(cittadinoRegistrato.getUserId());
                        eventiAvversiCentro.addAll(eventiAvversiCittadino);
                    }
                }
                if (!eventiAvversiCentro.isEmpty()) {
                    ArrayList<ReportEventoAvverso> listaReport = reportEventiAvversiCentro(eventiAvversiCentro);
                    risposta = new Risposta(Stato.GOOD, listaReport);
                } else {
                    risposta = new Risposta(Stato.ERROR, "Nessun Evento Avverso");
                }
            } else {
                risposta = new Risposta(Stato.ERROR, "Non sono state registrate Vaccinazioni in questo Centro Vaccinale");
            }
        } else {
            risposta = new Risposta(Stato.BAD, "");
        }
        return risposta;
    }

    public ArrayList<ReportEventoAvverso> reportEventiAvversiCentro(ArrayList<EventoAvverso> eventiAvversiCentro) {
        ArrayList<ReportEventoAvverso> listaReport = new ArrayList<ReportEventoAvverso>();
        String[] eventi = EnumDao.getEnumList("eventi");
        for (String evento: eventi) {
            int numeroSegnalazioni = 0;
            float somma = 0;
            double severitaMedia = 0.0;
            for (EventoAvverso eventoAvverso: eventiAvversiCentro) {
                if (eventoAvverso.getEvento().equalsIgnoreCase(evento)) {
                    numeroSegnalazioni += 1;
                    somma += eventoAvverso.getSeverita();
                }
            }
            severitaMedia = somma/numeroSegnalazioni;
            listaReport.add(new ReportEventoAvverso(evento,numeroSegnalazioni, severitaMedia));
        }
        return listaReport;
    }

    public String[] getTipologie() {
        return EnumDao.getEnumList("tipologie");
    }

    public Risposta esisteCentroVaccinale(String nomeCentroVaccinale) {
        Risposta risposta = new Risposta();
        risposta = cercaCentroVaccinale(nomeCentroVaccinale);
        if (risposta.getStato() == Stato.GOOD)
            risposta.setObject(true);
        else
            risposta.setObject(false);
        return risposta;
    }

    public String[] getVaccini() {
        return EnumDao.getEnumList("vaccini");
    }

    public void checkEnum() {
        if (getTipologie() == null)
            EnumDao.insert(new EnumModel(EnumDao.nextID(),"tipologie",
                    new String[]{"Hub", "Aziendale", "Ospedaliero"}));
        else if (getTipologie().length < 1){
            EnumDao.update(new EnumModel("tipologie",
                    new String[]{"Hub", "Aziendale", "Ospedaliero"}));
        }

        if (getVaccini() == null)
            EnumDao.insert(new EnumModel(EnumDao.nextID(),"vaccini",
                    new String[]{"Pfizer", "Moderna", "AstraZeneca", "Janssen (J&J)"}));
        else if (getVaccini().length < 1){
            EnumDao.update(new EnumModel("vaccini",
                    new String[]{"Pfizer", "Moderna", "AstraZeneca", "Janssen (J&J)"}));
        }
    }
}