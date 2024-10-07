package ma.projet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ma.projet.beans.Employe;

public class EmployeService implements IDao<Employe> {
    private static final Logger LOGGER = Logger.getLogger(EmployeService.class.getName());

    @Override
    public boolean create(Employe o) {
        boolean r = false;
        try {
            String req = "INSERT INTO employe (id, nom, salaire, poste) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getId());
            ps.setString(2, o.getNom());
            ps.setDouble(3, o.getSalaire());
            ps.setString(4, o.getPoste());
            int n = ps.executeUpdate();
            r = (n == 1);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la création de l'employé : ", ex);
        }
        return r;
    }

    @Override
    public boolean update(Employe o) {
        boolean r = false;
        try {
            String req = "UPDATE employe SET nom = ?, salaire = ?, poste = ? WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setDouble(2, o.getSalaire());
            ps.setString(3, o.getPoste());
            ps.setInt(4, o.getId());
            int n = ps.executeUpdate();
            r = (n == 1);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la mise à jour de l'employé : ", ex);
        }
        return r;
    }

    @Override
    public boolean delete(Employe o) {
        boolean r = false;
        try {
            String req = "DELETE FROM employe WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getId());
            int n = ps.executeUpdate();
            r = (n == 1);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la suppression de l'employé : ", ex);
        }
        return r;
    }

    @Override
    public Employe getById(int id) {
        Employe employe = null;
        try {
            String req = "SELECT * FROM employe WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employe = new Employe(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire"), rs.getString("poste"));
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la récupération de l'employé : ", ex);
        }
        return employe;
    }

    @Override
    public List<Employe> getAll() {
        List<Employe> employes = new ArrayList<>();
        try {
            String req = "SELECT * FROM employe";
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                employes.add(new Employe(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire"), rs.getString("poste")));
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la récupération de tous les employés : ", ex);
        }
        return employes;
    }
}
