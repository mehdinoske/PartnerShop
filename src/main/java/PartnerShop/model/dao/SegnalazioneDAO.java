package PartnerShop.model.dao;

import PartnerShop.model.entity.Segnalazione;
import PartnerShop.utils.ConPool;

import java.sql.*;

public class SegnalazioneDAO {

    public void doSave(Segnalazione segn) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO segnalazione (email_Cliente,stato,motivazione,commento) VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, segn.getEmail());
            ps.setInt(2, segn.getStato());
            ps.setString(3, segn.getMotivazione());
            ps.setString(4, segn.getCommento());

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
