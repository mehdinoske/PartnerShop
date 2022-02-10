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

    public boolean deleteProdottoById(int id) {
        pr.doDeleteById(id);
        return true;
    }

    public boolean doSaveProdotto(Prodotto p) {
        pr.doSave(p);
        return true;
    }

    public boolean doUpdateProdotto(Prodotto p) {
        pr.doUpdate(p);
        return true;
    }

    public ArrayList<Prodotto> getProdottiByVenditore(String email) {
        p = pr.doRetrieveByVenditore(email);
        return p;
    }

    public ArrayList<Prodotto> getAllProdotti() {
        p = pr.doRetrieveAllProdotti();
        return p;
    }

    public ArrayList<Prodotto> getProdottiByCategoria(String cat) {
        ArrayList<Prodotto> p = pr.doRetrieveByCategoria(cat);
        return p;
    }


    public ArrayList<Prodotto> getProdottiByNome(String nome) {
        ArrayList<Prodotto> p = pr.doRetrieveByNome(nome);
        return p;
    }

}
