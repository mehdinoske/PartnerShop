package IntegrationTesting.ControllerIncluso.Registrazione;

import PartnerShop.Exceptions.MyServletException;
import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.registrazione.controller.RegistrazioneController;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class RegistrazionClienteInclusoTest {

    MockHttpServletRequest request;
    MockHttpServletResponse response;
    MockHttpSession session;
    RegistrazioneController regContr;
    UtenteRegistrato ut;

    UtenteRegistrato utente;

    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = new MockHttpSession();
        regContr = new RegistrazioneController();
        ut = new UtenteRegistrato();
    }

    @Test
    public void registraEmailNonRispettaIlFormatoTest() throws ServletException, IOException {
        String email = "@hotmail.it"; //Formato Errato
        String username = "depalma1"; //Corretta
        String password = "Giuseppe99.";        //Corretta;
        String nome = "peppe";   //Corretto
        String cognome="abbatiello";    //Corretto
        String cellulare = "3219913211"; //Corretto
        String DataDiNascita = "2020-01-01"; //Corretto
        String indirizzo = "viaaa"; //Corretto
        request.setParameter("id","cliente");
        request.setParameter("nome",nome);
        request.setParameter("cognome",cognome);
        request.setParameter("email",email);
        request.setParameter("username",username);
        request.setParameter("indirizzo",indirizzo);
        request.setParameter("password",password);
        request.setParameter("ddn",DataDiNascita);
        request.setParameter("cellulare",cellulare);
        assertThrows(MyServletException.class,()-> regContr.doPost(request,response));
    }

    @Test
    public void registraUsernameClienteErrato() throws ServletException, IOException {
        String email = "peppe@hotmail.it"; //Corretto
        String username = "depalma."; //Errato
        String password = "Giuseppe99.";        //Corretta;
        String nome = "peppe";   //Corretto
        String cognome="abbatiello";    //Corretto
        String cellulare = "3219913211"; //Corretto
        String DataDiNascita = "2020-01-01"; //Corretto
        String indirizzo = "viaaa"; //Corretto
        request.setParameter("id","cliente");
        request.setParameter("nome",nome);
        request.setParameter("cognome",cognome);
        request.setParameter("email",email);
        request.setParameter("username",username);
        request.setParameter("indirizzo",indirizzo);
        request.setParameter("password",password);
        request.setParameter("ddn",DataDiNascita);
        request.setParameter("cellulare",cellulare);
        assertThrows(MyServletException.class,()-> regContr.doPost(request,response));
    }

    @Test
    public void registraPasswordClienteErrato() throws ServletException, IOException {
        String email = "peppe@hotmail.it"; //Corretta
        String username = "depalma123"; // Corretto
        String password = "asd";        //Formato errato;
        String nome = "peppe";   //Corretto
        String cognome="abbatiello";    //Corretto
        String cellulare = "3219913211"; //Corretto
        String DataDiNascita = "2020-01-01"; //Corretto
        String indirizzo = "viaaa"; //Corretto
        request.setParameter("id","cliente");
        request.setParameter("nome",nome);
        request.setParameter("cognome",cognome);
        request.setParameter("email",email);
        request.setParameter("username",username);
        request.setParameter("indirizzo",indirizzo);
        request.setParameter("password",password);
        request.setParameter("ddn",DataDiNascita);
        request.setParameter("cellulare",cellulare);
        assertThrows(MyServletException.class,()-> regContr.doPost(request,response));
    }

    @Test
    public void registraNomeClienteErrato() throws ServletException, IOException {
        String email = "peppe@hotmail.it"; //Corretta
        String username = "depalma1"; //Corretta
        String password = "Giuseppe99.";        //Corretta;
        String nome = "peppe1";   //Corretto
        String cognome="abbatiello";    //Corretto
        String cellulare = "3219913211"; //Corretto
        String DataDiNascita = "2020-01-01"; //Corretto
        String indirizzo = "viaaa"; //Corretto
        request.setParameter("id","cliente");
        request.setParameter("nome",nome);
        request.setParameter("cognome",cognome);
        request.setParameter("email",email);
        request.setParameter("username",username);
        request.setParameter("indirizzo",indirizzo);
        request.setParameter("password",password);
        request.setParameter("ddn",DataDiNascita);
        request.setParameter("cellulare",cellulare);
        assertThrows(MyServletException.class,()-> regContr.doPost(request,response));
    }
    @Test
    public void registraCognomeClienteErrato() throws ServletException, IOException {
        String email = "peppe@hotmail.it"; //Corretta
        String username = "depalma1"; //Corretta
        String password = "Giuseppe99.";        //Corretta;
        String nome = "peppe";   //Corretto
        String cognome="abbatiello1";    //Errato
        String cellulare = "3219913211"; //Corretto
        String DataDiNascita = "2020-01-01"; //Corretto
        String indirizzo = "viaaa"; //Corretto
        request.setParameter("id","cliente");
        request.setParameter("nome",nome);
        request.setParameter("cognome",cognome);
        request.setParameter("email",email);
        request.setParameter("username",username);
        request.setParameter("indirizzo",indirizzo);
        request.setParameter("password",password);
        request.setParameter("ddn",DataDiNascita);
        request.setParameter("cellulare",cellulare);
        assertThrows(MyServletException.class,()-> regContr.doPost(request,response));
    }

    @Test
    public void registraCellulareClienteErrato() throws ServletException, IOException {
        String email = "peppe@hotmail.it"; //Corretta
        String username = "depalma1"; //Corretta
        String password = "Giuseppe99.";        //Corretta;
        String nome = "peppe";   //Corretto
        String cognome="abbatiello";    //Corretto
        String cellulare = "3219913211q"; //Errato
        String DataDiNascita = "2020-01-01"; //Corretto
        String indirizzo = "viaaa"; //Corretto
        request.setParameter("id","cliente");
        request.setParameter("nome",nome);
        request.setParameter("cognome",cognome);
        request.setParameter("email",email);
        request.setParameter("username",username);
        request.setParameter("indirizzo",indirizzo);
        request.setParameter("password",password);
        request.setParameter("ddn",DataDiNascita);
        request.setParameter("cellulare",cellulare);
        assertThrows(MyServletException.class,()-> regContr.doPost(request,response));
    }

    @Test
    public void registraDataDiNascitaClienteErrato() throws ServletException, IOException {
        String email = "peppe@hotmail.it"; //Corretto
        String username = "depalma1"; //Corretta
        String password = "Giuseppe99.";        //Corretta;
        String nome = "peppe";   //Corretto
        String cognome="abbatiello";    //Corretto
        String cellulare = "3219913211"; //Corretto
        String DataDiNascita = "20200-01-01"; //Errato
        String indirizzo = "viaaa"; //Corretto
        request.setParameter("id","cliente");
        request.setParameter("nome",nome);
        request.setParameter("cognome",cognome);
        request.setParameter("email",email);
        request.setParameter("username",username);
        request.setParameter("indirizzo",indirizzo);
        request.setParameter("password",password);
        request.setParameter("ddn",DataDiNascita);
        request.setParameter("cellulare",cellulare);
        assertThrows(MyServletException.class,()-> regContr.doPost(request,response));
    }

    @Test
    public void registraIndirizzoClienteErrato() throws ServletException, IOException {
        String email = "peppe@hotmail.it"; //Corretto
        String username = "depalma1"; //Corretta
        String password = "Giuseppe99.";        //Corretta;
        String nome = "peppe";   //Corretto
        String cognome="abbatiello";    //Corretto
        String cellulare = "3219913211"; //Corretto
        String DataDiNascita = "2020-01-01"; //Corretto
        String indirizzo = "viaa"; //errato
        request.setParameter("id","cliente");
        request.setParameter("nome",nome);
        request.setParameter("cognome",cognome);
        request.setParameter("email",email);
        request.setParameter("username",username);
        request.setParameter("indirizzo",indirizzo);
        request.setParameter("password",password);
        request.setParameter("ddn",DataDiNascita);
        request.setParameter("cellulare",cellulare);
        assertThrows(MyServletException.class,()-> regContr.doPost(request,response));
    }

    @Test @Ignore
    public void registrazioneOkTest() throws ServletException, IOException {
        String email = "marcooo@hotmail.it"; //Corretta
        String username = "Marco123"; // Corretto
        String password = "Peppe2699.";        //Corretto;
        String nome = "peppe";   //Corretto
        String cognome="abbatiello";    //Corretto
        String cellulare = "3219913211"; //Corretto
        String DataDiNascita = "2020-01-01"; //Corretto
        String indirizzo = "viaaa"; //Corretto
        request.setParameter("id","cliente");
        request.setParameter("nome",nome);
        request.setParameter("cognome",cognome);
        request.setParameter("email",email);
        request.setParameter("username",username);
        request.setParameter("indirizzo",indirizzo);
        request.setParameter("password",password);
        request.setParameter("ddn",DataDiNascita);
        request.setParameter("cellulare",cellulare);
        regContr.doPost(request,response);
        UtenteRegistrato ut = (UtenteRegistrato) request.getSession().getAttribute("utente");
        assertNotNull(ut);

    }
}
