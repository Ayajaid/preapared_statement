package ma.projet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.projet.beans.Developpeur;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeveloppeurService implements IDao<Developpeur> {
    private static final Logger LOGGER = Logger.getLogger(EmployeService.class.getName());

    @Override
    public boolean create(Developpeur o) {
        boolean r = false;
        try {
            String req = "INSERT INTO developpeur (id, nom, salaire, manager_id) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getId());
            ps.setString(2, o.getNom());
            ps.setDouble(3, o.getSalaire());
            ps.setInt(4, o.getManagerId()); // Ajout de l'ID du manager
            int n = ps.executeUpdate();
            r = (n == 1);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return r;
    }

    @Override
    public boolean update(Developpeur o) {
        boolean r = false;
        try {
            String req = "UPDATE developpeur SET nom = ?, salaire = ? WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setDouble(2, o.getSalaire());
            ps.setInt(3, o.getId());
            int n = ps.executeUpdate();
            r = (n == 1);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return r;
    }

    @Override
    public boolean delete(Developpeur o) {
        boolean r = false;
        try {
            String req = "DELETE FROM developpeur WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getId());
            int n = ps.executeUpdate();
            r = (n == 1);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return r;
    }

    @Override
    public Developpeur getById(int id) {
        Developpeur developpeur = null;
        try {
            String req = "SELECT * FROM developpeur WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                developpeur = new Developpeur(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire"));
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return developpeur;
    }

    @Override
    public List<Developpeur> getAll() {
        List<Developpeur> developpeurs = new ArrayList<>();
        try {
            String req = "SELECT * FROM developpeur";
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                developpeurs.add(new Developpeur(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire")));
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return developpeurs;
    }
}