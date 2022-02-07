package PartnerShop.GestioneUtenti.model;

import PartnerShop.model.entity.UtenteRegistrato;

import java.util.ArrayList;

public interface GestioneUtenteService {

    void ModificaDati(UtenteRegistrato ut);
    void CancellaUtente(String email);
    ArrayList<UtenteRegistrato> VisualizzaUtenti();
}
