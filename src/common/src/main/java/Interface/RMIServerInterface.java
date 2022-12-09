package Interface;

import model.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerInterface extends Remote {
    Risposta registraCentroVaccinale(CentroVaccinale centroVaccinale) throws RemoteException;
    Risposta registraVaccinato(Vaccinazione vaccinazione, Cittadino cittadino) throws RemoteException;
    Risposta registraCittadino(CittadinoRegistrato cittadinoRegistrato, short idVaccinazione) throws RemoteException;
    Risposta cercaIdVaccinazione(String codiceFiscale) throws RemoteException;
}
