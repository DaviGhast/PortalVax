package model;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * E' il modello dei Enum
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class EnumModel implements Serializable {

    private int enumID;
    private String enumName;
    private String[] enumList;

    /**
     * Costruttore per l'oggetto EnumModel
     * @param enumID l'id del'enum
     * @param enumName e' il nome dell'enum
     * @param enum_list e' la lista dei enum
     */
    public EnumModel(int enumID, String enumName, String[] enum_list) {
        this.enumID = enumID;
        this.enumName = enumName;
        this.enumList = enum_list;
    }

    public EnumModel(String enumName, String[] enum_list) {
        this.enumName = enumName;
        this.enumList = enum_list;
    }

    public EnumModel() {

    }
    /**
     * Il Metodo <code>getEnumId</code> è il metodo che recupera l'enumId
     * @return ritorna il valore della password
     */
    public int getEnumID() {
        return enumID;
    }
    /**
     * Il Metodo <code>setEnumId</code> è il metodo che salva la password
     * @param enumID intero contenente la password
     */
    public void setEnumID(int enumID) {
        this.enumID = enumID;
    }
    /**
     * Il Metodo <code>getEnumId</code> è il metodo che recupera l'enumName
     * @return ritorna il valore dell'enumName
     */
    public String getEnumName() {
        return enumName;
    }
    /**
     * Il Metodo <code>setEnumName</code> è il metodo che salva l'enumName
     * @param enumName stringa contenente l'enumName
     */
    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }
    /**
     * Il Metodo <code>getEnumList</code> è il metodo che recupera l'enumList
     * @return ritorna il valore dell'enumList
     */
    public String[] getEnumList() {
        return enumList;
    }
    /**
     * Il Metodo <code>setEnumList</code> è il metodo che salva l'enumList
     * @param enum_list stringa contenente l'enumName
     */
    public void setEnumList(String[] enum_list) {
        this.enumList = enum_list;
    }
}
