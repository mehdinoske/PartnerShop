package PartnerShop.model.dao;

import PartnerShop.model.entity.Ordine;
import PartnerShop.utils.ConPool;

import java.sql.*;
import java.util.ArrayList;

public class GestioneAcquistiDAO {

    ArrayList<Prodotto> doRetrieveAllProdotti() {
        ArrayList<Prodotto> list = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT id,nome,descrizione,categoria,prezzo,quantita_disponbile FROM Prodotto");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Prodotto pr = new Prodotto();
                pr.setId(rs.getInt(1));
                pr.setNome(rs.getString(2));
                pr.setDescrizione(rs.getString(3));
                pr.setCategoria(rs.getString(4));
                pr.setPrezzo(rs.getInt(5));
                pr.setQuantita_disponibile(rs.getInt(6));
                list.add(pr);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    ArrayList<Prodotto> doRetrieveProdottiByIdOrdine(int idOrdine) {
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT id,nome,descrizione,categoria,prezzo,quantita_disponbile FROM Prodotto WHERE id_Ordine = ? ");
            ResultSet rs = ps.executeQuery();
            ps.setInt(1,idOrdine);
            while (rs.next()) {
                Prodotto pr = new Prodotto();
                pr.setId(rs.getInt(1));
                pr.setNome(rs.getString(2));
                pr.setDescrizione(rs.getString(3));
                pr.setCategoria(rs.getString(4));
                pr.setPrezzo(rs.getInt(5));
                pr.setQuantita_disponibile(rs.getInt(6));
                prodotti.add(pr);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prodotti;
    }

    void doSaveProdotto(Prodotto pr) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Prodotto (nome,descrizione,categoria,prezzo,quantita_disponbile) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pr.getNome());
            ps.setString(2, pr.getDescrizione());
            ps.setString(3, pr.getCategoria());
            ps.setInt(4, pr.getPrezzo());
            ps.setInt(5, pr.getQuantita_disponibile());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    ArrayList<Ordine> doRetrieveAllOrdini() {
        ArrayList<Ordine> ordini = new ArrayList<>();
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT id,email_Cliente,data_Ordine,indirizzo,prezzo_Tot FROM Ordine");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ordine pr = new Ordine();
                pr.setId(rs.getInt(1));
                pr.setEmailCliente(rs.getString(2));
                pr.setDataOrdine(rs.getString(3));
                pr.setIndirizzo(rs.getString(4));
                pr.setPrezzoTot(rs.getInt(5));
                ordini.add(pr);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ordini;
    }


}
