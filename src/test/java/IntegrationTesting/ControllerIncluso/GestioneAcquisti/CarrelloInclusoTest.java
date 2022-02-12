package IntegrationTesting.ControllerIncluso.GestioneAcquisti;

import PartnerShop.GestioneAcquisti.controller.GestioneAcquistiController;
import PartnerShop.GestioneAcquisti.service.GestioneAcquistiService;
import PartnerShop.GestioneAcquisti.service.GestioneAcquistiServiceImp;
import PartnerShop.model.dao.CarrelloDAO;
import PartnerShop.model.entity.Carrello;
import PartnerShop.model.entity.Ordine;
import PartnerShop.model.entity.Prodotto;
import PartnerShop.model.entity.UtenteRegistrato;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarrelloInclusoTest {
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
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = new MockHttpSession();
    }

    @Test
    public void aggiungiAlCarrelloProdottoNonPresenteTest() throws ServletException, IOException{
        String prodottoIdStr = "4";
        String quantStr = "20";
        request.setServletPath("/Carrello");
        session.setAttribute("utente",ut);
        session.setAttribute("Carrello",car);
        request.setParameter("idProdotto",prodottoIdStr);
        request.setParameter("quant",quantStr);
        request.setSession(session);
        assertTrue(gesCon.execute(request,response));
    }

    @Test
    public void aggiungiAlCarrelloProdottoGiaPresenteTest() throws ServletException, IOException{
        String prodottoIdStr = "4";
        String quantStr = "20";
        request.setServletPath("/Carrello");
        session.setAttribute("utente",ut);
        session.setAttribute("Carrello",car);
        request.setParameter("idProdotto",prodottoIdStr);
        request.setParameter("quant",quantStr);
        request.setSession(session);
        assertTrue(gesCon.execute(request,response));
    }

    @Test
    public void rimuoviDalCarrelloTest()throws ServletException, IOException{
        String prodottoIdStr = "2";
        String setQuantStr = "0";
        request.setServletPath("/Carrello");
        session.setAttribute("utente",ut);
        session.setAttribute("Carrello",car);
        request.setParameter("idProdotto",prodottoIdStr);
        request.setParameter("setQuant",setQuantStr);
        request.setSession(session);
        assertTrue(gesCon.execute(request,response));
    }

}
