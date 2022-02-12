package IntegrationTesting.ControllerEscluso.Autenticazione;

import PartnerShop.autenticazione.controller.AutenticazioneController;
import PartnerShop.autenticazione.service.AutenticazioneService;
import PartnerShop.autenticazione.service.AutenticazioneServiceImp;
import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.UtenteRegistrato;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class AutenticazioneTest {
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    MockHttpSession session;
    UtenteRegistrato utenteMock;
    UtenteRegistratoDAO utDBMock;
    AutenticazioneService autService;


    @Before
    public void setUp(){

        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = new MockHttpSession();
        utenteMock=mock(UtenteRegistrato.class);
        utDBMock = mock(UtenteRegistratoDAO.class);
        autService = new AutenticazioneServiceImp();
    }

    @Test
    public void loginUtenteUsernameFormatoErratoTest() throws IOException, ServletException {
        String username = "ancona1.";
        String password = "Marco99.";
        UtenteRegistrato ut =autService.login(username,password);
        Assert.assertEquals(null,ut);
    }
    @Test
    public void loginUtentePasswordNonAssociataNessunUtente(){
        String username = "ancona1."; //Corretta
        String password = "asd";        //Corretta
        UtenteRegistrato ut =autService.login(username,password);
        Assert.assertEquals(null,ut);
    }


    @Test
    public void loginOkTest() throws IOException, ServletException {
        String username = "ancona1";
        String password = "Marco99.";
        UtenteRegistrato ut =autService.login(username,password);
        Assert.assertNotEquals(null,ut);
    }


}
