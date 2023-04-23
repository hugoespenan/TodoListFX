package user;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Inscription {
    Bdd bdd = new Bdd();

    public void Inscripption(String nom, String prenom, String email, String mdp) throws SQLException {
        PreparedStatement requete = bdd.getConnexion().prepareStatement("INSERT INTO user (nom, prenom, mail, mdp) VALUES (?,?,?,?)");
        requete.setString(1, nom);
        requete.setString(2, prenom);
        requete.setString(3, email);
        requete.setString(4, mdp);
        requete.executeUpdate();
    }
}
