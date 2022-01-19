package PartnerShop.registrazione.service;

import PartnerShop.model.dao.ClienteDAO;
import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.dao.VenditoreDAO;
import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.model.entity.Venditore;

public class  RegistrazioneServiceImp implements RegistrazioneService{

    @Override
    public UtenteRegistrato RegistrazioneCliente(UtenteRegistrato ut) {

       ClienteDAO ctDAO = new ClienteDAO();
        ctDAO.doSave(ut,0);
        ctDAO.doSave(ut.getEmail());
        return ut;
    }

    @Override
    public UtenteRegistrato RegistrazioneVenditore(UtenteRegistrato ut,String nomeNegozio,String Piva) {
        VenditoreDAO vtDAO = new VenditoreDAO();
        vtDAO.doSave(ut,1);
        vtDAO.doSave(ut.getEmail(),nomeNegozio,Piva);
        return ut;
    }
}
