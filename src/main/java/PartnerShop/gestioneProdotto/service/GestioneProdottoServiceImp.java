package PartnerShop.gestioneProdotto.service;

import PartnerShop.model.dao.GestioneProdottoDAO;
import PartnerShop.model.entity.Prodotto;

import java.util.ArrayList;

public class GestioneProdottoServiceImp implements GestioneProdottoService {

    @Override
    public Prodotto getProdottoById(int id) {
        GestioneProdottoDAO pr = new GestioneProdottoDAO();
        Prodotto p = pr.doRetrieveById(id);
        return p;
    }

    public void deleteProdottoById(int id) {
        GestioneProdottoDAO pr = new GestioneProdottoDAO();
        pr.doDeleteById(id);
    }

    public void doSaveProdotto(Prodotto p) {
        GestioneProdottoDAO pr = new GestioneProdottoDAO();
        pr.doSave(p);
    }

    public void doUpdateProdotto(Prodotto p) {
        GestioneProdottoDAO pr = new GestioneProdottoDAO();
        pr.doUpdate(p);
    }

    public ArrayList<Prodotto> getProdottoByVenditore(String email) {
        GestioneProdottoDAO pr = new GestioneProdottoDAO();
        ArrayList<Prodotto> p = pr.doRetrieveByVenditore(email);
        return p;
    }

    public ArrayList<Prodotto> getAllProdotti() {
        GestioneProdottoDAO pr = new GestioneProdottoDAO();
        ArrayList<Prodotto> p = pr.doRetrieveAllProdotti();
        return p;
    }
}
