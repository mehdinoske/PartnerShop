package IntegrationTesting.ControllerIncluso.GestioneAcquisti;

import PartnerShop.Exceptions.MyServletException;
import PartnerShop.GestioneAcquisti.controller.GestioneAcquistiController;
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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AcquistaProdottoInclusoTest {
    private UtenteRegistrato ut;
    private Carrello car;
    private GestioneAcquistiService gesServ;
    private Ordine ord;
    private Prodotto pr;
    private CarrelloDAO carDB;
    private final GestioneAcquistiController gesCon = new GestioneAcquistiController();
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockHttpSession session;

    @Before
    public void setUp(){
        carDB = Mockito.mock(CarrelloDAO.class);
        ut = new UtenteRegistrato();
        car = new Carrello();
        gesServ = new GestioneAcquistiServiceImp(carDB);
        ord = new Ordine();
        pr = new Prodotto();
        ut.setEmail("anconamarco@gmail.com");
        ut.setId_Carrello(1);
        pr.setId(2);
        pr.setEmail_Venditore("depalmamarco@gmail.com");
        pr.setNome("Macbook");
        pr.setCategoria("Elettronica");
        pr.setDescrizione("MacBook che fa schifo");
        pr.setPrezzo_Cent(150000);
        pr.setDisponibilita(400);
        car.setProdottoHash(pr);
        car.setQuantHash(pr.getId(),1);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = new MockHttpSession();
    }

    @Test
    public void acquistaProdottoNomeFormatoErratoTest() throws ServletException, IOException {
        String nome = "EL Mehdi7";//errata
        String cognome = "Boudad";//corretta
        String indirizzo = "Via Roma 143, Bologna, SA";//corretta
        String cardc = "5333131844389943";//corretta
        request.setServletPath("/CompletaAcquisto");
        session.setAttribute("utente",ut);
        session.setAttribute("Carrello",car);
        request.setParameter("nome",nome);
        request.setParameter("cognome",cognome);
        request.setParameter("indirizzo",indirizzo);
        request.setParameter("cartadc",cardc);
        request.setSession(session);
        assertThrows(MyServletException.class,()-> gesCon.execute(request,response));
    }

    @Test
    public void acquistaProdottoCognomeFormatoErratoTest() throws ServletException, IOException{
        String nome = "Mario";//corretta
        String cognome = "Biancavilla7";//errata
        String indirizzo = "Via serracapilli 7, Salerno, SA ";//corretta
        String cardc = "5333171546789943";//corretta
        request.setServletPath("/CompletaAcquisto");
        session.setAttribute("utente",ut);
        session.setAttribute("Carrello",car);
        request.setParameter("nome",nome);
        request.setParameter("cognome",cognome);
        request.setParameter("indirizzo",indirizzo);
        request.setParameter("cartadc",cardc);
        request.setSession(session);
        assertThrows(MyServletException.class,()-> gesCon.execute(request,response));
    }

    @Test
    public void acquistaProdottoIndirizzoLunghezzaErratoTest() throws ServletException, IOException{
        String nome = "Alberto";//corretta
        String cognome = "De Palma";//corretta
        String indirizzo = "V";//errata
        String cardc = "5333171055489943";//corretta
        request.setServletPath("/CompletaAcquisto");
        session.setAttribute("utente",ut);
        session.setAttribute("Carrello",car);
        request.setParameter("nome",nome);
        request.setParameter("cognome",cognome);
        request.setParameter("indirizzo",indirizzo);
        request.setParameter("cartadc",cardc);
        request.setSession(session);
        assertThrows(MyServletException.class,()-> gesCon.execute(request,response));
    }

    @Test
    public void acquistaProdottoCartaDiCreditoFormatoErratoTest() throws ServletException, IOException{
        String nome = "Alessia";//corretta
        String cognome = "Rossi";//corretta
        String indirizzo = "Via Dante Alighieri 84, Firenze, FI";//corretta
        String cardc = "533319244748g9943re";//errata
        request.setServletPath("/CompletaAcquisto");
        session.setAttribute("utente",ut);
        session.setAttribute("Carrello",car);
        request.setParameter("nome",nome);
        request.setParameter("cognome",cognome);
        request.setParameter("indirizzo",indirizzo);
        request.setParameter("cartadc",cardc);
        request.setSession(session);
        assertThrows(MyServletException.class,()-> gesCon.execute(request,response));
    }

    @Test@Ignore
    public void acquistaProdottoOkTest() throws ServletException, IOException{
        String nome = "Bianca";//corretta
        String cognome = "Verdi";//corretta
        String indirizzo = "Via Garibaldi 90, Verona, VR";//corretta
        String cardc = "5333171044489943";//corretta
        request.setServletPath("/CompletaAcquisto");
        session.setAttribute("utente",ut);
        session.setAttribute("Carrello",car);
        request.setParameter("nome",nome);
        request.setParameter("cognome",cognome);
        request.setParameter("indirizzo",indirizzo);
        request.setParameter("cartadc",cardc);
        request.setSession(session);
        assertTrue(gesCon.execute(request,response));
    }
}
