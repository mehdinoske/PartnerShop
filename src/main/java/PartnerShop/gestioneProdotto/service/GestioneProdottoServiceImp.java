package PartnerShop.gestioneProdotto.service;

import PartnerShop.model.dao.GestioneProdottoDAO;
import PartnerShop.model.entity.Prodotto;

public class GestioneProdottoServiceImp implements GestioneProdottoService {

    @Override
    public Prodotto getProdottoById(int id) {
        GestioneProdottoDAO pr = new GestioneProdottoDAO();
        Prodotto p = pr.doRetrieveById(id);
        return p;
    }


}
