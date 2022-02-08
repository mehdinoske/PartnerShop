package UnitTesting.Registrazione;

import PartnerShop.Exceptions.MyServletException;
import PartnerShop.model.dao.CarrelloDAO;
import PartnerShop.model.dao.ClienteDAO;
import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.registrazione.service.RegistrazioneServiceImp;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class RegistrazioneClienteTest  {
    RegistrazioneServiceImp regService;
    UtenteRegistrato utenteMock;
    UtenteRegistratoDAO utDBMock;
    ClienteDAO clienteDAOMock;
    CarrelloDAO carrelloDAOMock;

    @Before
    public void setUp(){
        utenteMock = Mockito.mock(UtenteRegistrato.class);
        utDBMock= Mockito.mock(UtenteRegistratoDAO.class);
        clienteDAOMock = Mockito.mock(ClienteDAO.class);
        carrelloDAOMock = mock(CarrelloDAO.class);
        this.regService = new RegistrazioneServiceImp(clienteDAOMock,carrelloDAOMock);
    }

    @Test
    public void registraEmailNonRispettaIlFormatoTest() throws MyServletException {
        String email = "@hotmail.it"; //Formato Errato
        String username = "depalma1"; //Corretta
        String password = "asd";        //Corretta;
        when(utenteMock.getEmail()).thenReturn(email);
        when(utenteMock.getPasswordHash()).thenReturn(password);
        when(utenteMock.getUsername()).thenReturn(username);
        assertThrows(MyServletException.class,()->regService.RegistrazioneCliente(utenteMock));

    }

     @Test
    public void registraUsernameClienteErrato() throws MyServletException{
         String email = "peppe@hotmail.it"; //Corretta
         String username = "depalma1."; //Non Corretto
         String password = "asd";        //Corretta;
         when(utenteMock.getEmail()).thenReturn(email);
         when(utenteMock.getPasswordHash()).thenReturn(password);
         when(utenteMock.getUsername()).thenReturn(username);

         assertThrows(MyServletException.class,()->regService.RegistrazioneCliente(utenteMock));
    }

    @Test
    public void registraPasswordClienteErrato() throws MyServletException{
        String email = "peppe@hotmail.it"; //Corretta
        String username = "depalma1"; // Corretto
        String password = "asd";        //Formato errato;
        when(utenteMock.getEmail()).thenReturn(email);
        when(utenteMock.getPasswordHash()).thenReturn(password);
        when(utenteMock.getUsername()).thenReturn(username);

        assertThrows(MyServletException.class,()->regService.RegistrazioneCliente(utenteMock));
    }

    @Test
    public void registraNomeClienteErrato() throws MyServletException{
        String email = "peppe@hotmail.it"; //Corretta
        String username = "depalma1"; // Corretto
        String password = "Peppe2699.";        //Corretto;
        String nome = "peppe1";   //Errato
        String cognome="abbatiello";    //Corretto
        when(utenteMock.getEmail()).thenReturn(email);
        when(utenteMock.getPasswordHash()).thenReturn(password);
        when(utenteMock.getUsername()).thenReturn(username);
        when(utenteMock.getNome()).thenReturn(nome);
        when(utenteMock.getCognome()).thenReturn(cognome);
        assertThrows(MyServletException.class,()->regService.RegistrazioneCliente(utenteMock));
    }
    @Test
    public void registraCognomeClienteErrato() throws MyServletException{
        String email = "peppe@hotmail.it"; //Corretta
        String username = "depalma1"; // Corretto
        String password = "Peppe2699.";        //Corretto;
        String nome = "peppe";   //Corretto
        String cognome="abbatiello1";    //Errato
        when(utenteMock.getEmail()).thenReturn(email);
        when(utenteMock.getPasswordHash()).thenReturn(password);
        when(utenteMock.getUsername()).thenReturn(username);
        when(utenteMock.getNome()).thenReturn(nome);
        when(utenteMock.getCognome()).thenReturn(cognome);
        assertThrows(MyServletException.class,()->regService.RegistrazioneCliente(utenteMock));
    }

    @Test
    public void registraCellulareClienteErrato() throws MyServletException{
        String email = "peppe@hotmail.it"; //Corretta
        String username = "depalma1"; // Corretto
        String password = "Peppe2699.";        //Corretto;
        String nome = "peppe";   //Corretto
        String cognome="abbatiello";    //Corretto
        String cellulare = "3219913211a"; //Errato
        when(utenteMock.getEmail()).thenReturn(email);
        when(utenteMock.getPasswordHash()).thenReturn(password);
        when(utenteMock.getUsername()).thenReturn(username);
        when(utenteMock.getNome()).thenReturn(nome);
        when(utenteMock.getCognome()).thenReturn(cognome);
        when(utenteMock.getCellulare()).thenReturn(cellulare);
        assertThrows(MyServletException.class,()->regService.RegistrazioneCliente(utenteMock));
    }

    @Test
    public void registraDataDiNascitaClienteErrato() throws MyServletException{
        String email = "peppe@hotmail.it"; //Corretta
        String username = "depalma1"; // Corretto
        String password = "Peppe2699.";        //Corretto;
        String nome = "peppe";   //Corretto
        String cognome="abbatiello";    //Corretto
        String cellulare = "3219913211"; //Corretto
        String DataDiNascita = "20200-01-01"; //Errato
        when(utenteMock.getEmail()).thenReturn(email);
        when(utenteMock.getPasswordHash()).thenReturn(password);
        when(utenteMock.getUsername()).thenReturn(username);
        when(utenteMock.getNome()).thenReturn(nome);
        when(utenteMock.getCognome()).thenReturn(cognome);
        when(utenteMock.getCellulare()).thenReturn(cellulare);
        when(utenteMock.getDdn()).thenReturn(DataDiNascita);
        assertThrows(MyServletException.class,()->regService.RegistrazioneCliente(utenteMock));
    }

    @Test
    public void registraIndirizzoClienteErrato() throws MyServletException{
        String email = "peppe@hotmail.it"; //Corretta
        String username = "depalma1"; // Corretto
        String password = "Peppe2699.";        //Corretto;
        String nome = "peppe";   //Corretto
        String cognome="abbatiello";    //Corretto
        String cellulare = "3219913211"; //Corretto
        String DataDiNascita = "2020-01-01"; //Corretto
        String indirizzo = "via"; //Errato
        when(utenteMock.getEmail()).thenReturn(email);
        when(utenteMock.getPasswordHash()).thenReturn(password);
        when(utenteMock.getUsername()).thenReturn(username);
        when(utenteMock.getNome()).thenReturn(nome);
        when(utenteMock.getCognome()).thenReturn(cognome);
        when(utenteMock.getCellulare()).thenReturn(cellulare);
        when(utenteMock.getDdn()).thenReturn(DataDiNascita);
        when(utenteMock.getIndirizzo()).thenReturn(indirizzo);
        assertThrows(MyServletException.class,()->regService.RegistrazioneCliente(utenteMock));
    }

    @Test
    public void registrazioneOkTest() throws MyServletException{
        String email = "peppe@hotmail.it"; //Corretta
        String username = "depalma1"; // Corretto
        String password = "Peppe2699.";        //Corretto;
        String nome = "peppe";   //Corretto
        String cognome="abbatiello";    //Corretto
        String cellulare = "3219913211"; //Corretto
        String DataDiNascita = "2020-01-01"; //Corretto
        String indirizzo = "viaaa"; //Corretto
        when(utenteMock.getEmail()).thenReturn(email);
        when(utenteMock.getPasswordHash()).thenReturn(password);
        when(utenteMock.getUsername()).thenReturn(username);
        when(utenteMock.getNome()).thenReturn(nome);
        when(utenteMock.getCognome()).thenReturn(cognome);
        when(utenteMock.getCellulare()).thenReturn(cellulare);
        when(utenteMock.getDdn()).thenReturn(DataDiNascita);
        when(utenteMock.getIndirizzo()).thenReturn(indirizzo);

        assertNotNull(regService.RegistrazioneCliente(utenteMock));
    }


}
