package IntegrationTesting.ControllerEscluso.Registrazione;

import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.registrazione.service.RegistrazioneService;
import PartnerShop.registrazione.service.RegistrazioneServiceImp;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class RegistrazioneClienteTest {

    RegistrazioneService regService;
    UtenteRegistratoDAO utDB;
    UtenteRegistrato ut;

    @Before
    public void setUp(){
        this.regService = new RegistrazioneServiceImp();
        ut = new UtenteRegistrato();
    }

    @Test
    public void registraEmailNonRispettaIlFormatoTest(){
        String email = "@hotmail.it"; //Formato Errato
        String username = "depalma1"; //Corretta
        String password = "Giuseppe99.";        //Corretta;
        String nome = "peppe";   //Corretto
        String cognome="abbatiello";    //Corretto
        String cellulare = "3219913211"; //Corretto
        String DataDiNascita = "2020-01-01"; //Corretto
        String indirizzo = "viaaa"; //Corretto
        ut.setEmail(email);
        ut.setUsername(username);
        ut.setPasswordHash(password);
        ut.setNome(nome);
        ut.setCognome(cognome);
        ut.setCellulare(cellulare);
        ut.setDdn(DataDiNascita);
        ut.setIndirizzo(indirizzo);
        assertNull(regService.RegistrazioneCliente(ut));
    }

    @Test
    public void registraUsernameClienteErrato(){
        String email = "peppe@hotmail.it"; //Corretto
        String username = "depalma1"; //Errato
        String password = "Giuseppe99.";        //Corretta;
        String nome = "peppe";   //Corretto
        String cognome="abbatiello";    //Corretto
        String cellulare = "3219913211"; //Corretto
        String DataDiNascita = "2020-01-01"; //Corretto
        String indirizzo = "viaaa"; //Corretto
        ut.setEmail(email);
        ut.setUsername(username);
        ut.setPassword(password);
        ut.setNome(nome);
        ut.setCognome(cognome);
        ut.setCellulare(cellulare);
        ut.setDdn(DataDiNascita);
        ut.setIndirizzo(indirizzo);
        assertNull(regService.RegistrazioneCliente(ut));
    }

    @Test
    public void registraPasswordClienteErrato(){
        String email = "peppe@hotmail.it"; //Corretta
        String username = "depalma"; // Corretto
        String password = "asd";        //Formato errato;
        String nome = "peppe";   //Corretto
        String cognome="abbatiello";    //Corretto
        String cellulare = "3219913211"; //Corretto
        String DataDiNascita = "2020-01-01"; //Corretto
        String indirizzo = "viaaa"; //Corretto
        ut.setEmail(email);
        ut.setUsername(username);
        ut.setPassword(password);
        ut.setNome(nome);
        ut.setCognome(cognome);
        ut.setCellulare(cellulare);
        ut.setDdn(DataDiNascita);
        ut.setIndirizzo(indirizzo);
        assertNull(regService.RegistrazioneCliente(ut));
    }

    @Test
    public void registraNomeClienteErrato(){
        String email = "peppe@hotmail.it"; //Corretta
        String username = "depalma1"; //Corretta
        String password = "Giuseppe99.";        //Corretta;
        String nome = "peppe1";   //Corretto
        String cognome="abbatiello";    //Corretto
        String cellulare = "3219913211"; //Corretto
        String DataDiNascita = "2020-01-01"; //Corretto
        String indirizzo = "viaaa"; //Corretto
        ut.setEmail(email);
        ut.setUsername(username);
        ut.setPassword(password);
        ut.setNome(nome);
        ut.setCognome(cognome);
        ut.setCellulare(cellulare);
        ut.setDdn(DataDiNascita);
        ut.setIndirizzo(indirizzo);
        assertNull(regService.RegistrazioneCliente(ut));
    }
    @Test
    public void registraCognomeClienteErrato(){
        String email = "peppe@hotmail.it"; //Corretta
        String username = "depalma1"; //Corretta
        String password = "Giuseppe99.";        //Corretta;
        String nome = "peppe";   //Corretto
        String cognome="abbatiello1";    //Errato
        String cellulare = "3219913211"; //Corretto
        String DataDiNascita = "2020-01-01"; //Corretto
        String indirizzo = "viaaa"; //Corretto
        ut.setEmail(email);
        ut.setUsername(username);
        ut.setPassword(password);
        ut.setNome(nome);
        ut.setCognome(cognome);
        ut.setCellulare(cellulare);
        ut.setDdn(DataDiNascita);
        ut.setIndirizzo(indirizzo);
        assertNull(regService.RegistrazioneCliente(ut));
    }

    @Test
    public void registraCellulareClienteErrato(){
        String email = "peppe@hotmail.it"; //Corretta
        String username = "depalma1"; //Corretta
        String password = "Giuseppe99.";        //Corretta;
        String nome = "peppe";   //Corretto
        String cognome="abbatiello";    //Corretto
        String cellulare = "3219913211q"; //Errato
        String DataDiNascita = "2020-01-01"; //Corretto
        String indirizzo = "viaaa"; //Corretto
        ut.setEmail(email);
        ut.setUsername(username);
        ut.setPassword(password);
        ut.setNome(nome);
        ut.setCognome(cognome);
        ut.setCellulare(cellulare);
        ut.setDdn(DataDiNascita);
        ut.setIndirizzo(indirizzo);
        assertNull(regService.RegistrazioneCliente(ut));
    }

    @Test
    public void registraDataDiNascitaClienteErrato(){
        String email = "peppe@hotmail.it"; //Corretto
        String username = "depalma1"; //Corretta
        String password = "Giuseppe99.";        //Corretta;
        String nome = "peppe";   //Corretto
        String cognome="abbatiello";    //Corretto
        String cellulare = "3219913211"; //Corretto
        String DataDiNascita = "20200-01-01"; //Errato
        String indirizzo = "viaaa"; //Corretto
        ut.setEmail(email);
        ut.setUsername(username);
        ut.setPassword(password);
        ut.setNome(nome);
        ut.setCognome(cognome);
        ut.setCellulare(cellulare);
        ut.setDdn(DataDiNascita);
        ut.setIndirizzo(indirizzo);
        assertNull(regService.RegistrazioneCliente(ut));
    }

    @Test
    public void registraIndirizzoClienteErrato(){
        String email = "peppe@hotmail.it"; //Corretto
        String username = "depalma1"; //Corretta
        String password = "Giuseppe99.";        //Corretta;
        String nome = "peppe";   //Corretto
        String cognome="abbatiello";    //Corretto
        String cellulare = "3219913211"; //Corretto
        String DataDiNascita = "2020-01-01"; //Corretto
        String indirizzo = "viaa"; //errato
        ut.setEmail(email);
        ut.setUsername(username);
        ut.setPassword(password);
        ut.setNome(nome);
        ut.setCognome(cognome);
        ut.setCellulare(cellulare);
        ut.setDdn(DataDiNascita);
        ut.setIndirizzo(indirizzo);
        assertNull(regService.RegistrazioneCliente(ut));
    }

    @Test
    public void registrazioneOkTest(){
        String email = "peppeee@hotmail.it"; //Corretta
        String username = "peppe123"; // Corretto
        String password = "Peppe2699.";        //Corretto;
        String nome = "peppe";   //Corretto
        String cognome="abbatiello";    //Corretto
        String cellulare = "3219913211"; //Corretto
        String DataDiNascita = "2020-01-01"; //Corretto
        String indirizzo = "viaaa"; //Corretto
        ut.setEmail(email);
        ut.setUsername(username);
        ut.setPassword(password);
        ut.setPasswordHash(password);
        ut.setNome(nome);
        ut.setCognome(cognome);
        ut.setCellulare(cellulare);
        ut.setDdn(DataDiNascita);
        ut.setIndirizzo(indirizzo);
        assertNotNull(regService.RegistrazioneCliente(ut));
    }
}
