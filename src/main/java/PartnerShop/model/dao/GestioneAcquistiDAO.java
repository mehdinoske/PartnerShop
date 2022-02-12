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
    private GestioneProdottoDAO prodDB = new GestioneProdottoDAO();
    private Connection con;

    public GestioneAcquistiDAO(){
        this.con = ConPool.getConnection();
    }
    public void doSaveOrdine(Ordine ord) {
        try {
            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO ordine (nome,cognome,email_cliente,data_ordine,indirizzo,prezzo_tot) VALUES(?,?,?,?,?,?)", 1);
                ps.setString(1, ord.getNome());
                ps.setString(2,ord.getCognome());
                ps.setString(3, ord.getEmailCliente());
                Date sqlDate = new Date(ord.getData().getTime());
                ps.setDate(4, sqlDate);
                ps.setString(5,ord.getIndirizzo());
                ps.setFloat(6,ord.getPrezzo_tot());
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("Insert error.");
                }

                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                ord.setId(rs.getInt(1));
                Iterator var6 = ord.getProdottoHash().keySet().iterator();

                while(var6.hasNext()) {
                    Integer key = (Integer)var6.next();
                    ps = con.prepareStatement("INSERT INTO ordine_prodotto (id_ordine,id_prodotto,quantita) VALUES(?,?,?)");
                    ps.setInt(1, ord.getId());
                    ps.setInt(2, key);
                    ps.setInt(3, ord.getQuant(key));
                    if (ps.executeUpdate() != 1) {
                        throw new RuntimeException("Update error.");
                    }
                }
            } catch (Throwable var9) {

                throw var9;
            }

        } catch (SQLException var10) {
            throw new RuntimeException(var10);
        }
    }


    public ArrayList<Ordine> doRetrieveOrdiniByEmailCliente(String email_cliente) {
        ArrayList list = new ArrayList();

        try {
            try {
                PreparedStatement ps = con.prepareStatement("SELECT id,nome,cognome,email_cliente,data_ordine,indirizzo,prezzo_tot  FROM ordine where email_cliente = ?");
                ps.setString(1, email_cliente);
                ResultSet rs = ps.executeQuery();

                label50:
                while(true) {
                    if (!rs.next()) {
                        int i = 0;

                        while(true) {
                            if (i >= list.size()) {
                                break label50;
                            }

                            ps = con.prepareStatement("SELECT id_ordine,id_prodotto,quantita FROM ordine_prodotto where id_ordine = ?");
                            ps.setInt(1, ((Ordine)list.get(i)).getId());
                            rs = ps.executeQuery();

                            while(rs.next()) {
                                ((Ordine)list.get(i)).setProdottoHash(this.prodDB.doRetrieveById(rs.getInt(2)));
                                ((Ordine)list.get(i)).setQuantHash(rs.getInt(2), rs.getInt(3));
                                ((Ordine)list.get(i)).getProdotto(rs.getInt(2)).setDisponibilita(rs.getInt(3));
                            }

                            ++i;
                        }
                    }

                    Ordine p = new Ordine();
                    p.setId(rs.getInt(1));
                    p.setNome(rs.getString(2));
                    p.setCognome(rs.getString(3));
                    p.setEmailCliente(rs.getString(4));
                    p.setDataSql(rs.getDate(5));
                    p.setIndirizzo(rs.getString(6));
                    p.setPrezzo_tot(rs.getFloat(7));
                    list.add(p);
                }
            } catch (Throwable var8) {

                throw var8;
            }


            return list;
        } catch (SQLException var9) {
            throw new RuntimeException(var9);
        }
    }

   public ArrayList<Ordine> doRetrieveOrdiniByEmailVenditore(String email_venditore) {
        ArrayList list = new ArrayList();

        try {
            try {
                PreparedStatement ps = con.prepareStatement("SELECT DISTINCT ord.id,ord.nome,ord.cognome,ord.email_cliente,ord.data_ordine,ord.indirizzo,ord.prezzo_tot  FROM ordine ord,prodotto pr,ordine_prodotto orpr where ord.id=orpr.id_ordine and orpr.id_prodotto=pr.id and pr.email_venditore=?");
                ps.setString(1, email_venditore);
                ResultSet rs = ps.executeQuery();

                label50:
                while(true) {
                    if (!rs.next()) {
                        int i = 0;

                        while(true) {
                            if (i >= list.size()) {
                                break label50;
                            }

                            ps = con.prepareStatement("SELECT DISTINCT orpr.id_ordine,orpr.id_prodotto,orpr.quantita FROM ordine_prodotto orpr,prodotto pr where orpr.id_ordine = ? and pr.email_venditore = ?");
                            ps.setInt(1, ((Ordine)list.get(i)).getId());
                            ps.setString(2,email_venditore);
                            rs = ps.executeQuery();

                            while(rs.next()) {
                                ((Ordine)list.get(i)).setProdottoHash(this.prodDB.doRetrieveById(rs.getInt(2)));
                                ((Ordine)list.get(i)).setQuantHash(rs.getInt(2), rs.getInt(3));
                                ((Ordine)list.get(i)).getProdotto(rs.getInt(2)).setDisponibilita(rs.getInt(3));
                            }

                            ++i;
                        }
                    }

                    Ordine p = new Ordine();
                    p.setId(rs.getInt(1));
                    p.setNome(rs.getString(2));
                    p.setCognome(rs.getString(3));
                    p.setEmailCliente(rs.getString(4));
                    p.setDataSql(rs.getDate(5));
                    p.setIndirizzo(rs.getString(6));
                    p.setPrezzo_tot(rs.getFloat(7));
                    list.add(p);
                }
            } catch (Throwable var8) {

                throw var8;
            }


            return list;
        } catch (SQLException var9) {
            throw new RuntimeException(var9);
        }
    }

}
