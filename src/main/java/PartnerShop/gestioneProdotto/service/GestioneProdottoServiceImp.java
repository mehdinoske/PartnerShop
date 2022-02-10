package PartnerShop.gestioneProdotto.service;

import PartnerShop.model.dao.GestioneProdottoDAO;
import PartnerShop.model.entity.Prodotto;

import java.util.ArrayList;

/**
 *Questa classe contieni i metodi per la gestione del prodotto
 * @version 1.0
 * @see GestioneProdottoService interfaccia della classe
 */
public class GestioneProdottoServiceImp implements GestioneProdottoService {

    GestioneProdottoDAO pr = new GestioneProdottoDAO();
    ArrayList<Prodotto> p;

    /**
     * Questo metodo restituisce il prodotto che ha come id l'intero passato come parametro
     * @param id id del prodotto da restituire
     * @return l'oggetto prodotto
     */
    @Override
    public Prodotto getProdottoById(int id) {
        Prodotto p = pr.doRetrieveById(id);
        return p;
    }

    /**
     * Questo metodo elimina dal database il prodotto che ha come id l'intero passato come parametro
     * @param id id del prodotto da eliminare
     * @return variabile booleana usata per indicare che tutto è andato bene se è uguale a true
     */
    public boolean deleteProdottoById(int id) {
        pr.doDeleteById(id);
        return true;
    }

    /**
     * Questo metodo salva nel database il prodotto passato come parametro
     * @param p prodotto da salvare
     * @return variabile booleana usata per indicare che tutto è andato bene se è uguale a true
     */
    public boolean doSaveProdotto(Prodotto p) {
        pr.doSave(p);
        return true;
    }

    /**
     * Questo metodo aggiorna nel database il prodotto passato come parametro
     * @param p prodotto da aggiornare
     * @return variabile booleana usata per indicare che tutto è andato bene se è uguale a true
     */
    public boolean doUpdateProdotto(Prodotto p) {
        pr.doUpdate(p);
        return true;
    }

    /**
     * Questo metodo restituisce tutti i prodotti di un determinato venditore
     * @param email del venditore
     * @return restituisce un ArrayList contenente tutti i prodotti di un determinato venditore
     */
    public ArrayList<Prodotto> getProdottiByVenditore(String email) {
        p = pr.doRetrieveByVenditore(email);
        return p;
    }

    /**
     * Questo metodo restituisce tutti i prodotti
     * @return restituisce un ArrayList contenente tutti i prodotti
     */
    public ArrayList<Prodotto> getAllProdotti() {
        p = pr.doRetrieveAllProdotti();
        return p;
    }

    /**
     * Questo metodo restituisce un ArrayList contenente tutti i prodotti di una determinata categoria
     * @param cat la categoria selezionata
     * @return restituisce un ArrayList contenente tutti i prodotti di una determinata categoria
     */
    public ArrayList<Prodotto> getProdottiByCategoria(String cat) {
        ArrayList<Prodotto> p = pr.doRetrieveByCategoria(cat);
        return p;
    }

    /**
     * Questo metodo restituisce tutti i prodotti di una determinata ricerca tramite il nome
     * @param nome stringa usata per la ricerca
     * @return restituisce un ArrayList contenente tutti i prodotti di una determinata ricerca tramite il nome
     */
    public ArrayList<Prodotto> getProdottiByNome(String nome) {
        ArrayList<Prodotto> p = pr.doRetrieveByNome(nome);
        return p;
    }

}
