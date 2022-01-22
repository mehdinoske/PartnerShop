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

@WebServlet(urlPatterns = {"/prodotto-visualizza", "/prodotto-modifica-form", "/prodotto-aggiungi", "/prodotto-rimuovi", "/prodotto-modifica"})
public class GestioneProdottoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final GestioneProdottoService PrDAO = new GestioneProdottoServiceImp();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String s = request.getServletPath();
        RequestDispatcher requestDispatcher;
        Prodotto prodotto;
        int id;
        System.out.println(s);
        switch (s) {

            case "/prodotto-visualizza":
                id = Integer.parseInt(request.getParameter("id"));
                prodotto = PrDAO.getProdottoById(id);
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

            case "/prodotto-modifica-form":
                id = Integer.parseInt(request.getParameter("id"));
                prodotto = PrDAO.getProdottoById(id);
                if(prodotto != null) {
                    request.setAttribute("prodotto", prodotto);
                    requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/modificaProdotto.jsp");
                    requestDispatcher.forward(request, response);
                }
                break;

            case "/prodotto-aggiungi":
                break;

            case "/prodotto-rimuovi":
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

            case "/prodotto-modifica":
                id = Integer.parseInt(request.getParameter("id"));
                String nome = request.getParameter("nome");
                String descrizione = request.getParameter("descrizione");
                String categoria = request.getParameter("categoria");
                Long prezzo_Cent = Long.parseLong(request.getParameter("prezzo_Cent"));
                int disponibilita = Integer.parseInt(request.getParameter("disponibilita"));
                Prodotto prodotto1 = new Prodotto();
                prodotto1.setId(id);
                prodotto1.setNome(nome);
                prodotto1.setDisponibilita(disponibilita);
                prodotto1.setCategoria(categoria);
                prodotto1.setDescrizione(descrizione);
                prodotto1.setPrezzo_Cent(prezzo_Cent);
                PrDAO.doUpdateProdotto(prodotto1);
                request.setAttribute("messaggio", "Prodotto aggiornato con successo.");
                requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/notifica.jsp");
                requestDispatcher.forward(request, response);
                break;
        }


    }
}
