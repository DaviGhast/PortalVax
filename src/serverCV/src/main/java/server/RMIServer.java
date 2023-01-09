package server;

import Interface.RMIServerInterface;
import centrivaccinali.GestoreCentriVaccinali;
import cittadini.GestoreCittadini;
import model.*;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * The class <code>RMIServer</code>
 * @author Davide Mainardi (DaviGhast)
 * @version 1.0
 */
public class RMIServer extends UnicastRemoteObject implements RMIServerInterface {

    private static final long serialVersionUID = 1L;
    private static final GestoreCentriVaccinali gestoreCentriVaccinali = new GestoreCentriVaccinali();
    private static final GestoreCittadini gestoreCittadini = new GestoreCittadini();
    private static Registry registry;

    public RMIServer() throws RemoteException {
        super();
    }

    /**
     * The start method make server ready
     */
    public static void start() {
        try {
            registry = LocateRegistry.createRegistry(1099);
            RMIServer server = new RMIServer();
            registry.rebind("PortalVaxServer", server);
            System.out.println("Server bounded in registry");
        }catch( Exception e) {
            System.err.println( "Server Main exception:"+e) ;
        }
    }

    public static void stop() throws NoSuchObjectException {
        System.out.println("Stopping rmi server.");
        UnicastRemoteObject.unexportObject(registry, true);
    }

    @Override
    public Risposta registraCentroVaccinale(CentroVaccinale centroVaccinale) throws RemoteException {
        return gestoreCentriVaccinali.registraCentroVaccinale(centroVaccinale);
    }

    @Override
    public Risposta registraVaccinato(Vaccinazione vaccinazione, Cittadino cittadino) throws RemoteException {
        return gestoreCentriVaccinali.registraVaccinato(vaccinazione,cittadino);
    }

    @Override
    public Risposta registraCittadino(CittadinoRegistrato cittadinoRegistrato, short idVaccinazione) throws RemoteException {
        return gestoreCittadini.registraCittadino(cittadinoRegistrato, idVaccinazione);
    }

    @Override
    public Risposta cercaIdVaccinazione(String codiceFiscale) throws RemoteException {
        return gestoreCittadini.cercaIdVaccinazione(codiceFiscale);
    }

    @Override
    public Risposta loginCittadino(String emailAnduserid, String password) throws RemoteException {
        return gestoreCittadini.loginCittadino(emailAnduserid, password);
    }

    @Override
    public Risposta esisteCentroVaccinale(String nomeCentroVaccinale) throws RemoteException {
        return gestoreCentriVaccinali.esisteCentroVaccinale(nomeCentroVaccinale);
    }

    @Override
    public Risposta cercaCentroVaccinale(String nomeCentroVaccinale) throws RemoteException {
        return gestoreCentriVaccinali.cercaCentroVaccinale(nomeCentroVaccinale);
    }

    @Override
    public Risposta cercaCentroVaccinale(String comune, String tipologia) throws RemoteException {
        return gestoreCentriVaccinali.cercaCentroVaccinale(comune, tipologia);
    }

    @Override
    public Risposta visulizzaInfoCentroVaccinale(CentroVaccinale centroVaccinale) throws RemoteException {
        return gestoreCentriVaccinali.visulizzaInfoCentroVaccinale(centroVaccinale);
    }

    @Override
    public Risposta inserisciEventiAvversi(EventoAvverso eventoAvverso) throws RemoteException {
        return gestoreCittadini.inserisciEventiAvversi(eventoAvverso);
    }

    @Override
    public String[] getEventi() throws RemoteException {
        return gestoreCittadini.getEventi();
    }

    @Override
    public String[] getTipologie() throws RemoteException {
        return gestoreCentriVaccinali.getTipologie();
    }

    @Override
    public String[] getVaccini() throws RemoteException {
        return gestoreCentriVaccinali.getVaccini();
    }

    public static void checkEnum() {
        gestoreCentriVaccinali.checkEnum();
        gestoreCittadini.checkEnum();
    }

}
