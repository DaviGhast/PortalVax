package cittadini;

import database.*;
import model.*;

import java.util.ArrayList;
import java.util.Objects;

public class GestoreCittadini {

    /**
     * Il Metodo <code>registraCittadino</code> permette di registrare un cittadino
     * @param cittadinoRegistrato oggetto contenente tutte le informazioni del cittadino
     * @param idVaccinazione short contenente l'id di vaccinazione del cittadino
     * @return Oggetto risposta valorizzato
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

    /**
     * Il Metodo <code>cercaIdVaccinazione</code> permette di cercare l'id di vaccinazione in base al codice fiscale
     * @param codiceFiscale stringa contenente il codice fiscale in base al cui si cerca l'id
     * @return Oggetto risposta valorizzato
     */
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

    /**
     * Il Metodo <code>loginCittadino</code> permette di fare il login
     * @param emailAnduserid stringa contenente l'username
     * @param password stringa contenente la password
     * @return Oggetto risposta valorizzato
     */
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

    public String[] getEventi() {
        return EnumDao.getEnumList("eventi");
    }

    /**
     * Il Metodo <code>inserisciEventiAvversi</code> permette inserire gli eventi avversi
     * @param eventoAvverso oggetto contenente tutte le informazione dell'evento avverso
     * @return Oggetto risposta valorizzato
     */
    public Risposta inserisciEventiAvversi(EventoAvverso eventoAvverso) {
        Risposta risposta = new Risposta();
        if (!eventoAvverso.equals(new EventoAvverso())) {
            eventoAvverso.setId((short) EventoAvversoDAO.nextID());
            if (EventoAvversoDAO.insert(eventoAvverso)) {
                risposta = new Risposta(Stato.GOOD, "Registrazione eseguita con Successo");
            }
            else {
                risposta = new Risposta(Stato.ERROR, "Registrazione non andata a buon fine");
                EventoAvversoDAO.delete(eventoAvverso);
            }
        } else {
            risposta = new Risposta(Stato.BAD, "");
        }
        return risposta;
    }

    public void checkEnum() {
        String[] eventi = getEventi();
        if (eventi == null)
            EnumDao.insert(new EnumModel(EnumDao.nextID(),"eventi",
                    new String[]{"Mal di Testa", "Dolori Muscolari"}));
        else if (eventi.length < 1){
            EnumDao.update(new EnumModel("eventi",
                    new String[]{"Mal di Testa", "Dolori Muscolari"}));
        }
    }

}
