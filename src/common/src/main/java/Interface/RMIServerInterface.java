package Interface;

import model.CentroVaccinale;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerInterface extends Remote {
    boolean registraCentroVaccinale(CentroVaccinale centroVaccinale) throws RemoteException;
}
