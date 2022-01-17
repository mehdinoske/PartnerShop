package model.entity;

import java.util.ArrayList;

public class ListaDesideri {
    private ArrayList<Prodotto> prodotti;
    public ListaDesideri(){
    }

    public ListaDesideri(ArrayList prodottiPreferiti){
    this.prodotti = prodottiPreferiti;
    }

    public void setProdotti(ArrayList<Prodotto> prodotti){
        this.prodotti = prodotti;
    }

    public ArrayList<Prodotto> getProdotti() {
        return prodotti;
    }
}
