package UnitTesting.Segnalazione;


import PartnerShop.model.dao.SegnalazioneDAO;
import PartnerShop.model.entity.Segnalazione;
import PartnerShop.segnalazione.service.SegnalazioneService;
import PartnerShop.segnalazione.service.SegnalazioneServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class SegnalazioneTest {
    private Segnalazione sgnMock;
    private SegnalazioneService sgnServ;
    private SegnalazioneDAO sgnDB;

    @Before
    public void setUp(){
        sgnDB = Mockito.mock(SegnalazioneDAO.class);
        sgnMock = Mockito.mock(Segnalazione.class);
        sgnServ = new SegnalazioneServiceImp(sgnDB);
    }

    @Test
    public void aggiungiSegnalazioneCommentoErratoTest(){
        Mockito.when(sgnMock.getCommento()).thenReturn("Il venditore risulta poco serio, il pacco è arrivato danneggiato e quindi non funzionante, vorrei procedere con il reso.");
        assertFalse(sgnServ.aggiungiSegnalazione(sgnMock));
    }


    @Test
    public void aggiungiSegnalazioneCommentoOKTest(){
        Mockito.when(sgnMock.getCommento()).thenReturn("Il venditore risulta poco serio, il pacco è arrivato danneggiato e vorrei procedere con il reso.");
        assertTrue(sgnServ.aggiungiSegnalazione(sgnMock));
    }

}
