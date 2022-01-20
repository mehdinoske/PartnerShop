package PartnerShop.autenticazione.service;

import PartnerShop.model.dao.ClienteDAO;
import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.UtenteRegistrato;

import java.io.IOException;

public class AutenticazioneServiceImp implements AutenticazioneService{

    @Override
    public UtenteRegistrato login(String username, String password) {
        UtenteRegistrato ut = null;
        UtenteRegistratoDAO utDB = new UtenteRegistratoDAO();
        try {
            if (username != null && password != null) {
                ut = utDB.doRetrieveByUsernamePass(username, password);
            }
            if (ut == null) {
                throw new IOException("Errore utente null");
            }
        }catch(IOException e ){
            e.printStackTrace();
        }
        return ut;
    }
}
