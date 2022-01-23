package PartnerShop.utils;



import PartnerShop.model.dao.GestioneAcquistiDAO;
import PartnerShop.model.dao.GestioneProdottoDAO;
import PartnerShop.model.entity.Prodotto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "HomePageServlet", urlPatterns = "", loadOnStartup = 1)
public class HomePageServlet extends HttpServlet {


    public void init() throws ServletException{
        GestioneAcquistiDAO gp = new GestioneAcquistiDAO();
        ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
        prodotti = gp.doRetrieveAllProdotti();
        getServletContext().setAttribute("prodotti",prodotti);
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* GestioneAcquistiDAO gp = new GestioneAcquistiDAO();
        ArrayList<Prodotto> prodotti = gp.doRetrieveAllProdotti();
        request.setAttribute("prodotti",prodotti);*/
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
        dispatcher.forward(request, response);
    }
    public void destroy() {
    }
}