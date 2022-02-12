package IntegrationTesting.ControllerIncluso.GestioneUtente;

import PartnerShop.Exceptions.MyServletException;
import PartnerShop.GestioneUtenti.controller.GestioneUtenteController;
import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.Amministratore;
import PartnerShop.model.entity.UtenteRegistrato;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class GestioneUtenteInclusoTest {

    MockHttpServletRequest request;
    MockHttpServletResponse response;
    GestioneUtenteController utController;
    UtenteRegistrato ut;
    UtenteRegistratoDAO utDAO;
    Amministratore admin;

    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        utController = new GestioneUtenteController();
        admin = new Amministratore();
        utDAO = new UtenteRegistratoDAO();
        ut = utDAO.doRetrieveByEmail("lionelmessi@gmail.com");
    }

    @Test
    public void modificaNomeNonRispettaIlFormatoTest() {
        String nome = "Giuseppe2";
        String cognome = "Abbatiello";
        String cellulare = "3482697548";
        String indirizzo = "Via Pecereca";
        String password = "@Password1";
        String passConf = "@Password1";
        request.setServletPath("/ModificaForm");
        request.setParameter("nome", nome);
        request.setParameter("cognome", cognome);
        request.setParameter("cellulare", cellulare);
        request.setParameter("indirizzo", indirizzo);
        request.setParameter("password", password);
        request.setParameter("passwordConferma", passConf);
        request.setParameter("ddn", "1212-12-12");
        request.setParameter("username","ancona1");
        request.setParameter("email", "anconamarco@gmail.com");
        request.setParameter("tipo", "0");

        assertThrows(MyServletException.class,()-> utController.execute(request, response));
    }

    @Test
    public void modificaCognomeNonRispettaIlFormatoTest() {
        String nome = "Marco";
        String cognome = "De Palma5";
        String cellulare = "3472589763";
        String indirizzo = "Via Pago Veiano";
        String password = "Hotel36.";
        String passConf = "Hotel36.";
        request.setServletPath("/ModificaForm");
        request.setParameter("nome", nome);
        request.setParameter("cognome", cognome);
        request.setParameter("cellulare", cellulare);
        request.setParameter("indirizzo", indirizzo);
        request.setParameter("password", password);
        request.setParameter("passwordConferma", passConf);
        request.setParameter("ddn", "1212-12-12");
        request.setParameter("username","ancona1");
        request.setParameter("email", "anconamarco@gmail.com");
        request.setParameter("tipo", "0");

        assertThrows(MyServletException.class,()-> utController.execute(request, response));
    }

    @Test
    public void modificaCellulareNonRispettaIlFormatoTest() {
        String nome = "Mehdi";
        String cognome = "Boudad";
        String cellulare = "333256478";
        String indirizzo = "Bivio Cioffi";
        String password = ".Fastweb99.";
        String passConf = ".Fastweb99.";
        request.setServletPath("/ModificaForm");
        request.setParameter("nome", nome);
        request.setParameter("cognome", cognome);
        request.setParameter("cellulare", cellulare);
        request.setParameter("indirizzo", indirizzo);
        request.setParameter("password", password);
        request.setParameter("passwordConferma", passConf);
        request.setParameter("ddn", "1212-12-12");
        request.setParameter("username","ancona1");
        request.setParameter("email", "anconamarco@gmail.com");
        request.setParameter("tipo", "0");

        assertThrows(MyServletException.class,()-> utController.execute(request, response));
    }

    @Test
    public void modificaIndirizzoNonRispettaIlFormatoTest() {
        String nome = "Lorenzo";
        String cognome = "William";
        String cellulare = "3663524789";
        String indirizzo = "Via";
        String password = "@Pollastra9";
        String passConf = "@Pollastra9";
        request.setServletPath("/ModificaForm");
        request.setParameter("nome", nome);
        request.setParameter("cognome", cognome);
        request.setParameter("cellulare", cellulare);
        request.setParameter("indirizzo", indirizzo);
        request.setParameter("password", password);
        request.setParameter("passwordConferma", passConf);
        request.setParameter("ddn", "1212-12-12");
        request.setParameter("username","ancona1");
        request.setParameter("email", "anconamarco@gmail.com");
        request.setParameter("tipo", "0");

        assertThrows(MyServletException.class,()-> utController.execute(request, response));
    }

    @Test
    public void modificaPasswordNonRispettaIlFormatoTest() {
        String nome = "Gianna";
        String cognome = "Segugio";
        String cellulare = "3894856791";
        String indirizzo = "Via Padula";
        String password = "Password1";
        String passConf = "Password1";
        request.setServletPath("/ModificaForm");
        request.setParameter("nome", nome);
        request.setParameter("cognome", cognome);
        request.setParameter("cellulare", cellulare);
        request.setParameter("indirizzo", indirizzo);
        request.setParameter("password", password);
        request.setParameter("passwordConferma", passConf);
        request.setParameter("ddn", "1212-12-12");
        request.setParameter("username","ancona1");
        request.setParameter("email", "anconamarco@gmail.com");
        request.setParameter("tipo", "0");

        assertThrows(MyServletException.class,()-> utController.execute(request, response));
    }

    @Test
    public void modificaPasswordNonCoincidonoTest(){
        String nome = "Sara";
        String cognome = "Lilia";
        String cellulare = "3684579214";
        String indirizzo = "Via Luperto";
        String password = "Giuseppe99.";
        String passConf = "Giuseppe99";
        request.setServletPath("/ModificaForm");
        request.setParameter("nome", nome);
        request.setParameter("cognome", cognome);
        request.setParameter("cellulare", cellulare);
        request.setParameter("indirizzo", indirizzo);
        request.setParameter("password", password);
        request.setParameter("passwordConferma", passConf);
        request.setParameter("ddn", "1212-12-12");
        request.setParameter("username","ancona1");
        request.setParameter("email", "anconamarco@gmail.com");
        request.setParameter("tipo", "0");

        assertThrows(MyServletException.class,()-> utController.execute(request, response));
    }

    @Test
    public void modificaDatiOk() throws ServletException, IOException {
        String nome = "Marco";
        String cognome = "Ancona";
        String cellulare = "3451637097";
        String indirizzo = "Via Strettolone";
        String password = "@Giostra99";
        String passConf = "@Giostra99";
        request.setServletPath("/ModificaForm");
        request.setParameter("nome", nome);
        request.setParameter("cognome", cognome);
        request.setParameter("cellulare", cellulare);
        request.setParameter("indirizzo", indirizzo);
        request.setParameter("password", password);
        request.setParameter("passwordConferma", passConf);
        request.setParameter("ddn", "1212-12-12");
        request.setParameter("username","ancona1");
        request.setParameter("email", "anconamarco@gmail.com");
        request.setParameter("tipo", "0");

        assertTrue(utController.execute(request, response));
    }

    @Test
    public void cancellaDatiUtentiOk() throws ServletException, IOException {
        request.getSession().setAttribute("utente", ut);
        request.setParameter("email", "lionelmessi@gmail.com");
        request.getSession().setAttribute("admin", null);
        request.setServletPath("/CancellaDatiUtenti");

        assertTrue(utController.execute(request, response));
    }

    /*@Test
    public void cancellaDatiAdminOk(){

    }*/
}
