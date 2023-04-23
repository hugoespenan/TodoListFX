package com.example.todolistjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import user.Inscription;

import java.sql.SQLException;

public class InscriptionPage {

    @FXML
    private Button inscr;

    @FXML
    private TextField mail;

    @FXML
    private TextField mdp;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;
    @FXML
    private Text erreur;

    @FXML
    void inscription(ActionEvent event) throws SQLException {
        if (!nom.getText().isEmpty() && !prenom.getText().isEmpty() && !mail.getText().isEmpty() && !mdp.getText().isEmpty()){
            Inscription inscription = new Inscription();
            inscription.Inscripption(this.nom.getText(), this.prenom.getText(), this.mail.getText(), this.mdp.getText());
            HelloApplication.changeScene("connexionpage", new ConnexionPage());
        }else {
            this.erreur.setText("Veuillez remplir tout les champs");
        }
    }
    public void connexion(){
        HelloApplication.changeScene("connexionpage", new ConnexionPage());
    }

}