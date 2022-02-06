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
    private final String nomeReg ="^[A-Z][a-zA-Z\\s]{2,49}$";
    private final String descrizioneReg ="^[A-Z][a-zA-Z\\s.,:]{10,499}$";
    private final String categoriaReg ="^[A-Z][a-zA-Z\\s]{3,49}$";
    private final String prezzo_CentReg ="^[1-9][0-9]*$";
    private final String disponibilitaReg ="^[1-9][0-9]*$";
    private final GestioneProdottoService gps = new GestioneProdottoServiceImp();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String s = request.getServletPath();

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
        Prodotto prodotto = gps.getProdottoById(id);
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
            Prodotto prodotto = gps.getProdottoById(id);
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
            if(request.getParameter("nome").matches(nomeReg))
                nome = request.getParameter("nome");
            else
                throw new MyServletException("Nome prodotto errato.");

            if(request.getParameter("descrizione").matches(descrizioneReg))
                descrizione = request.getParameter("descrizione");
            else
                throw new MyServletException("Descrizione prodotto errata.");

            if(request.getParameter("categoria").matches(categoriaReg))
                categoria = request.getParameter("categoria");
            else
                throw new MyServletException("Categoria prodotto errata.");

            if(request.getParameter("prezzo_Cent").matches(prezzo_CentReg))
                prezzo_Cent = Long.parseLong(request.getParameter("prezzo_Cent"));
            else
                throw new MyServletException("Prezzo prodotto errato.");

            if(request.getParameter("disponibilita").matches(disponibilitaReg))
                disponibilita = Integer.parseInt(request.getParameter("disponibilita"));
            else
                throw new MyServletException("Disponibilita prodotto errata.");

            Prodotto prodotto = new Prodotto();
            prodotto.setNome(nome);
            prodotto.setEmail_Venditore(ut.getEmail());
            prodotto.setDisponibilita(disponibilita);
            prodotto.setCategoria(categoria);
            prodotto.setDescrizione(descrizione);
            prodotto.setPrezzo_Cent(prezzo_Cent);
            gps.doSaveProdotto(prodotto);
            prodotti.add(prodotto);
            request.getSession().setAttribute("prodotti",prodotti);
            prodotti = (ArrayList<Prodotto>) request.getSession().getAttribute("prodotti");
            Part filePart = (Part) request.getSession().getAttribute("img");
            if(filePart == null) {

            } else {
                Prodotto p = prodotti.get(prodotti.size() - 1);
                int i = p.getId();
                filePart.write("C:\\img\\" + i +".jpg");
            }
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
            Prodotto p2 = gps.getProdottoById(id);
            if(p2 == null)
                throw new MyServletException("Prodotto non trovato.");
            else {
                gps.deleteProdottoById(id);
                prodotti.remove(p2);
                request.getSession().setAttribute("prodotti",prodotti);
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
        if(prodotti == null)
            throw new MyServletException("Nessun prodotto disponibile.");
        else {
            request.getSession().setAttribute("listProdotti", prodotti);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/listaProdotti.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    public void visualizzaCategoria(HttpServletRequest request, HttpServletResponse response, GestioneProdottoService gps) throws SQLException, NoSuchAlgorithmException, IOException, ServletException {
        String categoria;
        if(request.getParameter("categoria").matches(categoriaReg))
            categoria = request.getParameter("categoria");
        else
            throw new MyServletException("Categoria prodotto errata.");
        ArrayList<Prodotto> listaProd = gps.getProdottiByCategoria(categoria);
        if(listaProd == null)
            throw new MyServletException("Nessun prodotto per questa categoria.");
        else {
            request.getSession().setAttribute("listProdotti",listaProd);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/listaProdotti.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    public void ricercaAjax(HttpServletRequest request, HttpServletResponse response, GestioneProdottoService gps) throws SQLException, NoSuchAlgorithmException, IOException, ServletException {
        String app = (request.getParameter("p") +"*");
        ArrayList<Prodotto> listProd = gps.getProdottiByNome(app);
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
            } catch (Exception e) {
                throw new MyServletException("Id prodotto errato.");
            }

            if(request.getParameter("nome").matches(nomeReg))
                nome = request.getParameter("nome");
            else
                throw new MyServletException("Nome prodotto errato.");

            if(request.getParameter("descrizione").matches(descrizioneReg))
                descrizione = request.getParameter("descrizione");
            else
                throw new MyServletException("Descrizione prodotto errata.");

            if(request.getParameter("categoria").matches(categoriaReg))
                categoria = request.getParameter("categoria");
           else
                throw new MyServletException("Categoria prodotto errata.");

            if(request.getParameter("prezzo_Cent").matches(prezzo_CentReg))
                prezzo_Cent = Long.parseLong(request.getParameter("prezzo_Cent"));
            else
                throw new MyServletException("Prezzo prodotto errato.");

            if(request.getParameter("disponibilita").matches(disponibilitaReg))
                disponibilita = Integer.parseInt(request.getParameter("disponibilita"));
            else
                throw new MyServletException("Disponibilita prodotto errata.");

            prodotto = new Prodotto();
            prodotto.setId(id);
            prodotto.setNome(nome);
            prodotto.setDisponibilita(disponibilita);
            prodotto.setCategoria(categoria);
            prodotto.setDescrizione(descrizione);
            prodotto.setPrezzo_Cent(prezzo_Cent);
            Prodotto p = gps.getProdottoById(id);
            if(p == null) {
                throw new MyServletException("Prodotto non trovato.");
            } else {
                gps.doUpdateProdotto(prodotto);
                prodotti.remove(p);
                prodotti.add(prodotto);
                request.getSession().setAttribute("prodotti",prodotti);
            }
            request.setAttribute("messaggio", "Prodotto aggiornato con successo.");
            request.getRequestDispatcher("WEB-INF/jsp/notifica.jsp").forward(request, response);
        }
    }
}
