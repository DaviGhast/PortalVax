package client;

import Interface.RMIServerInterface;
import controllers.MainClientUIController;
import javafx.scene.control.Alert;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {

    public static RMIServerInterface server;

    public static boolean serverConnection() {
        boolean isConnectedToServer = false;
        try {
            Registry reg = LocateRegistry.getRegistry();
            server = (RMIServerInterface)reg.lookup("Server");
            isConnectedToServer = true;
        } catch (RemoteException | NotBoundException e) {
            isConnectedToServer = false;
        }
        return isConnectedToServer;
    }

}
