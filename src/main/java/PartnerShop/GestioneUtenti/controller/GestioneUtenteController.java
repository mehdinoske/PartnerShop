package PartnerShop.GestioneUtenti.controller;

import PartnerShop.GestioneUtenti.service.GestioneUtenteService;
import PartnerShop.GestioneUtenti.service.GestioneUtenteServiceImp;

import PartnerShop.model.entity.Amministratore;
import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.UtenteRegistrato;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

@WebServlet(urlPatterns = {"/VisualizzaDatiUtente", "/VisualizzaModifica", "/ModificaForm","/CancellaDatiUtenti", "/VisualizzaUtenti","/AggiungiListaDesideri","/RimuoviListaDesideri","/VisualizzaListaDesideri"})
public class GestioneUtenteController extends HttpServlet {

    private final GestioneUtenteService gestioneUtenteService = new GestioneUtenteServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public boolean execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        String s = request.getServletPath();
        switch (s) {
            case "/VisualizzaDatiUtente":
                dispatcher = request.getRequestDispatcher("WEB-INF/jsp/visualizzaDatiUtente.jsp");
                break;
            case "/VisualizzaModifica":
                dispatcher = request.getRequestDispatcher("WEB-INF/jsp/modificaDatiUtente.jsp");
                break;
            case "/ModificaForm": {

                String nome = request.getParameter("nome");
                String cognome = request.getParameter("cognome");
                String email = request.getParameter("email");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String ddn = request.getParameter("ddn");
                String indirizzo = request.getParameter("indirizzo");
                String cellulare = request.getParameter("cellulare");
                int tipo = parseInt(request.getParameter("tipo"));
                UtenteRegistrato ut = new UtenteRegistrato();
                ut.setNome(nome);
                ut.setCognome(cognome);
                ut.setDdn(ddn);
                ut.setUsername(username);
                ut.setIndirizzo(indirizzo);
                ut.setEmail(email);
                ut.setPassword(password);
                ut.setPasswordHash(password);
                ut.setCellulare(cellulare);
                ut.setTipo(tipo);

                boolean mod = gestioneUtenteService.ModificaDati(ut);
                if (mod) {
                    request.getSession().setAttribute("utente", ut);
                }
                dispatcher = request.getRequestDispatcher("WEB-INF/jsp/visualizzaDatiUtente.jsp");
                break;
            }
            case "/CancellaDatiUtenti": {
                if ((UtenteRegistrato) request.getSession().getAttribute("utente") != null && ((Amministratore) request.getSession().getAttribute("admin")) == null) {
                    String email = request.getParameter("email");
                    gestioneUtenteService.CancellaUtente(email);
                    request.getSession().removeAttribute("utente");
                    dispatcher = request.getRequestDispatcher(("WEB-INF/jsp/index.jsp"));
                    break;
                } else if ((Amministratore) request.getSession().getAttribute("admin") != null) {
                    String email = request.getParameter("email");
                    gestioneUtenteService.CancellaUtente(email);
                    dispatcher = request.getRequestDispatcher(("WEB-INF/jsp/visualizzaUtentiRegistrati.jsp"));
                    break;
                }
            }
            case "/VisualizzaUtenti": {
                ArrayList<UtenteRegistrato> listUtenti = gestioneUtenteService.VisualizzaUtenti();
                request.setAttribute("utenti", listUtenti);
                dispatcher = request.getRequestDispatcher(("WEB-INF/jsp/visualizzaUtentiRegistrati.jsp"));
                break;
            }
            case "/AggiungiListaDesideri": {
                String idProdStr=request.getParameter("idProdotto");
                Cliente cl=(Cliente) request.getSession().getAttribute("cliente");
                if(idProdStr!=null && cl!=null){
                    int id=Integer.parseInt(idProdStr);
                    gestioneUtenteService.aggiungiListaDesideri(cl,id);
                    dispatcher = request.getRequestDispatcher(("WEB-INF/jsp/listaDesideri.jsp"));
                }
                break;
            }
            case "/RimuoviListaDesideri": {
                String idProdStr=request.getParameter("idProdotto");
                Cliente cl=(Cliente) request.getSession().getAttribute("cliente");
                if(idProdStr!=null && cl!=null){
                    int id=Integer.parseInt(idProdStr);
                    gestioneUtenteService.rimuoviListaDesideri(cl,id);
                    dispatcher = request.getRequestDispatcher(("WEB-INF/jsp/listaDesideri.jsp"));
                }
                break;
            }
            case "/VisualizzaListaDesideri":{
                Cliente cl=(Cliente) request.getSession().getAttribute("cliente");
                if(cl!=null){
                    dispatcher = request.getRequestDispatcher(("WEB-INF/jsp/listaDesideri.jsp"));
                }
                break;
            }
        }
        if (dispatcher != null)
            dispatcher.forward(request, response);
        return true;
    }
}
