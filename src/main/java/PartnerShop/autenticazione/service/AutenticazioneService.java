package PartnerShop.autenticazione.service;

import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.UtenteRegistrato;

import java.util.List;

public interface AutenticazioneService {

    UtenteRegistrato login(String email, String password);
    Cliente findClienteByUsernamePass(String username,String password);
}
