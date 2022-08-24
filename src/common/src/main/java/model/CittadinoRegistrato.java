package model;

public class CittadinoRegistrato {
    private short id;
    private String email;
    private short userId;

    private int password;
    private String codiceFiscale;

    public CittadinoRegistrato() {
    }

    public short getId() {
        return id;
    }

    public void setId(short id) { this.id = id;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }

    public short getUserId() {
        return userId;
    }

    public void setUserId(short userId) {
        this.userId = userId;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getCodiceFiscale() { return codiceFiscale; }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }


}
