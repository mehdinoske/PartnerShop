package model.dao;

public class UtenteDAO{
        public UtenteDAO() {

        }
 /*
        public ArrayList<Utente> doRetrieveAll() {
            ArrayList<Utente> list = new ArrayList<>();
            try (Connection con = ConPool.getConnection()) {
                PreparedStatement ps = con.prepareStatement("SELECT id,nome, cognome, ddn, username,email, passworduser,admin  FROM Utente");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Utente ut = new Utente();
                    ut.setId(rs.getInt(1));
                    ut.setNome(rs.getString(2));
                    ut.setCognome(rs.getString(3));
                    ut.setDdn(rs.getDate(4));
                    ut.setUsername(rs.getString(5));
                    ut.setEmail(rs.getString(6));
                    ut.setPassword(rs.getString(7));
                    ut.setAdmin(rs.getBoolean(8));
                    list.add(ut);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return list;
        }

        public Utente doRetrieveByUsernamePass(String user, String pass) {
            Utente ut = new Utente();
            try (Connection con = ConPool.getConnection()) {
                PreparedStatement ps = con.prepareStatement("SELECT id,nome, cognome, ddn, username,email, passworduser,admin  FROM Utente where username=? and passworduser=SHA1(?) ");
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

        public Utente doRetrieveByUsername(String user) {
            Utente ut = new Utente();
            try (Connection con = ConPool.getConnection()) {
                PreparedStatement ps = con.prepareStatement("SELECT id,nome, cognome, ddn, username,email, passworduser,admin  FROM Utente where username=? ");
                ps.setString(1, user);
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

        public void doSave(Utente ut) {
            try (Connection con = ConPool.getConnection()) {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO Utente (nome, cognome, ddn, username,email, passworduser) VALUES(?,?,?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, ut.getNome());
                ps.setString(2, ut.getCognome());
                ps.setDate(3, ut.getDdn());
                ps.setString(4, ut.getUsername());
                ps.setString(5, ut.getEmail());
                ps.setString(6, ut.getPassword());
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("INSERT error.");
                }
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                ut.setId(rs.getInt(1));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        public void doUpdate(Utente ut, int id) {
            try (Connection con = ConPool.getConnection()) {
                PreparedStatement ps = con.prepareStatement(
                        "UPDATE Utente SET nome = ?, cognome = ?, ddn = ?, username=? , email=?, admin=?, id=? WHERE id=?");
                ps.setString(1, ut.getNome());
                ps.setString(2, ut.getCognome());
                ps.setDate(3, ut.getDdn());
                ps.setString(4, ut.getUsername());
                ps.setString(5, ut.getEmail());
                ps.setBoolean(6, ut.isAdmin());
                ps.setInt(7, id);
                ps.setInt(8, ut.getId());
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("UPDATE error.");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void doDelete(int idUtente) {
            try (Connection con = ConPool.getConnection()) {
                PreparedStatement ps = con.prepareStatement("DELETE FROM Utente WHERE  id=? ");
                ps.setInt(1, idUtente);
                if (ps.executeUpdate() != 1) {
                    throw new RuntimeException("DELETE error.");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }*/
}