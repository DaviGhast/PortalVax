package model;

import java.io.Serializable;

public class Risposta implements Serializable {
    private Stato stato;
    private String message;
    private Object object;

    public Risposta() {

    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Risposta(Stato stato, String message, Object object) {
        this.stato = stato;
        this.message = message;
        this.object = object;
    }

    public Risposta(Stato stato, String message) {
        this.stato = stato;
        this.message = message;
    }

    public Risposta(Stato stato, Object object) {
        this.stato = stato;
        this.object = object;
    }
}

