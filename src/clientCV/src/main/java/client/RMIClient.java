package client;

import Interface.RMIServerInterface;
import com.sun.jndi.cosnaming.IiopUrl;
import controllers.MainClientUIController;
import javafx.scene.control.Alert;

import java.net.InetAddress;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

public class RMIClient {

    public static RMIServerInterface server;

    public static String nameServer;

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

    public static void setNameServer(String nameServer) {
        RMIClient.nameServer = nameServer;
    }
}
