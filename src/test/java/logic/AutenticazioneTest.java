package logic;



import PartnerShop.autenticazione.service.AutenticazioneServiceImp;
import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.UtenteRegistrato;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class AutenticazioneTest {
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    MockHttpSession session;
    AutenticazioneServiceImp login;

    UtenteRegistrato utenteMock;
    UtenteRegistratoDAO utDBMock;

  @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = new MockHttpSession();
        utenteMock = Mockito.mock(UtenteRegistrato.class);
        utDBMock= Mockito.mock(UtenteRegistratoDAO.class);
        login = new AutenticazioneServiceImp(utDBMock);
    }

    @Test
    public void loginUtenteUsernameFormatoErratoTest(){
        String username = "depalma1."; //Errato
        String password = "asd";        //Corretta;
        when(utenteMock.getTipo()).thenReturn(2);
        when(utDBMock.doRetrieveByUsernamePass(username,password)).thenReturn(utenteMock);
        assertNull(login.login(username,password));
    }

    @Test
    public void loginUtentePasswordNonAssociataNessunUtente(){
        String username = "depalma1"; //Corretta
        String password = "asd";        //Corretta
        when(utDBMock.doRetrieveByUsernamePass(username,password)).thenReturn(null);
        assertNull(login.login(username,password));
    }


    @Test
    public void loginUtenteOkTest(){
        String username = "peppe2699";
        String password = "asd";
        when(utDBMock.doRetrieveByUsernamePass(username,password)).thenReturn(utenteMock);
        when(utenteMock.getTipo()).thenReturn(2);
        assertNotNull(login.login(username,password));
    }


}
