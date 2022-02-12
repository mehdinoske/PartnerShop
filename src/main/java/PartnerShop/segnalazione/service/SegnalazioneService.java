package PartnerShop.segnalazione.service;

import PartnerShop.model.entity.Segnalazione;

import java.util.ArrayList;

/**
 * Questa classe rappresenta l'interfaccia utilizzata da SegnalazioneService e implementata per rispettare il facade pattern
 */
public interface SegnalazioneService {
     /**
      * Questo metodo salva nel database la segnalazione passata come parametro
      * @param segn segnalazione da salvare
      */
     void aggiungiSegnalazione(Segnalazione segn);

     /**
      * Questo metodo restituisce un ArrayList contenente tutte le segnalazioni ancora aperte oppure ancora chiuse
      * @param par quando sarà 0 restituire tutte le segnalazioni aperte, se 1 tutte quelle chiuse
      * @return restituisce un ArrayList contenente tutte le segnalazioni aperte o chiuse
      */
     ArrayList<Segnalazione> visualizzaListaSegnalazioni(int par);

     /**
      * Questo metodo restituisce la segnalazione che ha come id l'intero passato come parametro
      * @param id id della segnalazione da restituire
      * @return l'oggetto segnalazione
      */
     Segnalazione visualizzaSegnalazione(int id);

     /**
      * Questo metodo chiude la segnalazione che ha come id l'intero passato come parametro
      * @param id id della segnalazione da chiudere
      */
     void chiudiSegnalazione(int id);
}
