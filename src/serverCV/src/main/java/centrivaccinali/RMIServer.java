package centrivaccinali;

import Interface.RMIServerInterface;

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

    public RMIServer() throws RemoteException {
        super();
    }

    /**
     * The main method make server ready
     * @param args some input, not required
     */
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            RMIServer server = new RMIServer();
            reg.rebind("Server",server);
            System.out.println("Server bounded in registry");
        }catch( Exception e) {
            System.err.println( "Server Main exception:"+e) ;
        }
    }
}
