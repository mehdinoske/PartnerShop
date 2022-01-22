package PartnerShop.gestioneProdotto.service;

import PartnerShop.model.entity.Prodotto;

public interface GestioneProdottoService {

    public Prodotto getProdottoById(int id);

    public void deleteProdottoById(int id);

    public void doSaveProdotto(Prodotto p);

    public void doUpdateProdotto(Prodotto p);

}
