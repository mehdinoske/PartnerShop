package PartnerShop.model.dao;

import PartnerShop.model.entity.Carrello;
import PartnerShop.model.entity.Prodotto;
import PartnerShop.utils.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class CarrelloDAO {
    private GestioneAcquistiDAO prodottoDB = new GestioneAcquistiDAO();

    public CarrelloDAO() {
    }

    public Carrello doRetrieveByEmailCliente(String emailCliente, Carrello car) {
        try {
            Connection con = ConPool.getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT cp.id_Prodotto FROM carrello_prodotto cp, carrello c WHERE c.id = cp.id_Carrello and email_Cliente = ?");
                ps.setString(1, emailCliente);
                ResultSet rs = ps.executeQuery();

                while(rs.next()) {
                    int cod = rs.getInt(1);
                    int quant = rs.getInt(2);
                    Prodotto prodCar = car.getProdotto(cod);
                    if (prodCar == null) {
                        car.setProdottoHash(this.prodottoDB.doRetrieveProdottoById(cod));
                        car.setQuantHash(cod, quant);
                    }
                }
            } catch (Throwable var10) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var9) {
                        var10.addSuppressed(var9);
                    }
                }

                throw var10;
            }

            if (con != null) {
                con.close();
            }

            return car;
        } catch (SQLException var11) {
            throw new RuntimeException(var11);
        }
    }



    public void UpdateSession(Carrello car, String emailCliente,int id_Carrello) {
        try {
            Connection con = ConPool.getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT cp.id_Prodotto,cp.quantita FROM carrello_prodotto cp, carrello c WHERE c.id = cp.id_Carrello and email_Cliente = ? and id_Prodotto = ?");
                Iterator var5 = car.getProdottoHash().keySet().iterator();

                while(var5.hasNext()) {
                    Integer key = (Integer)var5.next();
                    ps.setString(1, emailCliente);
                    ps.setInt(2, key);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        this.doSave(key, id_Carrello, car.getQuant(key));
                    }

                    if (rs.next()) {
                        this.doUpdate(id_Carrello, key, car.getQuant(key));
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

    public void doSave(int id_Prodotto,int id_Carrello, int quant) {
        try {
            Connection con = ConPool.getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO carrello_prodotto (id_Carrello,id_Prodotto,quantita) VALUES(?,?,?)");
                ps.setInt(1, id_Carrello);
                ps.setInt(2, id_Prodotto);
                ps.setInt(3, quant);
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("Insert error.");
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

        } catch (SQLException var9) {
            throw new RuntimeException(var9);
        }
    }

    public void doCreateCarrello(String emailCliente) {
        try {
            Connection con = ConPool.getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO carrello (email_Cliente) VALUES(?)");
                ps.setString(1, emailCliente);
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("Insert error.");
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

        } catch (SQLException var9) {
            throw new RuntimeException(var9);
        }
    }

    public void doUpdate(int id_Carrello, int idProd, int quant) {
        try {
            Connection con = ConPool.getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("UPDATE carrello_prodotto SET quantita=? WHERE  idProdotti=? AND id_Carrello=?");
                ps.setInt(1, quant);
                ps.setInt(2, idProd);
                ps.setInt(3, id_Carrello);
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("Update error.");
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

        } catch (SQLException var9) {
            throw new RuntimeException(var9);
        }
    }

    public void doDelete(int idCarrello, int idProd) {
        try {
            Connection con = ConPool.getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("DELETE FROM carrello_prodotto WHERE  id_Carrello=? AND idProdotti=?");
                ps.setInt(1, idCarrello);
                ps.setInt(2, idProd);
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("DELETE error.");
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

        } catch (SQLException var8) {
            throw new RuntimeException(var8);
        }
    }
}
