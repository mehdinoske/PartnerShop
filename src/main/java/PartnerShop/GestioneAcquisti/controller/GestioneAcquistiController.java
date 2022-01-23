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
import java.util.Iterator;

@WebServlet(urlPatterns = {"/Carrello" , "/Acquista" , "/Ordini"})
public class GestioneAcquistiController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getServletPath();
        RequestDispatcher dispatcher;
        UtenteRegistrato ut = new UtenteRegistrato();
        Carrello car = new Carrello();
        GestioneAcquistiImp imp = new GestioneAcquistiImp();
        switch (s){
            case "/Carrello":
                HttpSession session = request.getSession();
                ut = (UtenteRegistrato) request.getSession().getAttribute("utente");
                car = (Carrello)session.getAttribute("Carrello");
                if (car == null) {
                    car = new Carrello();
                    session.setAttribute("Carrello", car);
                }
                String prodottoIdStr = request.getParameter("idProdotto");
                String quantStr = request.getParameter("quant");
                String setQuantStr = request.getParameter("setQuant");
                //GestioneAcquistiImp imp = new GestioneAcquistiImp();
                imp.aggiungiAlCarrello(car,ut,prodottoIdStr,quantStr,setQuantStr);
                if(setQuantStr!= null && Integer.parseInt(setQuantStr)<=0)
                    imp.rimuovidalcarrello(ut,car,Integer.parseInt(prodottoIdStr),setQuantStr);
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/carrello.jsp");
                dispatcher.forward(request, response);
                break;
            case "/Acquista":
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/completaAcquisto.jsp");
                dispatcher.forward(request, response);
                break;
            case "/Ordini":
                ut = (UtenteRegistrato) request.getSession().getAttribute("utente");
                if (ut != null) {
                    car = (Carrello)request.getSession().getAttribute("Carrello");
                    String indirizzo = request.getParameter("indirizzo");
                    String cardc = request.getParameter("cartadc");
                    imp.acquistaProdotto(ut,car,indirizzo,cardc);
                    request.getSession().removeAttribute("Carrello");
                    request.getRequestDispatcher("/WEB-INF/jsp/ordineEffettuato.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/WEB-INF/jsp/registrazioneCliente.jsp").forward(request, response);
                }
                break;
            case "/OrdiniVenditore":
                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
