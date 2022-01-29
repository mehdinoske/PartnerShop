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
import java.util.ArrayList;

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
        String nome, descrizione, categoria;
        long prezzo_Cent;
        int disponibilita, id;

        switch (s) {

            case "/prodotto-visualizza":
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                } catch (NumberFormatException e) {
                    throw new MyServletException("Id prodotto non corretto.");
                }
                prodotto = PrDAO.getProdottoById(id);
                if(prodotto == null) {
                throw new MyServletException("Prodotto non trovato.");
                } else {
                    request.setAttribute("prodotto", prodotto);
                    requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/prodotto.jsp");
                    requestDispatcher.forward(request, response);
                }
                break;

            case "/prodotto-modifica-form":
                ut = (UtenteRegistrato) request.getSession().getAttribute("utente");
                if(ut == null || ut.getTipo() == 0) {
                    throw new MyServletException("Non hai i permessi necessari.");
                }   else {
                    try {
                        id = Integer.parseInt(request.getParameter("id"));
                    } catch (NumberFormatException e) {
                        throw new MyServletException("Id prodotto non corretto.");
                    }
                    prodotto = PrDAO.getProdottoById(id);
                    if(prodotto == null) {
                        throw new MyServletException("Prodotto non trovato.");
                    } else {
                        request.setAttribute("prodotto", prodotto);
                        requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/modificaProdotto.jsp");
                        requestDispatcher.forward(request, response);
                    }
                }
                break;

            case "/prodotto-modifica":
                ut = (UtenteRegistrato) request.getSession().getAttribute("utente");
                if(ut == null || ut.getTipo() == 0) {
                    throw new MyServletException("Non hai i permessi necessari.");
                }   else {
                    try {
                        id = Integer.parseInt(request.getParameter("id"));
                        nome = request.getParameter("nome");
                        descrizione = request.getParameter("descrizione");
                        categoria = request.getParameter("categoria");
                        prezzo_Cent = Long.parseLong(request.getParameter("prezzo_Cent"));
                        disponibilita = Integer.parseInt(request.getParameter("disponibilita"));
                    } catch (Exception e) {
                        throw new MyServletException("Dati non corretti.");
                    }
                    prodotto = new Prodotto();
                    prodotto.setId(id);
                    prodotto.setNome(nome);
                    prodotto.setDisponibilita(disponibilita);
                    prodotto.setCategoria(categoria);
                    prodotto.setDescrizione(descrizione);
                    prodotto.setPrezzo_Cent(prezzo_Cent);
                    try {
                        PrDAO.doUpdateProdotto(prodotto);
                    } catch(Exception e) {
                        throw new MyServletException("Prodotto non trovato.");
                    }
                    request.setAttribute("messaggio", "Prodotto aggiornato con successo.");
                    requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/notifica.jsp");
                    requestDispatcher.forward(request, response);
                }
                break;

            case "/prodotto-aggiungi":
                ut = (UtenteRegistrato) request.getSession().getAttribute("utente");
                if(ut != null && ut.getTipo() == 1) {
                    try {
                        nome = request.getParameter("nome");
                        descrizione = request.getParameter("descrizione");
                        categoria = request.getParameter("categoria");
                        prezzo_Cent = Long.parseLong(request.getParameter("prezzo_Cent"));
                        disponibilita = Integer.parseInt(request.getParameter("disponibilita"));
                    }   catch (Exception e) {
                        throw new MyServletException("Dati non corretti.");
                    }
                    prodotto = new Prodotto();
                    prodotto.setNome(nome);
                    prodotto.setEmail_Venditore(ut.getEmail());
                    prodotto.setDisponibilita(disponibilita);
                    prodotto.setCategoria(categoria);
                    prodotto.setDescrizione(descrizione);
                    prodotto.setPrezzo_Cent(prezzo_Cent);
                    PrDAO.doSaveProdotto(prodotto);
                    ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) getServletContext().getAttribute("prodotti");
                    prodotti.add(prodotto);
                    getServletContext().setAttribute("prodotti",prodotti);
                    request.setAttribute("messaggio", "Prodotto aggiunto con successo.");
                    requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/notifica.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    throw new MyServletException("Non hai i permessi necessari.");
                }
                break;

            case "/prodotto-aggiungi-form":
                ut = (UtenteRegistrato) request.getSession().getAttribute("utente");
                if(ut != null && ut.getTipo() == 1) {
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
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
