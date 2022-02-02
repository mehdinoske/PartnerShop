package PartnerShop.model.dao;
import PartnerShop.model.entity.Amministratore;
import PartnerShop.model.entity.Cliente;
import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.utils.ConPool;

import java.sql.*;
import java.util.ArrayList;

public class UtenteRegistratoDAO {
    private Connection con;

    public UtenteRegistratoDAO(){
        this.con = ConPool.getConnection();
    }
        public ArrayList<UtenteRegistrato> doRetrieveAll() {
            ArrayList<UtenteRegistrato> list = new ArrayList<>();
            try  {
                PreparedStatement ps = con.prepareStatement("SELECT nome, cognome, ddn, username,email, passwordhash  FROM utente_registrato");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    UtenteRegistrato ut = new UtenteRegistrato();
                    ut.setNome(rs.getString(1));
                    ut.setCognome(rs.getString(2));
                    ut.setDdn(rs.getString(3));
                    ut.setUsername(rs.getString(4));
                    ut.setEmail(rs.getString(5));
                    ut.setPassword(rs.getString(6));
                    list.add(ut);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return list;
        }

    public void doSave(UtenteRegistrato ut,int tipo) {
        try  {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO utente_registrato (nome, cognome, ddn,email,indirizzo,username,passwordhash,cellulare,tipo) VALUES(?,?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ut.getNome());
            ps.setString(2, ut.getCognome());
            ps.setString(3, ut.getDdn());
            ps.setString(4, ut.getEmail());
            ps.setString(5, ut.getIndirizzo());
            ps.setString(6, ut.getUsername());
            ps.setString(7, ut.getPassword());
            ps.setString(8, ut.getCellulare());
            ps.setInt(9,tipo);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public UtenteRegistrato doRetrieveByUsernamePass(String user, String pass) {
            UtenteRegistrato ut = new UtenteRegistrato();
            try  {
                PreparedStatement ps = con.prepareStatement("SELECT nome, cognome, ddn,email,indirizzo,username,passwordhash,tipo FROM utente_registrato where username=? and passwordhash=SHA1(?) ");
                ps.setString(1, user);
                ps.setString(2, pass);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    ut.setNome(rs.getString(1));
                    ut.setCognome(rs.getString(2));
                    ut.setDdn(rs.getString(3));
                    ut.setEmail(rs.getString(4));
                    ut.setIndirizzo(rs.getString(5));
                    ut.setUsername(rs.getString(6));
                    ut.setPassword(rs.getString(7));
                    ut.setTipo(rs.getInt(8));
                    return ut;
                }
                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    public UtenteRegistrato doRetrieveByUsername(String user) {
        UtenteRegistrato ut = new UtenteRegistrato();
        try  {
            PreparedStatement ps = con.prepareStatement("SELECT nome, cognome, ddn,email,indirizzo,username,passwordhash,tipo FROM utente_registrato where username=?");
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ut.setNome(rs.getString(1));
                ut.setCognome(rs.getString(2));
                ut.setDdn(rs.getString(3));
                ut.setEmail(rs.getString(4));
                ut.setIndirizzo(rs.getString(5));
                ut.setUsername(rs.getString(6));
                ut.setPassword(rs.getString(7));
                ut.setTipo(rs.getInt(8));
                return ut;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UtenteRegistrato doRetrieveByEmail(String email) {
        UtenteRegistrato ut = new UtenteRegistrato();
        try  {
            PreparedStatement ps = con.prepareStatement("SELECT nome, cognome, ddn,email,indirizzo,username,passwordhash,tipo FROM utente_registrato where email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ut.setNome(rs.getString(1));
                ut.setCognome(rs.getString(2));
                ut.setDdn(rs.getString(3));
                ut.setEmail(rs.getString(4));
                ut.setIndirizzo(rs.getString(5));
                ut.setUsername(rs.getString(6));
                ut.setPassword(rs.getString(7));
                ut.setTipo(rs.getInt(8));
                return ut;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(String email) {
        try  {
            PreparedStatement ps = con.prepareStatement("DELETE FROM utente_registrato WHERE  email=? ");
            ps.setString(1, email);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(UtenteRegistrato ut) {
        try  {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE utente_registrato SET nome = ?, cognome = ?, ddn = ?, email=?, indirizzo=?, username=? , passwordhash=?, tipo=? WHERE email=?");
            ps.setString(1, ut.getNome());
            ps.setString(2, ut.getCognome());
            ps.setString(3, ut.getDdn());
            ps.setString(4, ut.getEmail());
            ps.setString(5, ut.getIndirizzo());
            ps.setString(6, ut.getUsername());
            ps.setString(7, ut.getPassword());
            ps.setInt(8, ut.getTipo());
            ps.setString(9, ut.getEmail());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Amministratore doRetrieveAdmin(String username,String password) {
        Amministratore amm = new Amministratore();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT id, username, passwordhash FROM amministratore where username=? and passwordhash=SHA1(?)  ");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                amm.setId(rs.getInt(1));
                amm.setUsername(rs.getString(2));
                amm.setPassword(rs.getString(3));

                return amm;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}