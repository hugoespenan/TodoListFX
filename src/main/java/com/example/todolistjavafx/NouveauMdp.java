package com.example.todolistjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import user.Bdd;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NouveauMdp {
    @FXML
    private PasswordField mdp;

    @FXML
    private PasswordField newmdp;
    @FXML
    private Text textee;
    private int id_user;
    public NouveauMdp(int id_user){
        this.id_user = id_user;
    }
    Bdd bdd = new Bdd();


    @FXML
    void valid(ActionEvent event) throws SQLException {
        if (!this.newmdp.getText().isEmpty() && !this.mdp.getText().isEmpty() && this.newmdp.getText().equals(this.mdp.getText())){
            PreparedStatement requete = bdd.getConnexion().prepareStatement("UPDATE user SET mdp = ? WHERE id_user = ?");
            requete.setString(1, this.newmdp.getText());
            requete.setInt(2, this.id_user);
            requete.executeUpdate();
            HelloApplication.changeScene("connexionpage", new ConnexionPage());
        }else {
            this.textee.setText("erreur");
        }
    }
}
