package Interface;

import model.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * La classe <code>RMIServerInterface</code> fornisce l'interfaccia dei metodi forniti dal server RMI
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public interface RMIServerInterface extends Remote {

    /**
     * richiama il metodo con il medesimo nome fornito dal server RMI
     * @param centroVaccinale oggetto centroVaccinale richesto in input
     * @return oggetto di ritorno
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    Risposta registraCentroVaccinale(CentroVaccinale centroVaccinale) throws RemoteException;

    /**
     * richiama il metodo con il medesimo nome fornito dal server RMI
     * @param vaccinazione oggetto vaccinazione richesto in input
     * @param cittadino oggetto cittadino richesto in input
     * @return oggetto di ritorno
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    Risposta registraVaccinato(Vaccinazione vaccinazione, Cittadino cittadino) throws RemoteException;

    /**
     * richiama il metodo con il medesimo nome fornito dal server RMI
     * @param cittadinoRegistrato oggetto cittadinoRegistrato richesto in input
     * @param idVaccinazione oggetto short richesto in input
     * @return oggetto di ritorno
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    Risposta registraCittadino(CittadinoRegistrato cittadinoRegistrato, short idVaccinazione) throws RemoteException;

    /**
     * richiama il metodo con il medesimo nome fornito dal server RMI
     * @param codiceFiscale oggetto String richesto in input
     * @return oggetto di ritorno
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    Risposta cercaIdVaccinazione(String codiceFiscale) throws RemoteException;

    /**
     * richiama il metodo con il medesimo nome fornito dal server RMI
     * @param emailAnduserid oggetto String richesto in input
     * @param password oggetto String richesto in input
     * @return oggetto di ritorno
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    Risposta loginCittadino(String emailAnduserid, String password) throws RemoteException;

    /**
     * richiama il metodo con il medesimo nome fornito dal server RMI
     * @param nomeCentroVaccinale oggetto String richesto in inpu
     * @return oggetto di ritorno
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    Risposta esisteCentroVaccinale(String nomeCentroVaccinale) throws RemoteException;

    /**
     * richiama il metodo con il medesimo nome fornito dal server RMI
     * @param nomeCentroVaccinale oggetto String richesto in input
     * @return oggetto di ritorno
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    Risposta cercaCentroVaccinale(String nomeCentroVaccinale) throws RemoteException;

    /**
     * richiama il metodo con il medesimo nome fornito dal server RMI
     * @param comune oggetto String richesto in input
     * @param tipologia oggetto String richesto in input
     * @return oggetto di ritorno
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    Risposta cercaCentroVaccinale(String comune, String tipologia) throws RemoteException;

    /**
     * richiama il metodo con il medesimo nome fornito dal server RMI
     * @param centroVaccinale oggetto centroVaccinale richesto in input
     * @return oggetto di ritorno
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    Risposta visulizzaInfoCentroVaccinale(CentroVaccinale centroVaccinale) throws RemoteException;

    /**
     * richiama il metodo con il medesimo nome fornito dal server RMI
     * @param eventoAvverso oggetto eventoAvverso richesto in input
     * @return oggetto di ritorno
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    Risposta inserisciEventiAvversi(EventoAvverso eventoAvverso) throws RemoteException;

    /**
     * richiama il metodo con il medesimo nome fornito dal server RMI
     * @param userid oggetto String richesto in input
     * @return oggetto di ritorno
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    Risposta visualizzaEventiAvversi(String userid) throws RemoteException;

    /**
     * richiama il metodo con il medesimo nome fornito dal server RMI
     * @return oggetto di ritorno
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    String[] getEventi() throws RemoteException;

    /**
     * richiama il metodo con il medesimo nome fornito dal server RMI
     * @return oggetto di ritorno
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    String[] getTipologie() throws RemoteException;

    /**
     * richiama il metodo con il medesimo nome fornito dal server RMI
     * @return oggetto di ritorno
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    String[] getVaccini() throws RemoteException;
}
