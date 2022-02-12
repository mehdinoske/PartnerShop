package IntegrationTesting.ControllerIncluso.Segnalazione;

import PartnerShop.model.entity.Segnalazione;
import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.segnalazione.controller.SegnalazioneController;
import PartnerShop.segnalazione.service.SegnalazioneService;
import PartnerShop.segnalazione.service.SegnalazioneServiceImp;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SegnalazioneInclusoTest {

    private Segnalazione sgn;
    private SegnalazioneService sgnServ;
    private SegnalazioneController sgncntrl;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockHttpSession session;
    private UtenteRegistrato ut;


    @Before
    public void setUp(){
        ut = new UtenteRegistrato();
        sgncntrl = new SegnalazioneController();
        sgn = new Segnalazione();
        sgnServ = new SegnalazioneServiceImp();
        ut.setEmail("elmehdi.boudad@gmail.com");
        ut.setTipo(0);
        String motivazione = "venditore poco serio";
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = new MockHttpSession();
        request.setParameter("motivazione",motivazione);
        session.setAttribute("utente",ut);
        request.setSession(session);
    }


    @Test
    public void aggiungiSegnalazioneCommentoErratoTest() throws ServletException, IOException {
        String commentiAggiuntivi = "Il venditore risulta poco serio, il pacco è arrivato danneggiato" +
                " e quindi non funzionante, vorrei procedere con il reso.";
        request.setServletPath("/AggiungiSegnalazione");
        request.setParameter("commentiAggiuntivi",commentiAggiuntivi);
        assertFalse(sgncntrl.aggiungiSegnalazione(request,response,sgnServ));
    }


    @Test
    public void aggiungiSegnalazioneCommentoOKTest() throws ServletException, IOException{
        String commentiAggiuntivi = "Il venditore risulta poco serio, il pacco è arrivato danneggiato e vorrei procedere con il reso.";
        request.setServletPath("/AggiungiSegnalazione");
        request.setParameter("commentiAggiuntivi",commentiAggiuntivi);
        assertTrue(sgncntrl.aggiungiSegnalazione(request,response,sgnServ));
    }

}
