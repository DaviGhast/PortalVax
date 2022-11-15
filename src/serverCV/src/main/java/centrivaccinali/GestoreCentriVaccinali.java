package centrivaccinali;

import database.CentroVaccinaleDAO;
import model.*;

public class GestoreCentriVaccinali {
    public boolean registraCentroVaccinale(CentroVaccinale centroVaccinale){
        return CentroVaccinaleDAO.insert(centroVaccinale);
    }
}
