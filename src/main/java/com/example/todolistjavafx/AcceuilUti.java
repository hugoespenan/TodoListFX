package com.example.todolistjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import user.Bdd;
import user.ListeController;
import user.UtilisateurController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.util.Callback;

public class AcceuilUti {

    @FXML
    private Text nom;
    @FXML
    private Text prenom;
    private int id_user;
    Bdd bdd = new Bdd();
    public AcceuilUti(int id_user){
        this.id_user = id_user;
    }
    public AcceuilUti(){
    }

    @FXML
    public void recherche(ActionEvent event) {
        RechercherTache rechercherTache = new RechercherTache(this.id_user);
        HelloApplication.changeScene("recherchertache",rechercherTache);
    }


    public void setNomPrenom() throws SQLException {
        int id = this.id_user;
        PreparedStatement requete = bdd.getConnexion().prepareStatement("SELECT * FROM user WHERE id_user = ?");
        requete.setInt(1, id);
        ResultSet result = requete.executeQuery();
        String nom1 = "";
        String prenom1 = "";
        if (result.next()){
            nom1 = result.getString(2);
            prenom1 = result.getString(3);
        }
        this.nom.setText(nom1);
        this.prenom.setText(prenom1);
    }
    public void deconnexion(){
        HelloApplication.changeScene("connexionpage", new ConnexionPage());
    }
    @FXML
    void listes(ActionEvent event) throws SQLException {
        AfficherListes afficherListes = new AfficherListes(this.id_user);
        ListeController listecontroller = new ListeController();
        HelloApplication.changeScene("afficherlistes", afficherListes);
        afficherListes.setItems(listecontroller.getAll(this.id_user));
    }





}
