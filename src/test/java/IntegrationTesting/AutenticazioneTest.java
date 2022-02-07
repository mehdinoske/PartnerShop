package IntegrationTesting;

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
    public void AutenticazioneUtenteTest() throws IOException, ServletException {
        request.setParameter("usernameLogin","ancona1");
        request.setParameter("passwordLogin","asd");
        autenticazioneController.doPost(request,response);
        UtenteRegistrato ut = (UtenteRegistrato)request.getSession().getAttribute("utente");
        when(utDBMock.doRetrieveByUsernamePass(anyString(),anyString())).thenReturn(utenteMock);
        when(utenteMock.getTipo()).thenReturn(2);
        System.out.println(ut);
        Assert.assertNotEquals(null,ut);
    }
}
