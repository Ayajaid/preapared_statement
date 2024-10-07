/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.beans;

/**
 *
 * @author Pc
 */



public class Employe {
    private int id;
    private String nom;
    private double salaire;
    private String poste;

    public Employe(int id, String nom, double salaire) {
        this.id = id;
        this.nom = nom;
        this.salaire = salaire;
    }

    public Employe(int id, String nom, double salaire, String poste) {
        this.id = id;
        this.nom = nom;
        this.salaire = salaire;
        this.poste = poste;
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

    public String getPoste() {
        return poste;
    }
}
