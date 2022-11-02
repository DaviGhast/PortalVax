package model;

public class CittadinoRegistrato {
    private short id;
    private String email;
    private String userId;

    private String password;
    private String codiceFiscale;

    public CittadinoRegistrato(short id, String email, String userId, String password, String codiceFiscale){
        this.id = id;
        this.email = email;
        this.userId = userId;
        this.password = password;
        this.codiceFiscale = codiceFiscale;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCodiceFiscale() { return codiceFiscale; }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }


}
