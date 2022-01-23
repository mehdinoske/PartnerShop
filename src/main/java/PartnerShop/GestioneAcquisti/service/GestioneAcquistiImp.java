package PartnerShop.GestioneAcquisti.service;

import PartnerShop.model.dao.CarrelloDAO;
import PartnerShop.model.dao.GestioneAcquistiDAO;
import PartnerShop.model.entity.Carrello;
import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.Prodotto;
import PartnerShop.model.entity.UtenteRegistrato;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GestioneAcquistiImp implements GestioneAcquistiService{
    CarrelloDAO carDB = new CarrelloDAO();
   public void aggiungiAlCarrello(Carrello car,UtenteRegistrato ut,String prodottoIdStr,String quantStr,String setQuantStr){
       CarrelloDAO carDB = new CarrelloDAO();
       if (ut != null) {
           carDB.UpdateSession(car,ut.getEmail(), ut.getId_Carrello());
           car = carDB.doRetrieveByEmailCliente(ut.getEmail(), car);
       }

       if (prodottoIdStr != null) {
           int prodottoId = Integer.parseInt(prodottoIdStr);
           GestioneAcquistiDAO prodotti = new GestioneAcquistiDAO();
           Prodotto pr = prodotti.doRetrieveProdottoById(prodottoId);
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

               /*if (setQuantStr != null) {
                   int setQuant = Integer.parseInt(setQuantStr);
                   if (setQuant <= 0) {
                       car.remove(prodottoId);
                       if (ut != null) {
                           carDB.doDelete(ut.getId_Carrello(), prodottoId);
                       }
                   }
               }*/
           }
       }
    }


    public void rimuovidalcarrello(UtenteRegistrato ut,Carrello car,int prodottoId,String setQuantStr){
        if (setQuantStr != null) {
            int setQuant = Integer.parseInt(setQuantStr);
            if (setQuant <= 0) {
                car.remove(prodottoId);
                if (ut != null) {
                    carDB.doDelete(ut.getId_Carrello(), prodottoId);
                }
            }
        }
    }

   public void acquistaProdotto(){

   }

   public void visualizzaOridine(){

   }
}
