package com.example.todolistjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import user.Bdd;
import user.ListeController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModifierListe {
    private int id_user;
    @FXML
    private TextField description;

    @FXML
    private TextField nom;
    Bdd bdd = new Bdd();
    private int id_liste;


    public ModifierListe(int id_user, int id_liste) {
        this.id_user = id_user;
        this.id_liste = id_liste;
    }

    public void modifier() throws SQLException {
        if (!this.nom.getText().isEmpty()) {
            PreparedStatement requete = bdd.getConnexion().prepareStatement("UPDATE liste SET nom = ? WHERE id_liste = ?");
            requete.setString(1, this.nom.getText());
            requete.setInt(2, this.id_liste);
            requete.executeUpdate();
            this.nom.setPromptText(this.nom.getText());
            this.nom.setText("");
        }
        if (!this.description.getText().isEmpty()) {
            PreparedStatement requete = bdd.getConnexion().prepareStatement("UPDATE liste SET description = ? WHERE id_liste = ?");
            requete.setString(1, this.description.getText());
            requete.setInt(2, this.id_liste);
            requete.executeUpdate();
            this.description.setPromptText(this.description.getText());
            this.description.setText("");
        }
    }

    public void modifierAfficher() throws SQLException {
        PreparedStatement requete = bdd.getConnexion().prepareStatement("SELECT * FROM liste WHERE id_liste = ?");
        requete.setInt(1, this.id_liste);
        ResultSet resultSet = requete.executeQuery();
        if (resultSet.next()) {
            this.nom.setPromptText(resultSet.getString(2));
            this.description.setPromptText(resultSet.getString(3));
        }
    }

    public void retour() throws SQLException {
        AfficherListes afficherListes = new AfficherListes(this.id_user);
        ListeController listecontroller = new ListeController();
        HelloApplication.changeScene("afficherlistes", afficherListes);
        afficherListes.setItems(listecontroller.getAll(this.id_user));
    }
}

