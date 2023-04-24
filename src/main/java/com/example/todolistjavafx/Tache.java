package com.example.todolistjavafx;

public class Tache {
    private int identifiant;
    private String nom;
    private String description;
    private boolean est_realise;
    private String ref_liste;
    private String ref_type;

    public Tache(int identifiant, String nom, String description, boolean est_realise, String ref_liste, String ref_type) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.description = description;
        this.est_realise = est_realise;
        this.ref_liste = ref_liste;
        this.ref_type = ref_type;
    }

    public int getIdentifiant() {
        return this.identifiant;
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

    public boolean isEst_realise() {
        return est_realise;
    }

    public void setEst_realise(boolean est_realise) {
        this.est_realise = est_realise;
    }

    public String getRef_liste() {
        return ref_liste;
    }

    public void setRef_liste(String ref_liste) {
        this.ref_liste = ref_liste;
    }

    public String getRef_type() {
        return ref_type;
    }

    public void setRef_type(String ref_type) {
        this.ref_type = ref_type;
    }
}
