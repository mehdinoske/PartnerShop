package IntegrationTesting.ControllerIncluso.GestioneUtente;

import PartnerShop.GestioneUtenti.controller.GestioneUtenteController;
import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.Prodotto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class ListaDesideriInclusoTest {

    MockHttpServletRequest request;
    MockHttpServletResponse response;
    GestioneUtenteController utController;
    MockHttpSession session;
    Cliente cl;
    Prodotto pr;

    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = new MockHttpSession();
        utController = new GestioneUtenteController();
        cl = new Cliente();
        pr = new Prodotto();
        pr.setId(1);
        cl.addListaDesideri(pr);
        cl.setEmail("peppe.abbatiello@gmail.com");
        cl.setCartaDiCredito(null);
    }

    @Test
    public void aggiuntaProdottoListaDesideriOk() throws ServletException, IOException {
        request.setServletPath("/AggiungiListaDesideri");
        request.setParameter("idProdotto", "4");
        request.getSession().setAttribute("cliente", cl);

        assertTrue(utController.execute(request, response));
    }

    @Test
    public void rimuoviProdottoListaDesideriOk() throws ServletException, IOException {
        request.setServletPath("/RimuoviListaDesideri");
        request.setParameter("idProdotto", "1");
        request.getSession().setAttribute("cliente", cl);

        assertTrue(utController.execute(request, response));
    }
}
