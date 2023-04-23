package com.example.todolistjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import user.Connexion;
import user.UtilisateurController;

import java.io.IOException;
import java.sql.SQLException;

public class ConnexionPage {

    @FXML
    private Button connexion;

    @FXML
    private Button inscri;

    @FXML
    private TextField login;

    @FXML
    private TextField mdp;

    @FXML
    private Label welcomeText;
    @FXML
    private Button oubli;
    @FXML
    private Text erreur;

    @FXML
    public void Connexion() throws IOException, SQLException {
        Connexion co = new Connexion();
        UtilisateurController utilisateurController = new UtilisateurController();
        int i = co.Connexxion(this.login.getText(), this.mdp.getText());
        if (i > 0) {
            AcceuilUti acceuilUti = new AcceuilUti(i);
            HelloApplication.changeScene("acceuiluti", acceuilUti);
            acceuilUti.setNomPrenom();
        } else {
            this.erreur.setText("erreur mauvais mot de passe ou email");
        }
    }

    public void oubli() {
        HelloApplication.changeScene("oubli", new Oubli());
    }

    @FXML
    public void inscription() {
        HelloApplication.changeScene("inscriptionpage", new InscriptionPage());
    }

}
