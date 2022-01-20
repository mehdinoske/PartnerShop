package PartnerShop.model.entity;

public class UtenteRegistrato {
    private String nome;
    private String cognome;
    private String ddn;
    private String username;
    private String password;
    private String email;
    private String indirizzo;
    private String cellulare;
    private int tipo;


    public UtenteRegistrato(String nome, String cognome, String ddn, String username, String password, String email, String indirizzo,String cellulare,int tipo) {
        this.nome = nome;
        this.cognome = cognome;
        this.ddn = ddn;
        this.username = username;
        this.password = password;
        this.email = email;
        this.indirizzo = indirizzo;
        this.cellulare = cellulare;
        this.tipo = tipo;
    }

    public UtenteRegistrato(){

    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getDdn() { return ddn;}

    public void setDdn(String ddn) {this.ddn = ddn;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }


    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }
}
