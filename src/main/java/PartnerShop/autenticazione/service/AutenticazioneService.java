package PartnerShop.autenticazione.service;

import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.UtenteRegistrato;

import java.util.List;

public interface AutenticazioneService {

    UtenteRegistrato login(String username, String password);
}
