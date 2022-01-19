package PartnerShop.registrazione.service;

import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.model.entity.Venditore;


public interface RegistrazioneService {
    public UtenteRegistrato RegistrazioneCliente(UtenteRegistrato ut);
    public UtenteRegistrato RegistrazioneVenditore(UtenteRegistrato ut);

}
