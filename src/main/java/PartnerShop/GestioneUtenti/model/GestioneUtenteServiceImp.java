package PartnerShop.GestioneUtenti.model;

import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.UtenteRegistrato;

public class GestioneUtenteServiceImp implements GestioneUtenteService {

    @Override
    public void ModificaDati(UtenteRegistrato ut, String email) {
        UtenteRegistratoDAO utenteDAO = new UtenteRegistratoDAO();
        utenteDAO.doUpdate(ut, email);
    }

    @Override
    public void CancellaUtente(String email) {
        UtenteRegistratoDAO utenteDAO  = new UtenteRegistratoDAO();
        utenteDAO.doDelete(email);
    }


}
