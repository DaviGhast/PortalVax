package Interface;

import model.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerInterface extends Remote {
    Risposta registraCentroVaccinale(CentroVaccinale centroVaccinale) throws RemoteException;

    Risposta registraVaccinato(Vaccinazione vaccinazione, Cittadino cittadino) throws RemoteException;

    Risposta registraCittadino(CittadinoRegistrato cittadinoRegistrato, short idVaccinazione) throws RemoteException;

    Risposta cercaIdVaccinazione(String codiceFiscale) throws RemoteException;

    Risposta loginCittadino(String emailAnduserid, String password) throws RemoteException;

    Risposta esisteCentroVaccinale(String nomeCentroVaccinale) throws RemoteException;

    Risposta cercaCentroVaccinale(String nomeCentroVaccinale) throws RemoteException;

    Risposta cercaCentroVaccinale(String comune, String tipologia) throws RemoteException;

    Risposta visulizzaInfoCentroVaccinale(CentroVaccinale centroVaccinale) throws RemoteException;

    Risposta inserisciEventiAvversi(EventoAvverso eventoAvverso) throws RemoteException;

    String[] getEventi() throws RemoteException;

    String[] getTipologie() throws RemoteException;

    String[] getVaccini() throws RemoteException;
}
