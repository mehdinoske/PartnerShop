package PartnerShop.segnalazione.controller;


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
}
