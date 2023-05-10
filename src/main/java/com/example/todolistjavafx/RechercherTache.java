package com.example.todolistjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import user.Bdd;
import user.TacheController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RechercherTache {

    private int id_user;

    private int id_type;

    public RechercherTache(int id_user){
        this.id_user=id_user;
    }

    @FXML
    private Button boutonrecherche;

    @FXML
    private ComboBox<String> typetache;
    public void initialize() {
        Bdd bdd2 = new Bdd();
        ObservableList<String> types = FXCollections.observableArrayList(); //On va remplir la combobpx des types présents dans la bdd :

        try {
            PreparedStatement requete = bdd2.getConnexion().prepareStatement("SELECT libelle FROM type");
            ResultSet resultSet = requete.executeQuery();
            while (resultSet.next()) {
                types.add(resultSet.getString("libelle"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        typetache.setItems(types);
        //Je récupère les id des types ci dessous
        typetache.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    PreparedStatement statement = bdd2.getConnexion().prepareStatement("SELECT id_type FROM type WHERE libelle = ?");
                    statement.setString(1, newValue);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        int idType = resultSet.getInt("id_type");
                        this.id_type = idType;
                        // L'id est récupéré
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    @FXML
    void retour(ActionEvent event) throws SQLException {
        AcceuilUti acceuilUti= new AcceuilUti();
        HelloApplication.changeScene("affichertaches", acceuilUti);
        acceuilUti.setNomPrenom();
    }

}

