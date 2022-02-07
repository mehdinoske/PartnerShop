package PartnerShop.GestioneUtenti.model;

import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.UtenteRegistrato;

import java.util.ArrayList;

public class GestioneUtenteServiceImp implements GestioneUtenteService {

    private String nomeMod="^[A-zÀ-ù ‘-]{2,30}$";
    private String cognomeMod="^[A-zÀ-ù ‘-]{2,30}$";
    private String cellMod = "^[0-9]\\d{9}$";
    private String indirizzoMod = "^[A-zÀ-ù ‘-]{4,100}$";
    private String passReg = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[_.,\\-+*!#@?])([a-zA-Z0-9_.,\\-+*!#@?]{6,25})$";

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
