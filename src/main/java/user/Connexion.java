package user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connexion {
    Bdd bdd = new Bdd();

    public int Connexxion(String email, String mdp) throws SQLException {
        int i = 0;
        PreparedStatement requete = bdd.getConnexion().prepareStatement("SELECT * FROM user WHERE mail = ? and mdp = ?");
        requete.setString(1, email);
        requete.setString(2, mdp);
        ResultSet resultat = requete.executeQuery();
        if (resultat.next()){
            i = resultat.getInt(1);
        }
        return i;
    }

}
