package PartnerShop.model.dao;

import PartnerShop.utils.ConPool;

import java.sql.*;

public class VenditoreDAO extends UtenteRegistratoDAO{


    public void doSave(String email,String nomeNegozio,String pIva) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO venditore (email,nome_negozio,partita_Iva) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, email);
            ps.setString(2, nomeNegozio);
            ps.setString(3, pIva);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
