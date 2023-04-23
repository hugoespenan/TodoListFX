package com.example.todolistjavafx;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Button connexion;

    @FXML
    private Button inscri;

    @FXML
    private Label welcomeText;

    @FXML
    void connexion(ActionEvent event) {
        HelloApplication.changeScene("connexionpage", new ConnexionPage());
    }

    @FXML
    void inscription(ActionEvent event) {
        HelloApplication.changeScene("inscriptionpage", new InscriptionPage());
    }

}