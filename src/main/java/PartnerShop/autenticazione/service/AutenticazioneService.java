package PartnerShop.autenticazione.service;

import PartnerShop.model.entity.Cliente;

public interface AutenticazioneService {
    Cliente findClienteByEmail(String email);
}
