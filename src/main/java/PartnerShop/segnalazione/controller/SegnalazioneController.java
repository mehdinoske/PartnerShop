package PartnerShop.segnalazione.controller;


import PartnerShop.model.dao.SegnalazioneDAO;
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

@WebServlet(urlPatterns = {"/AggiungiSegnalazione","/VisualizzaSegnalazioni","/visualizzaDettagliSegn","/chiudiSegnalazione"})
public class SegnalazioneController extends HttpServlet {

    private SegnalazioneService segnalazioneService = new SegnalazioneServiceImp();
    private ArrayList<Segnalazione> listSegnalazioni;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getServletPath();
        RequestDispatcher dispatcher = null;
        switch (s) {

            case "/AggiungiSegnalazione": {

                dispatcher = request.getRequestDispatcher("WEB-INF/jsp/aggiungiSegnalazione.jsp");
            }
            break;
            case "/VisualizzaSegnalazioni": {

              listSegnalazioni = segnalazioneService.visualizzaListaSegnalazioni(0);
                request.getSession().setAttribute("segnalazioni", listSegnalazioni);
                dispatcher = request.getRequestDispatcher("WEB-INF/jsp/listaSegnalazioni.jsp");
            }
            break;
            case "/visualizzaDettagliSegn": {
                int id = Integer.valueOf(request.getParameter("id"));
                Segnalazione segn = null;
                segn =  segnalazioneService.visualizzaSegnalazione(id);
                request.getSession().setAttribute("segnalazione", segn);
                dispatcher = request.getRequestDispatcher("WEB-INF/jsp/segnalazione.jsp");
            }
            break;
            case "/chiudiSegnalazione": {
                int id = Integer.parseInt(request.getParameter("id"));
                segnalazioneService.chiudiSegnalazione(id);
                dispatcher = request.getRequestDispatcher("/VisualizzaSegnalazioni");
                }break;

            }
          dispatcher.forward(request, response);
        }
        public void doPost (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
            String s = request.getServletPath();
            RequestDispatcher dispatcher = null;
            switch (s) {
                case "/AggiungiSegnalazione": {
                    String motivazione = request.getParameter("motivazione");
                    String commentiAggiuntivi = request.getParameter("commentiAggiuntivi");
                    String email = ((UtenteRegistrato) request.getSession().getAttribute("utente")).getEmail();
                    Segnalazione segn = new Segnalazione(email, 0, motivazione, commentiAggiuntivi);
                    segnalazioneService.aggiungiSegnalazione(segn);
                    dispatcher = request.getRequestDispatcher("WEB-INF/jsp/notificaSegnalazione.jsp");
                }
                break;

            }

            dispatcher.forward(request, response);
        }

}
