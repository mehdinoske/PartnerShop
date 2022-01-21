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
        RequestDispatcher requestDispatcher;
        int id;

        switch (s) {
            case "/Prodotto_visualizza":
                id = Integer.parseInt(request.getParameter("id"));
                Prodotto prodotto = PrDAO.getProdottoById(id);
                /*if (prodotto == null) {
                throw new MyServletException("Prodotto non trovato.");
                }*/
                if(prodotto != null) {
                    request.setAttribute("prodotto", prodotto);
                    requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/prodotto.jsp");
                    //System.out.println("visualizzato");
                } else {
                    request.setAttribute("messaggio", "Nessun prodotto trovato.");
                    requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/notifica.jsp");
                    //System.out.println("non trovato");
                }
                requestDispatcher.forward(request, response);
                break;

            case "/Prodotto_modifica": break;
            case "/Prodotto_aggiungi": break;

            case "/Prodotto_rimuovi":
                id = Integer.parseInt(request.getParameter("id"));
                PrDAO.deleteProdottoById(id);
                /*if (prodotto == null) {
                throw new MyServletException("Prodotto non trovato.");
                }*/
                request.setAttribute("messaggio", "Prodotto eliminato con successo.");
                requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/notifica.jsp");
                //System.out.println("rimosso");
                requestDispatcher.forward(request, response);
                break;

        }
    }

}
