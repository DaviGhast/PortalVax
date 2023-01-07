package model;

import java.io.Serializable;
import java.util.ArrayList;

public class EnumModel implements Serializable {

    private int enumID;
    private String enumName;
    private String[] enumList;

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

    public int getEnumID() {
        return enumID;
    }

    public void setEnumID(int enumID) {
        this.enumID = enumID;
    }

    public String getEnumName() {
        return enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    public String[] getEnumList() {
        return enumList;
    }

    public void setEnumList(String[] enum_list) {
        this.enumList = enum_list;
    }
}
