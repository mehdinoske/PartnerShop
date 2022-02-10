package IntegrationTesting.ControllerIncluso.GestioneUtente;

import PartnerShop.Exceptions.MyServletException;
import PartnerShop.GestioneUtenti.controller.GestioneUtenteController;
import PartnerShop.model.entity.UtenteRegistrato;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ModificaDatiInclusoTest {

    MockHttpServletRequest request;
    MockHttpServletResponse response;
    GestioneUtenteController utController;

    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        utController = new GestioneUtenteController();
        request.setParameter("ddn", "1212-12-12");
        request.setParameter("username","ancona1");
        request.setParameter("email", "anconamarco@gmail.com");
        request.setParameter("tipo", "0");
    }

    @Test
    public void modificaNomeNonRispettaIlFormatoTest() {
        String nome = "Giuseppe2";
        String cognome = "Abbatiello";
        String cellulare = "3482697548";
        String indirizzo = "Via Pecereca";
        String password = "@Password1";

        request.setServletPath("/ModificaForm");
        request.setParameter("nome", nome);
        request.setParameter("cognome", cognome);
        request.setParameter("cellulare", cellulare);
        request.setParameter("indirizzo", indirizzo);
        request.setParameter("password", password);
        assertThrows(MyServletException.class,()-> utController.execute(request, response));
    }

    @Test
    public void modificaCognomeNonRispettaIlFormatoTest() {
        String nome = "Marco";
        String cognome = "De Palma5";
        String cellulare = "3472589763";
        String indirizzo = "Via Pago Veiano";
        String password = "Hotel36.";
        request.setServletPath("/ModificaForm");
        request.setParameter("nome", nome);
        request.setParameter("cognome", cognome);
        request.setParameter("cellulare", cellulare);
        request.setParameter("indirizzo", indirizzo);
        request.setParameter("password", password);
        assertThrows(MyServletException.class,()-> utController.execute(request, response));
    }

    @Test
    public void modificaCellulareNonRispettaIlFormatoTest() {
        String nome = "Mehdi";
        String cognome = "Boudad";
        String cellulare = "333256478";
        String indirizzo = "Bivio Cioffi";
        String password = ".Fastweb99.";
        request.setServletPath("/ModificaForm");
        request.setParameter("nome", nome);
        request.setParameter("cognome", cognome);
        request.setParameter("cellulare", cellulare);
        request.setParameter("indirizzo", indirizzo);
        request.setParameter("password", password);
        assertThrows(MyServletException.class,()-> utController.execute(request, response));
    }

    @Test
    public void modificaIndirizzoNonRispettaIlFormatoTest() {
        String nome = "Lorenzo";
        String cognome = "William";
        String cellulare = "3663524789";
        String indirizzo = "Via";
        String password = "@Pollastra9";
        request.setServletPath("/ModificaForm");
        request.setParameter("nome", nome);
        request.setParameter("cognome", cognome);
        request.setParameter("cellulare", cellulare);
        request.setParameter("indirizzo", indirizzo);
        request.setParameter("password", password);
        assertThrows(MyServletException.class,()-> utController.execute(request, response));
    }

    @Test
    public void modificaPasswordNonRispettaIlFormatoTest() {
        String nome = "Gianna";
        String cognome = "Segugio";
        String cellulare = "3894856791";
        String indirizzo = "Via Padula";
        String password = "Password1";
        request.setServletPath("/ModificaForm");
        request.setParameter("nome", nome);
        request.setParameter("cognome", cognome);
        request.setParameter("cellulare", cellulare);
        request.setParameter("indirizzo", indirizzo);
        request.setParameter("password", password);
        assertThrows(MyServletException.class,()-> utController.execute(request, response));
    }

    @Test
    public void modificaDatiOk() throws ServletException, IOException {
        String nome = "Marco";
        String cognome = "Ancona";
        String cellulare = "3451637097";
        String indirizzo = "Via Strettolone";
        String password = "@Giostra99";
        request.setServletPath("/ModificaForm");
        request.setParameter("nome", nome);
        request.setParameter("cognome", cognome);
        request.setParameter("cellulare", cellulare);
        request.setParameter("indirizzo", indirizzo);
        request.setParameter("password", password);
        assertTrue(utController.execute(request, response));
    }
}
