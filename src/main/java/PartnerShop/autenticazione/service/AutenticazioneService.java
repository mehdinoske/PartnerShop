package PartnerShop.autenticazione.service;

import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.UtenteRegistrato;

import java.util.List;

public interface AutenticazioneService {
    UtenteRegistrato login(String email, String password);
    Cliente findClienteByEmail(String email);
}
