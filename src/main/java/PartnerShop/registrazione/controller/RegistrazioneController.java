package PartnerShop.registrazione.controller;

import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.registrazione.service.RegistrazioneService;
import PartnerShop.registrazione.service.RegistrazioneServiceImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.channels.Channels;

/**
 * implementa il controller che si occupa  del sottosistema registrazione
 * @author Giuseppe Abbatiello
 */
@WebServlet(urlPatterns = {"/Registrazione","/usernameAjax","/emailAjax"})
public final class RegistrazioneController extends HttpServlet {

    private final RegistrazioneService registrazioneService = new RegistrazioneServiceImp();

    /**
     * metodo che reindirizza un cliente o un venditore alla propria pagine per la registrazione
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getServletPath();
        switch (s){
            case "/Registrazione":
                RequestDispatcher dispatcher=null;
                if(request.getParameter("id").equals("cliente")) {
                    dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registrazioneCliente.jsp");

                }else if(request.getParameter("id").equals("venditore"))
                {
                    dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registrazioneVenditore.jsp");
                }
                dispatcher.forward(request, response);
                break;
            case "/usernameAjax":
                String user = request.getParameter("username");
                response.setContentType("text/xml");
                if (user != null && user.length() >= 6 && user.matches("^[0-9a-zA-z]+$") && registrazioneService.verificaUsernameEmail(user,0) == null) {
                    response.getWriter().append("<ok/>");
                } else {
                    response.getWriter().append("<no/>");
                }

                break;
            case "/emailAjax":
                String email = request.getParameter("email");
                response.setContentType("text/xml");
                if (email != null && email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$") && registrazioneService.verificaUsernameEmail(email,1) == null) {
                    response.getWriter().append("<ok/>");
                } else {
                    response.getWriter().append("<no/>");
                }
                break;
        }

    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String ddn = request.getParameter("ddn");
        String indirizzo = request.getParameter("indirizzo");
        String cellulare = request.getParameter("cellulare");

        UtenteRegistrato ut = new UtenteRegistrato();
        ut.setNome(nome);
        ut.setCognome(cognome);
        ut.setDdn(ddn);
        ut.setUsername(username);
        ut.setIndirizzo(indirizzo);
        ut.setEmail(email);
        ut.setPassword(password);
        ut.setPasswordHash(password);
        ut.setCellulare(cellulare);


        if(request.getParameter("id").equals("cliente")){
            registrazioneService.RegistrazioneCliente(ut);
            request.getSession().setAttribute("utente", ut);
        }

        else if(request.getParameter("id").equals("venditore")) {
            registrazioneService.RegistrazioneVenditore(ut, request.getParameter("nomedelnegozio"), request.getParameter("p.iva"));
            request.getSession().setAttribute("utente", ut);
        }
        RequestDispatcher disp = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
        disp.forward(request,response);
    }
}
