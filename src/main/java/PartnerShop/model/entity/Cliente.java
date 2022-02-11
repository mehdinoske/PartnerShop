package PartnerShop.model.entity;

import java.util.ArrayList;

public class Cliente extends UtenteRegistrato{
   private String cartaDiCredito;
   private ArrayList<Prodotto> listaDesideri = new ArrayList<Prodotto>();

    public Cliente(String nome, String cognome, String dataDiNascita, String username, String password, String email, String indirizzo,String cellulare,int tipo, String cartaDiCredito) {
        super(nome, cognome, dataDiNascita, username, password, email, indirizzo,cellulare,tipo);
        this.cartaDiCredito = cartaDiCredito;
    }
    public Cliente(){

    }

    public String getCartaDiCredito() {
        return cartaDiCredito;
    }

    public ArrayList<Prodotto> getListaDesideri() {
        return listaDesideri;
    }

    public void addListaDesideri(Prodotto pr){
        listaDesideri.add(pr);
    }

    public void removeListaDesideri(Prodotto pr){
        listaDesideri.remove(pr);
    }

    public boolean containsListaDesideri(Prodotto pr){
        return listaDesideri.contains(pr);
    }

    public void setListaDesideri(ArrayList<Prodotto> listaDesideri) {
        this.listaDesideri = listaDesideri;
    }

    public void setCartaDiCredito(String cartaDiCredito) {
        this.cartaDiCredito = cartaDiCredito;
    }

}
