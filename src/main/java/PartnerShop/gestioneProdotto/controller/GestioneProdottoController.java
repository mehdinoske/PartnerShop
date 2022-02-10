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

/**
 * Questa classe implemmenta il controller che si occupa del sottosistema gestione prodotto
 * @see HttpServlet fornisce l'interfaccia per creare una servlet
 * @version 1.0
 * @author Marco De Palma
 */
@WebServlet(name = "GP", urlPatterns = {"/prodotto-visualizza", "/prodotto-modifica-form", "/prodotto-aggiungi", "/prodotto-rimuovi",
        "/prodotto-modifica", "/prodotto-aggiungi-form", "/visualizza-prodotti","/visualizza-categoria","/ricercaAjax", "/ricerca"})
public class GestioneProdottoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final String nomeReg ="^[A-Z][a-zA-Z\\s]{2,49}$";
    private final String descrizioneReg ="^[A-Z][a-zA-Z\\s.,:]{10,499}$";
    private final String categoriaReg ="^[A-Z][a-zA-Z\\s]{3,49}$";
    private final String prezzo_CentReg ="^[1-9][0-9]*$";
    private final String disponibilitaReg ="^[1-9][0-9]*$";
    private final GestioneProdottoService gps = new GestioneProdottoServiceImp();

    /**
     * Il metodo ereditato dalla classe HttpServlet che esplicita i parametri della request e permette di usare il metodo switchPath
     * @param request Oggetto della servlet, che contiene i parametri inviati e la sessione corrente
     * @param response Oggetto della servlet, che contiene i parametri della risposta
     * @throws ServletException Un'eccezione lanciata quando si verifica un errore nella servlet
     * @throws IOException Un'eccezione lanciata quando si verifica un errore I/O
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switchPath(request,response, gps);
    }

    /**
     * Il metodo ereditato dalla classe HttpServlet che in questo caso fa le stesse cose del metodo doGet ma senza esplicitarne i parametri
     * @param request Oggetto della servlet, che contiene i parametri inviati e la sessione corrente
     * @param response Oggetto della servlet, che contiene i parametri della risposta
     * @throws ServletException Un'eccezione lanciata quando si verifica un errore nella servlet
     * @throws IOException Un'eccezione lanciata quando si verifica un errore I/O
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Il metodo che permette la visualizzazione in dettaglio di un prodotto
     * @param request Oggetto della servlet, che contiene l'id del prodotto da visualizzare e la sessione corrente
     * @param response Oggetto della servlet, che contiene i parametri della risposta
     * @param gps l'interfaccia di GestioneProdottoServiceImp che permette il collegamento con il database
     * @return variabile booleana usata per indicare che tutto è andato a buon fine quando restituisce true
     * @throws SQLException Un'eccezione che fornisce informazioni su un errore di accesso al database o altri errori
     * @throws NoSuchAlgorithmException Un'eccezione lanciata quando è richiesto un particolare algoritmo ma che non e disponibile
     * @throws IOException Un'eccezione lanciata quando si verifica un errore I/O
     * @throws ServletException Un'eccezione lanciata quando si verifica un errore nella servlet
     */
    public boolean prodottoVisualizza(HttpServletRequest request, HttpServletResponse response, GestioneProdottoService gps) throws SQLException, NoSuchAlgorithmException, IOException, ServletException {
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
        return true;
    }

    /**
     * Il metodo che permette la visualizzazione del form per modificare un prodotto, effettuabile solo dal venditore a cui appartiene quel prodotto
     * @param request Oggetto della servlet, che contiene l'id del prodotto da visualizzare e la sessione corrente che contiene l'attributo utente
     * @param response Oggetto della servlet, che contiene i parametri della risposta
     * @param gps l'interfaccia di GestioneProdottoServiceImp che permette il collegamento con il database
     * @return variabile booleana usata per indicare che tutto è andato a buon fine quando restituisce true
     * @throws SQLException Un'eccezione che fornisce informazioni su un errore di accesso al database o altri errori
     * @throws NoSuchAlgorithmException Un'eccezione lanciata quando è richiesto un particolare algoritmo ma che non e disponibile
     * @throws IOException Un'eccezione lanciata quando si verifica un errore I/O
     * @throws ServletException Un'eccezione lanciata quando si verifica un errore nella servlet
     */
    public boolean prodottoModificaForm(HttpServletRequest request, HttpServletResponse response, GestioneProdottoService gps) throws SQLException, NoSuchAlgorithmException, IOException, ServletException {
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
        return true;
    }

    /**
     * Il metodo che permette l'aggiunta di un prodotto al database, effettuabile solo dai venditori
     * @param request Oggetto della servlet, che contiene le variabili usate per aggiungere un nuovo prodotto e la sessione corrente che contiene l'attributo utente
     * @param response Oggetto della servlet, che contiene i parametri della risposta
     * @param gps l'interfaccia di GestioneProdottoServiceImp che permette il collegamento con il database
     * @return variabile booleana usata per indicare che tutto è andato a buon fine quando restituisce true
     * @throws SQLException Un'eccezione che fornisce informazioni su un errore di accesso al database o altri errori
     * @throws NoSuchAlgorithmException Un'eccezione lanciata quando è richiesto un particolare algoritmo ma che non e disponibile
     * @throws IOException Un'eccezione lanciata quando si verifica un errore I/O
     * @throws ServletException Un'eccezione lanciata quando si verifica un errore nella servlet
     */
    public boolean prodottoAggiungi(HttpServletRequest request, HttpServletResponse response, GestioneProdottoService gps) throws SQLException, NoSuchAlgorithmException, IOException, ServletException {
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
        return true;
    }

    /**
     * Il metodo che permette la rimozione di un prodotto dal database, effettuabile solo dai venditori e dagli admin
     * @param request Oggetto della servlet, che contiene l'id del prodotto da rimuovere e la sessione corrente che contiene l'attributo utente
     * @param response Oggetto della servlet, che contiene i parametri della risposta
     * @param gps l'interfaccia di GestioneProdottoServiceImp che permette il collegamento con il database
     * @return variabile booleana usata per indicare che tutto è andato a buon fine quando restituisce true
     * @throws SQLException Un'eccezione che fornisce informazioni su un errore di accesso al database o altri errori
     * @throws NoSuchAlgorithmException Un'eccezione lanciata quando è richiesto un particolare algoritmo ma che non e disponibile
     * @throws IOException Un'eccezione lanciata quando si verifica un errore I/O
     * @throws ServletException Un'eccezione lanciata quando si verifica un errore nella servlet
     */
    public boolean prodottoRimuovi(HttpServletRequest request, HttpServletResponse response, GestioneProdottoService gps) throws SQLException, NoSuchAlgorithmException, IOException, ServletException {
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
        return true;
    }

    /**
     * Il metodo che permette la visualizzazione del form per aggiungere un prodotto, effettuabile solo dai venditori
     * @param request Oggetto della servlet, che contiene i parametri inviati e la sessione corrente che contiene l'attributo utente
     * @param response Oggetto della servlet, che contiene i parametri della risposta
     * @param gps l'interfaccia di GestioneProdottoServiceImp che permette il collegamento con il database
     * @return variabile booleana usata per indicare che tutto è andato a buon fine quando restituisce true
     * @throws SQLException Un'eccezione che fornisce informazioni su un errore di accesso al database o altri errori
     * @throws NoSuchAlgorithmException Un'eccezione lanciata quando è richiesto un particolare algoritmo ma che non e disponibile
     * @throws IOException Un'eccezione lanciata quando si verifica un errore I/O
     * @throws ServletException Un'eccezione lanciata quando si verifica un errore nella servlet
     */
    public boolean prodottoAggiungiForm(HttpServletRequest request, HttpServletResponse response, GestioneProdottoService gps) throws SQLException, NoSuchAlgorithmException, IOException, ServletException {
        UtenteRegistrato ut = (UtenteRegistrato) request.getSession().getAttribute("utente");
        if(ut != null && ut.getTipo() == 1) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/aggiungiProdotto.jsp");
            requestDispatcher.forward(request, response);
        } else {
            throw new MyServletException("Non hai i permessi necessari.");
        }
        return true;
    }

    /**Il metodo che permette la visualizzazione di una lista di prodotti
     * @param request Oggetto della servlet, che contiene i parametri inviati e la sessione corrente
     * @param response Oggetto della servlet, che contiene i parametri della risposta
     * @param gps l'interfaccia di GestioneProdottoServiceImp che permette il collegamento con il database
     * @return variabile booleana usata per indicare che tutto è andato a buon fine quando restituisce true
     * @throws SQLException Un'eccezione che fornisce informazioni su un errore di accesso al database o altri errori
     * @throws NoSuchAlgorithmException Un'eccezione lanciata quando è richiesto un particolare algoritmo ma che non e disponibile
     * @throws IOException Un'eccezione lanciata quando si verifica un errore I/O
     * @throws ServletException Un'eccezione lanciata quando si verifica un errore nella servlet
     */
    public boolean visualizzaProdotti(HttpServletRequest request, HttpServletResponse response, GestioneProdottoService gps) throws SQLException, NoSuchAlgorithmException, IOException, ServletException {
        ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) request.getSession().getAttribute("prodotti");
        if(prodotti == null)
            throw new MyServletException("Nessun prodotto disponibile.");
        else {
            request.getSession().setAttribute("listProdotti", prodotti);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/listaProdotti.jsp");
            requestDispatcher.forward(request, response);
        }
        return true;
    }

    /**
     * Il metodo che permette la visualizzazione di tutti i prodotti di una determinata categoria
     * @param request Oggetto della servlet, che contiene la categoria dei prodotti da visualizzare e la sessione corrente
     * @param response Oggetto della servlet, che contiene i parametri della risposta
     * @param gps l'interfaccia di GestioneProdottoServiceImp che permette il collegamento con il database
     * @return variabile booleana usata per indicare che tutto è andato a buon fine quando restituisce true
     * @throws SQLException Un'eccezione che fornisce informazioni su un errore di accesso al database o altri errori
     * @throws NoSuchAlgorithmException Un'eccezione lanciata quando è richiesto un particolare algoritmo ma che non e disponibile
     * @throws IOException Un'eccezione lanciata quando si verifica un errore I/O
     * @throws ServletException Un'eccezione lanciata quando si verifica un errore nella servlet
     */
    public boolean visualizzaCategoria(HttpServletRequest request, HttpServletResponse response, GestioneProdottoService gps) throws SQLException, NoSuchAlgorithmException, IOException, ServletException {
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
        return true;
    }

    /**
     * Il metodo che permette la visualizzazione di una lista dei prodotti ottenuta tramite ricerca ajax
     * @param request Oggetto della servlet, che contiene la stringa da cercare e la sessione corrente
     * @param response Oggetto della servlet, che contiene i parametri della risposta
     * @param gps l'interfaccia di GestioneProdottoServiceImp che permette il collegamento con il database
     * @return variabile booleana usata per indicare che tutto è andato a buon fine quando restituisce true
     * @throws SQLException Un'eccezione che fornisce informazioni su un errore di accesso al database o altri errori
     * @throws NoSuchAlgorithmException Un'eccezione lanciata quando è richiesto un particolare algoritmo ma che non e disponibile
     * @throws IOException Un'eccezione lanciata quando si verifica un errore I/O
     * @throws ServletException Un'eccezione lanciata quando si verifica un errore nella servlet
     */
    public boolean ricercaAjax(HttpServletRequest request, HttpServletResponse response, GestioneProdottoService gps) throws SQLException, NoSuchAlgorithmException, IOException, ServletException {
        String app = (request.getParameter("p") +"*");
        ArrayList<Prodotto> listProd = gps.getProdottiByNome(app);
        if(listProd == null) {
            throw new MyServletException("Nessun prodotto per questa ricerca.");
        }
        JSONArray listaJson = new JSONArray();
        for(Prodotto p : listProd){
            listaJson.put(p.getNome());
        }
        response.setContentType("application/json");
        response.getWriter().append(listaJson.toString());
        return true;
    }

    /**
     * Il metodo che permette la visualizzazione di una lista dei prodotti ottenuta tramite una ricerca
     * @param request Oggetto della servlet, che contiene la stringa da cercare e la sessione corrente
     * @param response Oggetto della servlet, che contiene i parametri della risposta
     * @param gps l'interfaccia di GestioneProdottoServiceImp che permette il collegamento con il database
     * @return variabile booleana usata per indicare che tutto è andato a buon fine quando restituisce true
     * @throws IOException Un'eccezione lanciata quando si verifica un errore I/O
     * @throws ServletException Un'eccezione lanciata quando si verifica un errore nella servlet
     */
    public boolean ricerca(HttpServletRequest request,HttpServletResponse response,GestioneProdottoService gps)throws ServletException, IOException {
        if(!request.getParameter("p").isBlank()){
        String nome = request.getParameter("p")+"*";
        ArrayList<Prodotto> listProd = gps.getProdottiByNome(nome);
        if(listProd == null) {
            throw new MyServletException("Nessun prodotto per questa ricerca.");
        }
        request.setAttribute("prodottiRicerca",listProd);}
        request.getRequestDispatcher("WEB-INF/jsp/risultatoRicerca.jsp").forward(request,response);
        return true;
    }

    /**Il metodo che permette la modifica di un prodotto, effettuabile solo dal venditore a cui appartiene quel prodotto
     * @param request Oggetto della servlet, che contiene le variabili relative al prodotto da modificare e la sessione corrente
     * @param response Oggetto della servlet, che contiene i parametri della risposta
     * @param gps l'interfaccia di GestioneProdottoServiceImp che permette il collegamento con il database
     * @return variabile booleana usata per indicare che tutto è andato a buon fine quando restituisce true
     * @throws SQLException Un'eccezione che fornisce informazioni su un errore di accesso al database o altri errori
     * @throws NoSuchAlgorithmException Un'eccezione lanciata quando è richiesto un particolare algoritmo ma che non e disponibile
     * @throws IOException Un'eccezione lanciata quando si verifica un errore I/O
     * @throws ServletException Un'eccezione lanciata quando si verifica un errore nella servlet
     */
    public boolean prodottoModifica(HttpServletRequest request, HttpServletResponse response, GestioneProdottoService gps) throws SQLException, NoSuchAlgorithmException, IOException, ServletException {
        UtenteRegistrato ut = (UtenteRegistrato) request.getSession().getAttribute("utente");
        Prodotto prodotto;
        String nome, descrizione, categoria;
        long prezzo_Cent;
        int disponibilita, id;
        ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) request.getSession().getAttribute("prodotti");
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            throw new MyServletException("Id prodotto errato.");
        }
        Prodotto p1 = gps.getProdottoById(id);
        if(p1 == null)
            throw new MyServletException("Prodotto non trovato.");

        if(ut == null || ut.getTipo() == 0 || !gps.getProdottoById(id).getEmail_Venditore().equals(ut.getEmail())) {
            throw new MyServletException("Non hai i permessi necessari.");
        }   else {

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

            gps.doUpdateProdotto(prodotto);
            prodotti.remove(p1);
            prodotti.add(prodotto);
            request.getSession().setAttribute("prodotti",prodotti);

            request.setAttribute("messaggio", "Prodotto aggiornato con successo.");
            request.getRequestDispatcher("WEB-INF/jsp/notifica.jsp").forward(request, response);
        }
        return true;
    }

    /**
     * Il metodo che seleziona in base al ServletPath quale metodo richiamare
     * @param request Oggetto della servlet, che contiene il ServletPath usato in uno switch per decretare quale meotodo richiamare
     *               , i parametri relativi al quel metodo e la sessione corrente
     * @param response Oggetto della servlet, che contiene i parametri della risposta
     * @param gps l'interfaccia di GestioneProdottoServiceImp che permette il collegamento con il database
     * @return variabile booleana usata per indicare che tutto è andato a buon fine quando restituisce true
     * @throws IOException Un'eccezione lanciata quando si verifica un errore I/O
     * @throws ServletException Un'eccezione lanciata quando si verifica un errore nella servlet
     */
    public boolean switchPath(HttpServletRequest request, HttpServletResponse response, GestioneProdottoService gps) throws ServletException, IOException {
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
                return true;

            case "/prodotto-modifica-form":
                try {
                    prodottoModificaForm(request,response, gps);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                return true;

            case "/prodotto-modifica":
                try {
                    prodottoModifica(request, response, gps);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                return true;

            case "/prodotto-aggiungi":
                try {
                    prodottoAggiungi(request,response, gps);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                return true;

            case "/prodotto-aggiungi-form":
                try {
                    prodottoAggiungiForm(request,response, gps);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                return true;

            case "/prodotto-rimuovi":
                try {
                    prodottoRimuovi(request,response, gps);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                return true;

            case "/visualizza-prodotti":
                try {
                    visualizzaProdotti(request,response, gps);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                return true;

            case "/visualizza-categoria":
                try {
                    visualizzaCategoria(request,response, gps);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                return true;

            case "/ricercaAjax":
                try {
                    ricercaAjax(request,response, gps);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                return true;

            case "/ricerca":
                ricerca(request,response,gps);
                return true;
        }
        return false;
    }
}
