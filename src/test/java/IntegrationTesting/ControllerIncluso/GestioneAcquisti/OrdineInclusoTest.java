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
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrdineInclusoTest {
    private UtenteRegistrato cl;
    private UtenteRegistrato vd;
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
        cl = new UtenteRegistrato();
        vd = new UtenteRegistrato();
        car = new Carrello();
        gesServ = new GestioneAcquistiServiceImp(carDB);
        ord = new Ordine();
        pr = new Prodotto();
        cl.setEmail("anconamarco@gmail.com");
        cl.setId_Carrello(1);
        cl.setTipo(0);
        vd.setEmail("boudad@gmail.com");
        vd.setTipo(1);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = new MockHttpSession();
    }

    @Test
    public void visualizzaOrdineUtenteNullTest() throws ServletException, IOException {
        request.setServletPath("/OrdiniVenditore");
        session.setAttribute("utente",null);
        session.setAttribute("Carrello",car);
        request.setSession(session);
        gesCon.execute(request,response);
        ArrayList<Ordine> listOrdini = (ArrayList<Ordine>) session.getAttribute("ordini");
        assertNull(listOrdini);
    }

    @Test
    public void visualizzaOrdineClienteTest() throws ServletException, IOException {
        request.setServletPath("/OrdiniCliente");
        session.setAttribute("utente",cl);
        request.setSession(session);
        gesCon.execute(request,response);
        ArrayList<Ordine> listOrdini = (ArrayList<Ordine>) session.getAttribute("ordini");
        assertNotNull(listOrdini);
    }

    @Test
    public void visualizzaOrdineVenditoreTest() throws ServletException, IOException {
        request.setServletPath("/OrdiniVenditore");
        session.setAttribute("utente",vd);
        session.setAttribute("Carrello",car);
        request.setSession(session);
        gesCon.execute(request,response);
        ArrayList<Ordine> listOrdini = (ArrayList<Ordine>) session.getAttribute("ordini");
        assertNotNull(listOrdini);
    }

}
