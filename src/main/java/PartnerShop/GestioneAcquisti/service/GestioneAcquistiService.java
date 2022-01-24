package PartnerShop.GestioneAcquisti.service;

import PartnerShop.model.entity.Carrello;
import PartnerShop.model.entity.Ordine;
import PartnerShop.model.entity.UtenteRegistrato;

import java.util.ArrayList;

public interface GestioneAcquistiService {
    public void aggiungiAlCarrello(Carrello car, UtenteRegistrato ut, String prodottoIdStr, String quantStr, String setQuantStr);
    public void rimuovidalcarrello(UtenteRegistrato ut,Carrello car,int prodottoId,String setQuantStr);
    public void acquistaProdotto(UtenteRegistrato ut,Carrello car,String indirizzo,String cardc);
    public ArrayList<Ordine> visualizzaOrdine(UtenteRegistrato ut);
}
