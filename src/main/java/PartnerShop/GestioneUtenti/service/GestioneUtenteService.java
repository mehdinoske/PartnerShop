package PartnerShop.GestioneUtenti.service;

import PartnerShop.Exceptions.MyServletException;
import PartnerShop.model.entity.UtenteRegistrato;

import java.util.ArrayList;

/**
 *  interfaccia per i metodi del sottosistema GestioneUtente implementata per rispettare il Facade Pattern
 *  @author Marco Ancona
 */

public interface GestioneUtenteService {

    boolean ModificaDati(UtenteRegistrato ut) throws MyServletException;

    void CancellaUtente(String email);

    ArrayList<UtenteRegistrato> VisualizzaUtenti();
}
