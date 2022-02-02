package PartnerShop.registrazione.service;

import PartnerShop.model.dao.CarrelloDAO;
import PartnerShop.model.dao.ClienteDAO;
import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.dao.VenditoreDAO;
import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.model.entity.Venditore;

/**
 * implementa la classe che esplicita i metodi definiti nell'interfaccia di registrazione
 * @author Giuseppe Abbatiello
 */
public class  RegistrazioneServiceImp implements RegistrazioneService{

    /**
     * implementa la funzionalità di registrazione del cliente
     * @param ut - utente inviato da RegistrazioneController
     * @return cliente per essere loggato
     */
    @Override
    public UtenteRegistrato RegistrazioneCliente(UtenteRegistrato ut) {

        CarrelloDAO carDAO = new CarrelloDAO();
        ClienteDAO ctDAO = new ClienteDAO();
        ctDAO.doSave(ut,0);
        ctDAO.doSave(ut.getEmail());
        carDAO.doCreateCarrello(ut.getEmail());
        ut.setId_Carrello(carDAO.doRetrieveIdCarrelloByEmailCliente(ut.getEmail()));
        return ut;
    }

    /**
     * implementa la funzionalita di registrazione del venditore
     * @param ut           utente inviato da RegistrazioneController
     * @param nomeNegozio  nome del negozio inserito dal venditore
     * @param Piva        partita iva del negozio inserita dal venditore
     * @return venditore per essere loggato
     */
    @Override
    public UtenteRegistrato RegistrazioneVenditore(UtenteRegistrato ut,String nomeNegozio,String Piva) {
        VenditoreDAO vtDAO = new VenditoreDAO();
        vtDAO.doSave(ut,1);
        vtDAO.doSave(ut.getEmail(),nomeNegozio,Piva);
        return ut;
    }

    public UtenteRegistrato verificaUsernameEmail(String str,int tipo){
        UtenteRegistrato ut = new UtenteRegistrato();
        UtenteRegistratoDAO utDB = new UtenteRegistratoDAO();
       if(tipo == 0){
            ut = utDB.doRetrieveByUsername(str);
        }else if(tipo == 1){
            ut = utDB.doRetrieveByEmail(str);
        }
        return ut;
    }
}
