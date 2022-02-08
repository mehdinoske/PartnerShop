package IntegrationTesting.ControllerEscluso.GestioneProdotto;

import PartnerShop.Exceptions.MyServletException;
import PartnerShop.GestioneUtenti.model.GestioneUtenteService;
import PartnerShop.GestioneUtenti.model.GestioneUtenteServiceImp;
import PartnerShop.gestioneProdotto.controller.GestioneProdottoController;
import PartnerShop.gestioneProdotto.service.GestioneProdottoService;
import PartnerShop.gestioneProdotto.service.GestioneProdottoServiceImp;
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

public class ModificaProdottoTest {
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    MockHttpSession session;
    GestioneProdottoController gpc;
    GestioneProdottoService gps;
    GestioneUtenteService gus;
    ArrayList<Prodotto> prodotti;
    ArrayList<UtenteRegistrato> utenti;
    UtenteRegistrato utente;
    Prodotto prodotto;

    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = new MockHttpSession();
        gpc = new GestioneProdottoController();
        gus = new GestioneUtenteServiceImp();
        gps = new GestioneProdottoServiceImp();
        prodotti = gps.getAllProdotti();
        utenti = gus.VisualizzaUtenti();
        session = new MockHttpSession();
        request.setSession(session);

    }

    @Test
    public void utenteNonAutorizzato() {
        ArrayList<Prodotto> pr = new ArrayList<>();
        for(int i = 0; i < utenti.size(); i++) {
           utente = utenti.get(i);
           if(utente.getTipo() == 0)
               break;
        }

        request.getSession().setAttribute("utente", utente);
        request.getSession().setAttribute("prodotti", pr);

        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoModifica(request,response,gps));
        assertEquals("Non hai i permessi necessari.", mse.getMessage());
    }

    @Test
    public void idProdottoNonPresenteTest() {
        for(int i = 0; i < utenti.size(); i++) {
            utente = utenti.get(i);
            if(utente.getTipo() == 1)
                break;
        }

        for(int i = 0; i < prodotti.size(); i++) {
            prodotto = prodotti.get(i);
            if(prodotti.get(i).getEmail_Venditore().equals(utente.getEmail()))
                break;
        }

        System.out.println(utente.getEmail());
        System.out.println(prodotto.getEmail_Venditore());
        int id = 100;
        String nome = prodotto.getNome();
        String descrizione = prodotto.getDescrizione();
        String categoria = prodotto.getCategoria();
        long prezzo_Cent = prodotto.getPrezzo_Cent();
        int disponibilita = prodotto.getDisponibilita();

        ArrayList<Prodotto> pr = new ArrayList<>();
        request.getSession().setAttribute("utente", utente);
        request.getSession().setAttribute("prodotti", pr);

        setParametersRequest(id, nome, descrizione, categoria, prezzo_Cent, disponibilita);
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoModifica(request,response,gps));
        assertEquals("Prodotto non trovato.", mse.getMessage());
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
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoModifica(request,response,gps));
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
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoModifica(request,response,gps));
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
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoModifica(request,response,gps));
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
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoModifica(request,response,gps));
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
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoModifica(request,response,gps));
        assertEquals("Disponibilita prodotto errata.", mse.getMessage());
    }

    @Test
    public void idProdottoNonIntero() {
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
        double idErrato = 11.7;
        request.setParameter("id", String.valueOf(idErrato));
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoModifica(request,response,gps));
        assertEquals("Id prodotto errato.", mse.getMessage());
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

        setParametersRequest(id, nome, descrizione, categoria, prezzo_Cent, disponibilita);
        Mockito.when(gps.getProdottoById(id)).thenReturn(prodotto);
        assertEquals(true, gpc.prodottoModifica(request,response,gps));
    }

    private void setParametersRequest(int id, String nome, String descrizione, String categoria, long prezzo_Cent, int disponibilita) {
        request.setParameter("id", String.valueOf(id));
        request.setParameter("nome", nome);
        request.setParameter("descrizione", descrizione);
        request.setParameter("categoria", categoria);
        request.setParameter("prezzo_Cent", String.valueOf(prezzo_Cent));
        request.setParameter("disponibilita", String.valueOf(disponibilita));
    }
}

