package UnitTesting.GestioneUtente;

import PartnerShop.Exceptions.MyServletException;
import PartnerShop.GestioneUtenti.service.GestioneUtenteServiceImp;
import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.UtenteRegistrato;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GestioneUtenteServiceImpTest {

    GestioneUtenteServiceImp utService;
    UtenteRegistrato utMock;
    UtenteRegistratoDAO utDBMock;

    @Before
    public void setup(){
        utMock = Mockito.mock(UtenteRegistrato.class);
        utDBMock = Mockito.mock(UtenteRegistratoDAO.class);
        this.utService = new GestioneUtenteServiceImp(utDBMock);
    }

    @Test
    public void modificaNomeNonRispettaIlFormatoTest() {
        String nome = "Giuseppe2";
        String cognome = "Abbatiello";
        String cellulare = "3482697548";
        String indirizzo = "Via Pecereca";
        String password = "@Password1";
        when(utMock.getNome()).thenReturn(nome);
        when(utMock.getCognome()).thenReturn(cognome);
        when(utMock.getCellulare()).thenReturn(cellulare);
        when(utMock.getIndirizzo()).thenReturn(indirizzo);
        when(utMock.getPassword()).thenReturn(password);

        assertThrows(MyServletException.class,()->utService.ModificaDati(utMock));
    }

    @Test
    public void modificaCognomeNonRispettaIlFormatoTest() {
        String nome = "Marco";
        String cognome = "De Palma5";
        String cellulare = "3472589763";
        String indirizzo = "Via Pago Veiano";
        String password = "Hotel36.";
        when(utMock.getNome()).thenReturn(nome);
        when(utMock.getCognome()).thenReturn(cognome);
        when(utMock.getCellulare()).thenReturn(cellulare);
        when(utMock.getIndirizzo()).thenReturn(indirizzo);
        when(utMock.getPassword()).thenReturn(password);

        assertThrows(MyServletException.class,()->utService.ModificaDati(utMock));
    }

    @Test
    public void modificaCellulareNonRispettaIlFormatoTest() {
        String nome = "Mehdi";
        String cognome = "Boudad";
        String cellulare = "333256478";
        String indirizzo = "Bivio Cioffi";
        String password = ".Fastweb99.";
        when(utMock.getNome()).thenReturn(nome);
        when(utMock.getCognome()).thenReturn(cognome);
        when(utMock.getCellulare()).thenReturn(cellulare);
        when(utMock.getIndirizzo()).thenReturn(indirizzo);
        when(utMock.getPassword()).thenReturn(password);

        assertThrows(MyServletException.class,()->utService.ModificaDati(utMock));
    }

    @Test
    public void modificaIndirizzoNonRispettaIlFormatoTest() {
        String nome = "Lorenzo";
        String cognome = "William";
        String cellulare = "3663524789";
        String indirizzo = "Via";
        String password = "@Pollastra9";
        when(utMock.getNome()).thenReturn(nome);
        when(utMock.getCognome()).thenReturn(cognome);
        when(utMock.getCellulare()).thenReturn(cellulare);
        when(utMock.getIndirizzo()).thenReturn(indirizzo);
        when(utMock.getPassword()).thenReturn(password);

        assertThrows(MyServletException.class,()->utService.ModificaDati(utMock));
    }

    @Test
    public void modificaPasswordNonRispettaIlFormatoTest() {
        String nome = "Gianna";
        String cognome = "Segugio";
        String cellulare = "3894856791";
        String indirizzo = "Via Padula";
        String password = "Password1";
        when(utMock.getNome()).thenReturn(nome);
        when(utMock.getCognome()).thenReturn(cognome);
        when(utMock.getCellulare()).thenReturn(cellulare);
        when(utMock.getIndirizzo()).thenReturn(indirizzo);
        when(utMock.getPassword()).thenReturn(password);

        assertThrows(MyServletException.class,()->utService.ModificaDati(utMock));
    }

    @Test
    public void modificaDatiOk() throws MyServletException{
        String nome = "Marco";
        String cognome = "Ancona";
        String cellulare = "3451637097";
        String indirizzo = "Via Strettolone";
        String password = "@Giostra99";
        when(utMock.getNome()).thenReturn(nome);
        when(utMock.getCognome()).thenReturn(cognome);
        when(utMock.getCellulare()).thenReturn(cellulare);
        when(utMock.getIndirizzo()).thenReturn(indirizzo);
        when(utMock.getPassword()).thenReturn(password);

        assertTrue(utService.ModificaDati(utMock));
    }
}
