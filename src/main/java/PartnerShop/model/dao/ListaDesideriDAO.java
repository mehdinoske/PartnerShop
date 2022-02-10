package PartnerShop.model.dao;

import PartnerShop.model.entity.Prodotto;

import java.sql.*;
import java.util.ArrayList;

public class ListaDesideriDAO {

    private GestioneProdottoDAO prodDB = new GestioneProdottoDAO();
    private Connection con;

    public ArrayList<Prodotto> doRetrieveByEmailCliente(String email) {
        ArrayList<Prodotto> lista = new ArrayList<Prodotto>();
        try{
            PreparedStatement ps = con
                    .prepareStatement("SELECT id FROM lista_desideri WHERE email_cliente=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Prodotto p = prodDB.doRetrieveById(rs.getInt(1));
                lista.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }


    public void doSave(Prodotto prodotto,String email) {
        try  {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO lista_desideri (id,email_cliente) VALUES(?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, prodotto.getId());
            ps.setString(2, email);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            prodotto.setId(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void doDeleteByIdEmailCliente(int id,String email) {
        try  {
            PreparedStatement ps = con
                    .prepareStatement("DELETE FROM lista_desideri WHERE id=? and email_cliente=?");
            ps.setInt(1, id);
            ps.setString(2, email);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
