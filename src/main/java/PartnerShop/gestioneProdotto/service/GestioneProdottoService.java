package PartnerShop.gestioneProdotto.service;

import PartnerShop.model.entity.Prodotto;

import java.util.ArrayList;
/**
 * Questa classe rappresenta l'interfaccia utilizzata da GestioneProdottoServiceImp e implementata per rispettare il facade pattern
 */
public interface GestioneProdottoService {

    public Prodotto getProdottoById(int id);

    public boolean deleteProdottoById(int id);

    public boolean doSaveProdotto(Prodotto p);

    public boolean doUpdateProdotto(Prodotto p);

    public ArrayList<Prodotto> getAllProdotti();

    public ArrayList<Prodotto> getProdottiByVenditore(String email);

    public ArrayList<Prodotto> getProdottiByCategoria(String cat);

    public ArrayList<Prodotto> getProdottiByNome(String nome);

}
