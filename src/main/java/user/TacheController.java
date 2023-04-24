package user;

import com.example.todolistjavafx.Liste;
import com.example.todolistjavafx.Tache;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TacheController {
    Bdd bdd = new Bdd();
    public ObservableList<Tache> getAll(int id_liste) throws SQLException {
        PreparedStatement requete = bdd.getConnexion().prepareStatement("SELECT tache.id_tache, tache.nom, tache.description, tache.est_realise, liste.nom, type.libelle FROM tache LEFT JOIN liste ON liste.id_liste = tache.ref_liste LEFT JOIN type ON type.id_type = tache.ref_type WHERE ref_liste = ?");
        requete.setInt(1, id_liste);
        ResultSet resultSet = requete.executeQuery();
        ObservableList<Tache> liste = FXCollections.observableArrayList();
        while (resultSet.next()){
            liste.add(new Tache(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getBoolean(4), resultSet.getString(5), resultSet.getString(6)));
        }
        return liste;
    }
}
