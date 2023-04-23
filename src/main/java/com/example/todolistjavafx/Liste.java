package com.example.todolistjavafx;

public class Liste {
    private int identifiant;
    private String nom;
    private String description;

    public Liste(int identifiant, String nom, String description) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.description = description;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
