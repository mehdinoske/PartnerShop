package PartnerShop.GestioneAcquisti.controller;

import PartnerShop.GestioneAcquisti.service.GestioneAcquistiImp;
import PartnerShop.model.dao.CarrelloDAO;
import PartnerShop.model.dao.GestioneAcquistiDAO;
import PartnerShop.model.entity.Carrello;
import PartnerShop.model.entity.Prodotto;
import PartnerShop.model.entity.UtenteRegistrato;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Carrello")
public class GestioneAcquistiController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarrelloDAO carDB = new CarrelloDAO();
        HttpSession session = request.getSession();
        UtenteRegistrato ut = (UtenteRegistrato) request.getSession().getAttribute("utente");
        Carrello car = (Carrello)session.getAttribute("Carrello");
        if (car == null) {
            car = new Carrello();
            session.setAttribute("Carrello", car);
        }
        String prodottoIdStr = request.getParameter("idProdotto");
        String quantStr = request.getParameter("quant");
        String setQuantStr = request.getParameter("setQuant");
        GestioneAcquistiImp imp = new GestioneAcquistiImp();
        imp.aggiungiRimuoviCarrello(car,ut,prodottoIdStr,quantStr,setQuantStr);
        if(setQuantStr!= null && Integer.parseInt(setQuantStr)<=0)
            imp.rimuovidalcarrello(ut,car,Integer.parseInt(prodottoIdStr),setQuantStr);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/carrello.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
