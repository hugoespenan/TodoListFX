package user;

import com.example.todolistjavafx.Liste;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListeController {
    Bdd bdd = new Bdd();
    public ObservableList<Liste> getAll(int id_uti) throws SQLException {
        PreparedStatement requete = bdd.getConnexion().prepareStatement("SELECT * FROM liste LEFT JOIN participe ON participe.ref_liste = liste.id_liste WHERE participe.ref_user = ?");
        requete.setInt(1, id_uti);
        ResultSet resultSet = requete.executeQuery();
        ObservableList<Liste> liste = FXCollections.observableArrayList();
        while (resultSet.next()){
            liste.add(new Liste(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
        }
        return liste;
    }
}
