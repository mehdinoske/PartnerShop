package UnitTesting.GestioneSegnalazione;

import PartnerShop.Exceptions.MyServletException;
import PartnerShop.gestioneProdotto.controller.GestioneProdottoController;
import PartnerShop.gestioneProdotto.service.GestioneProdottoService;
import PartnerShop.model.entity.Amministratore;
import PartnerShop.model.entity.Prodotto;
import PartnerShop.model.entity.Segnalazione;
import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.segnalazione.controller.SegnalazioneController;
import PartnerShop.segnalazione.service.SegnalazioneService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.ServletException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SegnalazioneControllerTest {
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    MockHttpSession session;
    SegnalazioneController sc;
    SegnalazioneService ss;
    Prodotto prodotto;
    UtenteRegistrato utente;
    Amministratore admin;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = new MockHttpSession();
        sc = new SegnalazioneController();
        ss = Mockito.mock(SegnalazioneService.class);
        prodotto = Mockito.mock(Prodotto.class);
        session = new MockHttpSession();
        utente = Mockito.mock(UtenteRegistrato.class);
        admin = Mockito.mock(Amministratore.class);

        request.setSession(session);
    }

    @Test
    public void aggiungiSegnalazioneUtenteNonAutorizzato() {
        UtenteRegistrato ut = new UtenteRegistrato("pinco", "palla", "12-12-1122", "ciaociao", "qazwsx2", "pinco@palla.com", "aaaaa", "222222", 1);
        request.getSession().setAttribute("utente", ut);
        request.setServletPath("/AggiungiSegnalazione");
        MyServletException mse = assertThrows(MyServletException.class, () -> sc.switchPath(request,response,ss));
        assertEquals("Non sei loggato come cliente.", mse.getMessage());
    }

    @Test
    public void aggiungiSegnalazioneUtenteNull() {
        request.getSession().setAttribute("utente", null);
        request.setServletPath("/AggiungiSegnalazione");
        MyServletException mse = assertThrows(MyServletException.class, () -> sc.switchPath(request,response,ss));
        assertEquals("Non sei loggato come cliente.", mse.getMessage());
    }

    @Test
    public void aggiungiSegnalazioneOk() throws ServletException, IOException {
        UtenteRegistrato ut = new UtenteRegistrato("pinco", "palla", "12-12-1122", "ciaociao", "qazwsx2", "pinco@palla.com", "aaaaa", "222222", 0);
        request.getSession().setAttribute("utente", ut);
        request.setServletPath("/AggiungiSegnalazione");
        assertTrue(sc.switchPath(request, response, ss));
    }





    @Test
    public void visualizzaSegnalazioniAdminNull() {
        request.getSession().setAttribute("admin", null);
        request.setServletPath("/VisualizzaSegnalazioni");
        MyServletException mse = assertThrows(MyServletException.class, () -> sc.switchPath(request,response,ss));
        assertEquals("Non sei loggato come admin.", mse.getMessage());
    }

    @Test
    public void visualizzaSegnalazioniNonEsistonoSegnalazioniInSospeso() {
        request.getSession().setAttribute("admin", admin);
        request.setServletPath("/VisualizzaSegnalazioni");
        Mockito.when(ss.visualizzaListaSegnalazioni(0)).thenReturn(null);
        MyServletException mse = assertThrows(MyServletException.class, () -> sc.switchPath(request,response,ss));
        assertEquals("Non ci sono segnalazioni in sospeso.", mse.getMessage());
    }

    @Test
    public void visualizzaSegnalazioniOk() throws ServletException, IOException {
        request.getSession().setAttribute("admin", admin);
        request.setServletPath("/VisualizzaSegnalazioni");
        Mockito.when(ss.visualizzaListaSegnalazioni(0)).thenReturn(Mockito.mock(ArrayList.class));
        assertTrue(sc.switchPath(request, response, ss));
    }




    @Test
    public void visualizzaDettagliSegnAdminNull() {
        request.getSession().setAttribute("admin", null);
        request.setServletPath("/visualizzaDettagliSegn");
        MyServletException mse = assertThrows(MyServletException.class, () -> sc.switchPath(request,response,ss));
        assertEquals("Non sei loggato come admin.", mse.getMessage());
    }

    @Test
    public void visualizzaDettagliSegnIdErrato() {
        request.getSession().setAttribute("admin", admin);
        request.setServletPath("/visualizzaDettagliSegn");
        double id = 13.55;
        request.setParameter("id", String.valueOf(id));
        MyServletException mse = assertThrows(MyServletException.class, () -> sc.switchPath(request,response,ss));
        assertEquals("Id prodotto errato.", mse.getMessage());
    }

    @Test
    public void visualizzaDettagliSegnSegnalazioneNonTrovata() {
        request.getSession().setAttribute("admin", admin);
        request.setServletPath("/visualizzaDettagliSegn");
        int id = 1;
        request.setParameter("id", String.valueOf(id));
        Mockito.when(ss.visualizzaSegnalazione(id)).thenReturn(null);
        MyServletException mse = assertThrows(MyServletException.class, () -> sc.switchPath(request,response,ss));
        assertEquals("Segnalazione non trovata.", mse.getMessage());
    }

    @Test
    public void visualizzaDettagliSegnTuttoOk() throws ServletException, IOException {
        request.getSession().setAttribute("admin", admin);
        request.setServletPath("/visualizzaDettagliSegn");
        int id = 1;
        request.setParameter("id", String.valueOf(id));
        Mockito.when(ss.visualizzaSegnalazione(id)).thenReturn(Mockito.mock(Segnalazione.class));
        assertTrue(sc.switchPath(request, response, ss));
    }





    @Test
    public void chiudiSegnalazioneSegnAdminNull() {
        request.getSession().setAttribute("admin", null);
        request.setServletPath("/chiudiSegnalazione");
        MyServletException mse = assertThrows(MyServletException.class, () -> sc.switchPath(request,response,ss));
        assertEquals("Non sei loggato come admin.", mse.getMessage());
    }

    @Test
    public void chiudiSegnalazioneIdErrato() {
        request.getSession().setAttribute("admin", admin);
        request.setServletPath("/chiudiSegnalazione");
        double id = 13.55;
        request.setParameter("id", String.valueOf(id));
        MyServletException mse = assertThrows(MyServletException.class, () -> sc.switchPath(request,response,ss));
        assertEquals("Id prodotto errato.", mse.getMessage());
    }

    @Test
    public void chiudiSegnalazioneOk() throws ServletException, IOException {
        request.getSession().setAttribute("admin", admin);
        request.setServletPath("/chiudiSegnalazione");
        int id = 1;
        request.setParameter("id", String.valueOf(id));
        assertTrue(sc.switchPath(request, response, ss));
    }






    @Test
    public void aggiungiSegnalazioneUtenteNNull() {
        request.getSession().setAttribute("utente", null);
        request.setServletPath("/AggiungiSegnalazione");
        MyServletException mse = assertThrows(MyServletException.class, () -> sc.aggiungiSegnalazione(request,response,ss));
        assertEquals("Non sei loggato come cliente.", mse.getMessage());
    }

    @Test
    public void aggiungiSegnalazioneUtenteNonAutorizzato2() {
        UtenteRegistrato ut = new UtenteRegistrato("pinco", "palla", "12-12-1122", "ciaociao", "qazwsx2", "pinco@palla.com", "aaaaa", "222222", 1);
        request.getSession().setAttribute("utente", ut);
        request.setServletPath("/AggiungiSegnalazione");
        MyServletException mse = assertThrows(MyServletException.class, () -> sc.aggiungiSegnalazione(request,response,ss));
        assertEquals("Non sei loggato come cliente.", mse.getMessage());
    }

    @Test
    public void aggiungiSegnalazioneTuttoOk() throws ServletException, IOException {
        UtenteRegistrato ut = new UtenteRegistrato("pinco", "palla", "12-12-1122", "ciaociao", "qazwsx2", "pinco@palla.com", "aaaaa", "222222", 0);
        request.getSession().setAttribute("utente", ut);
        request.setServletPath("/AggiungiSegnalazione");
        request.setParameter("motivazione", "Ciao");
        request.setParameter("commentiAggiuntivi", "Ciao");
        assertTrue(sc.aggiungiSegnalazione(request, response, ss));
    }







    @Test
    public void aggiungiSegnalazioneServletPathErrata() throws ServletException, IOException {
        UtenteRegistrato ut = new UtenteRegistrato("pinco", "palla", "12-12-1122", "ciaociao", "qazwsx2", "pinco@palla.com", "aaaaa", "222222", 0);
        request.getSession().setAttribute("utente", ut);
        request.setServletPath("pippo");
        assertFalse(sc.aggiungiSegnalazione(request,response,ss));
    }

    @Test
    public void switchPathServletPathErrata() throws ServletException, IOException {
        UtenteRegistrato ut = new UtenteRegistrato("pinco", "palla", "12-12-1122", "ciaociao", "qazwsx2", "pinco@palla.com", "aaaaa", "222222", 0);
        request.getSession().setAttribute("utente", ut);
        request.setServletPath("pippo");
        assertFalse(sc.switchPath(request,response,ss));
    }
}