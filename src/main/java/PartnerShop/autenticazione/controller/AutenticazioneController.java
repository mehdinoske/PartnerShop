package PartnerShop.autenticazione.controller;

import PartnerShop.autenticazione.service.AutenticazioneService;
import PartnerShop.autenticazione.service.AutenticazioneServiceImp;
import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.Amministratore;
import PartnerShop.model.entity.UtenteRegistrato;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * implementa il controller che si occupa  del sottosistema autenticazione
 * @author Giuseppe Abbatiello
 */
@WebServlet("/Autenticazione")
public final class AutenticazioneController extends HttpServlet {
    private Amministratore amm;
    private final AutenticazioneService autenticazioneService = new AutenticazioneServiceImp();
    /**
     * metodo doPost che si occupa di recuperare nella rischiesta l'username e la password dell'utente
     * e di richiamare le funzionalità del login
     * @param request
     * @param response
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String username = request.getParameter("usernameLogin");
        String password = request.getParameter("passwordLogin");

        UtenteRegistrato ut =autenticazioneService.login(username,password);
        if(ut ==null){
            amm= autenticazioneService.verificaAdmin(username,password);
            request.getSession().setAttribute("admin", amm);
            int mes = 1;
            request.getSession().setAttribute("mes",mes);
        }else{
            request.getSession().removeAttribute("mes");
        }
        request.getSession().setAttribute("utente", ut);
        response.sendRedirect(".");
    }

    /**@
     * metodo do get che implementa le funzionalità di logout
     * @param request
     * @param response
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException  {
        request.getSession().removeAttribute("utente");
        request.getSession().removeAttribute("Carrello");
        request.getSession().removeAttribute("ordini");
        request.getSession().removeAttribute("mes");
        if(amm!=null)
        {
             request.getSession().removeAttribute("admin");
        }
        response.sendRedirect(".");
    }
}
