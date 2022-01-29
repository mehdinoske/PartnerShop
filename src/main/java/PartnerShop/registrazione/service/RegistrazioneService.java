package PartnerShop.registrazione.service;

import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.model.entity.Venditore;


/**
 * interfaccia per il sottosistema registrazione
 * @author Giuseppe Abbatiello
 */
public interface RegistrazioneService {
    /**
     * firma del metodo che implementa la funzione registrazione utente
     * @param ut - utente inviato da RegistrazioneController
     * @return cliente da loggato
     */
    public UtenteRegistrato RegistrazioneCliente(UtenteRegistrato ut);

    /**
     * firma del metodo che implementa la funzione di registrazione venditore
     * @param ut - utente inviato da RegistrazioneController
     * @param nomeNegozio - nome del negozio inserito dal venditore
     * @param Piva - partita iva del negozio inserita dal venditore
     * @return venditore da loggato
     */
    public UtenteRegistrato RegistrazioneVenditore(UtenteRegistrato ut, String nomeNegozio,String Piva);

}
