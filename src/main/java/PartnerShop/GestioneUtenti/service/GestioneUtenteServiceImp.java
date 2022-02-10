package PartnerShop.GestioneUtenti.service;

import PartnerShop.Exceptions.MyServletException;
import PartnerShop.model.dao.GestioneProdottoDAO;
import PartnerShop.model.dao.ListaDesideriDAO;
import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.Prodotto;
import PartnerShop.model.entity.UtenteRegistrato;

import java.util.ArrayList;

/**
 * Implementa la classe che esplicita i metodi definiti nell'interfaccia di GestioneUtente
 * @author Marco Ancona
 */

public class GestioneUtenteServiceImp implements GestioneUtenteService {

    private String nomeMod="^[A-zÀ-ù ‘-]{2,30}$";
    private String cognomeMod="^[A-zÀ-ù ‘-]{2,30}$";
    private String cellMod = "^[0-9]\\d{9}$";
    private String passMod = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[_.,\\-+*!#@?])([a-zA-Z0-9_.,\\-+*!#@?]{6,25})$";

    private UtenteRegistratoDAO utDAO;
    private GestioneProdottoDAO prodDB;
    private ListaDesideriDAO lisDB;

    public GestioneUtenteServiceImp(){
        utDAO = new UtenteRegistratoDAO();
        prodDB = new GestioneProdottoDAO();
        lisDB = new ListaDesideriDAO();
    }

    public GestioneUtenteServiceImp(UtenteRegistratoDAO utenteDAO){
        this.utDAO = utenteDAO;
    }

    /**
     *firma del metodo che implementa la modifica dei dati personali
     * @param ut UtenteRegistrato che contiene le modifiche da applicare
     * @return un boolean che indica se la modifica va a buon fine
     * @throws MyServletException eccezione
     */
    @Override
    public boolean ModificaDati(UtenteRegistrato ut) throws MyServletException {
        if(ut.getNome().matches(nomeMod) && ut.getCognome().matches(cognomeMod)
                && ut.getCellulare().matches(cellMod) && (ut.getIndirizzo().length())>5
                && ut.getPassword().matches(passMod)){
            utDAO.doUpdate(ut);
            return true;
        }else{
            throw new MyServletException("Modifica non andata a buon fine");
        }
    }

    /**
     * firma del metodo che implementa la rimozione dell'utente dal db
     * @param email stringa che identifica il cliente
     */
    @Override
    public void CancellaUtente(String email) {
        utDAO.doDelete(email);
    }

    /**
     *firma del metodo che permette la visualizzazione della lista degli utenti registrati
     * @return un ArrayList di UtenteRegistrato che contiene la lista di utenti
     */
    @Override
    public ArrayList<UtenteRegistrato> VisualizzaUtenti() {
        return utDAO.doRetrieveAll();
    }

    public void aggiungiListaDesideri(Cliente cl, int idProdotto){
        if(cl!=null && idProdotto!=0){
            Prodotto pr = prodDB.doRetrieveById(idProdotto);
            if(!cl.containsListaDesideri(pr)){
                cl.addListaDesideri(pr);
                lisDB.doSave(pr, cl.getEmail());
            }
        }
    }


    public void rimuoviListaDesideri(Cliente cl, int idProdotto){
            if(cl!=null && idProdotto!=0){
                Prodotto pr = prodDB.doRetrieveById(idProdotto);
                if(cl.containsListaDesideri(pr)){
                    cl.removeListaDesideri(pr);
                    lisDB.doDeleteByIdEmailCliente(idProdotto,cl.getEmail());
                }
            }
    }

    public ArrayList<Prodotto> getListaDesideri(String email){
        System.out.println("DAO"+email);
        return lisDB.doRetrieveByEmailCliente(email);
    }
}
