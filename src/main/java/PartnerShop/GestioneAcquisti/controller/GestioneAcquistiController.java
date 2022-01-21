package PartnerShop.GestioneAcquisti.controller;

import PartnerShop.GestioneAcquisti.service.GestioneAcquistiImp;
import PartnerShop.model.dao.CarrelloDAO;
import PartnerShop.model.dao.GestioneAcquistiDAO;
import PartnerShop.model.entity.Carrello;
import PartnerShop.model.entity.Prodotto;
import PartnerShop.model.entity.UtenteRegistrato;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Carrello")
public class GestioneAcquistiController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarrelloDAO carDB = new CarrelloDAO();
        HttpSession session = req.getSession();
        UtenteRegistrato ut = (UtenteRegistrato) req.getSession().getAttribute("utente");
        Carrello car = (Carrello)session.getAttribute("Carrello");
        if (car == null) {
            car = new Carrello();
            session.setAttribute("Carrello", car);
        }

        String prodottoIdStr = req.getParameter("idProdotto");
        if (ut != null) {
            carDB.UpdateSession(car,ut.getEmail(), ut.getId_Carrello());
            car = carDB.doRetrieveByEmailCliente(ut.getEmail(), car);
        }

        if (prodottoIdStr != null) {
            int prodottoId = Integer.parseInt(prodottoIdStr);
            GestioneAcquistiDAO prodotti = new GestioneAcquistiDAO();
            Prodotto pr = prodotti.doRetrieveProdottoById(prodottoId);
            String quantStr = req.getParameter("quant");
            if (quantStr != null) {
                int quant = Integer.parseInt(quantStr);
                Prodotto prodottoCar = car.getProdotto(prodottoId);
                if (prodottoCar != null) {
                    if (ut != null) {
                        carDB.doUpdate(ut.getId_Carrello(), prodottoId, car.getQuant(prodottoId) + quant);
                    }

                    car.setQuantHash(prodottoId, car.getQuant(prodottoId) + quant);
                } else {
                    car.setProdottoHash(pr);
                    car.setQuantHash(prodottoId, quant);
                    if (ut != null) {
                        carDB.doSave(prodottoId, ut.getId_Carrello(), quant);
                    }
                }
            } else {
                String setQuantStr = req.getParameter("setQuant");
                if (setQuantStr != null) {
                    int setQuant = Integer.parseInt(setQuantStr);
                    if (setQuant <= 0) {
                        car.remove(prodottoId);
                        if (ut != null) {
                            carDB.doDelete(1, prodottoId);
                        }
                    }
                }
            }
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/carrello.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
