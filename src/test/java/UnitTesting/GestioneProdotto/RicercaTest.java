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

import static org.junit.Assert.*;

public class RicercaTest {
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
    public void nessunParolaInseritaPerLaRicerca() {
        String p = "";
        request.setParameter("p", p);
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.ricerca(request,response,gps));
        assertEquals("Nessun parola chiave inserita.", mse.getMessage());
    }

    @Test
    public void nessunRisultatoPerLaRicerca() {
        String p = "Piatto";
        String app = p + "*";
        request.setParameter("p", p);
        Mockito.when(gps.getProdottiByNome(app)).thenReturn(null);
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.ricerca(request,response,gps));
        assertEquals("Nessun prodotto per questa ricerca.", mse.getMessage());
    }

    @Test
    public void tuttoOk() throws ServletException, SQLException, NoSuchAlgorithmException, IOException {
        String p = "Piatto";
        String app = p + "*";
        request.setParameter("p", p);
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        prodotti.add(prodotto);
        Mockito.when(gps.getProdottiByNome(app)).thenReturn(prodotti);
        assertTrue(gpc.ricerca(request, response, gps));
    }
}