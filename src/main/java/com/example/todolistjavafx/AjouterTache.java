package com.example.todolistjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import user.Bdd;
import user.TacheController;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AjouterTache {
    @FXML
    private TextField description;

    @FXML
    private Text erreur;
    @FXML
    private CheckBox est_realise;

    @FXML
    private ComboBox<String> type_tache;


    public void initialize() {
        Bdd bdd2 = new Bdd();
        ObservableList<String> types = FXCollections.observableArrayList(); //On va remplir la combobpx des types présents dans la bdd :

        try {
            PreparedStatement requete = bdd2.getConnexion().prepareStatement("SELECT libelle FROM types");
            ResultSet resultSet = requete.executeQuery();
            while (resultSet.next()) {
                types.add(resultSet.getString("libelle"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        type_tache.setItems(types);
        //Je récupère les id des types ci dessous
        type_tache.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    PreparedStatement statement = bdd.getConnexion().prepareStatement("SELECT id_type FROM type WHERE libelle = ?");
                    statement.setString(1, newValue);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        int idType = resultSet.getInt("id_type");
                        // L'id est récupéré (normalement mdr)
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }



    @FXML
    private TextField nom;

    @FXML
    private Button retour;

    @FXML
    private Button valider;

    private int id_user;

    private int ref_liste;
    private int ref_type;
    Bdd bdd = new Bdd();

    public AjouterTache(int id_user, int ref_liste,int ref_type) {
        this.id_user = id_user;
        this.ref_liste = ref_liste;
        this.ref_type = ref_type;
    }

    public void ajouter() throws SQLException {
        if (!this.nom.getText().isEmpty() && !this.description.getText().isEmpty()) {
            PreparedStatement requete = bdd.getConnexion().prepareStatement("INSERT INTO tache (nom, description, est_realise, ref_liste, ref_type) VALUES (?, ?, ?, ?, ?)");
            requete.setString(1, this.nom.getText());
            requete.setString(2, this.description.getText());
            requete.setBoolean(3, this.est_realise.isSelected());
            requete.setInt(4, this.ref_liste);
            requete.setInt(5, this.ref_type);
            requete.executeUpdate();
            AfficherTaches afficherTaches = new AfficherTaches(this.id_user, this.ref_liste);
            TacheController tacheController = new TacheController();
            HelloApplication.changeScene("affichertaches", afficherTaches);
            afficherTaches.setItems(tacheController.getAll(this.ref_liste));
        } else {
            this.erreur.setText("Veuillez remplir tout les champs");
        }
    }

    public void retour() throws SQLException {
        AfficherTaches afficherTaches = new AfficherTaches(this.id_user, this.ref_liste);
        HelloApplication.changeScene("affichertaches", afficherTaches);
    }

}
