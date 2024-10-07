/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.beans;



import java.util.ArrayList;
import java.util.List;

import java.util.List;

public class Manager  {
    private int id;
    private String nom;
    private double salaire;
    
    private List<Developpeur> developpeurs;

    public Manager(int id, String nom, double salaire, List<Developpeur> developpeurs) {
       this.id = id;
        this.nom = nom;
        this.salaire = salaire;
        this.developpeurs = developpeurs;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public double getSalaire() {
        return salaire;
    }

    
        public List<Developpeur> getDeveloppeurs() {
        return developpeurs;
    }
}
