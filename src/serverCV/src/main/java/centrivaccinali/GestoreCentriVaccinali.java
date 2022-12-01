package centrivaccinali;

import database.CentroVaccinaleDAO;
import model.*;

import java.util.ArrayList;

/**
 * la classe si occupa di gestire i centri vaccinali
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class GestoreCentriVaccinali {
    public boolean registraCentroVaccinale(CentroVaccinale centroVaccinale) {
        return CentroVaccinaleDAO.insert(centroVaccinale);
    }

    /**
     * il metodo verifica l'esistenza di un centroVaccinale
     * @param nomeCentroVaccinale nome centro
     * @return esiste o non esiste
     */
    public boolean cercaCentroEsiste(String nomeCentroVaccinale) {
        boolean exist = false;
        ArrayList<CentroVaccinale> lista = searchCentroByName(nomeCentroVaccinale);
        for (CentroVaccinale centroVaccinale : lista) {
            if (centroVaccinale.getNomeCentroVaccinale().equals(nomeCentroVaccinale))
                exist = true;
        }
        return exist;
    }

    /**
     * il metodo cerca all'interno del centro Vaccinale utilizzando come parametro di ricerca il nome del centro vaccinale stesso
     * @param nomeCentroVaccinale è il parametro di ricerca
     * @return ListaRisultati centri vaccinali trovati nella tabella
     */
    public ArrayList<CentroVaccinale> searchCentroByName(String nomeCentroVaccinale) {
        ArrayList<CentroVaccinale> listaCentriVaccinali = CentroVaccinaleDAO.getByName(nomeCentroVaccinale);
        return listaCentriVaccinali;
    }

    /**
     * legge ogni riga dalla tabella centro_vaccinale.sql e per ognuna di essa istanzia oggetti di tipo centroVaccinale
     * @return listaCentriVaccinali e' una lista di CentriVaccinali
     */
    public ArrayList<CentroVaccinale> getCentriVaccinali(){
        ArrayList<CentroVaccinale> listaCentriVaccinali = new ArrayList<>();
        listaCentriVaccinali = CentroVaccinaleDAO.getAll();
        return listaCentriVaccinali;
    }

    /**
     * il metodo si occupa di cercare il centro vaccinale di interesse tramite comune all'interno della tabella
     * @param comune il nome del comune
     * @return listaCentriVaccinali contiene i centri vaccinali trovati
     */
    public ArrayList<CentroVaccinale> searchCentroByComune(String comune) {
        ArrayList<CentroVaccinale> listaCentriVaccinali = CentroVaccinaleDAO.getByComune(comune);
        return listaCentriVaccinali;
    }

    /**
     * il metodo si occupa di cercare il centro vaccinale di interesse tramite tipologia all'interno della tabella
     * @param tipologia il nome della tipologia
     * @return listarisultati contiene i centri vaccinali trovati
     */
    public ArrayList<CentroVaccinale> searchCentroByTipologia( String tipologia) {
        ArrayList<CentroVaccinale> listaCentriVaccinali = CentroVaccinaleDAO.getAll();
        ArrayList<CentroVaccinale> listaRisultati = new ArrayList<>();
        for (CentroVaccinale centroVaccinale: listaCentriVaccinali) {
            if (centroVaccinale.getTipologia().toLowerCase().contains(tipologia.toLowerCase()))
                listaRisultati.add(centroVaccinale);
        }
        return listaRisultati;
    }

}