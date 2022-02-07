package PartnerShop.model.entity;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;

public class Ordine {
    private int id;
    private String nome;
    private String cognome;

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

    private LinkedHashMap<Integer, Prodotto> prodottoHash = new LinkedHashMap();
    private LinkedHashMap<Integer, Integer> quantHash = new LinkedHashMap();
    private String email_cliente;
    private String indirizzo;
    private float prezzo_tot;
    private Date data;
    private Calendar today = Calendar.getInstance();
    private java.sql.Date dataSql;

    public Ordine() {
    }

    public Collection<Prodotto> getProdotti() {
        return this.getProdottoHash().values();
    }

    public int getId() {
        return this.id;
    }

    public Prodotto getProdotto(int idProdotto) {
        return (Prodotto) this.getProdottoHash().get(idProdotto);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProdottoHash(Prodotto pr) {
        this.getProdottoHash().put(pr.getId(), pr);
    }

    public void setQuantHash(int idProdotto, int quant) {
        this.getQuantHash().put(idProdotto, quant);
    }

    public int getQuant(int idProdotto) {
        return (Integer)this.getQuantHash().get(idProdotto);
    }

    public long getPrezzoEuro(int idProdotto) {
        return (long)(Integer)this.getQuantHash().get(idProdotto) * (long)((Prodotto)this.getProdottoHash().get(idProdotto)).getPrezzo_Cent();
    }

    public LinkedHashMap<Integer, Prodotto> getProdottoHash() {
        return this.prodottoHash;
    }

    public LinkedHashMap<Integer, Integer> getQuantHash() {
        return this.quantHash;
    }

    public void setQuantHash(LinkedHashMap<Integer, Integer> quantHash) {
        this.quantHash = quantHash;
    }

    public String getEmailCliente() {
        return this.email_cliente;
    }

    public void setEmailCliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    public Date getData() {
        return this.data;
    }

    public void setData() {
        this.today.set(11, 0);
        this.data = this.today.getTime();
    }

    public java.sql.Date getDataSql() {
        return this.dataSql;
    }

    public void setDataSql(java.sql.Date dataSql) {
        this.dataSql = dataSql;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public float getPrezzo_tot() {
        return prezzo_tot;
    }

    public void setPrezzo_tot(float prezzo_tot) {
        this.prezzo_tot = prezzo_tot;
    }
}
