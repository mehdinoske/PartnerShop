package PartnerShop.utils;



import PartnerShop.gestioneProdotto.service.GestioneProdottoService;
import PartnerShop.gestioneProdotto.service.GestioneProdottoServiceImp;
import PartnerShop.model.dao.GestioneAcquistiDAO;
import PartnerShop.model.dao.GestioneProdottoDAO;
import PartnerShop.model.entity.Prodotto;
import PartnerShop.model.entity.UtenteRegistrato;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "")
public class HomePageServlet extends HttpServlet {

    private final GestioneProdottoService PrDAO = new GestioneProdottoServiceImp();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UtenteRegistrato ut = (UtenteRegistrato) request.getSession().getAttribute("utente");
        ArrayList<Prodotto> prodotti;

        if(ut != null && ut.getTipo() == 1) {
            prodotti = PrDAO.getProdottoByVenditore(ut.getEmail());
        } else {
            prodotti = PrDAO.getAllProdotti();
        }
        getServletContext().setAttribute("prodotti",prodotti);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
        dispatcher.forward(request, response);
    }
    public void destroy() {
    }
}