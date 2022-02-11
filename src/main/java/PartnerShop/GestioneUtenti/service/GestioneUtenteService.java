package PartnerShop.GestioneUtenti.service;

import PartnerShop.Exceptions.MyServletException;
import PartnerShop.model.entity.UtenteRegistrato;

import java.util.ArrayList;

/**
 *  interfaccia per i metodi del sottosistema GestioneUtente implementata per rispettare il Facade Pattern
 *  @author Marco Ancona
 */

public interface GestioneUtenteService {

    /**
     *firma del metodo che implementa la modifica dei dati personali
     * @param ut UtenteRegistrato che contiene le modifiche da applicare
     * @return un boolean che indica se la modifica va a buon fine
     * @throws MyServletException eccezione
     */
    boolean ModificaDati(UtenteRegistrato ut) throws MyServletException;

    /**
     * firma del metodo che implementa la rimozione dell'utente dal db
     * @param email stringa che identifica il cliente
     */
    void CancellaUtente(String email);

    /**
     *firma del metodo che permette la visualizzazione della lista degli utenti registrati
     * @return un ArrayList di UtenteRegistrato che contiene la lista di utenti
     */
    ArrayList<UtenteRegistrato> VisualizzaUtenti();
}
