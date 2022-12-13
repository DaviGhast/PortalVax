package cittadini;

import database.*;
import model.*;

import java.util.ArrayList;
import java.util.Objects;

public class GestoreCittadini {

    /**
     *
     * @param cittadinoRegistrato
     * @param idVaccinazione
     * @return
     */
    public Risposta registraCittadino(CittadinoRegistrato cittadinoRegistrato, short idVaccinazione) {
        // creazione Oggetto Risposta non valorizzato
        Risposta risposta = new Risposta();
        // controllo se parametro in input è valorizzzato
        if (cittadinoRegistrato != null) {
            //controllo se l'utente si è già registrato
            if (CittadinoRegistratoDAO.getByCodiceFiscale(cittadinoRegistrato.getCodiceFiscale()) != null) {
                //controllo se idVaccinazione è corretto
                if (VaccinazioneDAO.getVaccinazione(idVaccinazione) != null){
                    //controllo se idVaccinazione è quello associato al codice fiscale
                    Risposta risposta_interna = cercaIdVaccinazione(cittadinoRegistrato.getCodiceFiscale());
                    if (risposta_interna.getStato() == Stato.GOOD && (short) risposta_interna.getObject() == idVaccinazione){
                        //controllo se userid è già usato
                        if (CittadinoRegistratoDAO.getByUserId(cittadinoRegistrato.getUserId()) == null) {
                            if (CittadinoRegistratoDAO.insert(cittadinoRegistrato))
                                risposta = new Risposta(Stato.GOOD, "Registrazione eseguita");
                            else
                                risposta = new Risposta(Stato.ERROR, "Registrazione non eseguita");
                        } else {
                            risposta = new Risposta(Stato.BAD, "UserId già utilizzato");
                        }
                    } else {
                        risposta = new Risposta(Stato.BAD,"IdVaccinazione inserito non è associato al tuo codice fiscale");
                    }
                } else {
                    risposta = new Risposta(Stato.BAD,"IdVaccinazione non esistente");
                }
            } else {
                risposta = new Risposta(Stato.BAD,"Ti sei già registrato, verifica fatta tramite codice fiscale");
            }
        } else {
            risposta = new Risposta(Stato.BAD,"Oggetto Ricevuto Nullo");
        }
        return risposta;
    }

    public Risposta cercaIdVaccinazione(String codiceFiscale) {
        Risposta risposta = new Risposta();
        Vaccinazione vaccinazione = VaccinazioneDAO.getByCodiceFiscale(codiceFiscale);
        if (vaccinazione != null) {
            risposta = new Risposta(Stato.GOOD, vaccinazione.getId());
        } else {
            risposta = new Risposta(Stato.BAD, "Nessuna Vaccinazione associata a questo Codice Fiscale");
        }
        return risposta;
    }

    public Risposta loginCittadino(String emailAnduserid, String password) {
        Risposta risposta = new Risposta();
        CittadinoRegistrato cittadinoRegistrato = new CittadinoRegistrato();
        cittadinoRegistrato = CittadinoRegistratoDAO.getByUserId(emailAnduserid.toLowerCase());
        if (cittadinoRegistrato == null) {
            cittadinoRegistrato = CittadinoRegistratoDAO.getByEmail(emailAnduserid.toLowerCase());
            if (cittadinoRegistrato == null) {
                risposta = new Risposta(Stato.BAD, "Email o UserId errati");
            }
        }
        assert cittadinoRegistrato != null;
        if (cittadinoRegistrato.getPassword().equals(password)) {
            risposta = new Risposta(Stato.GOOD, "Login Effettuato!");
        } else {
            risposta = new Risposta(Stato.BAD, "Password errata");
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





}
