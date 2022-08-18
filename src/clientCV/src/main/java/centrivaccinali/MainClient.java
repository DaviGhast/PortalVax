package centrivaccinali;

import Interface.RMIServerInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainClient {
    public static void main(String[] args) throws NotBoundException, RemoteException {
        Registry reg = LocateRegistry.getRegistry();
        RMIServerInterface server = (RMIServerInterface)reg.lookup("Server");

    }
}
