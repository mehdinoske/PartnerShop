package PartnerShop.segnalazione.service;

import PartnerShop.model.dao.SegnalazioneDAO;
import PartnerShop.model.entity.Segnalazione;
import PartnerShop.model.entity.UtenteRegistrato;

import java.util.ArrayList;

public class SegnalazioneServiceImp implements SegnalazioneService{
    private SegnalazioneDAO segnalazioneDAO;
    private Segnalazione segn;

    @Override
    public void aggiungiSegnalazione(Segnalazione segn) {
        segnalazioneDAO = new SegnalazioneDAO();
        segnalazioneDAO.doSave(segn);
    }

    @Override
    public ArrayList<Segnalazione> visualizzaListaSegnalazioni(int par) {
        segnalazioneDAO = new SegnalazioneDAO();
        ArrayList<Segnalazione> listSegnalazioni;
        listSegnalazioni = segnalazioneDAO.doRetrieveAll(par);
        return listSegnalazioni;
    }

    @Override
    public Segnalazione visualizzaSegnalazione(int id) {
        SegnalazioneDAO segnalazioneDAO = new SegnalazioneDAO();
        segn = segnalazioneDAO.doRetrieveById(id);
        return segn;
    }

    @Override
    public void chiudiSegnalazione(int id) {
        segnalazioneDAO.aggiornaStato(id);
    }
}
