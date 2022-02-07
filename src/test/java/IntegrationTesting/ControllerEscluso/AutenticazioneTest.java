package IntegrationTesting.ControllerEscluso;

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

import static org.mockito.Mockito.mock;


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
        String password = "asd";
        UtenteRegistrato ut =autService.login(username,password);
        System.out.println(ut);
        Assert.assertEquals(null,ut);
    }

    @Test
    public void loginOkTest() throws IOException, ServletException {
        String username = "ancona1";
        String password = "asd";
        UtenteRegistrato ut =autService.login(username,password);
        System.out.println(ut);
        Assert.assertNotEquals(null,ut);
    }


}
