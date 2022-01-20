package PartnerShop.model.dao;

import PartnerShop.model.entity.Carrello;
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

    public Carrello doRetrieveById(String email, Carrello car) {
        try {
            Connection con = ConPool.getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT idProdotti, quantita FROM Carrello where idUtente=?");
                ps.setInt(1, idUtente);
                ResultSet rs = ps.executeQuery();

                while(rs.next()) {
                    int cod = rs.getInt(1);
                    int quant = rs.getInt(2);
                    Videogioco prodCar = car.getGame(cod);
                    if (prodCar == null) {
                        car.setGameHash(this.gamesDB.doRetrieveById(cod));
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

    public void UpdateSession(Carrello car, int idUtente) {
        try {
            Connection con = ConPool.getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT idProdotti, quantita FROM Carrello where idUtente=? AND idProdotti=?");
                Iterator var5 = car.getGameHash().keySet().iterator();

                while(var5.hasNext()) {
                    Integer key = (Integer)var5.next();
                    ps.setInt(1, idUtente);
                    ps.setInt(2, key);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        this.doSave(key, idUtente, car.getQuant(key));
                    }

                    if (rs.next()) {
                        this.doUpdate(idUtente, key, car.getQuant(key));
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

    public void doSave(int idGioco, int idUtente, int quant) {
        try {
            Connection con = ConPool.getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO Carrello (idUtente,idProdotti,quantita) VALUES(?,?,?)");
                ps.setInt(1, idUtente);
                ps.setInt(2, idGioco);
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

    public void doUpdate(int idUtente, int idProd, int quant) {
        try {
            Connection con = ConPool.getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("UPDATE Carrello SET quantita=? WHERE  idProdotti=? AND idUtente=?");
                ps.setInt(1, quant);
                ps.setInt(2, idProd);
                ps.setInt(3, idUtente);
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

    public void doDelete(int idUtente, int idProd) {
        try {
            Connection con = ConPool.getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("DELETE FROM Carrello WHERE  idUtente=? AND idProdotti=?");
                ps.setInt(1, idUtente);
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
