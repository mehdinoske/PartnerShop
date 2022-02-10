package PartnerShop.GestioneAcquisti.service;

import PartnerShop.Exceptions.MyServletException;
import PartnerShop.model.entity.Carrello;
import PartnerShop.model.entity.Ordine;
import PartnerShop.model.entity.UtenteRegistrato;

import java.util.ArrayList;

/**
 * Implementa l'interfaccia service per il sottosistema Gestione Acquisti.
 * @author El Mehdi Boudad
 */
public interface GestioneAcquistiService {

    /**firma del metodo che implemeta l'aggiunta al carrello di un prodotto.
     * @param car oggetto di tipo Carrello prelevato dalla sessione.
     * @param ut  oggetto di tipo UtenteRegistrato prelevato dalla sessione con le informazioni dell'utente loggato.
     * @param prodottoIdStr stringa contenente l'id del prodotto da aggiungere al carrello.
     * @param quantStr string contenente la quantità da aggiugere al carrello.
     *
     */
    void aggiungiAlCarrello(Carrello car, UtenteRegistrato ut, String prodottoIdStr, String quantStr);

    /** firma del metodo che implementa la rimozione dal carrello di un prodotto.
     * @param ut oggetto di tipo UtenteRegistrato prelevato dalla sessione passato tramite controller contenente le informazioni dell'utente loggato in quel momento.
     * @param car oggetto di tipo Carrello prelevato dalla sessione passato tramite cotroller.
     * @param prodottoId intero contenente l'id del prodotto da rimuovere dal carrello.
     * @param setQuantStr stringa contenente quantita da rimuovere dal carrello.
     */
    void rimuovidalcarrello(UtenteRegistrato ut,Carrello car,int prodottoId,String setQuantStr);

    /** firma del metodo che implementa l'acquisto dei prodotti.
     * @param ut oggetto di tipo UtenteRegistrato prelevato dalla sessione passato tramite controller contenente le informazioni dell'utente loggato in quel momento.
     * @param car oggetto di tipo Carrello prelevato dalla sessione passato tramite cotroller.
     * @param nome stringa inviata dall'utente.
     * @param cognome stringa inviata dall'utente.
     * @param indirizzo stringa inviata dall'utente.
     * @param cardc stringa inviata dall'utente.
     * @return oggetto di tipo Ordine contenete l'ordine appena creato.
     */
    Ordine acquistaProdotto(UtenteRegistrato ut,Carrello car,String nome, String cognome, String indirizzo,String cardc);

    /** firma del metodo che implemeta la visualizzazione degli ordini.
     * @param ut oggetto di tipo UtenteRegistrato prelevato dalla sessione passato tramite controller contenente le informazioni dell'utente loggato in quel momento.
     * @return lista di oggetti di tipo Ordine.
     */
    ArrayList<Ordine> visualizzaOrdine(UtenteRegistrato ut);
}
