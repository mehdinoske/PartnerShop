package PartnerShop.segnalazione.service;

import PartnerShop.gestioneProdotto.service.GestioneProdottoService;
import PartnerShop.model.dao.SegnalazioneDAO;
import PartnerShop.model.entity.Segnalazione;
import PartnerShop.model.entity.UtenteRegistrato;

import java.util.ArrayList;

/**
 *Questa classe contieni i metodi per la gestione della segnalazione
 * @version 1.0
 * @see SegnalazioneService interfaccia della classe
 */
public class SegnalazioneServiceImp implements SegnalazioneService{
    private SegnalazioneDAO segnalazioneDAO;
    private Segnalazione segn;

    /**
     * Questo metodo salva nel database la segnalazione passata come parametro
     * @param segn segnalazione da salvare
     */
    @Override
    public void aggiungiSegnalazione(Segnalazione segn) {
        segnalazioneDAO = new SegnalazioneDAO();
        segnalazioneDAO.doSave(segn);
    }

    /**
     * Questo metodo restituisce un ArrayList contenente tutte le segnalazioni ancora aperte oppure ancora chiuse
     * @param par quando sarà 0 restituire tutte le segnalazioni aperte, se 1 tutte quelle chiuse
     * @return restituisce un ArrayList contenente tutte le segnalazioni aperte o chiuse
     */
    @Override
    public ArrayList<Segnalazione> visualizzaListaSegnalazioni(int par) {
        segnalazioneDAO = new SegnalazioneDAO();
        ArrayList<Segnalazione> listSegnalazioni;
        listSegnalazioni = segnalazioneDAO.doRetrieveAll(par);
        return listSegnalazioni;
    }

    /**
     * Questo metodo restituisce la segnalazione che ha come id l'intero passato come parametro
     * @param id id della segnalazione da restituire
     * @return l'oggetto segnalazione
     */
    @Override
    public Segnalazione visualizzaSegnalazione(int id) {
        SegnalazioneDAO segnalazioneDAO = new SegnalazioneDAO();
        segn = segnalazioneDAO.doRetrieveById(id);
        return segn;
    }

    /**
     * Questo metodo chiude la segnalazione che ha come id l'intero passato come parametro
     * @param id id della segnalazione da chiudere
     */
    @Override
    public void chiudiSegnalazione(int id) {
        segnalazioneDAO.aggiornaStato(id);
    }
}
