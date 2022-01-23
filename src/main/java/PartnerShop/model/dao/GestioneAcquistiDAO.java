package PartnerShop.model.dao;

import PartnerShop.model.entity.Ordine;
import PartnerShop.model.entity.Prodotto;
import PartnerShop.model.entity.UtenteRegistrato;
import PartnerShop.utils.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class GestioneAcquistiDAO {

    public ArrayList<Prodotto> doRetrieveAllProdotti() {
        ArrayList<Prodotto> list = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT id,nome,descrizione,categoria,prezzo_cent,quantita_disponibile FROM prodotto");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Prodotto pr = new Prodotto();
                pr.setId(rs.getInt(1));
                pr.setNome(rs.getString(2));
                pr.setDescrizione(rs.getString(3));
                pr.setCategoria(rs.getString(4));
                pr.setPrezzo_Cent(rs.getLong(5));
                pr.setDisponibilita(rs.getInt(6));
                list.add(pr);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Prodotto doRetrieveProdottoById(int idProdotto) {
        Prodotto pr = new Prodotto();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT email_venditore,nome,descrizione,categoria,prezzo_cent,quantita_disponibile FROM prodotto where id = ?");
            ps.setInt(1,idProdotto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pr.setId(idProdotto);
                pr.setEmail_Venditore(rs.getString(1));
                pr.setNome(rs.getString(2));
                pr.setDescrizione(rs.getString(3));
                pr.setCategoria(rs.getString(4));
                pr.setPrezzo_Cent(rs.getLong(5));
                pr.setDescrizione(rs.getString(6));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pr;
    }



    /*public ArrayList<Prodotto> doRetrieveProdottiByIdOrdine(int idOrdine) {
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT id,nome,descrizione,categoria,prezzo,quantita_disponbile FROM prodotto WHERE id_Ordine = ? ");
            ResultSet rs = ps.executeQuery();
            ps.setInt(1,idOrdine);
            while (rs.next()) {
                Prodotto pr = new Prodotto();
                pr.setId(rs.getInt(1));
                pr.setNome(rs.getString(2));
                pr.setDescrizione(rs.getString(3));
                pr.setCategoria(rs.getString(4));
                pr.setPrezzo_Cent(rs.getInt(5));
                pr.setDisponibilita(rs.getInt(6));
                prodotti.add(pr);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prodotti;
    }

    public int doSaveOrdine(UtenteRegistrato ut,String indirizzo,float prezzo_tot) {
        int id_Ordine = 0;
        try (Connection con = ConPool.getConnection()) {

            GregorianCalendar dataAttuale = new GregorianCalendar();
            String data = dataAttuale.get(GregorianCalendar.DATE)+dataAttuale.get(GregorianCalendar.MONTH)+dataAttuale.get(GregorianCalendar.DAY_OF_MONTH)+"";
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO ordine (email_cliente,data_ordine,indirizzo,prezzo_tot) VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,ut.getEmail());
            ps.setString(2,data);
            ps.setString(3,indirizzo);
            ps.setFloat(4,prezzo_tot);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            id_Ordine = rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id_Ordine;
    }

    public void doSaveProdottiInOrdine(Prodotto pr,int id_Ordine,int quantita) {
        try (Connection con = ConPool.getConnection()) {

            GregorianCalendar dataAttuale = new GregorianCalendar();
            String data = dataAttuale.get(GregorianCalendar.DATE)+dataAttuale.get(GregorianCalendar.MONTH)+dataAttuale.get(GregorianCalendar.DAY_OF_MONTH)+"";
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO ordine_prod (email_cliente,data_ordine,indirizzo,prezzo_tot) VALUES(?,?,?,?)");

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }*/

    public void doSaveOrdine(Ordine ord) {
        try {
            Connection con = ConPool.getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO ordine (email_cliente,data_ordine,indirizzo,prezzo_tot) VALUES(?,?,?,?)", 1);
                ps.setString(1, ord.getEmailCliente());
                Date sqlDate = new Date(ord.getData().getTime());
                ps.setDate(2, sqlDate);
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("Insert error.");
                }

                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                ord.setId(rs.getInt(1));
                Iterator var6 = ord.getProdottoHash().keySet().iterator();

                while(var6.hasNext()) {
                    Integer key = (Integer)var6.next();
                    ps = con.prepareStatement("INSERT INTO ordine_prodotto (id_ordine,id_prodotto,quantita) VALUES(?,?,?,?)");
                    ps.setInt(1, ord.getId());
                    ps.setInt(2, key);
                    ps.setInt(3, ord.getQuant(key));
                    ps.setDouble(4, ord.getProdotto(key).getPrezzo_Cent());
                    if (ps.executeUpdate() != 1) {
                        throw new RuntimeException("Update error.");
                    }
                }
            } catch (Throwable var9) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var8) {
                        var9.addSuppressed(var8);
                    }
                }

                throw var9;
            }

            if (con != null) {
                con.close();
            }

        } catch (SQLException var10) {
            throw new RuntimeException(var10);
        }
    }

    public ArrayList<Ordine> doRetrieveAll() {
        ArrayList list = new ArrayList();

        try {
            Connection con = ConPool.getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT id_ordine, email_cliente, data_ordine,prezzo_tot FROM ordine");
                ResultSet rs = ps.executeQuery();

                label50:
                while(true) {
                    if (!rs.next()) {
                        int i = 0;

                        while(true) {
                            if (i >= list.size()) {
                                break label50;
                            }

                            ps = con.prepareStatement("SELECT idOrdine,idGioco,quantita FROM ordine_prodotto where idOrdine = ?");
                            ps.setInt(1, ((Ordine)list.get(i)).getId());
                            rs = ps.executeQuery();

                            while(rs.next()) {
                                ((Ordine)list.get(i)).setGameHash(this.gameDB.doRetrieveById(rs.getInt(2)));
                                ((Ordine)list.get(i)).setQuantHash(rs.getInt(2), rs.getInt(3));
                                ((Ordine)list.get(i)).getVideogioco(rs.getInt(2)).setPrezzo(rs.getDouble(4));
                            }

                            ++i;
                        }
                    }

                    Ordine p = new Ordine();
                    p.setId(rs.getInt(1));
                    p.setIdUtente(rs.getInt(2));
                    p.setDataSql(rs.getDate(3));
                    list.add(p);
                }
            } catch (Throwable var7) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }
                }

                throw var7;
            }

            if (con != null) {
                con.close();
            }

            return list;
        } catch (SQLException var8) {
            throw new RuntimeException(var8);
        }
    }


}
