package PartnerShop.autenticazione.service;

import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.UtenteRegistrato;

import java.util.List;

public interface AutenticazioneService {

    Cliente findClienteByUsernamePass(String username,String password);
}
