package PartnerShop.GestioneAcquisti.service;

import PartnerShop.model.dao.CarrelloDAO;
import PartnerShop.model.dao.ClienteDAO;
import PartnerShop.model.dao.GestioneAcquistiDAO;
import PartnerShop.model.dao.GestioneProdottoDAO;
import PartnerShop.model.entity.*;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

public class GestioneAcquistiServiceImp implements GestioneAcquistiService{

    CarrelloDAO carDB = new CarrelloDAO();
    GestioneAcquistiDAO gesDB = new GestioneAcquistiDAO();
    GestioneProdottoDAO prodDAO = new GestioneProdottoDAO();
   public void aggiungiAlCarrello(Carrello car,UtenteRegistrato ut,String prodottoIdStr,String quantStr){
       CarrelloDAO carDB = new CarrelloDAO();
       if (ut != null) {
           carDB.UpdateSession(car,ut.getEmail(), ut.getId_Carrello());
           car = carDB.doRetrieveByEmailCliente(ut.getEmail(), car);
       }

       if (prodottoIdStr != null) {
           int prodottoId = Integer.parseInt(prodottoIdStr);
           Prodotto pr = prodDAO.doRetrieveById(prodottoId);
           if (quantStr != null) {
               int quant = Integer.parseInt(quantStr);
               Prodotto prodottoCar = new Prodotto();
               prodottoCar = car.getProdotto(prodottoId);
               if (prodottoCar != null ) {
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

   public void acquistaProdotto(UtenteRegistrato ut,Carrello car,String indirizzo,String cardc){

       Ordine ord = new Ordine();
       if (car != null) {
           ord.setEmailCliente(ut.getEmail());
           ord.setData();
           ord.setIndirizzo(indirizzo);
           Cliente cl = new Cliente();
           cl.setEmail(ut.getEmail());
           cl.setCartaDiCredito(cardc);
           ClienteDAO clDB = new ClienteDAO();
           clDB.doSave(cl);
           Iterator var8 = car.getProdottoHash().keySet().iterator();

           Integer key;
           ArrayList<Integer> badProduct = new ArrayList<Integer>();
           while(var8.hasNext()) {
               key = (Integer)var8.next();
               if(car.getProdotto(key).getDisponibilita()>=car.getQuant(key)){
                   ord.setProdottoHash(car.getProdotto(key));
                   ord.setQuantHash(key, car.getQuant(key));
               }else{badProduct.add(key);}
           }
           for(int i : badProduct){
               car.remove(i);
           }
           ord.setPrezzo_tot(car.sommaTot());
           gesDB.doSaveOrdine(ord);
           var8 = car.getProdottoHash().keySet().iterator();

           while(var8.hasNext()) {
               key = (Integer)var8.next();
               Prodotto pr = ord.getProdotto(key);
               pr.setDisponibilita(pr.getDisponibilita()-ord.getQuant(key));
               prodDAO.doUpdate(pr);
           }

           var8 = car.getProdottoHash().keySet().iterator();
           while(var8.hasNext()) {
               key = (Integer)var8.next();
               carDB.doDelete(ut.getId_Carrello(), key);
           }


       }
   }

   public ArrayList<Ordine> visualizzaOrdine(UtenteRegistrato ut){
       if(ut!=null && ut.getTipo() == 0)
        return gesDB.doRetrieveOrdiniByEmailCliente(ut.getEmail());
       else
           return gesDB.doRetrieveOrdiniByEmailVenditore(ut.getEmail());
   }
}
