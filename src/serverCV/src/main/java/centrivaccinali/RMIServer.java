package centrivaccinali;

import model.CentroVaccinale;

import java.rmi.registry.Registry;

/**
 * The class <code>RMIServer</code>
 * @author Davide Mainardi (DaviGhast)
 * @version 1.0
 */
public class RMIServer {
    private static Registry registry;
    /**
     * The main method make server ready
     * @param args some input, not required
     */
    public static void main(String[] args) {
        try {
            CentroVaccinale centroVaccinale = new CentroVaccinale();
        }catch( Exception e) {
            System.err.println( "Server Main exception:"+e.toString( ) ) ;
        }
    }
}
