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
     * Il Metodo <code>registraVaccinato</code> permette di registrare un cittadino vaccinato
     * @param vaccinazione oggetto vaccinazione contenente tutte le informazioni della vaccinazione
     * @param cittadino oggetto cittadino contenente tutte le informazioni del cittadino
     * @return Oggetto Risposta valorizzato
     */
    public Risposta registraVaccinato(Vaccinazione vaccinazione, Cittadino cittadino){
        Risposta risposta = new Risposta();
        if (vaccinazione != null && cittadino != null) {
            if (vaccinazione.getId() == 0){
                vaccinazione.setId((short) VaccinazioneDAO.nextID());
            }
            if (VaccinazioneDAO.getByCodiceFiscale(cittadino.getCodiceFiscale()) == null) {
                if (CittadinoDAO.insert(cittadino)) {
                    if (VaccinazioneDAO.insert(vaccinazione)) {
                        risposta = new Risposta(Stato.GOOD, "Registrazione eseguita con Successo");
                    } else {
                        risposta = new Risposta(Stato.ERROR, "Registrazione non andata a buon fine");
                        CittadinoDAO.delete(cittadino);
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

    /**
     * Il Metodo <code>cercaCentroVaccinale</code> permette di fare la ricerca di un centro vaccinale in base al nome
     * @param nomeCentroVaccinale stringa contenente il nome del centro ricercato
     * @return Oggetto Risposta valorizzato
     */
    public Risposta cercaCentroVaccinale(String nomeCentroVaccinale) {
        Risposta risposta = new Risposta();
        if (!nomeCentroVaccinale.isEmpty()) {
            ArrayList<CentroVaccinale> centriVaccinali = searchCentroByName(CentroVaccinaleDAO.getAll(),nomeCentroVaccinale);
            if (!centriVaccinali.isEmpty()) {
                risposta = new Risposta(Stato.GOOD, centriVaccinali);
            } else {
                risposta = new Risposta(Stato.ERROR, "Non esiste nessun Centro Vaccinale con al suo interno la stringa: "+nomeCentroVaccinale);
            }
        } else {
            risposta = new Risposta(Stato.BAD, "Nome Centro inserito è nullo");
        }
        return risposta;
    }

    /**
     * Il Metodo <code>cercaCentroVaccinale</code> permette di fare la ricerca di un centro vaccinale in base al comune e alla tipologia
     * @param comune stringa contenente il nome del comune del centro ricercato
     * @param tipologia stringa contenente la tipologia del centro ricercato
     * @return Oggetto Risposta valorizzato
     */
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
     * Il Metodo <code>searchCentroByName</code> si occupa di cercare il centro vaccinale di interesse tramite il nome
     * del centro all'interno di una lista di Centri Vaccinali
     * @param listaCentriVaccinali lista contenente i centri vaccinali
     * @param nomeCentroVaccinale è il parametro di ricerca
     * @return ListaRisultati centri vaccinali trovati nel file csv
     */
    public ArrayList<CentroVaccinale> searchCentroByName(ArrayList<CentroVaccinale> listaCentriVaccinali, String nomeCentroVaccinale) {
        ArrayList<CentroVaccinale> listaRisultati = new ArrayList<>();
        for (CentroVaccinale centroVaccinale: listaCentriVaccinali) {
            if (centroVaccinale.getNomeCentroVaccinale().toLowerCase().contains(nomeCentroVaccinale.toLowerCase()))
                listaRisultati.add(centroVaccinale);
        }
        return listaRisultati;
    }

    /**
     * Il Metodo <code>searchCentroByTipologia</code> si occupa di cercare il centro vaccinale di interesse tramite tipologia all'interno della tabella
     * @param listaCentriVaccinali lista contenente i centri vaccinali
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

    /**
     * Il Metodo <code>visualizzaInfoCentroVaccinale</code> permette di visualizzare eventuali informazioni di un determinato centro
     * @param centroVaccinale oggetto contenente le informazioni del centro di cui si volle visualizzare le informazioni
     * @return Oggetto Risposta valorizzato
     */
    public Risposta visulizzaInfoCentroVaccinale(CentroVaccinale centroVaccinale) {
        Risposta risposta = new Risposta();
        ArrayList<EventoAvverso> eventiAvversiCentro = new ArrayList<EventoAvverso>();
        if (!Objects.equals(centroVaccinale, new CentroVaccinale())) {
            ArrayList<Vaccinazione> vaccinazioni = VaccinazioneDAO.getByIdCentro(centroVaccinale.getId());
            if (!vaccinazioni.isEmpty()) {
                for (Vaccinazione vaccinazione: vaccinazioni) {
                    CittadinoRegistrato cittadinoRegistrato = CittadinoRegistratoDAO.getByCodiceFiscale(vaccinazione.getCodiceFiscale());
                    if (cittadinoRegistrato != null) {
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

    /**
     * Il Metodo <code>reportEventiAvversiCentro</code> permette di avere una lista di report degli evventi avversi di un centro
     * @param eventiAvversiCentro lista contenente gli eventi avversi del centro
     * @return Oggetto listaReport valorizzato
     */
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

    /**
     * permette di richiamare gli enum delle tipologie di centri vaccinali
     * @return lista delle tipologie
     */
    public String[] getTipologie() {
        return EnumDao.getEnumList("tipologie");
    }

    /**
     * Il Metodo <code>esisteCentroVaccinale</code> permette di fare una ricerca dei dentri vaccinali in base al nome
     * @param nomeCentroVaccinale stringa contenente il nome del centro vaccinale ricercato
     * @return Oggetto listaReport valorizzato
     */
    public Risposta esisteCentroVaccinale(String nomeCentroVaccinale) {
        Risposta risposta = new Risposta();
        risposta = cercaCentroVaccinale(nomeCentroVaccinale);
        if (risposta.getStato() == Stato.GOOD)
            risposta.setObject(true);
        else
            risposta.setObject(false);
        return risposta;
    }

    /**
     * Il metodo <code>getVaccini</code> ritorna i vaccini
     * @return lista enum dei vaccini
     */
    public String[] getVaccini() {
        return EnumDao.getEnumList("vaccini");
    }
    /**
     * Il metodo <code>checkEnum</code> permette di inserire gli enum
     */
    public void checkEnum() {
        String[] tipologieDefault = {"Hub", "Aziendale", "Ospedaliero"};
        String[] tipologie = getTipologie();
        if (tipologie == null)
            EnumDao.insert(new EnumModel(EnumDao.nextID(),"tipologie",tipologieDefault));
        else if (tipologie.length < 1){
            EnumDao.update(new EnumModel("tipologie",tipologieDefault));
        }

        String[] vacciniDefault = {"Pfizer", "Moderna", "AstraZeneca", "Janssen (J&J)"};
        String[] vaccini = getVaccini();
        if (vaccini == null)
            EnumDao.insert(new EnumModel(EnumDao.nextID(),"vaccini",vacciniDefault));
        else if (vaccini.length < 1){
            EnumDao.update(new EnumModel("vaccini",vacciniDefault));
        }
    }
}