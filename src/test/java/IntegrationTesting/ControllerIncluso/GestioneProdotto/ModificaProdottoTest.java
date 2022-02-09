package IntegrationTesting.ControllerIncluso.GestioneProdotto;

import PartnerShop.Exceptions.MyServletException;

import PartnerShop.GestioneUtenti.service.GestioneUtenteService;
import PartnerShop.GestioneUtenti.service.GestioneUtenteServiceImp;
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
    public void setUp() throws MyServletException {
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
        int id = prodotti.get(0).getId();
        request.setParameter("id", String.valueOf(id));
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoModifica(request,response,gps));
        assertEquals("Non hai i permessi necessari.", mse.getMessage());
    }

    @Test
    public void idProdottoNonPresente() {
        for(int i = 0; i < utenti.size(); i++) {
            utente = utenti.get(i);
            if(utente.getTipo() == 1)
                break;
        }

        int id = 10000;
        request.setParameter("id", String.valueOf(id));
        ArrayList<Prodotto> pr = new ArrayList<>();
        request.getSession().setAttribute("utente", utente);
        request.getSession().setAttribute("prodotti", pr);
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoModifica(request,response,gps));
        assertEquals("Prodotto non trovato.", mse.getMessage());
    }

    @Test
    public void idProdottoNonIntero() {
        for(int i = 0; i < utenti.size(); i++) {
            utente = utenti.get(i);
            if(utente.getTipo() == 1)
                break;
        }

        double id = 100.56;
        request.setParameter("id", String.valueOf(id));
        ArrayList<Prodotto> pr = new ArrayList<>();
        request.getSession().setAttribute("utente", utente);
        request.getSession().setAttribute("prodotti", pr);
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoModifica(request,response,gps));
        assertEquals("Id prodotto errato.", mse.getMessage());
    }

    @Test
    public void nomeProdottoNonRispettaFormato() {
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

        int id = prodotto.getId();
        String nome = "g";
        String descrizione = "Cgjjjjjjjao";
        String categoria = "Poroco";
        int prezzo_Cent = 11;
        int disponibilita = 22;

        ArrayList<Prodotto> pr = new ArrayList<>();
        request.getSession().setAttribute("utente", utente);
        request.getSession().setAttribute("prodotti", pr);

        setParametersRequest(id, nome, descrizione, categoria, prezzo_Cent, disponibilita);
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoModifica(request,response,gps));
        assertEquals("Nome prodotto errato.", mse.getMessage());
    }

    @Test
    public void descrizioneProdottoNonRispettaFormato() {
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

        int id = prodotto.getId();
        String nome = "Forbice";
        String descrizione = "j";
        String categoria = "Poroco";
        int prezzo_Cent = 11;
        int disponibilita = 22;

        ArrayList<Prodotto> pr = new ArrayList<>();
        request.getSession().setAttribute("utente", utente);
        request.getSession().setAttribute("prodotti", pr);

        setParametersRequest(id, nome, descrizione, categoria, prezzo_Cent, disponibilita);
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoModifica(request,response,gps));
        assertEquals("Descrizione prodotto errata.", mse.getMessage());
    }

    @Test
    public void categoriaProdottoNonRispettaFormato() {
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

        int id = prodotto.getId();
        String nome = "Forbice";
        String descrizione = "Fooo oooooo";
        String categoria = "g";
        int prezzo_Cent = 11;
        int disponibilita = 22;

        ArrayList<Prodotto> pr = new ArrayList<>();
        request.getSession().setAttribute("utente", utente);
        request.getSession().setAttribute("prodotti", pr);

        setParametersRequest(id, nome, descrizione, categoria, prezzo_Cent, disponibilita);
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoModifica(request,response,gps));
        assertEquals("Categoria prodotto errata.", mse.getMessage());
    }

    @Test
    public void prezzoProdottoNonRispettaFormato() {
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

        int id = prodotto.getId();
        String nome = "Forbice";
        String descrizione = "Fooo oooooo";
        String categoria = "Utensili";
        int prezzo_Cent = 11;
        int disponibilita = 22;

        ArrayList<Prodotto> pr = new ArrayList<>();
        request.getSession().setAttribute("utente", utente);
        request.getSession().setAttribute("prodotti", pr);

        setParametersRequest(id, nome, descrizione, categoria, prezzo_Cent, disponibilita);
        double prezzoErrato = 11.7;
        request.setParameter("prezzo_Cent", String.valueOf(prezzoErrato));
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoModifica(request,response,gps));
        assertEquals("Prezzo prodotto errato.", mse.getMessage());
    }

    @Test
    public void tuttoOk() throws ServletException, SQLException, NoSuchAlgorithmException, IOException {
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

        int id = prodotto.getId();
        String nome = "Forbice";
        String descrizione = "Fooo oooooo";
        String categoria = "Utensili";
        int prezzo_Cent = 11;
        int disponibilita = 22;

        ArrayList<Prodotto> pr = new ArrayList<>();
        request.getSession().setAttribute("utente", utente);
        request.getSession().setAttribute("prodotti", pr);

        setParametersRequest(id, nome, descrizione, categoria, prezzo_Cent, disponibilita);
        assertEquals(true, gpc.prodottoModifica(request,response,gps));
    }

    @Test
    public void disponibilitaProdottoNonRispettaFormato() {
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

        int id = prodotto.getId();
        String nome = "Forbice";
        String descrizione = "Fooo oooooo";
        String categoria = "Utensili";
        int prezzo_Cent = 11;
        int disponibilita = 22;

        ArrayList<Prodotto> pr = new ArrayList<>();
        request.getSession().setAttribute("utente", utente);
        request.getSession().setAttribute("prodotti", pr);

        setParametersRequest(id, nome, descrizione, categoria, prezzo_Cent, disponibilita);
        double dispErrata = 11.7;
        request.setParameter("disponibilita", String.valueOf(dispErrata));
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoModifica(request,response,gps));
        assertEquals("Disponibilita prodotto errata.", mse.getMessage());
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

