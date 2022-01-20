package PartnerShop.model.entity;

public class Amministratore {
    private int id;
    private String username;
    private String password;

    public Amministratore(){

    }

    public Amministratore(String username,String password){

    this.username = username;
    this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
