package PartnerShop.segnalazione.controller;


import PartnerShop.Exceptions.MyServletException;
import PartnerShop.gestioneProdotto.service.GestioneProdottoService;
import PartnerShop.model.dao.SegnalazioneDAO;
import PartnerShop.model.entity.Amministratore;
import PartnerShop.model.entity.Segnalazione;
import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.segnalazione.service.SegnalazioneService;
import PartnerShop.segnalazione.service.SegnalazioneServiceImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Questa classe implemmenta il controller che si occupa del sottosistema gestione segnalazione
 * @see HttpServlet fornisce l'interfaccia per creare una servlet
 * @version 1.0
 * @author Marco De Palma, Giuseppe Abbatiello
 */
@WebServlet(urlPatterns = {"/AggiungiSegnalazione","/VisualizzaSegnalazioni","/visualizzaDettagliSegn","/chiudiSegnalazione"})
public class SegnalazioneController extends HttpServlet {

    private final SegnalazioneService segnalazioneService = new SegnalazioneServiceImp();

    /**
     * Il metodo ereditato dalla classe HttpServlet che esplicita i parametri della request e permette di usare il metodo switchPath
     * @param request Oggetto della servlet, che contiene i parametri inviati e la sessione corrente
     * @param response Oggetto della servlet, che contiene i parametri della risposta
     * @throws ServletException Un'eccezione lanciata quando si verifica un errore nella servlet
     * @throws IOException Un'eccezione lanciata quando si verifica un errore I/O
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switchPath(request,response, segnalazioneService);
    }

    /**
     * Il metodo ereditato dalla classe HttpServlet che non esplicita i parametri della request e permette di usare il metodo aggiungiSegnalazione
     * @param request Oggetto della servlet, che contiene i parametri inviati e la sessione corrente
     * @param response Oggetto della servlet, che contiene i parametri della risposta
     * @throws ServletException Un'eccezione lanciata quando si verifica un errore nella servlet
     * @throws IOException Un'eccezione lanciata quando si verifica un errore I/O
     */
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        aggiungiSegnalazione(request,response, segnalazioneService);
    }

    /**
     * Il metodo che seleziona in base al ServletPath quale istruzioni eseguire
     * @param request Oggetto della servlet, che contiene i parametri inviati e la sessione corrente
     * @param response Oggetto della servlet, che contiene i parametri della risposta
     * @throws ServletException Un'eccezione lanciata quando si verifica un errore nella servlet
     * @throws IOException Un'eccezione lanciata quando si verifica un errore I/O
     */
    public boolean switchPath(HttpServletRequest request, HttpServletResponse response, SegnalazioneService ss) throws ServletException, IOException {
        String s = request.getServletPath();
        UtenteRegistrato ut = (UtenteRegistrato) request.getSession().getAttribute("utente");
        Amministratore admin = (Amministratore) request.getSession().getAttribute("admin");
        switch (s) {

            case "/AggiungiSegnalazione": {
                if(ut == null ||  ut.getTipo() != 0)
                    throw new MyServletException("Non sei loggato come cliente.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/aggiungiSegnalazione.jsp");
                dispatcher.forward(request, response);
            }
            return true;
            case "/VisualizzaSegnalazioni": {
                if(admin == null)
                    throw new MyServletException("Non sei loggato come admin.");
                ArrayList<Segnalazione> listSegnalazioni = ss.visualizzaListaSegnalazioni(0);
                if(listSegnalazioni == null)
                    throw new MyServletException("Non ci sono segnalazioni in sospeso.");
                request.getSession().setAttribute("segnalazioni", listSegnalazioni);
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/listaSegnalazioni.jsp");
                dispatcher.forward(request, response);
            }
            return true;
            case "/visualizzaDettagliSegn": {
                if (admin == null)
                    throw new MyServletException("Non sei loggato come admin.");
                int id;
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                } catch (Exception e) {
                    throw new MyServletException("Id prodotto errato.");
                }
                Segnalazione segn;
                segn = ss.visualizzaSegnalazione(id);
                if (segn == null)
                    throw new MyServletException("Segnalazione non trovata.");
                request.getSession().setAttribute("segnalazione", segn);
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/segnalazione.jsp");
                dispatcher.forward(request, response);
            }
            return true;
            case "/chiudiSegnalazione": {
                if(admin == null)
                    throw new MyServletException("Non sei loggato come admin.");
                int id;
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                } catch (Exception e) {
                    throw new MyServletException("Id prodotto errato.");
                }
                ss.chiudiSegnalazione(id);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/VisualizzaSegnalazioni");
                dispatcher.forward(request, response);
            }
            return true;
        }
        return false;
    }

    /**
     * Il metodo che permette l'aggiunta di una segnalazione
     * @param request Oggetto della servlet, che contiene i parametri inviati e la sessione corrente
     * @param response Oggetto della servlet, che contiene i parametri della risposta
     * @throws ServletException Un'eccezione lanciata quando si verifica un errore nella servlet
     * @throws IOException Un'eccezione lanciata quando si verifica un errore I/O
     */
    public boolean aggiungiSegnalazione(HttpServletRequest request, HttpServletResponse response, SegnalazioneService ss) throws ServletException, IOException {
        String s = request.getServletPath();
        UtenteRegistrato ut = (UtenteRegistrato) request.getSession().getAttribute("utente");
        if (ut == null || ut.getTipo() != 0)
            throw new MyServletException("Non sei loggato come cliente.");
        if (s.equals("/AggiungiSegnalazione")) {
            String motivazione = request.getParameter("motivazione");
            String commentiAggiuntivi = request.getParameter("commentiAggiuntivi");
            String email = ut.getEmail();
            Segnalazione segn = new Segnalazione(email, 0, motivazione, commentiAggiuntivi);
            if(!ss.aggiungiSegnalazione(segn))
                return false;
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/notificaSegnalazione.jsp");
            dispatcher.forward(request, response);
            return true;
        }
        return false;
    }
}
