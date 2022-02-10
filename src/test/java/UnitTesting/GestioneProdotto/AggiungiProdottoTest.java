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
import javax.servlet.http.Part;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class AggiungiProdottoTest {
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    MockHttpSession session;
    GestioneProdottoController gpc;
    GestioneProdottoService gps;
    Prodotto prodotto;
    UtenteRegistrato utente;

    @Before
    public void setUp(){
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
    public void utenteNonAutorizzato() {
        UtenteRegistrato ut = new UtenteRegistrato("pinco", "palla", "12-12-1122", "ciaociao", "qazwsx2", "pinco@palla.com", "aaaaa", "222222", 0);
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        request.getSession().setAttribute("utente", ut);
        request.getSession().setAttribute("prodotti", prodotti);

        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoAggiungi(request,response,gps));
        assertEquals("Non hai i permessi necessari.", mse.getMessage());
    }

    @Test
    public void nomeProdottoNonRispettaFormato() {
        int id = 1;
        String nome = "g";
        String descrizione = "Cgjjjjjjjao";
        String categoria = "Poroco";
        int prezzo_Cent = 11;
        int disponibilita = 22;

        UtenteRegistrato ut = new UtenteRegistrato("pinco", "palla", "12-12-1122", "ciaociao", "qazwsx2", "pinco@palla.com", "aaaaa", "222222", 1);
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        request.getSession().setAttribute("utente", ut);
        request.getSession().setAttribute("prodotti", prodotti);

        setParametersRequest(id, nome, descrizione, categoria, prezzo_Cent, disponibilita);
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoAggiungi(request,response,gps));
        assertEquals("Nome prodotto errato.", mse.getMessage());
    }

    @Test
    public void descrizioneProdottoNonRispettaFormato() {
        int id = 1;
        String nome = "Forbice";
        String descrizione = "j";
        String categoria = "Poroco";
        int prezzo_Cent = 11;
        int disponibilita = 22;

        UtenteRegistrato ut = new UtenteRegistrato("pinco", "palla", "12-12-1122", "ciaociao", "qazwsx2", "pinco@palla.com", "aaaaa", "222222", 1);
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        request.getSession().setAttribute("utente", ut);
        request.getSession().setAttribute("prodotti", prodotti);

        setParametersRequest(id, nome, descrizione, categoria, prezzo_Cent, disponibilita);
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoAggiungi(request,response,gps));
        assertEquals("Descrizione prodotto errata.", mse.getMessage());
    }

    @Test
    public void categoriaProdottoNonRispettaFormato() {
        int id = 1;
        String nome = "Forbice";
        String descrizione = "Fooo oooooo";
        String categoria = "g";
        int prezzo_Cent = 11;
        int disponibilita = 22;

        UtenteRegistrato ut = new UtenteRegistrato("pinco", "palla", "12-12-1122", "ciaociao", "qazwsx2", "pinco@palla.com", "aaaaa", "222222", 1);
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        request.getSession().setAttribute("utente", ut);
        request.getSession().setAttribute("prodotti", prodotti);

        setParametersRequest(id, nome, descrizione, categoria, prezzo_Cent, disponibilita);
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoAggiungi(request,response,gps));
        assertEquals("Categoria prodotto errata.", mse.getMessage());
    }

    @Test
    public void prezzoProdottoNonRispettaFormato() {
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

        setParametersRequest(id, nome, descrizione, categoria, prezzo_Cent, disponibilita);
        double prezzoErrato = 11.7;
        request.setParameter("prezzo_Cent", String.valueOf(prezzoErrato));
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoAggiungi(request,response,gps));
        assertEquals("Prezzo prodotto errato.", mse.getMessage());
    }

    @Test
    public void disponibilitaProdottoNonRispettaFormato() {
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

        setParametersRequest(id, nome, descrizione, categoria, prezzo_Cent, disponibilita);
        double dispErrata = 11.7;
        request.setParameter("disponibilita", String.valueOf(dispErrata));
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoAggiungi(request,response,gps));
        assertEquals("Disponibilita prodotto errata.", mse.getMessage());
    }

    @Test
    public void tuttoOkSenzaImmagine() throws ServletException, SQLException, NoSuchAlgorithmException, IOException {
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
        Part pr = null;
        request.getSession().setAttribute("img", pr);
        setParametersRequest(id, nome, descrizione, categoria, prezzo_Cent, disponibilita);
        Mockito.when(gps.getProdottoById(id)).thenReturn(prodotto);
        gpc.prodottoAggiungi(request,response,gps);
    }

    @Test
    public void tuttoOk() throws ServletException, SQLException, NoSuchAlgorithmException, IOException {
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
        assertEquals(true, gpc.prodottoAggiungi(request,response,gps));
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

