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
GestioneProdottoDAO prodDB = new GestioneProdottoDAO();

    public void doSaveOrdine(Ordine ord) {
        try {
            Connection con = ConPool.getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO ordine (email_cliente,data_ordine,indirizzo,prezzo_tot) VALUES(?,?,?,?)", 1);
                ps.setString(1, ord.getEmailCliente());
                Date sqlDate = new Date(ord.getData().getTime());
                ps.setDate(2, sqlDate);
                ps.setString(3,ord.getIndirizzo());
                ps.setFloat(4,ord.getPrezzo_tot());
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

    public ArrayList<Ordine> doRetrieveAllOrdine() {
        ArrayList list = new ArrayList();

        try {
            Connection con = ConPool.getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT id, email_cliente, data_ordine,indirizzo,prezzo_tot FROM ordine");
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
                                ((Ordine)list.get(i)).setProdottoHash(this.prodDB.doRetrieveById(rs.getInt(2)));
                                ((Ordine)list.get(i)).setQuantHash(rs.getInt(2), rs.getInt(3));
                                ((Ordine)list.get(i)).getProdotto(rs.getInt(2)).setDisponibilita(rs.getInt(4));
                            }

                            ++i;
                        }
                    }

                    Ordine p = new Ordine();
                    p.setId(rs.getInt(1));
                    p.setEmailCliente(rs.getString(2));
                    p.setDataSql(rs.getDate(3));
                    p.setIndirizzo(rs.getString(4));
                    p.setPrezzo_tot(rs.getFloat(5));
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


    public ArrayList<Ordine> doRetrieveOrdiniByEmailCliente(String email_cliente) {
        ArrayList list = new ArrayList();

        try {
            Connection con = ConPool.getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT id,email_cliente,data_ordine,indirizzo,prezzo_tot  FROM ordine where email_cliente = ?");
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
                    p.setEmailCliente(rs.getString(2));
                    p.setDataSql(rs.getDate(3));
                    p.setIndirizzo(rs.getString(4));
                    p.setPrezzo_tot(rs.getFloat(5));
                    list.add(p);
                }
            } catch (Throwable var8) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                    }
                }

                throw var8;
            }

            if (con != null) {
                con.close();
            }

            return list;
        } catch (SQLException var9) {
            throw new RuntimeException(var9);
        }
    }

   public ArrayList<Ordine> doRetrieveOrdiniByEmailVenditore(String email_venditore) {
        ArrayList list = new ArrayList();

        try {
            Connection con = ConPool.getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT DISTINCT ord.id,ord.email_cliente,ord.data_ordine,ord.indirizzo,ord.prezzo_tot  FROM ordine ord,prodotto pr,ordine_prodotto orpr where ord.id=orpr.id_ordine and orpr.id_prodotto=pr.id and pr.email_venditore=?");
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
                    p.setEmailCliente(rs.getString(2));
                    p.setDataSql(rs.getDate(3));
                    p.setIndirizzo(rs.getString(4));
                    p.setPrezzo_tot(rs.getFloat(5));
                    list.add(p);
                }
            } catch (Throwable var8) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                    }
                }

                throw var8;
            }

            if (con != null) {
                con.close();
            }

            return list;
        } catch (SQLException var9) {
            throw new RuntimeException(var9);
        }
    }

}
