package model;

public class CittadinoRegistrato {
    private String userId;
    private String email;
    private String password;
    private String codiceFiscale;

    public CittadinoRegistrato(String userId, String email, String password, String codiceFiscale){
        this.userId = userId.toLowerCase();
        this.email = email.toLowerCase();
        this.password = password;
        this.codiceFiscale = codiceFiscale;
    }

    public CittadinoRegistrato() {
    }

    public String getUserId() {
        return userId.toLowerCase();
    }

    public void setUserId(String userId) {
        this.userId = userId.toLowerCase();
    }

    public String getEmail() {
        return email.toLowerCase();
    }

    public void setEmail(String email) { this.email = email.toLowerCase(); }

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
