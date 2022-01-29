package PartnerShop.gestioneProdotto.service;

import PartnerShop.model.entity.Prodotto;

import java.util.ArrayList;

public interface GestioneProdottoService {

    public Prodotto getProdottoById(int id);

    public void deleteProdottoById(int id);

    public void doSaveProdotto(Prodotto p);

    public void doUpdateProdotto(Prodotto p);

    public ArrayList<Prodotto> getAllProdotti();

    public ArrayList<Prodotto> getProdottoByVenditore(String email);

}
