package PartnerShop.registrazione.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Registrazione")
public class RegistrazioneController extends HttpServlet {

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
}
