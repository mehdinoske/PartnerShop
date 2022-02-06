package logic;

import PartnerShop.autenticazione.service.AutenticazioneServiceImp;
import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.registrazione.service.RegistrazioneServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

public class RegistrazioneTest {


    RegistrazioneServiceImp regService;

    UtenteRegistrato utenteMock;
    UtenteRegistratoDAO utDBMock;

    @Before
    public void setUp(){
        utenteMock = Mockito.mock(UtenteRegistrato.class);
        utDBMock= Mockito.mock(UtenteRegistratoDAO.class);
        this.regService = new RegistrazioneServiceImp();
    }

    @Test
    public void registraEmailNonRispettaIlFormatoTest(){
        String email = "@hotmail.it"; //Formato Errato
        String username = "depalma1"; //Corretta
        String password = "asd";        //Corretta;
        UtenteRegistrato utente2 = new UtenteRegistrato();
        utente2.setEmail(email);
        utente2.setUsername(username);
        utente2.setPassword(password);
        assertNull(regService.RegistrazioneCliente(utente2));
    }

     @Test
    public void registraUsernameClienteErrato(){
         String email = "peppe@hotmail.it"; //Corretta
         String username = "depalma1."; //Non Corretto
         String password = "asd";        //Corretta;
         UtenteRegistrato utente2 = new UtenteRegistrato();
         utente2.setEmail(email);
         utente2.setUsername(username);
         utente2.setPassword(password);
         assertNull(regService.RegistrazioneCliente(utente2));
    }


}
