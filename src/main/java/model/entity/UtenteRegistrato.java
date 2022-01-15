package model.entity;

import java.util.Date;

public class UtenteRegistrato {
    private String nome;
    private String cognome;
    private String DataDiNascita;
    private String username;
    private String password;
    private String email;
    private String indirizzo;

    public UtenteRegistrato(String nome, String cognome, String dataDiNascita, String username, String password, String email, String indirizzo) {
        this.nome = nome;
        this.cognome = cognome;
        DataDiNascita = dataDiNascita;
        this.username = username;
        this.password = password;
        this.email = email;
        this.indirizzo = indirizzo;
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

    public String getDataDiNascita() {
        return DataDiNascita;
    }

    public void setDataDiNascita(String dataDiNascita) {
        DataDiNascita = dataDiNascita;
    }

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
}
