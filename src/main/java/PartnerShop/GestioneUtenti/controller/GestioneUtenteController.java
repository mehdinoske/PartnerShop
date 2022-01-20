package PartnerShop.GestioneUtenti.controller;

import PartnerShop.model.dao.UtenteRegistratoDAO;
import PartnerShop.model.entity.UtenteRegistrato;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/VisualizzaDatiUtente", "/ModificaDatiUtenti", "/CancellaDatiUtenti"})
public class GestioneUtenteController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String s = req.getServletPath();
        if(s.equals("/VisualizzaDatiUtente")){
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/visualizzaDatiUtente.jsp");
            dispatcher.forward(req, resp);
        }else if(s.equals("/ModificaDatiUtenti")){
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/modificaDatiUtente.jsp");
            dispatcher.forward(req, resp);
        }else if(s.equals("/CancellaDatiUtenti")){

            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/modificaDatiUtente.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
