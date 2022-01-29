package PartnerShop.autenticazione.service;

import PartnerShop.model.entity.Amministratore;
import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.UtenteRegistrato;

import java.util.List;

/**
 *  interfaccia
 */
public interface AutenticazioneService {
    UtenteRegistrato login(String username, String password);
    Amministratore verificaAdmin(String username,String password);
}
