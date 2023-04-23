package user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurController {
    private String mail;
    private String mdp;
    private int id_user;
    Bdd bdd = new Bdd();


    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    public int getIdByMail(String mail) throws SQLException {
        PreparedStatement requete = bdd.getConnexion().prepareStatement("SELECT * FROM user WHERE mail = ?");
        requete.setString(1, mail);
        ResultSet resultat = requete.executeQuery();
        int aff = 0;
        if (resultat.next()){
            aff = resultat.getInt(1);
        }
        return aff;
    }
}
