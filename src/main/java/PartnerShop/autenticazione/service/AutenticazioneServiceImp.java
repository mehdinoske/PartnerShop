package PartnerShop.autenticazione.service;

import PartnerShop.model.dao.CarrelloDAO;
import PartnerShop.model.dao.ClienteDAO;
import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.Amministratore;
import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.UtenteRegistrato;

import java.io.IOException;

public class AutenticazioneServiceImp implements AutenticazioneService{

    UtenteRegistratoDAO utDB =null;
    @Override
    public UtenteRegistrato login(String username, String password) {
        UtenteRegistrato ut = null;
         utDB = new UtenteRegistratoDAO();
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
        if(ut != null && ut.getTipo() == 0){
            CarrelloDAO car = new CarrelloDAO();
            int id_carrello = car.doRetrieveIdCarrelloByEmailCliente(ut.getEmail());
            /*if(id_carrello == 0){
                car.doCreateCarrello(ut.getEmail());
                car.doRetrieveIdCarrelloByEmailCliente(ut.getEmail());
            }*/
                car.doCreateCarrello(ut.getEmail());
            ut.setId_Carrello(id_carrello);
        }
        return ut;
    }

    @Override
    public Amministratore verificaAdmin(String username, String password) {

         utDB = new UtenteRegistratoDAO();
         Amministratore amm = new Amministratore();
        try {
            if (username != null && password != null) {
                amm = utDB.doRetrieveAdmin(username,password);
            }
            if (amm == null) {
                throw new IOException("Errore admin null");
            }
        }catch(IOException e ){
            e.printStackTrace();
        }
        return amm;
    }
}
