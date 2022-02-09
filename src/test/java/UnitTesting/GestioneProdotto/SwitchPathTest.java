package UnitTesting.GestioneProdotto;

import PartnerShop.gestioneProdotto.controller.GestioneProdottoController;
import PartnerShop.gestioneProdotto.service.GestioneProdottoService;
import PartnerShop.model.entity.Prodotto;
import PartnerShop.model.entity.UtenteRegistrato;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class SwitchPathTest {
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    MockHttpSession session;
    GestioneProdottoController gpc;
    GestioneProdottoService gps;
    Prodotto prodotto;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = new MockHttpSession();
        gpc = new GestioneProdottoController();
        gps = Mockito.mock(GestioneProdottoService.class);
        session = new MockHttpSession();
        prodotto = Mockito.mock(Prodotto.class);
        request.setSession(session);
    }

    @Test
    public void nessunPath() throws ServletException, IOException {
        assertEquals(false, gpc.switchPath(request, response, gps));
    }

    @Test
    public void prodottoVisualizzaOk() throws ServletException, IOException {
        request.setServletPath("/prodotto-visualizza");
        int id = 1;
        request.setParameter("id", String.valueOf(id));
        Mockito.when(gps.getProdottoById(id)).thenReturn(prodotto);
        assertEquals(true, gpc.switchPath(request, response, gps));
    }

    @Test
    public void prodottoModificaFormOk() throws ServletException, IOException {
        request.setServletPath("/prodotto-modifica-form");
        UtenteRegistrato ut = new UtenteRegistrato("pinco", "palla", "12-12-1122", "ciaociao", "qazwsx2", "pinco@palla.com", "aaaaa", "222222", 1);
        request.getSession().setAttribute("utente", ut);
        int id = 1;
        request.setParameter("id", String.valueOf(id));
        Mockito.when(gps.getProdottoById(id)).thenReturn(prodotto);
        assertEquals(true, gpc.switchPath(request, response, gps));
    }

    @Test
    public void prodottoModificaOk() throws ServletException, IOException {
        request.setServletPath("/prodotto-modifica");
        int id = 1;
        String nome = "Forbice";
        String descrizione = "Fooo oooooo";
        String categoria = "Utensili";
        int prezzo_Cent = 11;
        int disponibilita = 22;

        UtenteRegistrato ut = new UtenteRegistrato("pinco", "palla", "12-12-1122", "ciaociao", "qazwsx2", "pinco@palla.com", "aaaaa", "222222", 1);
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        request.getSession().setAttribute("utente", ut);
        request.getSession().setAttribute("prodotti", prodotti);
        Prodotto p = new Prodotto();
        p.setEmail_Venditore("pinco@palla.com");
        Mockito.when(gps.getProdottoById(id)).thenReturn(p);
        setParametersRequest(id, nome, descrizione, categoria, prezzo_Cent, disponibilita);
        assertEquals(true, gpc.switchPath(request, response, gps));
    }

    @Test
    public void prodottoAggiungiOk() throws ServletException, IOException {
        request.setServletPath("/prodotto-aggiungi");
        int id = 1;
        String nome = "Forbice";
        String descrizione = "Fooo oooooo";
        String categoria = "Utensili";
        int prezzo_Cent = 11;
        int disponibilita = 22;

        UtenteRegistrato ut = new UtenteRegistrato("pinco", "palla", "12-12-1122", "ciaociao", "qazwsx2", "pinco@palla.com", "aaaaa", "222222", 1);
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        request.getSession().setAttribute("utente", ut);
        request.getSession().setAttribute("prodotti", prodotti);
        Part pr = Mockito.mock(Part.class);
        request.getSession().setAttribute("img", pr);
        setParametersRequest(id, nome, descrizione, categoria, prezzo_Cent, disponibilita);
        Mockito.when(gps.getProdottoById(id)).thenReturn(prodotto);
        assertEquals(true, gpc.switchPath(request, response, gps));
    }

    @Test
    public void prodottoAggiungiFormOk() throws ServletException, IOException {
        request.setServletPath("/prodotto-aggiungi-form");
        UtenteRegistrato ut = new UtenteRegistrato("pinco", "palla", "12-12-1122", "ciaociao", "qazwsx2", "pinco@palla.com", "aaaaa", "222222", 1);
        request.getSession().setAttribute("utente", ut);
        assertEquals(true, gpc.switchPath(request, response, gps));
    }

    @Test
    public void prodottoRimuoviOk() throws ServletException, IOException {
        request.setServletPath("/prodotto-rimuovi");
        UtenteRegistrato ut = new UtenteRegistrato("pinco", "palla", "12-12-1122", "ciaociao", "qazwsx2", "pinco@palla.com", "aaaaa", "222222", 1);
        request.getSession().setAttribute("utente", ut);
        int id = 1;
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        request.getSession().setAttribute("prodotti", prodotti);
        request.setParameter("id", String.valueOf(id));
        Mockito.when(gps.getProdottoById(id)).thenReturn(prodotto);
        assertEquals(true, gpc.switchPath(request, response, gps));
    }

    @Test
    public void visualizzaProdottiOk() throws ServletException, IOException {
        request.setServletPath("/visualizza-prodotti");
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        prodotti.add(prodotto);
        request.getSession().setAttribute("prodotti", prodotti);
        assertEquals(true, gpc.switchPath(request, response, gps));
    }

    @Test
    public void visualizzaCategoriaOk() throws ServletException, IOException {
        request.setServletPath("/visualizza-categoria");
        String categoria = "Utensili";
        request.setParameter("categoria", categoria);
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        prodotti.add(prodotto);
        Mockito.when(gps.getProdottiByCategoria(categoria)).thenReturn(prodotti);
        assertEquals(true, gpc.switchPath(request, response, gps));
    }

    @Test
    public void ricercaAjaxOk() throws ServletException, IOException {
        request.setServletPath("/ricercaAjax");
        String p = "Piatto";
        String app = p + "*";
        request.setParameter("p", p);
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        prodotti.add(prodotto);
        Mockito.when(gps.getProdottiByNome(app)).thenReturn(prodotti);
        assertEquals(true, gpc.switchPath(request, response, gps));
    }

    @Test
    public void ricercaOk() throws ServletException, IOException {
        request.setServletPath("/ricerca");
        String p = "Piatto";
        String app = p + "*";
        request.setParameter("p", p);
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        prodotti.add(prodotto);
        Mockito.when(gps.getProdottiByNome(app)).thenReturn(prodotti);
        assertEquals(true, gpc.switchPath(request, response, gps));
    }

    private void setParametersRequest(int id, String nome, String descrizione, String categoria, int prezzo_Cent, int disponibilita) {
        request.setParameter("id", String.valueOf(id));
        request.setParameter("nome", nome);
        request.setParameter("descrizione", descrizione);
        request.setParameter("categoria", categoria);
        request.setParameter("prezzo_Cent", String.valueOf(prezzo_Cent));
        request.setParameter("disponibilita", String.valueOf(disponibilita));
    }
}
