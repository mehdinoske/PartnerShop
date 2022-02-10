package PartnerShop.autenticazione.controller;

import PartnerShop.GestioneUtenti.service.GestioneUtenteService;
import PartnerShop.GestioneUtenti.service.GestioneUtenteServiceImp;
import PartnerShop.autenticazione.service.*;


import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.Amministratore;
import PartnerShop.model.entity.Cliente;
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
public  class AutenticazioneController extends HttpServlet {
    private Amministratore amm;
    private  AutenticazioneService autenticazioneService = new AutenticazioneServiceImp();
    private GestioneUtenteService gestioneUtenteService = new GestioneUtenteServiceImp();

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
            else
                request.getSession().removeAttribute("mes");
        }else{
            request.getSession().removeAttribute("mes");
        }
        if(ut!=null && ut.getTipo()==0){
            Cliente cl = new Cliente();
            cl.setEmail(ut.getEmail());
            cl.setListaDesideri(gestioneUtenteService.getListaDesideri(ut.getEmail()));
            request.getSession().setAttribute("cliente",cl);
        }

        request.getSession().setAttribute("utente", ut);
        response.sendRedirect(".");
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException  {

        autenticazioneService.logout(request.getSession());
        response.sendRedirect(".");
    }



}
