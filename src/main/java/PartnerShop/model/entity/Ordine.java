package PartnerShop.model.entity;

public class Ordine {
private int id;
private String emailCliente;
private String dataOrdine;
private String indirizzo;
private double prezzoTot;

    public Ordine(int id, String emailCliente, String dataOrdine, String indirizzo, double prezzoTot) {
        this.id = id;
        this.emailCliente = emailCliente;
        this.dataOrdine = dataOrdine;
        this.indirizzo = indirizzo;
        this.prezzoTot = prezzoTot;
    }

    public Ordine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(String dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public double getPrezzoTot() {
        return prezzoTot;
    }

    public void setPrezzoTot(double prezzoTot) {
        this.prezzoTot = prezzoTot;
    }
}
