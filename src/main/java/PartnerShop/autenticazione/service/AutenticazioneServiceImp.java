package PartnerShop.autenticazione.service;

import PartnerShop.model.dao.CarrelloDAO;
import PartnerShop.model.dao.ClienteDAO;
import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.Amministratore;
import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.UtenteRegistrato;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *  implementa la classe che esplicita i metodi definiti nell'interfaccia di autenticazione
 * 
 */
public class AutenticazioneServiceImp implements AutenticazioneService{

    UtenteRegistratoDAO utDB =null;

    /**
     * @param username - dell'utente
     * @param password - stringa convertita attraverso sha1
     * @return un utente registrato
     */
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
        if(ut!=null && ut.getTipo()==0) {
            CarrelloDAO car = new CarrelloDAO();
            int id_carrello = car.doRetrieveIdCarrelloByEmailCliente(ut.getEmail());
            if(id_carrello==0)
                car.doCreateCarrello(ut.getEmail());
            id_carrello = car.doRetrieveIdCarrelloByEmailCliente(ut.getEmail());
            ut.setId_Carrello(id_carrello);
        }
        return ut;
    }

    /**
     * @param username inserita dall'utente
     * @param password inserita dall'utente
     * @return l'admin se username e password sono nel database
     *
     */
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

    @Override
    public boolean logout(HttpSession sessione) {
        if(sessione.getAttribute("utente")!=null){
            sessione.removeAttribute("utente");
            sessione.removeAttribute("Carrello");
            sessione.removeAttribute("ordini");
            sessione.removeAttribute("mes");
            sessione.removeAttribute("admin");
            return true;
        }
       return false;
    }


}
