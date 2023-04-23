package com.example.todolistjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;


public class Code {
    private String code1;
    private int id_user;
    private int chance = 3;
    public Code(String code, int id_user) {
        this.code1 = code;
        this.id_user = id_user;
    }
        @FXML
        private TextField code;
    @FXML
    private Text textee;

        public void valider(){
            if (this.code1.equals(this.code.getText()) && chance>0){
                HelloApplication.changeScene("nouveaumdp", new NouveauMdp(this.id_user));
            }else {
                chance = chance-1;
                this.textee.setText("Mauvais code il vous reste "+chance+" chance(s) ");
            }
            if (chance<1){
                HelloApplication.changeScene("connexionpage", new ConnexionPage());
            }
        }
    public void retour() throws SQLException {
        HelloApplication.changeScene("connexionpage", new ConnexionPage());
    }
}
