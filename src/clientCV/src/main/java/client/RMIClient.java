package client;

import Interface.RMIServerInterface;
import controllers.MainClientUIController;
import javafx.scene.control.Alert;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

public class RMIClient {

    public static RMIServerInterface server;

    public static boolean serverConnection() {
        boolean isConnectedToServer = false;
        try {
            Registry registro = LocateRegistry.getRegistry(1099);
            RMIServerInterface stub = (RMIServerInterface) registro.lookup("PortalVaxServer");
            server = stub;
            isConnectedToServer = true;
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        return isConnectedToServer;
    }

}
