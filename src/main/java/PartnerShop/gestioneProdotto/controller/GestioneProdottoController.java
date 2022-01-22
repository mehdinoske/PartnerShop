package PartnerShop.gestioneProdotto.controller;

import PartnerShop.gestioneProdotto.service.GestioneProdottoService;
import PartnerShop.gestioneProdotto.service.GestioneProdottoServiceImp;
import PartnerShop.model.entity.Amministratore;
import PartnerShop.model.entity.Prodotto;
import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.utils.MyServletException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/prodotto-visualizza", "/prodotto-modifica-form", "/prodotto-aggiungi", "/prodotto-rimuovi", "/prodotto-modifica", "/prodotto-aggiungi-form"})
public class GestioneProdottoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final GestioneProdottoService PrDAO = new GestioneProdottoServiceImp();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String s = request.getServletPath();
        RequestDispatcher requestDispatcher;
        Prodotto prodotto;
        UtenteRegistrato ut;
        Amministratore adm;
        int id;
        System.out.println(s);
        switch (s) {

            case "/prodotto-visualizza":
                id = Integer.parseInt(request.getParameter("id"));
                prodotto = PrDAO.getProdottoById(id);
                (prodotto == null) {
                throw new MyServletException("Prodotto non trovato.");
                }
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
                ut = (UtenteRegistrato) request.getSession().getAttribute("utente");
                if(ut != null && ut.getTipo() == 1) {
                    String nome = request.getParameter("nome");
                    String descrizione = request.getParameter("descrizione");
                    String categoria = request.getParameter("categoria");
                    System.out.println(request.getParameter("descrizione"));
                    long prezzo_Cent = Long.parseLong(request.getParameter("prezzo_Cent"));
                    int disponibilita = Integer.parseInt(request.getParameter("disponibilita"));
                    prodotto = new Prodotto();
                    prodotto.setNome(nome);
                    prodotto.setDisponibilita(disponibilita);
                    prodotto.setCategoria(categoria);
                    prodotto.setDescrizione(descrizione);
                    prodotto.setPrezzo_Cent(prezzo_Cent);
                    PrDAO.doSaveProdotto(prodotto);
                    request.setAttribute("messaggio", "Prodotto aggiornato con successo.");
                    requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/notifica.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    throw new MyServletException("Non hai i permessi necessari.");
                }
                break;

            case "/prodotto-aggiungi-form":
                ut = (UtenteRegistrato) request.getSession().getAttribute("utente");
                if(ut.getTipo() == 1) {
                    requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/aggiungiProdotto.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    throw new MyServletException("Non hai i permessi necessari.");
                }
                break;

            case "/prodotto-rimuovi":
                ut = (UtenteRegistrato) request.getSession().getAttribute("utente");
                adm = (Amministratore) request.getSession().getAttribute("admin");
                if((ut != null && ut.getTipo() == 1) || adm != null) {
                    try {
                        id = Integer.parseInt(request.getParameter("id"));
                    } catch (NumberFormatException e) {
                        throw new MyServletException("Id prodotto non corretto.");
                    }
                    try {
                        PrDAO.deleteProdottoById(id);
                    } catch(Exception e) {
                        throw new MyServletException("Prodotto non trovato.");
                    }
                } else {
                    throw new MyServletException("Non hai i permessi necessari.");
                }
                request.setAttribute("messaggio", "Prodotto eliminato con successo.");
                requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/notifica.jsp");
                requestDispatcher.forward(request, response);
                break;

            case "/prodotto-modifica":
                id = Integer.parseInt(request.getParameter("id"));
                String nome = request.getParameter("nome");
                String descrizione = request.getParameter("descrizione");
                String categoria = request.getParameter("categoria");
                System.out.println(request.getParameter("descrizione"));
                long prezzo_Cent = Long.parseLong(request.getParameter("prezzo_Cent"));
                int disponibilita = Integer.parseInt(request.getParameter("disponibilita"));
                prodotto = new Prodotto();
                prodotto.setId(id);
                prodotto.setNome(nome);
                prodotto.setDisponibilita(disponibilita);
                prodotto.setCategoria(categoria);
                prodotto.setDescrizione(descrizione);
                prodotto.setPrezzo_Cent(prezzo_Cent);
                PrDAO.doUpdateProdotto(prodotto);
                request.setAttribute("messaggio", "Prodotto aggiornato con successo.");
                requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/notifica.jsp");
                requestDispatcher.forward(request, response);
                break;
        }


    }
}
