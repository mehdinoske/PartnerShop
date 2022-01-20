package PartnerShop.autenticazione.controller;

import PartnerShop.autenticazione.service.AutenticazioneService;
import PartnerShop.autenticazione.service.AutenticazioneServiceImp;
import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.UtenteRegistrato;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Autenticazione")
public final class AutenticazioneController extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        String username = request.getParameter("usernameLogin");
        String password = request.getParameter("passwordLogin");
        AutenticazioneService autenticazioneService = new AutenticazioneServiceImp();
        UtenteRegistrato ut =autenticazioneService.login(username,password);
        request.getSession().setAttribute("utente", ut);
        String dest = request.getHeader("referer");
        if (dest == null || dest.contains("/Login") || dest.trim().isEmpty()) {
            dest = ".";
        }

        response.sendRedirect(dest);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException  {
        request.getSession().removeAttribute("utente");
       // request.getSession().removeAttribute("carrello");
        String dest = request.getHeader("referer");
        if (dest == null || dest.contains("/Login") || dest.trim().isEmpty() || dest.contains("/visualizzaOrdini")) {
            dest = ".";
        }
        response.sendRedirect(dest);
    }
}
