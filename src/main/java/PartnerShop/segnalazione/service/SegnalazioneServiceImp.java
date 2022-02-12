package PartnerShop.segnalazione.service;

import PartnerShop.model.dao.SegnalazioneDAO;
import PartnerShop.model.entity.Segnalazione;
import PartnerShop.model.entity.UtenteRegistrato;

import java.util.ArrayList;

public class SegnalazioneServiceImp implements SegnalazioneService{
    private SegnalazioneDAO segnalazioneDAO;
    private Segnalazione segn;

    public SegnalazioneServiceImp(){
        segnalazioneDAO = new SegnalazioneDAO();
    }

    public SegnalazioneServiceImp(SegnalazioneDAO segnalazioneDAO){
        this.segnalazioneDAO = segnalazioneDAO;
    }

    @Override
    public boolean aggiungiSegnalazione(Segnalazione segn) {
        if(segn.getCommento().trim().length()<=100){
            segnalazioneDAO.doSave(segn);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Segnalazione> visualizzaListaSegnalazioni(int par) {
        ArrayList<Segnalazione> listSegnalazioni;
        listSegnalazioni = segnalazioneDAO.doRetrieveAll(par);
        return listSegnalazioni;
    }

    @Override
    public Segnalazione visualizzaSegnalazione(int id) {
        segn = segnalazioneDAO.doRetrieveById(id);
        return segn;
    }

    @Override
    public void chiudiSegnalazione(int id) {
        segnalazioneDAO.aggiornaStato(id);
    }
}
