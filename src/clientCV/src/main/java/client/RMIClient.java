package client;

import Interface.RMIServerInterface;
import controllers.MainClientUIController;
import javafx.scene.control.Alert;

import java.net.InetAddress;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
/**
 * Classe RMI lato client
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 * @version 1.0
 */
public class RMIClient {

    public static RMIServerInterface server;

    public static String nameServer;

    /**
     * Il metodo <code>serverConnection</code> esegue la connessione al server
     * @return risposta true/false
     */
    public static boolean serverConnection() {
        boolean isConnectedToServer = false;
        try {
            Registry registro = null;
            if (nameServer.isEmpty() | nameServer.equalsIgnoreCase("localhost")){
                registro = LocateRegistry.getRegistry(1099);
            } else {
                String ipServer = String.valueOf(InetAddress.getByName(nameServer).getHostAddress());
                registro = LocateRegistry.getRegistry(ipServer,1099);
            }
            RMIServerInterface stub = (RMIServerInterface) registro.lookup("PortalVaxServer");
            server = stub;
            isConnectedToServer = true;
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        return isConnectedToServer;
    }

    /**
     * Il metodo <code>setNameServer</code> imposta il nome del server
     * @param nameServer stringa contenente il nome del server
     */
    public static void setNameServer(String nameServer) {
        RMIClient.nameServer = nameServer;
    }
}
