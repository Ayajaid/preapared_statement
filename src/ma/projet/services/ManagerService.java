package ma.projet.services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.projet.beans.Manager;
import ma.projet.beans.Developpeur;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;

public class ManagerService implements IDao<Manager> {
    private static final Logger LOGGER = Logger.getLogger(ManagerService.class.getName());

    @Override
    public boolean create(Manager o) {
        boolean r = false;
        try {
            String req = "INSERT INTO manager (id, nom, salaire) VALUES (?, ?, ?)";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getId());
            ps.setString(2, o.getNom());
            ps.setDouble(3, o.getSalaire());
            int n = ps.executeUpdate();
            r = (n == 1);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la création du manager : ", ex);
        }
        return r;
    }

    @Override
    public boolean update(Manager o) {
        boolean r = false;
        try {
            String req = "UPDATE manager SET nom = ?, salaire = ? WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setDouble(2, o.getSalaire());
            ps.setInt(3, o.getId());
            int n = ps.executeUpdate();
            r = (n == 1);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la mise à jour du manager : ", ex);
        }
        return r;
    }

    @Override
    public boolean delete(Manager o) {
        boolean r = false;
        try {
            String req = "DELETE FROM manager WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getId());
            int n = ps.executeUpdate();
            r = (n == 1);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la suppression du manager : ", ex);
        }
        return r;
    }

    @Override
    public Manager getById(int id) {
        Manager manager = null;
        try {
            String req = "SELECT * FROM manager WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                manager = new Manager(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire"), new ArrayList<>());
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la récupération du manager : ", ex);
        }
        return manager;
    }

    @Override
    public List<Manager> getAll() {
        List<Manager> managers = new ArrayList<>();
        try {
            String req = "SELECT * FROM manager";
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                managers.add(new Manager(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire"), new ArrayList<>()));
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la récupération de tous les managers : ", ex);
        }
        return managers;
    }

    public List<Developpeur> getDeveloppeursByManagerId(int managerId) {
        List<Developpeur> developpeurs = new ArrayList<>();
        try {
            String req = "SELECT * FROM developpeur WHERE manager_id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, managerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                developpeurs.add(new Developpeur(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire")));
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la récupération des développeurs : ", ex);
        }
        return developpeurs;
    }
}
