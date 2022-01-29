package PartnerShop.model.dao;

import PartnerShop.model.entity.Segnalazione;
import PartnerShop.utils.ConPool;

import java.sql.*;
import java.util.ArrayList;

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

    public ArrayList<Segnalazione> doRetrieveAll(int stato) {
        ArrayList<Segnalazione> list = new ArrayList<>();
        Segnalazione segn =null;
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT id,email_cliente,stato, motivazione,commento  FROM segnalazione where stato=?");
            ps.setInt(1,stato);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                segn = new Segnalazione();
                segn.setId(rs.getInt(1));
                segn.setEmail(rs.getString(2));
                segn.setStato(rs.getInt(3));
                segn.setMotivazione(rs.getString(4));
                segn.setCommento(rs.getString(5));
                list.add(segn);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


    public Segnalazione doRetrieveById(int id) {
        Segnalazione segn = new Segnalazione();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT id,email_cliente,stato, motivazione,commento  FROM segnalazione where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                segn.setId(rs.getInt(1));
                segn.setEmail(rs.getString(2));
                segn.setStato(rs.getInt(3));
                segn.setMotivazione(rs.getString(4));
                segn.setCommento(rs.getString(5));
                return segn;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void aggiornaStato( int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE segnalazione SET stato=1 WHERE id=?");
            ps.setInt(1, id);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
