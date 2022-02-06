package logic.gestioneProdotto;

import PartnerShop.Exceptions.MyServletException;
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
import org.springframework.mock.web.MockServletContext;

import javax.servlet.ServletException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class GestioneProdottoTest {
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
    public void idProdottoNonAssociatoNessunProdottoTest() throws SQLException, NoSuchAlgorithmException, ServletException, IOException {
        int id = 1;
        String nome = "Cane";
        String descrizione = "Ciao";
        String categoria = "Poroco";
        int prezzo_Cent = 11;
        int disponibilita = 22;

        UtenteRegistrato ut = new UtenteRegistrato("pinco", "palla", "12-12-1122", "ciaociao", "qazwsx2", "pinco@palla.com", "aaaaa", "222222", 1);
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        request.getSession().setAttribute("utente", ut);
        request.getSession().setAttribute("prodotti", prodotti);

        setParametersRequest(id, nome, descrizione, categoria, prezzo_Cent, disponibilita);
        Mockito.when(gps.getProdottoById(id)).thenReturn(null);
        MyServletException mse = assertThrows(MyServletException.class, () -> gpc.prodottoModifica(request,response,gps));
        assertEquals("Prodotto non trovato.", mse.getMessage());
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

