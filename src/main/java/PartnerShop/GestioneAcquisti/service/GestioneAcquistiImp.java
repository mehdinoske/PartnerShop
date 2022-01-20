package PartnerShop.GestioneAcquisti.service;

import PartnerShop.model.dao.CarrelloDAO;
import PartnerShop.model.dao.GestioneAcquistiDAO;
import PartnerShop.model.entity.Carrello;
import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.UtenteRegistrato;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GestioneAcquistiImp {
   public void aggiungiAlCarrello(HttpServletRequest request, HttpServletResponse response){
       CarrelloDAO carDB = new CarrelloDAO();
       HttpSession session = request.getSession();
       Cliente ut = (Cliente) request.getSession().getAttribute("utente");
       Carrello car = (Carrello)session.getAttribute("carrello");
       if (car == null) {
           car = new Carrello();
           session.setAttribute("carrello", car);
       }

       String gameIdStr = request.getParameter("idGioco");
       if (ut != null) {
           carDB.UpdateSession(car, ut.getIdUtente());
           car = carDB.doRetrieveById(ut.getIdUtente(), car);
       }

       if (gameIdStr != null) {
           int gameId = Integer.parseInt(gameIdStr);
           VideogiocoDAO gamesDB = new VideogiocoDAO();
           Videogioco game = gamesDB.doRetrieveById(gameId);
           String quantStr = request.getParameter("quant");
           if (quantStr != null) {
               int quant = Integer.parseInt(quantStr);
               Videogioco gameCar = car.getGame(gameId);
               if (gameCar != null) {
                   if (ut != null) {
                       carDB.doUpdate(ut.getIdUtente(), gameId, car.getQuant(gameId) + quant);
                   }

                   car.setQuantHash(gameId, car.getQuant(gameId) + quant);
               } else {
                   car.setGameHash(game);
                   car.setQuantHash(gameId, quant);
                   if (ut != null) {
                       carDB.doSave(gameId, ut.getIdUtente(), quant);
                   }
               }
           } else {
               String setQuantStr = request.getParameter("setQuant");
               if (setQuantStr != null) {
                   int setQuant = Integer.parseInt(setQuantStr);
                   if (setQuant <= 0) {
                       car.remove(gameId);
                       if (ut != null) {
                           carDB.doDelete(ut.getIdUtente(), gameId);
                       }
                   }
               }
           }
       }
    }

   public void rimuoviDalCarrello(){

   }

   public void acquistaProdotto(){

   }

   public void visualizzaOridine(){

   }
}
