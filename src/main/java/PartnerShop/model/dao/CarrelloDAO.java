package PartnerShop.model.dao;

import PartnerShop.model.entity.Carrello;
import PartnerShop.model.entity.Prodotto;
import PartnerShop.utils.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class CarrelloDAO {
    private Connection con;

    public CarrelloDAO(){
        this.con = ConPool.getConnection();
    }
    private GestioneProdottoDAO prodottoDB = new GestioneProdottoDAO();



    public Carrello doRetrieveByEmailCliente(String emailCliente, Carrello car) {

            try {
                PreparedStatement ps = con.prepareStatement("SELECT cp.id_Prodotto, cp.quantita FROM carrello_prodotto cp, carrello c WHERE c.id = cp.id_Carrello and email_Cliente = ?");
                ps.setString(1, emailCliente);
                ResultSet rs = ps.executeQuery();

                while(rs.next()) {
                    int cod = rs.getInt(1);
                    int quant = rs.getInt(2);
                    Prodotto prodCar = car.getProdotto(cod);
                    if (prodCar == null) {
                        car.setProdottoHash(this.prodottoDB.doRetrieveById(cod));
                        car.setQuantHash(cod, quant);
                    }
                }


            return car;
        } catch (SQLException var11) {
            throw new RuntimeException(var11);
        }
    }

    public int doRetrieveIdCarrelloByEmailCliente(String emailCliente) {
            int id_carrello=0;
            try {
                PreparedStatement ps = con.prepareStatement("SELECT id FROM carrello WHERE email_Cliente = ?");
                ps.setString(1, emailCliente);
                ResultSet rs = ps.executeQuery();

                while(rs.next()) {
                    id_carrello = rs.getInt(1);
                }

            return id_carrello;
        } catch (SQLException var11) {
            throw new RuntimeException(var11);
        }
    }

    public void UpdateSession(Carrello car, String emailCliente,int id_Carrello) {
            try {
                PreparedStatement ps = con.prepareStatement("SELECT cp.id_Prodotto,cp.quantita FROM carrello_prodotto cp, carrello c WHERE c.id = cp.id_Carrello and email_Cliente = ? and id_Prodotto = ?");
                Iterator var5 = car.getProdottoHash().keySet().iterator();

                while(var5.hasNext()) {
                    Integer key = (Integer)var5.next();
                    ps.setString(1, emailCliente);
                    ps.setInt(2, key);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        this.doSave(key, id_Carrello, car.getQuant(key));
                    }

                    if (rs.next()) {
                        this.doUpdate(id_Carrello, key, car.getQuant(key));
                    }
                }

        } catch (SQLException var10) {
            throw new RuntimeException(var10);
        }
    }

    public void doSave(int id_Prodotto,int id_Carrello, int quant) {
            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO carrello_prodotto (id_carrello,id_prodotto,quantita) VALUES(?,?,?)");
                ps.setInt(1, id_Carrello);
                ps.setInt(2, id_Prodotto);
                ps.setInt(3, quant);
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("Insert error.");
                }

        } catch (SQLException var9) {
            throw new RuntimeException(var9);
        }
    }

    public void doCreateCarrello(String emailCliente) {
            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO carrello (email_Cliente) VALUES(?)");
                ps.setString(1, emailCliente);
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("Insert error.");
                }

        } catch (SQLException var9) {
            throw new RuntimeException(var9);
        }
    }

    public void doUpdate(int id_Carrello, int idProd, int quant) {
            try {
                PreparedStatement ps = con.prepareStatement("UPDATE carrello_prodotto SET quantita=? WHERE  id_Prodotto=? AND id_Carrello=?");
                ps.setInt(1, quant);
                ps.setInt(2, idProd);
                ps.setInt(3, id_Carrello);
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("Update error.");
                }

        } catch (SQLException var9) {
            throw new RuntimeException(var9);
        }
    }

    public void doDelete(int idCarrello, int idProd) {
            try {
                PreparedStatement ps = con.prepareStatement("DELETE FROM carrello_prodotto WHERE  id_Carrello=? AND id_Prodotto=?");
                ps.setInt(1, idCarrello);
                ps.setInt(2, idProd);
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("DELETE error.");
                }


        } catch (SQLException var8) {
            throw new RuntimeException(var8);
        }
    }
}
