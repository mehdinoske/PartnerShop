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

@WebServlet("/Registrazione")
public final class RegistrazioneController extends HttpServlet {

    private final RegistrazioneService registrazioneService = new RegistrazioneServiceImp();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher=null;
        if(request.getParameter("id").equals("cliente")) {
           dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registrazioneCliente.jsp");

        }else if(request.getParameter("id").equals("venditore"))
        {
            dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registrazioneVenditore.jsp");
        }
        dispatcher.forward(request, response);
    }

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
        ut.setDataDiNascita(ddn);
        ut.setUsername(username);
        ut.setEmail(email);
        ut.setPassword(password);

        if(request.getParameter("id").equals("cliente"))
            registrazioneService.RegistrazioneCliente(ut);
        else if(request.getParameter("id").equals("venditore"))
            registrazioneService.RegistrazioneVenditore(ut,request.getParameter("nomedelnegozio"),request.getParameter("p.iva"));

        RequestDispatcher disp = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
        disp.forward(request,response);
    }
}
