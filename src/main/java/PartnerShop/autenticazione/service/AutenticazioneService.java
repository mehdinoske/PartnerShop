package PartnerShop.autenticazione.service;

import PartnerShop.model.entity.Amministratore;
import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.UtenteRegistrato;

import java.util.List;

/**
 *  interfaccia per i metodi del sottosistema autenticazione
 *  @author Giuseppe Abbatiello
 */
public interface AutenticazioneService {
    /**
     * firma del metodo che implementa il login
     * @param username stringa inviata dall'utente
     * @param password stringa già convertita in sha1 inviata dall'utente
     * @return utente loggato
     */
    UtenteRegistrato login(String username, String password);

    /** firma del metodo che implementa la verifica dell'admin
     * @param username stringa inviata dall'utente
     * @param password stringa già convertita in sha1 inviata dall'utente
     * @return un amministratore se la verifica ha successo
     */
    Amministratore verificaAdmin(String username,String password);
}
