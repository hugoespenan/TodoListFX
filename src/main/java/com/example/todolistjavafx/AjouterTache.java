package com.example.todolistjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import user.Bdd;
import user.TacheController;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AjouterTache {
    @FXML
    private TextField description;

    @FXML
    private Text erreur;
    @FXML
    private CheckBox est_realise;

    @FXML
    private TextField nom;

    @FXML
    private Button retour;

    @FXML
    private Button valider;

    private int id_user;
    private int ref_liste;
    Bdd bdd = new Bdd();

    public AjouterTache(int id_user, int ref_liste) {
        this.id_user = id_user;
        this.ref_liste = ref_liste;
    }

    public void ajouter() throws SQLException {
        if (!this.nom.getText().isEmpty() && !this.description.getText().isEmpty()) {
            PreparedStatement requete = bdd.getConnexion().prepareStatement("INSERT INTO tache (nom, description, est_realise, ref_liste, ref_type) VALUES (?, ?, ?, ?, ?)");
            requete.setString(1, this.nom.getText());
            requete.setString(2, this.description.getText());
            requete.setBoolean(3, this.est_realise.isSelected());
            requete.setInt(4, this.ref_liste);
            requete.setInt(5, this.id_user);
            requete.executeUpdate();
            AfficherTaches afficherTaches = new AfficherTaches(this.id_user, this.ref_liste);
            TacheController tacheController = new TacheController();
            HelloApplication.changeScene("affichertaches", afficherTaches);
            afficherTaches.setItems(tacheController.getAll(this.ref_liste));
        }else {
            this.erreur.setText("Veuillez remplir tout les champs");
        }
    }
    public void retour() throws SQLException {
        AfficherTaches afficherTaches = new AfficherTaches(this.id_user, this.ref_liste);
        HelloApplication.changeScene("affichertaches", afficherTaches);
    }

}
