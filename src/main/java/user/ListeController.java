package user;

import com.example.todolistjavafx.Liste;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListeController {
    Bdd bdd = new Bdd();
    public ObservableList<Liste> getAll() throws SQLException {
        PreparedStatement requete = bdd.getConnexion().prepareStatement("SELECT * FROM liste");
        ResultSet resultSet = requete.executeQuery();
        ObservableList<Liste> liste = FXCollections.observableArrayList();
        while (resultSet.next()){
            liste.add(new Liste(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
        }
        return liste;
    }
}
