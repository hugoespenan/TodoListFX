package com.example.todolistjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import user.Bdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModifierTache {
    private int id_user;
    private int id_liste;

    private int id_tache;
    Bdd bdd = new Bdd();
    @FXML
    private TextField description;

    @FXML
    private CheckBox est_realise;

    @FXML
    private TextField nom;

    public ModifierTache(int id_user, int id_tache, int id_liste) {
        this.id_user = id_user;
        this.id_tache = id_tache;
        this.id_liste = id_liste;
    }
    public void modifierAfficher() throws SQLException {
        PreparedStatement requete = bdd.getConnexion().prepareStatement("SELECT * FROM tache WHERE id_tache = ?");
        requete.setInt(1, this.id_tache);
        ResultSet resultSet = requete.executeQuery();
        if (resultSet.next()){
            this.nom.setPromptText(resultSet.getString(2));
            this.description.setPromptText(resultSet.getString(3));
            this.est_realise.setSelected(resultSet.getBoolean(4));
        }
    }
    public void modifier() throws SQLException {
        if (!this.nom.getText().isEmpty()){
            PreparedStatement requete = bdd.getConnexion().prepareStatement("UPDATE tache SET nom = ? WHERE id_tache = ?");
            requete.setString(1, this.nom.getText());
            requete.setInt(2,id_tache);
            requete.executeUpdate();
            this.nom.setPromptText(this.nom.getText());
            this.nom.setText("");
        }
        if (!this.description.getText().isEmpty()){
            PreparedStatement requete = bdd.getConnexion().prepareStatement("UPDATE tache SET description = ? WHERE id_tache = ?");
            requete.setString(1, this.description.getText());
            requete.setInt(2,id_tache);
            requete.executeUpdate();
            this.description.setPromptText(this.description.getText());
            this.description.setText("");
        }
        if (!this.est_realise.getText().isEmpty()){
            PreparedStatement requete = bdd.getConnexion().prepareStatement("UPDATE tache SET est_realise = ? WHERE id_tache = ?");
            requete.setBoolean(1, this.est_realise.isSelected());
            requete.setInt(2,id_tache);
            requete.executeUpdate();
        }
    }
    public void retour() throws SQLException {
        AfficherTaches afficherTaches = new AfficherTaches(this.id_user, this.id_liste);
        HelloApplication.changeScene("affichertaches", afficherTaches);
    }

}
