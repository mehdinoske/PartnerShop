package logic;

import PartnerShop.model.dao.CarrelloDAO;
import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.dao.VenditoreDAO;
import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.registrazione.service.RegistrazioneServiceImp;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore
public class RegistrazioneVenditoreTest {

    RegistrazioneServiceImp regService;

    UtenteRegistrato utenteMock;
    UtenteRegistratoDAO utDBMock;
    VenditoreDAO venditoreDAOMock;
    CarrelloDAO carrelloDAOMock;

    @Before
    public void setUp(){
        utenteMock = Mockito.mock(UtenteRegistrato.class);
        utDBMock= Mockito.mock(UtenteRegistratoDAO.class);
        venditoreDAOMock = Mockito.mock(VenditoreDAO.class);
        carrelloDAOMock = mock(CarrelloDAO.class);
        this.regService = new RegistrazioneServiceImp(venditoreDAOMock,carrelloDAOMock);
    }

    @Test
    public void registraEmailVenditoreErrato(){
        String email = "@gmail.com"; //Errata
        String username = "depalma1"; // Corretto
        String password = "Giuseppe99."; //Corretto;
        String nome = "Marco";   //Corretto
        String cognome="De Palma";    //Corretto
        String cellulare = "3331122333"; //Corretto
        String DataDiNascita = "2000-01-01"; //Corretto
        String indirizzo = "via Napoli"; //Corretto
        String PIva = "AA1231328";
        String nomeNegozio="negozio";
        when(utenteMock.getEmail()).thenReturn(email);
        when(utenteMock.getPassword()).thenReturn(password);
        when(utenteMock.getUsername()).thenReturn(username);
        when(utenteMock.getNome()).thenReturn(nome);
        when(utenteMock.getCognome()).thenReturn(cognome);
        when(utenteMock.getCellulare()).thenReturn(cellulare);
        when(utenteMock.getDdn()).thenReturn(DataDiNascita);
        when(utenteMock.getIndirizzo()).thenReturn(indirizzo);
        assertEquals(null,regService.RegistrazioneVenditore(utenteMock,nomeNegozio, PIva));
    }

    @Test
    public void registraUsernameClienteErrato(){
        String email = "@gmail.com"; //Errata
        String username = "depalma1"; // Corretto
        String password = "Giuseppe99."; //Corretto;
        String nome = "Marco";   //Corretto
        String cognome="De Palma";    //Corretto
        String cellulare = "3331122333"; //Corretto
        String DataDiNascita = "2000-01-01"; //Corretto
        String indirizzo = "via Napoli"; //Corretto
        String PIva = "AA1231328";
        String nomeNegozio="negozio";
        when(utenteMock.getEmail()).thenReturn(email);
        when(utenteMock.getPassword()).thenReturn(password);
        when(utenteMock.getUsername()).thenReturn(username);
        when(utenteMock.getNome()).thenReturn(nome);
        when(utenteMock.getCognome()).thenReturn(cognome);
        when(utenteMock.getCellulare()).thenReturn(cellulare);
        when(utenteMock.getDdn()).thenReturn(DataDiNascita);
        when(utenteMock.getIndirizzo()).thenReturn(indirizzo);
        assertEquals(null,regService.RegistrazioneVenditore(utenteMock,nomeNegozio, PIva));    }

    @Test
    public void registraPasswordClienteErrato(){
        String email = "@gmail.com"; //Errata
        String username = "depalma1"; // Corretto
        String password = "Giuseppe99."; //Corretto;
        String nome = "Marco";   //Corretto
        String cognome="De Palma";    //Corretto
        String cellulare = "3331122333"; //Corretto
        String DataDiNascita = "2000-01-01"; //Corretto
        String indirizzo = "via Napoli"; //Corretto
        String PIva = "AA1231328";
        String nomeNegozio="negozio";
        when(utenteMock.getEmail()).thenReturn(email);
        when(utenteMock.getPassword()).thenReturn(password);
        when(utenteMock.getUsername()).thenReturn(username);
        when(utenteMock.getNome()).thenReturn(nome);
        when(utenteMock.getCognome()).thenReturn(cognome);
        when(utenteMock.getCellulare()).thenReturn(cellulare);
        when(utenteMock.getDdn()).thenReturn(DataDiNascita);
        when(utenteMock.getIndirizzo()).thenReturn(indirizzo);
        assertEquals(null,regService.RegistrazioneVenditore(utenteMock,nomeNegozio, PIva));    }

    @Test
    public void registraNomeClienteErrato(){
        String email = "@gmail.com"; //Errata
        String username = "depalma1"; // Corretto
        String password = "Giuseppe99."; //Corretto;
        String nome = "Marco";   //Corretto
        String cognome="De Palma";    //Corretto
        String cellulare = "3331122333"; //Corretto
        String DataDiNascita = "2000-01-01"; //Corretto
        String indirizzo = "via Napoli"; //Corretto
        String PIva = "AA1231328";
        String nomeNegozio="negozio";
        when(utenteMock.getEmail()).thenReturn(email);
        when(utenteMock.getPassword()).thenReturn(password);
        when(utenteMock.getUsername()).thenReturn(username);
        when(utenteMock.getNome()).thenReturn(nome);
        when(utenteMock.getCognome()).thenReturn(cognome);
        when(utenteMock.getCellulare()).thenReturn(cellulare);
        when(utenteMock.getDdn()).thenReturn(DataDiNascita);
        when(utenteMock.getIndirizzo()).thenReturn(indirizzo);
        assertNotEquals(null,regService.RegistrazioneVenditore(utenteMock,nomeNegozio, PIva));    }
    @Test
    public void registraCognomeClienteErrato(){
        String email = "@gmail.com"; //Errata
        String username = "depalma1"; // Corretto
        String password = "Giuseppe99."; //Corretto;
        String nome = "Marco";   //Corretto
        String cognome="De Palma";    //Corretto
        String cellulare = "3331122333"; //Corretto
        String DataDiNascita = "2000-01-01"; //Corretto
        String indirizzo = "via Napoli"; //Corretto
        String PIva = "AA1231328";
        String nomeNegozio="negozio";
        when(utenteMock.getEmail()).thenReturn(email);
        when(utenteMock.getPassword()).thenReturn(password);
        when(utenteMock.getUsername()).thenReturn(username);
        when(utenteMock.getNome()).thenReturn(nome);
        when(utenteMock.getCognome()).thenReturn(cognome);
        when(utenteMock.getCellulare()).thenReturn(cellulare);
        when(utenteMock.getDdn()).thenReturn(DataDiNascita);
        when(utenteMock.getIndirizzo()).thenReturn(indirizzo);
        assertNotEquals(null,regService.RegistrazioneVenditore(utenteMock,nomeNegozio, PIva));    }

    @Test
    public void registraCellulareClienteErrato(){
        String email = "@gmail.com"; //Errata
        String username = "depalma1"; // Corretto
        String password = "Giuseppe99."; //Corretto;
        String nome = "Marco";   //Corretto
        String cognome="De Palma";    //Corretto
        String cellulare = "3331122333"; //Corretto
        String DataDiNascita = "2000-01-01"; //Corretto
        String indirizzo = "via Napoli"; //Corretto
        String PIva = "AA1231328";
        String nomeNegozio="negozio";
        when(utenteMock.getEmail()).thenReturn(email);
        when(utenteMock.getPassword()).thenReturn(password);
        when(utenteMock.getUsername()).thenReturn(username);
        when(utenteMock.getNome()).thenReturn(nome);
        when(utenteMock.getCognome()).thenReturn(cognome);
        when(utenteMock.getCellulare()).thenReturn(cellulare);
        when(utenteMock.getDdn()).thenReturn(DataDiNascita);
        when(utenteMock.getIndirizzo()).thenReturn(indirizzo);
        assertNotEquals(null,regService.RegistrazioneVenditore(utenteMock,nomeNegozio, PIva));    }

    @Test
    public void registraDataDiNascitaClienteErrato(){
        String email = "@gmail.com"; //Errata
        String username = "depalma1"; // Corretto
        String password = "Giuseppe99."; //Corretto;
        String nome = "Marco";   //Corretto
        String cognome="De Palma";    //Corretto
        String cellulare = "3331122333"; //Corretto
        String DataDiNascita = "2000-01-01"; //Corretto
        String indirizzo = "via Napoli"; //Corretto
        String PIva = "AA1231328";
        String nomeNegozio="negozio";
        when(utenteMock.getEmail()).thenReturn(email);
        when(utenteMock.getPassword()).thenReturn(password);
        when(utenteMock.getUsername()).thenReturn(username);
        when(utenteMock.getNome()).thenReturn(nome);
        when(utenteMock.getCognome()).thenReturn(cognome);
        when(utenteMock.getCellulare()).thenReturn(cellulare);
        when(utenteMock.getDdn()).thenReturn(DataDiNascita);
        when(utenteMock.getIndirizzo()).thenReturn(indirizzo);
        assertNotEquals(null,regService.RegistrazioneVenditore(utenteMock,nomeNegozio, PIva));    }

    @Test
    public void registraIndirizzoClienteErrato(){
        String email = "@gmail.com"; //Errata
        String username = "depalma1"; // Corretto
        String password = "Giuseppe99."; //Corretto;
        String nome = "Marco";   //Corretto
        String cognome="De Palma";    //Corretto
        String cellulare = "3331122333"; //Corretto
        String DataDiNascita = "2000-01-01"; //Corretto
        String indirizzo = "via Napoli"; //Corretto
        String PIva = "AA1231328";
        String nomeNegozio="negozio";
        when(utenteMock.getEmail()).thenReturn(email);
        when(utenteMock.getPassword()).thenReturn(password);
        when(utenteMock.getUsername()).thenReturn(username);
        when(utenteMock.getNome()).thenReturn(nome);
        when(utenteMock.getCognome()).thenReturn(cognome);
        when(utenteMock.getCellulare()).thenReturn(cellulare);
        when(utenteMock.getDdn()).thenReturn(DataDiNascita);
        when(utenteMock.getIndirizzo()).thenReturn(indirizzo);
        assertNotEquals(null,regService.RegistrazioneVenditore(utenteMock,nomeNegozio, PIva));    }

    @Test
    public void registrazioneVenditoreOkTest(){
        String email = "peppe@hotmail.it"; //Corretta
        String username = "depalma1"; // Corretto
        String password = "Peppe2699.";        //Corretto;
        String nome = "peppe";   //Corretto
        String cognome="abbatiello";    //Corretto
        String cellulare = "3219913211"; //Corretto
        String DataDiNascita = "2020-01-01"; //Corretto
        String indirizzo = "viaaa"; //Corretto
        when(utenteMock.getEmail()).thenReturn(email);
        when(utenteMock.getPassword()).thenReturn(password);
        when(utenteMock.getUsername()).thenReturn(username);
        when(utenteMock.getNome()).thenReturn(nome);
        when(utenteMock.getCognome()).thenReturn(cognome);
        when(utenteMock.getCellulare()).thenReturn(cellulare);
        when(utenteMock.getDdn()).thenReturn(DataDiNascita);
        when(utenteMock.getIndirizzo()).thenReturn(indirizzo);
        assertNotEquals(null,regService.RegistrazioneVenditore(utenteMock,"negozio1 ","piva1"));
    }

}
