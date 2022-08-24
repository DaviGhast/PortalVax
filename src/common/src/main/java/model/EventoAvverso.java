package model;

public class EventoAvverso {
    private short id;
    private String email;
    private String userId;
    private Integer password;
    private String codiceFiscale;

    public EventoAvverso(){
    }
    public short getId() {
        return id;
    }

    public void setId(short id) { this.id = id;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email; }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
