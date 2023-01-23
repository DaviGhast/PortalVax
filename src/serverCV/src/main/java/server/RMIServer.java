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

    /**
     * richiama il metodo con il medesimo nome contenuto nella classe di logica {@link GestoreCentriVaccinali}
     * @see GestoreCentriVaccinali#registraCentroVaccinale(CentroVaccinale)
     * @param centroVaccinale oggetto proveniente dal client
     * @return oggetto di ritorno da mandare al client
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    @Override
    public Risposta registraCentroVaccinale(CentroVaccinale centroVaccinale) throws RemoteException {
        return gestoreCentriVaccinali.registraCentroVaccinale(centroVaccinale);
    }

    /**
     * richiama il metodo con il medesimo nome contenuto nella classe di logica {@link GestoreCentriVaccinali}
     * @see GestoreCentriVaccinali#registraVaccinato(Vaccinazione, Cittadino)
     * @param vaccinazione oggetto di tipo Vaccinazione proveniente dal client
     * @param cittadino oggetto di tipo Cittadino proveniente dal client
     * @return oggetto di ritorno da mandare al client
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    @Override
    public Risposta registraVaccinato(Vaccinazione vaccinazione, Cittadino cittadino) throws RemoteException {
        return gestoreCentriVaccinali.registraVaccinato(vaccinazione,cittadino);
    }
    /**
     * richiama il metodo con il medesimo nome contenuto nella classe di logica {@link GestoreCittadini}
     * @see GestoreCittadini#registraCittadino(CittadinoRegistrato, short)
     * @param cittadinoRegistrato oggetto di tipo cittadinoVaccinato
     * @param idVaccinazione oggetto di tipo Cittadino proveniente dal client
     * @return oggetto di ritorno da mandare al client
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    @Override
    public Risposta registraCittadino(CittadinoRegistrato cittadinoRegistrato, short idVaccinazione) throws RemoteException {
        return gestoreCittadini.registraCittadino(cittadinoRegistrato, idVaccinazione);
    }
    /**
     * richiama il metodo con il medesimo nome contenuto nella classe di logica {@link GestoreCittadini}
     * @see GestoreCittadini#cercaIdVaccinazione(String)
     * @param codiceFiscale stringa contenente il codice fiscale
     * @return oggetto di ritorno da mandare al client
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    @Override
    public Risposta cercaIdVaccinazione(String codiceFiscale) throws RemoteException {
        return gestoreCittadini.cercaIdVaccinazione(codiceFiscale);
    }
    /**
     * richiama il metodo con il medesimo nome contenuto nella classe di logica {@link GestoreCittadini}
     * @see GestoreCittadini#loginCittadino(String, String)
     * @param emailAnduserid stringa contenente il codice fiscale
     * @param password stringa contenente la password
     * @return oggetto di ritorno da mandare al client
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    @Override
    public Risposta loginCittadino(String emailAnduserid, String password) throws RemoteException {
        return gestoreCittadini.loginCittadino(emailAnduserid, password);
    }
    /**
     * richiama il metodo con il medesimo nome contenuto nella classe di logica {@link GestoreCentriVaccinali}
     * @see GestoreCentriVaccinali#esisteCentroVaccinale(String)
     * @param nomeCentroVaccinale stringa contenente il nome del centro vaccinale
     * @return oggetto di ritorno da mandare al client
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    @Override
    public Risposta esisteCentroVaccinale(String nomeCentroVaccinale) throws RemoteException {
        return gestoreCentriVaccinali.esisteCentroVaccinale(nomeCentroVaccinale);
    }
    /**
     * richiama il metodo con il medesimo nome contenuto nella classe di logica {@link GestoreCentriVaccinali}
     * @see GestoreCentriVaccinali#cercaCentroVaccinale(String)
     * @param nomeCentroVaccinale stringa contenente il nome del centro vaccinale
     * @return oggetto di ritorno da mandare al client
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    @Override
    public Risposta cercaCentroVaccinale(String nomeCentroVaccinale) throws RemoteException {
        return gestoreCentriVaccinali.cercaCentroVaccinale(nomeCentroVaccinale);
    }
    /**
     * richiama il metodo con il medesimo nome contenuto nella classe di logica {@link GestoreCentriVaccinali}
     * @see GestoreCentriVaccinali#cercaCentroVaccinale(String,String)
     * @param comune stringa contenente il nome del comune
     * @param tipologia stringa contenente il valore della tipologia
     * @return oggetto di ritorno da mandare al client
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    @Override
    public Risposta cercaCentroVaccinale(String comune, String tipologia) throws RemoteException {
        return gestoreCentriVaccinali.cercaCentroVaccinale(comune, tipologia);
    }
    /**
     * richiama il metodo con il medesimo nome contenuto nella classe di logica {@link GestoreCentriVaccinali}
     * @see GestoreCentriVaccinali#visulizzaInfoCentroVaccinale(CentroVaccinale)
     * @param centroVaccinale stringa contenente il nome del centro vaccinale
     * @return oggetto di ritorno da mandare al client
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    @Override
    public Risposta visulizzaInfoCentroVaccinale(CentroVaccinale centroVaccinale) throws RemoteException {
        return gestoreCentriVaccinali.visulizzaInfoCentroVaccinale(centroVaccinale);
    }
    /**
     * richiama il metodo con il medesimo nome contenuto nella classe di logica {@link GestoreCittadini}
     * @see GestoreCittadini#inserisciEventiAvversi(EventoAvverso)
     * @param eventoAvverso stringa contenente il valore dell'evento averso
     * @return oggetto di ritorno da mandare al client
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    @Override
    public Risposta inserisciEventiAvversi(EventoAvverso eventoAvverso) throws RemoteException {
        return gestoreCittadini.inserisciEventiAvversi(eventoAvverso);
    }
    /**
     * richiama il metodo con il medesimo nome contenuto nella classe di logica {@link GestoreCittadini}
     * @see GestoreCittadini#visualizzaEventiAvversi(String)
     * @param userId stringa contenente il valore dell'id dell'user
     * @return oggetto di ritorno da mandare al client
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    @Override
    public Risposta visualizzaEventiAvversi(String userId) throws RemoteException {
        return gestoreCittadini.visualizzaEventiAvversi(userId);
    }
    /**
     * richiama il metodo con il medesimo nome contenuto nella classe di logica {@link GestoreCittadini}
     * @see GestoreCittadini#getEventi()
     * @return oggetto di ritorno da mandare al client
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    @Override
    public String[] getEventi() throws RemoteException {
        return gestoreCittadini.getEventi();
    }
    /**
     * richiama il metodo con il medesimo nome contenuto nella classe di logica {@link GestoreCentriVaccinali}
     * @see GestoreCentriVaccinali#getTipologie()
     * @return oggetto di ritorno da mandare al client
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    @Override
    public String[] getTipologie() throws RemoteException {
        return gestoreCentriVaccinali.getTipologie();
    }
    /**
     * richiama il metodo con il medesimo nome contenuto nella classe di logica {@link GestoreCentriVaccinali}
     * @see GestoreCentriVaccinali#getVaccini()
     * @return oggetto di ritorno da mandare al client
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    @Override
    public String[] getVaccini() throws RemoteException {
        return gestoreCentriVaccinali.getVaccini();
    }

    /**
     * Il metodo <code>checkEnum</code> richiama i metodi <code>checkEnum</code> delle classi di logica {@link GestoreCittadini},{@link GestoreCentriVaccinali}
     */
    public static void checkEnum() {
        gestoreCentriVaccinali.checkEnum();
        gestoreCittadini.checkEnum();
    }

}
