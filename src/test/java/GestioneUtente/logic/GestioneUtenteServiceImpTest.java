package GestioneUtente.logic;

import PartnerShop.GestioneUtenti.model.GestioneUtenteServiceImp;
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
        this.utService = new GestioneUtenteServiceImp();
    }

    @Test
    public void modificaNomeNonRispettaIlFormatoTest(){
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

        assertFalse(utService.ModificaDati(utMock));
    }
}
