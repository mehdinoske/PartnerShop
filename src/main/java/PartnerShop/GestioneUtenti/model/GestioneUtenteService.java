package PartnerShop.GestioneUtenti.model;

import PartnerShop.model.entity.UtenteRegistrato;

public interface GestioneUtenteService {

    void ModificaDati(UtenteRegistrato ut);
    void CancellaUtente(String email);
}
