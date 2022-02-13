package IntegrationTesting.ControllerEscluso.Segnalazione;

import PartnerShop.model.dao.SegnalazioneDAO;
import PartnerShop.model.entity.Segnalazione;
import PartnerShop.segnalazione.service.SegnalazioneService;
import PartnerShop.segnalazione.service.SegnalazioneServiceImp;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SegnalazioneEsclusoTest {

    private Segnalazione sgn;
    private SegnalazioneService sgnServ;

    @Before
    public void setUp(){
        sgn = new Segnalazione();
        sgnServ = new SegnalazioneServiceImp();
        sgn.setEmail("elmehdi.boudad@gmail.com");
        sgn.setStato(0);
        sgn.setMotivazione("venditore poco serio");
    }


    @Test
    public void aggiungiSegnalazioneCommentoErratoTest(){
        sgn.setCommento("Il venditore risulta poco serio, il pacco è arrivato" +
                " danneggiato e quindi non funzionante, vorrei procedere con il reso.");
        assertFalse(sgnServ.aggiungiSegnalazione(sgn));
    }


    @Test
    public void aggiungiSegnalazioneCommentoOKTest(){
        sgn.setCommento("Il venditore risulta poco serio, il pacco è arrivato" +
                " danneggiato e vorrei procedere con il reso.");
        assertTrue(sgnServ.aggiungiSegnalazione(sgn));
    }

}
