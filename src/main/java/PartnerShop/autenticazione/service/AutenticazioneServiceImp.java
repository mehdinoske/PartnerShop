package PartnerShop.autenticazione.service;

import PartnerShop.Exceptions.MyServletException;
import PartnerShop.model.dao.CarrelloDAO;
import PartnerShop.model.dao.ClienteDAO;
import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.Amministratore;
import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.UtenteRegistrato;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 *  implementa la classe che esplicita i metodi definiti nell'interfaccia di autenticazione
 * 
 */
public class AutenticazioneServiceImp implements AutenticazioneService{

    private UtenteRegistratoDAO utDB =null;
    private String usernameReg = "^[a-zA-Z0-9\\-_]{1,20}$";


    public AutenticazioneServiceImp(){
        utDB = new UtenteRegistratoDAO();
    }

    public AutenticazioneServiceImp(UtenteRegistratoDAO utDB){
        this.utDB = utDB;
    }

    /**
     * @param username - dell'utente
     * @param password - stringa convertita attraverso sha1
     * @return un utente registrato
     */
    @Override
    public UtenteRegistrato login(String username, String password) {

        UtenteRegistrato ut = null;

        try {
            if (username != null && password != null) {
                if(username.matches(usernameReg)) {
                    ut = utDB.doRetrieveByUsernamePass(username, password);
                }else
                {
                    throw new MyServletException("errore parametri registrazione");
                }
            }
            if (ut == null) {
                throw new IOException("Errore utente null");
            }
        }catch(IOException e ){
            e.printStackTrace();
        }
        catch (MyServletException e){
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
            return true;
        }else if(sessione.getAttribute("admin")!=null){
            sessione.removeAttribute("admin");
            return true;
        }
       return false;
    }


}
