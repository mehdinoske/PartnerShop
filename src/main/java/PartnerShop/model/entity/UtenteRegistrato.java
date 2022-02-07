package PartnerShop.model.entity;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class UtenteRegistrato {
    private static final Pattern USERNAME = Pattern.compile("^[a-zA-Z0-9\\-_]{1,40}$");

    private String nome;
    private String cognome;
    private String ddn;
    private String username;
    private String password;
    private String passwordHash;
    private String email;
    private String indirizzo;
    private String cellulare;
    private int id_Carrello;
    private int tipo;

    public int getId_Carrello() {
        return id_Carrello;
    }

    public void setId_Carrello(int id_Carrello) {
        this.id_Carrello = id_Carrello;
    }




    public UtenteRegistrato(String nome, String cognome, String ddn, String username, String passwordHash, String email, String indirizzo,String cellulare,int tipo) {
        this.nome = nome;
        this.cognome = cognome;
        this.ddn = ddn;
        this.username = username;
        this.passwordHash = passwordHash;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String pass){
        this.password = pass;
    }

    public void setPasswordHash(String password) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            this.passwordHash = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
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


    public boolean isValidUsername(String username){
        return USERNAME.matcher(username).matches();
    }

    @Override
    public String toString() {
        return "UtenteRegistrato{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", ddn='" + ddn + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", cellulare='" + cellulare + '\'' +
                ", id_Carrello=" + id_Carrello +
                ", tipo=" + tipo +
                '}';
    }
}
