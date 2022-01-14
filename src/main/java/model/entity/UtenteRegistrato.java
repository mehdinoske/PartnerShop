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
}
