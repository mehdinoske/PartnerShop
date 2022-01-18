package PartnerShop.autenticazione.service;

import PartnerShop.model.dao.ClienteDAO;
import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.UtenteRegistrato;

public class AutenticazioneServiceImp implements AutenticazioneService{

    @Override
    public UtenteRegistrato login(String email, String password) {
        return null;
    }

    /*@Override
        public UtenteRegistrato login(String email, String password) {
            UtenteRegistrato u = new UtenteRegistrato() ;
            if ((u = ClienteDAO.findByEmailPassword(email, password)) != null) {
                return u;
            } else  ((u =
                    bibliotecaDAO.findByEmailAndPassword(email, password)) != null) {
                return u;
            }

        }
    */
    @Override
    public Cliente findClienteByUsernamePass(String username, String password) {
        //Cliente cliente = ClienteDAO.
        return null;
    }
}
