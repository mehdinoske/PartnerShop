package PartnerShop.GestioneAcquisti.controller;

import PartnerShop.GestioneAcquisti.service.GestioneAcquistiImp;
import PartnerShop.model.dao.GestioneAcquistiDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Carrello")
public class GestioneAcquistiController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getServletPath().equals("Carrello")){
            GestioneAcquistiImp geA = new GestioneAcquistiImp();
            req = geA.aggiungiRimuoviCarrello(req);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/carrello.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
