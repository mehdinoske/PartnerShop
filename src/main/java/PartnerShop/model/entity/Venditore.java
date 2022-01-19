package PartnerShop.model.entity;

public class Venditore extends UtenteRegistrato{
    private String nomeNegozio;
    private String pIva;

    public Venditore(String nome, String cognome, String dataDiNascita, String username, String password, String email, String indirizzo, String cellulare,int tipo, String nomeNegozio, String pIva) {
        super(nome, cognome, dataDiNascita, username, password, email, indirizzo,cellulare, tipo);
        this.nomeNegozio = nomeNegozio;
        this.pIva = pIva;
    }

    public String getNomeNegozio() {
        return nomeNegozio;
    }

    public void setNomeNegozio(String nomeNegozio) {
        this.nomeNegozio = nomeNegozio;
    }

    public String getpIva() {
        return pIva;
    }

    public void setpIva(String pIva) {
        this.pIva = pIva;
    }
}
