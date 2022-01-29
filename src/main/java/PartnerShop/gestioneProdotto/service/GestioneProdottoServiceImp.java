package PartnerShop.gestioneProdotto.service;

import PartnerShop.model.dao.GestioneProdottoDAO;
import PartnerShop.model.entity.Prodotto;

import java.util.ArrayList;

public class GestioneProdottoServiceImp implements GestioneProdottoService {

    GestioneProdottoDAO pr = new GestioneProdottoDAO();
    ArrayList<Prodotto> p;

    @Override
    public Prodotto getProdottoById(int id) {
        Prodotto p = pr.doRetrieveById(id);
        return p;
    }

    public void deleteProdottoById(int id) {
        pr.doDeleteById(id);
    }

    public void doSaveProdotto(Prodotto p) {
        pr.doSave(p);
    }

    public void doUpdateProdotto(Prodotto p) {
        pr.doUpdate(p);
    }

    public ArrayList<Prodotto> getProdottoByVenditore(String email) {
        p = pr.doRetrieveByVenditore(email);
        return p;
    }

    public ArrayList<Prodotto> getAllProdotti() {
        p = pr.doRetrieveAllProdotti();
        return p;
    }
}
