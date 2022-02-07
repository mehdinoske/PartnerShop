package PartnerShop.registrazione.service;

import PartnerShop.model.dao.CarrelloDAO;
import PartnerShop.model.dao.ClienteDAO;
import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.dao.VenditoreDAO;
import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.model.entity.Venditore;

/**
 * implementa la classe che esplicita i metodi definiti nell'interfaccia di registrazione
 * @author Giuseppe Abbatiello
 */
public class  RegistrazioneServiceImp implements RegistrazioneService{
    private String usernameReg = "^[a-zA-Z0-9\\-_]{1,20}$";
    private String emailReg ="^[A-z0-9._%+-]+@[A-z0-9.-]+\\.[A-z]{2,10}$";
    private String passReg = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[_.,\\-+*!#@?])([a-zA-Z0-9_.,\\-+*!#@?]{6,25})$";
    private String nomeReg="^[A-zÀ-ù ‘-]{2,30}$";
    private String cellReg = "^[0-9]\\d{9}$";
    private String dataReg = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";

    private ClienteDAO ctDAO;
    private VenditoreDAO vtDAO;
    private CarrelloDAO carDAO;


    public RegistrazioneServiceImp(){
        carDAO = new CarrelloDAO();
        ctDAO = new ClienteDAO();
    }

    public RegistrazioneServiceImp(ClienteDAO ctDAO,CarrelloDAO carDAO){
        this.carDAO = carDAO;
        this.ctDAO = ctDAO;
    }

    public RegistrazioneServiceImp(VenditoreDAO vtDAO,CarrelloDAO carDAO){
        this.carDAO = carDAO;
        this.vtDAO = vtDAO;
    }

    /**
     * implementa la funzionalità di registrazione del cliente
     * @param ut - utente inviato da RegistrazioneController
     * @return cliente per essere loggato
     */
    @Override
    public UtenteRegistrato RegistrazioneCliente(UtenteRegistrato ut)  {


        try{
        if(ut.getEmail().matches(emailReg) && ut.getUsername().matches(usernameReg) && ut.getPassword().matches(passReg)
                && ut.getNome().matches(nomeReg)&& ut.getCognome().matches(nomeReg)&& ut.getCellulare().matches(cellReg)
                && ut.getDdn().matches(dataReg) && (ut.getIndirizzo().length())>=5) {
        ctDAO.doSave(ut,0);
        ctDAO.doSave(ut.getEmail());
        carDAO.doCreateCarrello(ut.getEmail());
        ut.setId_Carrello(carDAO.doRetrieveIdCarrelloByEmailCliente(ut.getEmail()));
        }else{
            ut = null;
            throw new IllegalArgumentException();
        }}
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return ut;
    }

    /**
     * implementa la funzionalita di registrazione del venditore
     * @param ut           utente inviato da RegistrazioneController
     * @param nomeNegozio  nome del negozio inserito dal venditore
     * @param Piva        partita iva del negozio inserita dal venditore
     * @return venditore per essere loggato
     */
    @Override
    public UtenteRegistrato RegistrazioneVenditore(UtenteRegistrato ut,String nomeNegozio,String Piva) {
        VenditoreDAO vtDAO = new VenditoreDAO();
        try{
        if(ut.getEmail().matches(emailReg) && ut.getUsername().matches(usernameReg) && ut.getPassword().matches(passReg)
                && ut.getNome().matches(nomeReg)&& ut.getCognome().matches(nomeReg)&& ut.getCellulare().matches(cellReg)
                && ut.getDdn().matches(dataReg) && (ut.getIndirizzo().length())>=5) {
            vtDAO.doSave(ut, 1);
            vtDAO.doSave(ut.getEmail(), nomeNegozio, Piva);
        }else{
            ut=null;
          //  throw new IllegalArgumentException();
        }}
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return ut;
    }

    public UtenteRegistrato verificaUsernameEmail(String str,int tipo){
        UtenteRegistrato ut = new UtenteRegistrato();
        UtenteRegistratoDAO utDB = new UtenteRegistratoDAO();
       if(tipo == 0){
            ut = utDB.doRetrieveByUsername(str);
        }else if(tipo == 1){
            ut = utDB.doRetrieveByEmail(str);
        }
        return ut;
    }
}
