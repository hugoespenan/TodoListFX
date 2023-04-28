package com.example.todolistjavafx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.todolistjavafx.Tache;
import user.Bdd;
import user.ListeController;
import user.TacheController;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class AfficherTaches {
    private int id_user;
    private int id_liste;
    @FXML
    private TableColumn<Tache, String> description;

    @FXML
    private TableColumn<Tache, String> est_realise;

    @FXML
    private TableColumn<Tache, Integer> identifiant;

    @FXML
    private TableColumn<Tache, String> nom;

    @FXML
    private TableColumn<Tache, String> ref_liste;

    @FXML
    private TableColumn<Tache, String> type;
    @FXML
    private TableView<Tache> table;
    Bdd bdd = new Bdd();


    public AfficherTaches(int id_user, int id_liste) {
        this.id_user = id_user;
        this.id_liste = id_liste;
    }

    public void setItems(ObservableList<Tache> data) {
        this.identifiant.setCellValueFactory(new PropertyValueFactory<Tache, Integer>("identifiant"));
        this.nom.setCellValueFactory(new PropertyValueFactory<Tache, String>("nom"));
        this.description.setCellValueFactory(new PropertyValueFactory<Tache, String>("description"));
        this.est_realise.setCellValueFactory(new PropertyValueFactory<Tache, String>("est_realise"));
        this.ref_liste.setCellValueFactory(new PropertyValueFactory<Tache, String>("ref_liste"));
        this.type.setCellValueFactory(new PropertyValueFactory<Tache, String>("ref_type"));
        this.table.setItems(data);
    }

    public void retour() throws SQLException {
        AfficherListes afficherListes = new AfficherListes(this.id_user);
        ListeController listecontroller = new ListeController();
        HelloApplication.changeScene("afficherlistes", afficherListes);
        afficherListes.setItems(listecontroller.getAll(this.id_user));
    }
    @FXML
    void ajouter(ActionEvent event) {
        Tache tacheSelectionnee = table.getSelectionModel().getSelectedItem();
        HelloApplication.changeScene("ajoutertache", new AjouterTache(this.id_user, this.id_liste));
    }

    public void supprimer() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette tache ?");
        alert.setContentText("Cliquez sur OK pour continuer ou sur Annuler pour annuler.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Tache tacheSelectionnee = table.getSelectionModel().getSelectedItem();
            PreparedStatement requete3 = bdd.getConnexion().prepareStatement("DELETE FROM tache WHERE id_tache = ?");
            requete3.setInt(1, tacheSelectionnee.getIdentifiant());
            requete3.executeUpdate();
            AfficherTaches afficherTaches = new AfficherTaches(this.id_user, this.id_liste);
            TacheController tacheController = new TacheController();
            HelloApplication.changeScene("affichertaches", afficherTaches);
            afficherTaches.setItems(tacheController.getAll(this.id_liste));
        }
    }
    public void modifier() throws SQLException {
        Tache tacheSelectionnee = table.getSelectionModel().getSelectedItem();
        int rowIndex = table.getSelectionModel().getSelectedIndex();
        ModifierTache modifierTache = new ModifierTache(this.id_user, tacheSelectionnee.getIdentifiant(), this.id_liste);
        HelloApplication.changeScene("modifiertache", modifierTache);
        modifierTache.modifierAfficher();
    }
}
