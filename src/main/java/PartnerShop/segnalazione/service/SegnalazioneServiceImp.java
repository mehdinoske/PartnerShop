package PartnerShop.segnalazione.service;

import PartnerShop.model.dao.SegnalazioneDAO;
import PartnerShop.model.entity.Segnalazione;
import PartnerShop.model.entity.UtenteRegistrato;

public class SegnalazioneServiceImp implements SegnalazioneService{
    @Override
    public void inserisciSegnalazione(Segnalazione segn) {
        SegnalazioneDAO segnalazioneDAO = new SegnalazioneDAO();
        segnalazioneDAO.doSave(segn);
    }

    @Override
    public void eliminaSegnalazione(Segnalazione segn) {

    }
}
