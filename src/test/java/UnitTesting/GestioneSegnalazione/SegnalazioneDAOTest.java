package UnitTesting.GestioneSegnalazione;

import PartnerShop.model.dao.SegnalazioneDAO;
import PartnerShop.model.entity.Segnalazione;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SegnalazioneDAOTest {

    private SegnalazioneDAO segnalazioneDAO;

    @Before
    public void setUp(){
        this.segnalazioneDAO=new SegnalazioneDAO();
    }


    @Test
    public void doGetByIdNonPresente() {
        int id = 16666666;
        assertNull(segnalazioneDAO.doRetrieveById(id));
    }

    @Test
    public void doGetByIdOk() {
        int id = 1;
        assertNotNull(segnalazioneDAO.doRetrieveById(id));
    }




    @Test
    public void doSaveEmailNull() {
        String email = null;
        Segnalazione segnalazione = new Segnalazione();
        segnalazione.setEmail(email);
        assertThrows(RuntimeException.class, ()->segnalazioneDAO.doSave(segnalazione));
    }

    @Test
    public void doSaveOk() {
        Segnalazione segnalazione = new Segnalazione();
        segnalazione.setEmail("anconamarco@gmail.com");
        segnalazione.setStato(0);
        segnalazione.setMotivazione("ffffff");
        segnalazione.setCommento("fffffffff");
        assertTrue(segnalazioneDAO.doSave(segnalazione));
    }




    @Test
    public void cambiaStatoIdNonPresente() {
        int id = 14444444;
        assertThrows(RuntimeException.class, ()->segnalazioneDAO.aggiornaStato(id));
    }

    @Test
    public void cambiaStatoOk() {
        int id = 1;
        assertTrue(segnalazioneDAO.aggiornaStato(id));
    }




    @Test
    public void doRetriveAllStatoErrato() {
        int i = 8;
        assertTrue(segnalazioneDAO.doRetrieveAll(i).isEmpty());
    }

    @Test
    public void doRetriveAllStatoOk() {
        int i = 0;
        assertFalse(segnalazioneDAO.doRetrieveAll(i).isEmpty());
    }
}
