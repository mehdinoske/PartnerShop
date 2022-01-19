package PartnerShop.registrazione.service;

import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.model.entity.Venditore;

public class  RegistrazioneServiceImp implements RegistrazioneService{

    @Override
    public UtenteRegistrato RegistrazioneCliente(UtenteRegistrato ut) {

       UtenteRegistratoDAO utDAO = new UtenteRegistratoDAO();
        utDAO.doSave(ut);
        return ut;
    }

    @Override
    public UtenteRegistrato RegistrazioneVenditore(UtenteRegistrato ut) {
        return null;
    }
}
