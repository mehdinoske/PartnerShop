package UnitTesting.HomeServlet;

import PartnerShop.GestioneAcquisti.controller.GestioneAcquistiController;
import PartnerShop.GestioneAcquisti.service.GestioneAcquistiService;
import PartnerShop.GestioneAcquisti.service.GestioneAcquistiServiceImp;
import PartnerShop.HomePageServlet;
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

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeServletTest {
private HomePageServlet homectrl;
 private MockHttpServletRequest request;
  private MockHttpServletResponse response;
   private MockHttpSession session;
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = new MockHttpSession();
        homectrl = new HomePageServlet();
    }

    @Test
    public void homeServlet() throws ServletException, IOException {

        assertTrue(homectrl.execute(request,response));
        request.setAttribute("utente","");
        assertTrue(homectrl.execute(request,response));
    }
}
