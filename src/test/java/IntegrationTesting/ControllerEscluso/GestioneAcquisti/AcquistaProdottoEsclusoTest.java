package IntegrationTesting.ControllerEscluso.GestioneAcquisti;


import PartnerShop.Exceptions.MyServletException;
import PartnerShop.GestioneAcquisti.service.GestioneAcquistiService;
import PartnerShop.GestioneAcquisti.service.GestioneAcquistiServiceImp;
import PartnerShop.model.dao.CarrelloDAO;
import PartnerShop.model.entity.Carrello;
import PartnerShop.model.entity.Ordine;
import PartnerShop.model.entity.Prodotto;
import PartnerShop.model.entity.UtenteRegistrato;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AcquistaProdottoEsclusoTest {

    private UtenteRegistrato ut;
    private Carrello car;
    private GestioneAcquistiService gesServ;
    private Prodotto pr,pr2;

    @Before
    public void setUp(){
        CarrelloDAO carDB = Mockito.mock(CarrelloDAO.class);
        ut = new UtenteRegistrato();
        car = new Carrello();
        gesServ = new GestioneAcquistiServiceImp();
        Ordine ord = new Ordine();
         pr = new Prodotto();
        ut.setEmail("anconamarco@gmail.com");
        ut.setId_Carrello(1);
        pr.setId(5);
        pr.setEmail_Venditore("boudad@gmail.com");
        pr.setNome("Penna");
        pr.setCategoria("Cancelleria");
        pr.setDescrizione("Penna blu molto costosa");
        pr.setPrezzo_Cent(100);

        pr2  = new Prodotto();
        pr2.setId(2);
        if( pr.equals(pr2) )System.out.print("");
        pr.setDisponibilita(2000);
        car.setProdottoHash(pr);
        car.setQuantHash(pr.getId(),1);
    }

    @Test
    public void acquistaProdottoNomeFormatoErratoTest() {
        String nome = "EL Mehdi7";//errata
        String cognome = "Boudad";//corretta
        String indirizzo = "Via Roma 143, Bologna, SA";//corretta
        String cardc = "5333131844389943";//corretta
        assertNull(gesServ.acquistaProdotto(ut,car,nome,cognome,indirizzo,cardc));
    }

    @Test
    public void acquistaProdottoCognomeFormatoErratoTest(){
        String nome = "Mario";//corretta
        String cognome = "Biancavilla7";//errata
        String indirizzo = "Via serracapilli 7, Salerno, SA ";//corretta
        String cardc = "5333171546789943";//corretta
        assertNull(gesServ.acquistaProdotto(ut,car,nome,cognome,indirizzo,cardc));
    }

    @Test
    public void acquistaProdottoIndirizzoLunghezzaErratoTest(){
        String nome = "Alberto";//corretta
        String cognome = "De Palma";//corretta
        String indirizzo = "V";//errata
        String cardc = "5333171055489943";//corretta
        assertNull(gesServ.acquistaProdotto(ut,car,nome,cognome,indirizzo,cardc));
    }

    @Test
    public void acquistaProdottoCartaDiCreditoFormatoErratoTest(){
        String nome = "Alessia";//corretta
        String cognome = "Rossi";//corretta
        String indirizzo = "Via Dante Alighieri 84, Firenze, FI";//corretta
        String cardc = "533319244748g9943re";//errata
        assertNull(gesServ.acquistaProdotto(ut,car,nome,cognome,indirizzo,cardc));
    }

    @Test
    public void acquistaProdottoOkTest(){
        String nome = "Bianca";//corretta
        String cognome = "Verdi";//corretta
        String indirizzo = "Via Garibaldi 90, Verona, VR";//corretta
        String cardc = "5333171044489943";//corretta

        assertNotNull(gesServ.acquistaProdotto(ut,car,nome,cognome,indirizzo,cardc));
    }
}
