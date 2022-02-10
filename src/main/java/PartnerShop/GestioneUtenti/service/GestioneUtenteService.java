package PartnerShop.GestioneUtenti.service;

import PartnerShop.Exceptions.MyServletException;
import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.Prodotto;
import PartnerShop.model.entity.UtenteRegistrato;

import java.util.ArrayList;

public interface GestioneUtenteService {

    boolean ModificaDati(UtenteRegistrato ut) throws MyServletException;
    void CancellaUtente(String email);
    ArrayList<UtenteRegistrato> VisualizzaUtenti();
    void aggiungiListaDesideri(Cliente cl,int idProdotto);
    void rimuoviListaDesideri(Cliente cl,int idProdotto);
    ArrayList<Prodotto> getListaDesideri(String email);
}
