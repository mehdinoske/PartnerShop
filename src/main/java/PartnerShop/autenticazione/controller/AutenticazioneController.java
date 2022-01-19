package PartnerShop.autenticazione.controller;

import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.UtenteRegistrato;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Autenticazione")
public class AutenticazioneController extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        String username = request.getParameter("usernameLogin");
        String password = request.getParameter("passwordLogin");
        UtenteRegistrato ut = null;
        UtenteRegistratoDAO utDB = new UtenteRegistratoDAO();
        if (username != null && password != null) {
            ut = utDB.doRetrieveByUsernamePass(username, password);
        }
        if (ut == null) {
            throw new IOException("Errore utente null");
        }
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
