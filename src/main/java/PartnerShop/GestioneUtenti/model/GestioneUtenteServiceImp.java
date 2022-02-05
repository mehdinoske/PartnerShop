package PartnerShop.GestioneUtenti.model;

import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.UtenteRegistrato;

import java.util.ArrayList;

public class GestioneUtenteServiceImp implements GestioneUtenteService {

    @Override
    public void ModificaDati(UtenteRegistrato ut) {
        UtenteRegistratoDAO utenteDAO = new UtenteRegistratoDAO();
        utenteDAO.doUpdate(ut);
    }

    @Override
    public void CancellaUtente(String email) {
        UtenteRegistratoDAO utenteDAO  = new UtenteRegistratoDAO();
        utenteDAO.doDelete(email);
    }

    @Override
    public ArrayList<UtenteRegistrato> VisualizzaUtenti() {
        UtenteRegistratoDAO utenteDAO = new UtenteRegistratoDAO();
        ArrayList<UtenteRegistrato> list = utenteDAO.doRetrieveAll();
        return list;
    }
}
