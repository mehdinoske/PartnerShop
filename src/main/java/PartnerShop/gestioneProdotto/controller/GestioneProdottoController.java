package PartnerShop.gestioneProdotto.controller;

import PartnerShop.gestioneProdotto.service.GestioneProdottoService;
import PartnerShop.gestioneProdotto.service.GestioneProdottoServiceImp;
import PartnerShop.model.entity.Amministratore;
import PartnerShop.model.entity.Prodotto;
import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.utils.MyServletException;
import org.json.JSONArray;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/prodotto-visualizza", "/prodotto-modifica-form", "/prodotto-aggiungi", "/prodotto-rimuovi",
        "/prodotto-modifica", "/prodotto-aggiungi-form", "/visualizza-prodotti","/visualizza-categoria","/ricercaAjax"})
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
        ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) getServletContext().getAttribute("prodotti");

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
                        Prodotto p = PrDAO.getProdottoById(id);
                        PrDAO.doUpdateProdotto(prodotto);
                        prodotti.remove(p);
                        prodotti.add(prodotto);
                        getServletContext().setAttribute("prodotti",prodotti);
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
                        Prodotto p2 = PrDAO.getProdottoById(id);
                        PrDAO.deleteProdottoById(id);
                        prodotti.remove(p2);
                        getServletContext().setAttribute("prodotti",prodotti);
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
            case "/visualizza-prodotti":
                request.getSession().setAttribute("listProdotti",prodotti);
                requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/listaProdotti.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "/visualizza-categoria":
               String cat =  request.getParameter("categoria");
                ArrayList listaProd = PrDAO.getProdottiByCategoria(cat);
                request.getSession().setAttribute("listProdotti",listaProd);
                requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/listaProdotti.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "/ricercaAjax":

                String app = (request.getParameter("p") +"*");
                ArrayList<Prodotto> listProd = PrDAO.getProdottiByNome(app);
                JSONArray listaJson = new JSONArray();
                for(Prodotto p : listProd){
                    listaJson.put(p.getNome());
                }
                response.setContentType("application/json");
                response.getWriter().append(listaJson.toString());
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
