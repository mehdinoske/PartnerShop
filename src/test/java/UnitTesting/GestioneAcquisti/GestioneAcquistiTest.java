package UnitTesting.GestioneAcquisti;

import PartnerShop.GestioneAcquisti.service.GestioneAcquistiServiceImp;
import PartnerShop.model.dao.ClienteDAO;
import PartnerShop.model.dao.GestioneAcquistiDAO;
import PartnerShop.model.entity.Carrello;
import PartnerShop.model.entity.Ordine;
import PartnerShop.model.entity.UtenteRegistrato;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;

public class GestioneAcquistiTest {

    private Carrello carMock;
    private Ordine ordMock;
    private GestioneAcquistiDAO gesDBmock;
    private GestioneAcquistiServiceImp gesServ;
    private UtenteRegistrato utMock;
    private ClienteDAO clDBmock;

    @Before
    public void setUp(){
        utMock = Mockito.mock(UtenteRegistrato.class);
        carMock = Mockito.mock(Carrello.class);
        ordMock = Mockito.mock(Ordine.class);
        gesDBmock = Mockito.mock(GestioneAcquistiDAO.class);
        clDBmock = Mockito.mock(ClienteDAO.class);
        gesServ = new GestioneAcquistiServiceImp(gesDBmock,clDBmock);
    }

    @Test
    public void acquistaProdottoNomeFormatoErratoTest(){
        String nome = "EL Mehdi7";//errata
        String cognome = "Boudad";//corretta
        String indirizzo = "Via Roma 143, Bologna, SA";//corretta
        String cardc = "5333131844389943";//corretta
        assertNull(gesServ.acquistaProdotto(utMock,carMock,nome,cognome,indirizzo,cardc));
    }

    @Test
    public void acquistaProdottoCognomeFormatoErratoTest(){
        String nome = "Mario";//corretta
        String cognome = "Biancavilla7";//errata
        String indirizzo = "Via serracapilli 7, Salerno, SA ";//corretta
        String cardc = "5333171546789943";//corretta
        assertNull(gesServ.acquistaProdotto(utMock,carMock,nome,cognome,indirizzo,cardc));
    }

    @Test
    public void acquistaProdottoIndirizzoLunghezzaErratoTest(){
        String nome = "Alberto";//corretta
        String cognome = "De Palma";//corretta
        String indirizzo = "V";//errata
        String cardc = "5333171055489943";//corretta
        assertNull(gesServ.acquistaProdotto(utMock,carMock,nome,cognome,indirizzo,cardc));
    }

    @Test
    public void acquistaProdottoCartaDiCreditoFormatoErratoTest(){
        String nome = "Alessia";//corretta
        String cognome = "Rossi";//corretta
        String indirizzo = "Via Dante Alighieri 84, Firenze, FI";//corretta
        String cardc = "533319244748g9943re";//errata
        assertNull(gesServ.acquistaProdotto(utMock,carMock,nome,cognome,indirizzo,cardc));
    }

    @Test
    public void acquistaProdottoOkTest(){
        String nome = "Bianca";//corretta
        String cognome = "Verdi";//corretta
        String indirizzo = "Via Garibaldi 90, Verona, VR";//corretta
        String cardc = "5333171044489943";//corretta
        assertNotNull(gesServ.acquistaProdotto(utMock,carMock,nome,cognome,indirizzo,cardc));
    }
}
