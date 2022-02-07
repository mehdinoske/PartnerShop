package PartnerShop.GestioneUtenti.model;

import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.UtenteRegistrato;

import java.util.ArrayList;

public class GestioneUtenteServiceImp implements GestioneUtenteService {

    private String nomeMod="^[A-zÀ-ù ‘-]{2,30}$";
    private String cognomeMod="^[A-zÀ-ù ‘-]{2,30}$";
    private String cellMod = "^[0-9]\\d{9}$";
    private String passMod = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[_.,\\-+*!#@?])([a-zA-Z0-9_.,\\-+*!#@?]{6,25})$";

    @Override
    public boolean ModificaDati(UtenteRegistrato ut) {
        System.out.println(ut.getPassword());
        boolean mod = false;
        try{
            if(ut.getNome().matches(nomeMod) && ut.getCognome().matches(cognomeMod)
                    && ut.getCellulare().matches(cellMod) && (ut.getIndirizzo().length())>5
                    && ut.getPassword().matches(passMod)){
                UtenteRegistratoDAO utenteDAO = new UtenteRegistratoDAO();
                utenteDAO.doUpdate(ut);
                mod = true;
            }else{
                throw new IllegalArgumentException();
            }
        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }
        return mod;
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
