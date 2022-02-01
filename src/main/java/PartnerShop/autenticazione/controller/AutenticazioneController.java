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


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String username = request.getParameter("usernameLogin");
        String password = request.getParameter("passwordLogin");

        UtenteRegistrato ut =autenticazioneService.login(username,password);
        if(ut ==null){
            amm= autenticazioneService.verificaAdmin(username,password);
            request.getSession().setAttribute("admin", amm);
            int mes = 1;
            if(amm==null)
            request.getSession().setAttribute("mes",mes);
        }else{
            request.getSession().removeAttribute("mes");
        }
        request.getSession().setAttribute("utente", ut);
        response.sendRedirect(".");
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException  {

        autenticazioneService.logout(request.getSession());
        response.sendRedirect(".");
    }
}
