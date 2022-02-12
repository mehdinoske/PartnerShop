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
 * @author Marco Ancona, El Mehdi Boudad
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
     *implementazione del metodo che realizza la modifica dei dati personali
     * @param ut UtenteRegistrato che contiene le modifiche da applicare
     * @return un boolean che indica se la modifica va a buon fine
     * @throws MyServletException eccezione quando alcuni campi non matchano con la REGEX
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
     * implementazione del metodo che realizza  la rimozione dell'utente dal db
     * @param email stringa che identifica il cliente
     * @throws MyServletException eccezione quando l'utente non esiste nel db
     */
    @Override
    public void CancellaUtente(String email) throws MyServletException {
        if(utDAO.doRetrieveByEmail(email) != null) {
            utDAO.doDelete(email);
        }else{
            throw new MyServletException("Utente non esiste");
        }
    }

    /**
     *implementazione del metodo che realizza la visualizzazione della lista degli utenti registrati
     * @return un ArrayList di UtenteRegistrato che contiene la lista di utenti
     */
    @Override
    public ArrayList<UtenteRegistrato> VisualizzaUtenti() {
        return utDAO.doRetrieveAll();
    }

    /**
     * implementazione del metodo che realizza l'aggiunta di un prodotto alla lista desideri
     * @param cl oggetto Clinte che contiene i dati dell'utente
     * @param idProdotto intero che identifica il prodotto da aggiungere alla lista
     * @return un boolean che indica il successo o meno dell'operazione di aggiunta
     * @throws MyServletException eccezione che indica se il prodotto è già in lista
     */
    public boolean aggiungiListaDesideri(Cliente cl, int idProdotto) throws MyServletException {
        Prodotto pr = prodDB.doRetrieveById(idProdotto);
        if(!cl.containsListaDesideri(pr)){
            cl.addListaDesideri(pr);
            lisDB.doSave(pr, cl.getEmail());
            return true;
        }else{
            throw new MyServletException("Prodotto già in lista!");
        }
    }

    /**
     * implementazione del metodo che realizza la rimozione di un prodotto dalla lista desideri
     * @param cl oggetto Cliente che contiene i dati dell'utente
     * @param idProdotto intero che identifica il prodotto da rimuovere dalla lista
     * @throws MyServletException eccezione che indica se il prodotto non è in lista
     */
    public boolean rimuoviListaDesideri(Cliente cl, int idProdotto) throws MyServletException {
            Prodotto pr = prodDB.doRetrieveById(idProdotto);
            if (cl.containsListaDesideri(pr)) {
                cl.removeListaDesideri(pr);
                lisDB.doDeleteByIdEmailCliente(idProdotto, cl.getEmail());
                return true;
            }else{
                throw new MyServletException("Prodotto non in lista!");
            }
    }

    /**
     * implementazione del metodo che realizza il recupero della lista desideri di un utente loggato dal DB
     * @param email stringa che identifica l'utente
     * @return ArrayList di oggetti Prodotto
     */
    public ArrayList<Prodotto> getListaDesideri(String email){
        return lisDB.doRetrieveByEmailCliente(email);
    }
}
