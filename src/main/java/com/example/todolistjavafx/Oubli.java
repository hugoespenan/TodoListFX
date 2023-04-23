package com.example.todolistjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import user.UtilisateurController;

import java.sql.SQLException;
import java.util.Random;

public class Oubli {
    @FXML
    private TextField email;

    @FXML
    private Button rein;

    @FXML
    void rein(ActionEvent event) throws SQLException {
        if (!this.email.getText().isEmpty()) {
            Email email = new Email();
            String chars = "abcdefghijklmnopqrstuvwxyz";
            StringBuilder sb = new StringBuilder();
            Random rand = new Random();

            int length = 3;

            for(int i = 0; i < length; i++) {
                int index = rand.nextInt(chars.length());
                char randomChar = chars.charAt(index);
                sb.append(randomChar);
            }

            String randomString = sb.toString();
            Random random = new Random();
            int randomNumber = random.nextInt(900) + 100;
            String code = randomString+randomNumber;
            email.envoyerMail(this.email.getText(), "Voici votre code pour pouvoir réinitialiser votre mot de passe", "Votre code est "+code);
            UtilisateurController utilisateurController = new UtilisateurController();
            int idid = utilisateurController.getIdByMail(this.email.getText());
            HelloApplication.changeScene("code", new Code(code, idid));
        }
    }
}
