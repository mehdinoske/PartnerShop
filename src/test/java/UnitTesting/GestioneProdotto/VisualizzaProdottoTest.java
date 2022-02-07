package UnitTesting.GestioneProdotto;

import PartnerShop.Exceptions.MyServletException;
import PartnerShop.gestioneProdotto.controller.GestioneProdottoController;
import PartnerShop.gestioneProdotto.service.GestioneProdottoService;
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
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class VisualizzaProdottoTest {
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    MockHttpSession session;
    GestioneProdottoController gpc;
    GestioneProdottoService gps;
    Prodotto prodotto;
    UtenteRegistrato utente;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = new MockHttpSession();
        gpc = new GestioneProdottoController();
        gps = Mockito.mock(GestioneProdottoService.class);
        prodotto = Mockito.mock(Prodotto.class);
        session = new MockHttpSession();
        utente = Mockito.mock(UtenteRegistrato.class);

        request.setSession(session);
    }

    @Test
    public void idProdottoErrato() {
        double id = 45.5;
        request.setParameter("id", String.valueOf(id));
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoVisualizza(request,response,gps));
        assertEquals("Id prodotto non corretto.", mse.getMessage());
    }

    @Test
    public void idProdottoNonPresente() {
        int id = 1;
        request.setParameter("id", String.valueOf(id));
        Mockito.when(gps.getProdottoById(id)).thenReturn(null);
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoVisualizza(request,response,gps));
        assertEquals("Prodotto non trovato.", mse.getMessage());
    }

    @Test
    public void tuttoOk() throws ServletException, SQLException, NoSuchAlgorithmException, IOException {
        int id = 1;
        request.setParameter("id", String.valueOf(id));
        Mockito.when(gps.getProdottoById(id)).thenReturn(prodotto);
        gpc.prodottoVisualizza(request,response,gps);
    }
}