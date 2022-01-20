package PartnerShop.model.dao;

import PartnerShop.model.entity.Prodotto;
import PartnerShop.utils.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestioneProdottoDAO {

    public Prodotto doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT id, email_venditore, nome, descrizione, categoria, prezzo, quantita_disponibile FROM prodotto WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Prodotto p = new Prodotto();
                p.setId(rs.getInt(1));
                p.setEmail_Venditore(rs.getString(2));
                p.setNome(rs.getString(3));
                p.setDescrizione(rs.getString(4));
                p.setCategoria(rs.getString(5));
                p.setPrezzo_Cent(rs.getInt(6));
                p.setDisponibilità(rs.getInt(7));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
