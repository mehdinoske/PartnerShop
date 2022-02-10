package IntegrationTesting.ControllerEscluso.GestioneUtente;

import PartnerShop.Exceptions.MyServletException;
import PartnerShop.GestioneUtenti.service.GestioneUtenteService;
import PartnerShop.GestioneUtenti.service.GestioneUtenteServiceImp;
import PartnerShop.model.entity.UtenteRegistrato;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class GestioneUtenteEsclusoTest {

   GestioneUtenteService utService;
   UtenteRegistrato ut;

    @Before
    public void setUp(){
        this.utService = new GestioneUtenteServiceImp();
        ut = new UtenteRegistrato();
        ut.setDdn("1212-12-12");
        ut.setUsername("peppe1");
        ut.setEmail("peppe.abbatiello@gmail.com");
        ut.setTipo(0);
    }

    @Test
    public void modificaNomeNonRispettaIlFormatoTest() {
        String nome = "Giuseppe2";
        String cognome = "Abbatiello";
        String cellulare = "3482697548";
        String indirizzo = "Via Pecereca";
        String password = "@Password1";
        ut.setNome(nome);
        ut.setCognome(cognome);
        ut.setCellulare(cellulare);
        ut.setIndirizzo(indirizzo);
        ut.setPassword(password);
        ut.setPasswordHash(password);
        assertThrows(MyServletException.class,()->utService.ModificaDati(ut));
    }

    @Test
    public void modificaCognomeNonRispettaIlFormatoTest() {
        String nome = "Marco";
        String cognome = "De Palma5";
        String cellulare = "3472589763";
        String indirizzo = "Via Pago Veiano";
        String password = "Hotel36.";
        ut.setNome(nome);
        ut.setCognome(cognome);
        ut.setCellulare(cellulare);
        ut.setIndirizzo(indirizzo);
        ut.setPassword(password);
        ut.setPasswordHash(password);
        assertThrows(MyServletException.class,()->utService.ModificaDati(ut));
    }

    @Test
    public void modificaCellulareNonRispettaIlFormatoTest() {
        String nome = "Mehdi";
        String cognome = "Boudad";
        String cellulare = "333256478";
        String indirizzo = "Bivio Cioffi";
        String password = ".Fastweb99.";
        ut.setNome(nome);
        ut.setCognome(cognome);
        ut.setCellulare(cellulare);
        ut.setIndirizzo(indirizzo);
        ut.setPassword(password);
        ut.setPasswordHash(password);
        assertThrows(MyServletException.class,()->utService.ModificaDati(ut));
    }

    @Test
    public void modificaIndirizzoNonRispettaIlFormatoTest() {
        String nome = "Lorenzo";
        String cognome = "William";
        String cellulare = "3663524789";
        String indirizzo = "Via";
        String password = "@Pollastra9";
        ut.setNome(nome);
        ut.setCognome(cognome);
        ut.setCellulare(cellulare);
        ut.setIndirizzo(indirizzo);
        ut.setPassword(password);
        ut.setPasswordHash(password);
        assertThrows(MyServletException.class,()->utService.ModificaDati(ut));
    }

    @Test
    public void modificaPasswordNonRispettaIlFormatoTest() {
        String nome = "Gianna";
        String cognome = "Segugio";
        String cellulare = "3894856791";
        String indirizzo = "Via Padula";
        String password = "Password1";
        ut.setNome(nome);
        ut.setCognome(cognome);
        ut.setCellulare(cellulare);
        ut.setIndirizzo(indirizzo);
        ut.setPassword(password);
        ut.setPasswordHash(password);
        assertThrows(MyServletException.class,()->utService.ModificaDati(ut));
    }

    @Test
    public void modificaDatiOk() throws MyServletException{
        String nome = "Marco";
        String cognome = "Ancona";
        String cellulare = "3451637097";
        String indirizzo = "Via Pago Veiano";
        String password = "@Giostra99";
        ut.setNome(nome);
        ut.setCognome(cognome);
        ut.setCellulare(cellulare);
        ut.setIndirizzo(indirizzo);
        ut.setPassword(password);
        ut.setPasswordHash(password);
        assertTrue(utService.ModificaDati(ut));
    }
}
