/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ma.projet.beans;

public class Developpeur {
    private int managerId;
    private int id;
    private String nom;
    private double salaire;

    public Developpeur(int id, String nom, double salaire) {
        this.id = id;
        this.nom = nom;
        this.salaire = salaire;
        this.managerId = -1; // Initialize with a default value
    }

    public Developpeur(int id, String nom, double salaire, int managerId) {
        this.id = id;
        this.nom = nom;
        this.salaire = salaire;
        this.managerId = managerId;
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

    public int getManagerId() {
        return managerId;
    }
}


    

