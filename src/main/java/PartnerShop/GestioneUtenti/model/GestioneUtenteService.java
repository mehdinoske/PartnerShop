package PartnerShop.GestioneUtenti.model;

import PartnerShop.model.entity.UtenteRegistrato;

public interface GestioneUtenteService {

    public void ModificaDati(UtenteRegistrato ut, String email);
    public void CancellaUtente(String email);
}
