package PartnerShop.gestioneProdotto.controller;

import PartnerShop.gestioneProdotto.service.GestioneProdottoService;
import PartnerShop.gestioneProdotto.service.GestioneProdottoServiceImp;
import PartnerShop.model.entity.Prodotto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/Prodotto_visualizza", "/Prodotto_modifica", "/Prodotto_aggiungi", "/Prodotto_rimuovi"})
public class GestioneProdottoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final GestioneProdottoService PrDAO = new GestioneProdottoServiceImp();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String s = request.getServletPath();

        switch (s) {
            case "/Prodotto_visualizza":
                {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Prodotto prodotto = PrDAO.getProdottoById(id);
                    /*if (prodotto == null) {
                    throw new MyServletException("Prodotto non trovato.");
                    }*/
                    request.setAttribute("prodotto", prodotto);

                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/prodotto.jsp");
                    requestDispatcher.forward(request, response);
                }
            case "/Prodotto_modifica":   ;
            case "/Prodotto_aggiungi":   ;
            case "/Prodotto_Rimuovi":   ;
        }
    }

}
