package PartnerShop.gestioneProdotto.controller;


import PartnerShop.gestioneProdotto.service.GestioneProdottoService;
import PartnerShop.gestioneProdotto.service.GestioneProdottoServiceImp;
import PartnerShop.model.entity.Amministratore;
import PartnerShop.model.entity.Prodotto;
import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.Exceptions.MyServletException;
import org.json.JSONArray;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "GP", urlPatterns = {"/prodotto-visualizza", "/prodotto-modifica-form", "/prodotto-aggiungi", "/prodotto-rimuovi",
        "/prodotto-modifica", "/prodotto-aggiungi-form", "/visualizza-prodotti","/visualizza-categoria","/ricercaAjax"})
public class GestioneProdottoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final GestioneProdottoService PrDAO = new GestioneProdottoServiceImp();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String s = request.getServletPath();
        GestioneProdottoService gps = new GestioneProdottoServiceImp();

        switch (s) {

            case "/prodotto-visualizza":
                try {
                    prodottoVisualizza(request, response, gps);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                break;

            case "/prodotto-modifica-form":
                try {
                    prodottoModificaForm(request,response, gps);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                break;

            case "/prodotto-modifica":
                try {
                    prodottoModifica(request, response, gps);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                break;

            case "/prodotto-aggiungi":
                try {
                    prodottoAggiungi(request,response, gps);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                break;

            case "/prodotto-aggiungi-form":
                try {
                    prodottoAggiungiForm(request,response, gps);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                break;

            case "/prodotto-rimuovi":
                try {
                    prodottoRimuovi(request,response, gps);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                break;

            case "/visualizza-prodotti":
                try {
                    visualizzaProdotti(request,response, gps);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                break;

            case "/visualizza-categoria":
                try {
                    visualizzaCategoria(request,response, gps);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                break;

            case "/ricercaAjax":
                try {
                    ricercaAjax(request,response, gps);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void prodottoVisualizza(HttpServletRequest request, HttpServletResponse response, GestioneProdottoService gps) throws SQLException, NoSuchAlgorithmException, IOException, ServletException {
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            throw new MyServletException("Id prodotto non corretto.");
        }
        Prodotto prodotto = PrDAO.getProdottoById(id);
        if(prodotto == null) {
            throw new MyServletException("Prodotto non trovato.");
        } else {
            request.setAttribute("prodotto", prodotto);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/prodotto.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    public void prodottoModificaForm(HttpServletRequest request, HttpServletResponse response, GestioneProdottoService gps) throws SQLException, NoSuchAlgorithmException, IOException, ServletException {
        UtenteRegistrato ut = (UtenteRegistrato) request.getSession().getAttribute("utente");
        int id;
        if(ut == null || ut.getTipo() == 0) {
            throw new MyServletException("Non hai i permessi necessari.");
        }   else {
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                throw new MyServletException("Id prodotto non corretto.");
            }
            Prodotto prodotto = PrDAO.getProdottoById(id);
            if(prodotto == null) {
                throw new MyServletException("Prodotto non trovato.");
            } else {
                request.setAttribute("prodotto", prodotto);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/modificaProdotto.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }

    public void prodottoAggiungi(HttpServletRequest request, HttpServletResponse response, GestioneProdottoService gps) throws SQLException, NoSuchAlgorithmException, IOException, ServletException {
        UtenteRegistrato ut = (UtenteRegistrato) request.getSession().getAttribute("utente");
        String nome, descrizione, categoria;
        int disponibilita;
        long prezzo_Cent;
        ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) request.getSession().getAttribute("prodotti");
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
            Prodotto prodotto = new Prodotto();
            prodotto.setNome(nome);
            prodotto.setEmail_Venditore(ut.getEmail());
            prodotto.setDisponibilita(disponibilita);
            prodotto.setCategoria(categoria);
            prodotto.setDescrizione(descrizione);
            prodotto.setPrezzo_Cent(prezzo_Cent);
            PrDAO.doSaveProdotto(prodotto);
            prodotti.add(prodotto);
            getServletContext().setAttribute("prodotti",prodotti);
            prodotti = (ArrayList<Prodotto>) request.getSession().getAttribute("prodotti");
            Part filePart = (Part) request.getSession().getAttribute("img");
            Prodotto p = prodotti.get(prodotti.size() - 1);
            int i = p.getId();
            filePart.write("C:\\img\\" + i +".jpg");
            request.setAttribute("messaggio", "Prodotto aggiunto con successo.");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/notifica.jsp");
            requestDispatcher.forward(request, response);
        } else {
            throw new MyServletException("Non hai i permessi necessari.");
        }
    }

    public void prodottoRimuovi(HttpServletRequest request, HttpServletResponse response, GestioneProdottoService gps) throws SQLException, NoSuchAlgorithmException, IOException, ServletException {
        UtenteRegistrato ut = (UtenteRegistrato) request.getSession().getAttribute("utente");
        Amministratore adm = (Amministratore) request.getSession().getAttribute("admin");
        ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) request.getSession().getAttribute("prodotti");
        int id;
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
                request.getSession().setAttribute("prodotti",prodotti);
            } catch(Exception e) {
                throw new MyServletException("Prodotto non trovato.");
            }
        } else {
            throw new MyServletException("Non hai i permessi necessari.");
        }
        request.setAttribute("messaggio", "Prodotto eliminato con successo.");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/notifica.jsp");
        requestDispatcher.forward(request, response);
    }

    public void prodottoAggiungiForm(HttpServletRequest request, HttpServletResponse response, GestioneProdottoService gps) throws SQLException, NoSuchAlgorithmException, IOException, ServletException {
        UtenteRegistrato ut = (UtenteRegistrato) request.getSession().getAttribute("utente");
        if(ut != null && ut.getTipo() == 1) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/aggiungiProdotto.jsp");
            requestDispatcher.forward(request, response);
        } else {
            throw new MyServletException("Non hai i permessi necessari.");
        }
    }

    public void visualizzaProdotti(HttpServletRequest request, HttpServletResponse response, GestioneProdottoService gps) throws SQLException, NoSuchAlgorithmException, IOException, ServletException {
        ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) request.getSession().getAttribute("prodotti");
        request.getSession().setAttribute("listProdotti", prodotti);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/listaProdotti.jsp");
        requestDispatcher.forward(request, response);
    }

    public void visualizzaCategoria(HttpServletRequest request, HttpServletResponse response, GestioneProdottoService gps) throws SQLException, NoSuchAlgorithmException, IOException, ServletException {
        String cat =  request.getParameter("categoria");
        ArrayList listaProd = PrDAO.getProdottiByCategoria(cat);
        request.getSession().setAttribute("listProdotti",listaProd);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/listaProdotti.jsp");
        requestDispatcher.forward(request, response);
    }

    public void ricercaAjax(HttpServletRequest request, HttpServletResponse response, GestioneProdottoService gps) throws SQLException, NoSuchAlgorithmException, IOException, ServletException {
        String app = (request.getParameter("p") +"*");
        ArrayList<Prodotto> listProd = PrDAO.getProdottiByNome(app);
        JSONArray listaJson = new JSONArray();
        for(Prodotto p : listProd){
            listaJson.put(p.getNome());
        }
        response.setContentType("application/json");
        response.getWriter().append(listaJson.toString());
    }

    public void prodottoModifica(HttpServletRequest request, HttpServletResponse response, GestioneProdottoService gps) throws SQLException, NoSuchAlgorithmException, IOException, ServletException {
        UtenteRegistrato ut = (UtenteRegistrato) request.getSession().getAttribute("utente");
        Prodotto prodotto;
        String nome, descrizione, categoria;
        long prezzo_Cent;
        int disponibilita, id;
        ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) request.getSession().getAttribute("prodotti");

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
                throw new MyServletException("Dati errati.");
            }
            prodotto = new Prodotto();
            prodotto.setId(id);
            prodotto.setNome(nome);
            prodotto.setDisponibilita(disponibilita);
            prodotto.setCategoria(categoria);
            prodotto.setDescrizione(descrizione);
            prodotto.setPrezzo_Cent(prezzo_Cent);
            try {
                Prodotto p = gps.getProdottoById(id);
                gps.doUpdateProdotto(prodotto);
                prodotti.remove(p);
                prodotti.add(prodotto);
                getServletContext().setAttribute("prodotti",prodotti);
            } catch(Exception e) {
                throw new MyServletException("Prodotto non trovato.");
            }
            request.setAttribute("messaggio", "Prodotto aggiornato con successo.");
            request.getRequestDispatcher("WEB-INF/jsp/notifica.jsp").forward(request, response);
        }
    }
}
