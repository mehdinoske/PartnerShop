package PartnerShop.model.dao;

import PartnerShop.model.entity.Cliente;
import PartnerShop.utils.ConPool;

import java.sql.*;

public class ClienteDAO extends UtenteRegistratoDAO{
/*
    public Cliente findByEmail(String email) {
            Cliente ct = new Cliente();
            try (Connection con = ConPool.getConnection()) {
                PreparedStatement ps = con.prepareStatement("SELECT id,nome, cognome, ddn, username,email, passworduser,admin  FROM Cliente where username=? and passworduser=SHA1(?) ");
                ps.setString(1, user);
                ps.setString(2, pass);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    ut.setId(rs.getInt(1));
                    ut.setNome(rs.getString(2));
                    ut.setCognome(rs.getString(3));
                    ut.setDdn(rs.getDate(4));
                    ut.setUsername(rs.getString(5));
                    ut.setEmail(rs.getString(6));
                    ut.setPassword(rs.getString(7));
                    ut.setAdmin(rs.getBoolean(8));
                    return ut;
                }
                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
*/

}
