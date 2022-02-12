package PartnerShop.segnalazione.service;

import PartnerShop.model.entity.Segnalazione;

import java.util.ArrayList;

public interface SegnalazioneService {
     boolean aggiungiSegnalazione(Segnalazione segn);
     ArrayList<Segnalazione> visualizzaListaSegnalazioni(int par);
     Segnalazione visualizzaSegnalazione(int id);
     void chiudiSegnalazione(int id);
}
