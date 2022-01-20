package PartnerShop.segnalazione.controller;


import PartnerShop.model.dao.SegnalazioneDAO;
import PartnerShop.model.entity.Segnalazione;
import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.segnalazione.service.SegnalazioneService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Segnalazione")
public class SegnalazioneController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher=null;
        dispatcher = request.getRequestDispatcher("WEB-INF/jsp/segnalazione.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher=null;
        String motivazione =  request.getParameter("motivazione");
        String commentiAggiuntivi = request.getParameter("commentiAggiuntivi");
        String email =((UtenteRegistrato)request.getSession().getAttribute("utente")).getEmail();
        Segnalazione  segn = new Segnalazione(email,0,motivazione,commentiAggiuntivi);

        dispatcher = request.getRequestDispatcher("WEB-INF/jsp/notificaSegnalazione.jsp");
        dispatcher.forward(request, response);
    }
}
