package PartnerShop.model.dao;

import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.utils.ConPool;

import java.sql.*;

public class ClienteDAO extends UtenteRegistratoDAO{
    private Connection con;

    public ClienteDAO(){
        this.con = ConPool.getConnection();
    }

    public void doSave(String email) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO cliente (email) VALUES(?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, email);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Cliente ct) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE cliente SET creditcard=? WHERE email=?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(2, ct.getEmail());
            ps.setString(1, ct.getCartaDiCredito());
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
