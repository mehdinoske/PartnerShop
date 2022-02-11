package PartnerShop.GestioneUtenti.service;

import PartnerShop.Exceptions.MyServletException;
import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.Prodotto;
import PartnerShop.model.entity.UtenteRegistrato;

import java.util.ArrayList;

/**
 *  interfaccia per i metodi del sottosistema GestioneUtente implementata per rispettare il Facade Pattern
 *  @author Marco Ancona, El Mehdi Boudad
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

    /**
     *firma del metodo che permette l'aggiunta di un prodotto alla lista desideri personale
     * @param cl oggetto Clinte che contiene i dati dell'utente
     * @param idProdotto intero che identifica il prodotto da aggiungere alla lista
     */
    void aggiungiListaDesideri(Cliente cl,int idProdotto);

    /**
     *firma del metodo che permette la rimozione di un prodotto dalla lista desideri personale
     * @param cl oggetto Cliente che contiene i dati dell'utente
     * @param idProdotto intero che identifica il prodotto da rimuovere dalla lista
     */
    void rimuoviListaDesideri(Cliente cl,int idProdotto);

    /**
     *firma del metodo che recupera la lista desideri dell'utente loggato dal DB
     * @param email stringa che identifica l'utente
     * @return ArrayList di oggetti Prodotto che contiene tutti i prodotti inseriti nella lista desideri
     */
    ArrayList<Prodotto> getListaDesideri(String email);
}
