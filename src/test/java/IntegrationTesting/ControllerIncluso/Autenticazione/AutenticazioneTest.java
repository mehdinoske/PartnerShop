package IntegrationTesting.ControllerIncluso.Autenticazione;

import PartnerShop.autenticazione.controller.AutenticazioneController;
import PartnerShop.autenticazione.service.AutenticazioneServiceImp;
import PartnerShop.gestioneProdotto.controller.GestioneProdottoController;
import PartnerShop.gestioneProdotto.service.GestioneProdottoService;
import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.Prodotto;
import PartnerShop.model.entity.UtenteRegistrato;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class AutenticazioneTest {
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    MockHttpSession session;
    UtenteRegistrato utenteMock;
    UtenteRegistratoDAO utDBMock;
    AutenticazioneController autenticazioneController;


    @Before
    public void setUp(){

        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = new MockHttpSession();
        utenteMock=mock(UtenteRegistrato.class);
        utDBMock = mock(UtenteRegistratoDAO.class);
        autenticazioneController = new AutenticazioneController();
    }

    @Test
    public void loginUtenteUsernameFormatoErratoTest() throws ServletException, IOException {
        request.setParameter("usernameLogin","ancona1."); //errato
        request.setParameter("passwordLogin","Marco99.");    //corretto
        autenticazioneController.doPost(request,response);
        UtenteRegistrato ut = (UtenteRegistrato)request.getSession().getAttribute("utente");
        Assert.assertEquals(null,ut);
    }


    @Test
    public void loginUtentePasswordNonAssociataNessunUtente() throws ServletException, IOException {
        request.setParameter("usernameLogin","ancona1"); //corretto
        request.setParameter("passwordLogin","asd.");   //errato
        autenticazioneController.doPost(request,response);
        UtenteRegistrato ut = (UtenteRegistrato)request.getSession().getAttribute("utente");
        Assert.assertEquals(null,ut);
    }


    @Test
    public void loginOkTest() throws IOException, ServletException {
        request.setParameter("usernameLogin","ancona1");
        request.setParameter("passwordLogin","Marco99.");
        autenticazioneController.doPost(request,response);
        UtenteRegistrato ut = (UtenteRegistrato)request.getSession().getAttribute("utente");
        Assert.assertNotEquals(null,ut);
    }



}
