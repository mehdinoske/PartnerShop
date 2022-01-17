package PartnerShop.autenticazione;

import PartnerShop.model.entity.UtenteRegistrato;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class LoginController {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("usernameLogin");
        String password = request.getParameter("passwordLogin");
        UtenteRegistrato ut = null;
        if (username != null && password != null) {
            //ut = utDB.doRetrieveByUsernamePass(username, password);
        }
        if (ut == null) {
            throw new Exception("Errore utente null");
        }
        request.getSession().setAttribute("utente", ut);
        String dest = request.getHeader("referer");
        if (dest == null || dest.contains("/Login") || dest.trim().isEmpty()) {
            dest = ".";
        }

        response.sendRedirect(dest);
    }
}
