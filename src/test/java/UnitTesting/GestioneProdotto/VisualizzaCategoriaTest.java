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

public class VisualizzaCategoriaTest {
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
    public void categoriaProdottoNonRispettaFormato() {
        String categoria = "g";
        request.setParameter("categoria", categoria);
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.visualizzaCategoria(request,response,gps));
        assertEquals("Categoria prodotto errata.", mse.getMessage());
    }

    @Test
    public void nesssunProdottoPerQuellaCategoria() {
        String categoria = "Utensili";
        request.setParameter("categoria", categoria);
        Mockito.when(gps.getProdottiByCategoria(categoria)).thenReturn(null);
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.visualizzaCategoria(request,response,gps));
        assertEquals("Nessun prodotto per questa categoria.", mse.getMessage());
    }

    @Test
    public void tuttoOk() throws ServletException, SQLException, NoSuchAlgorithmException, IOException {
        String categoria = "Utensili";
        request.setParameter("categoria", categoria);
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        prodotti.add(prodotto);
        Mockito.when(gps.getProdottiByCategoria(categoria)).thenReturn(prodotti);
        assertEquals(true, gpc.visualizzaCategoria(request,response,gps));
    }
}