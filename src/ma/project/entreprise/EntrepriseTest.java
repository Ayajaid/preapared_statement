/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.project.entreprise;

/**
 *
 * @author Pc
 */

   
import ma.projet.beans.Developpeur;
import ma.projet.beans.Manager;
import ma.projet.services.EmployeService;
import ma.projet.services.ManagerService;
import ma.projet.services.DeveloppeurService;


import java.util.ArrayList;
import java.util.List;
import ma.projet.beans.Employe;

public class EntrepriseTest {
    public static void main(String[] args) {
        ManagerService managerService = new ManagerService();
        DeveloppeurService developpeurService = new DeveloppeurService();
        EmployeService employeService = new EmployeService();
        
        Employe directeur = new Employe(1, "Farih", 45000,"directeur general") ;
        employeService.create(directeur);
        // Créer un manager
        Manager manager = new Manager(1, "Salim", 25000, new ArrayList<>());
        managerService.create(manager);

        // Créer des développeurs
        Developpeur dev1 = new Developpeur(1, "Alaoui", 10000, manager.getId());
        Developpeur dev2 = new Developpeur(2, "Alami", 10000, manager.getId());
        developpeurService.create(dev1);
        developpeurService.create(dev2);

        List<Employe> employe = employeService.getAll();
        for (Employe m : employe) {
            System.out.println(m.getPoste() +" :"+ m.getNom() + ", Salaire: " + m.getSalaire());}
        
        List<Manager> managers = managerService.getAll();
        for (Manager m : managers) {
            System.out.println("Manager: " + m.getNom() + ", Salaire: " + m.getSalaire());
           
            List<Developpeur> devs = managerService.getDeveloppeursByManagerId(m.getId());
            for (Developpeur d : devs) {
                System.out.println("  Développeur: " + d.getNom() + ", Salaire: " + d.getSalaire());
            }
        }
    }
}


