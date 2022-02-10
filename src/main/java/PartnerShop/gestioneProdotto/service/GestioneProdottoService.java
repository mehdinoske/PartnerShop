package PartnerShop.gestioneProdotto.service;

import PartnerShop.model.entity.Prodotto;

import java.util.ArrayList;
/**
 * Questa classe rappresenta l'interfaccia utilizzata da GestioneProdottoServiceImp e implementata per rispettare il facade pattern
 */
public interface GestioneProdottoService {

    /**
     * Questo metodo restituisce il prodotto che ha come id l'intero passato come parametro
     * @param id id del prodotto da restituire
     * @return l'oggetto prodotto
     */
    Prodotto getProdottoById(int id);

    /**
     * Questo metodo elimina dal database il prodotto che ha come id l'intero passato come parametro
     * @param id id del prodotto da eliminare
     * @return variabile booleana usata per indicare che tutto è andato bene se è uguale a true
     */
    boolean deleteProdottoById(int id);

    /**
     * Questo metodo salva nel database il prodotto passato come parametro
     * @param p prodotto da salvare
     * @return variabile booleana usata per indicare che tutto è andato bene se è uguale a true
     */
    boolean doSaveProdotto(Prodotto p);

    /**
     * Questo metodo aggiorna nel database il prodotto passato come parametro
     * @param p prodotto da aggiornare
     * @return variabile booleana usata per indicare che tutto è andato bene se è uguale a true
     */
    boolean doUpdateProdotto(Prodotto p);

    /**
     * Questo metodo restituisce tutti i prodotti
     * @return restituisce un ArrayList contenente tutti i prodotti
     */
    ArrayList<Prodotto> getAllProdotti();

    /**
     * Questo metodo restituisce tutti i prodotti di un determinato venditore
     * @param email del venditore
     * @return restituisce un ArrayList contenente tutti i prodotti di un determinato venditore
     */
    ArrayList<Prodotto> getProdottiByVenditore(String email);

    /**
     * Questo metodo restituisce un ArrayList contenente tutti i prodotti di una determinata categoria
     * @param cat la categoria selezionata
     * @return restituisce un ArrayList contenente tutti i prodotti di una determinata categoria
     */
    ArrayList<Prodotto> getProdottiByCategoria(String cat);

    /**
     * Questo metodo restituisce tutti i prodotti di una determinata ricerca tramite il nome
     * @param nome stringa usata per la ricerca
     * @return restituisce un ArrayList contenente tutti i prodotti di una determinata ricerca tramite il nome
     */
    ArrayList<Prodotto> getProdottiByNome(String nome);

}
