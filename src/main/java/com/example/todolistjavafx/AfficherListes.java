package com.example.todolistjavafx;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import user.Bdd;
import user.ListeController;
import user.TacheController;
import user.UtilisateurController;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class AfficherListes {
    private int id_user;

    @FXML
    private TableColumn<Liste, String> description;

    @FXML
    private TableColumn<Liste, Integer> identifiant;
    Bdd bdd = new Bdd();

    @FXML
    private TableColumn<Liste, String> nom;

    @FXML
    private TableView<Liste> table;

    public AfficherListes(int id_user){
        this.id_user = id_user;
    }
    public void setItems(ObservableList<Liste> data) {
        this.identifiant.setCellValueFactory(new PropertyValueFactory<Liste, Integer>("identifiant"));
        this.nom.setCellValueFactory(new PropertyValueFactory<Liste, String>("nom"));
        this.description.setCellValueFactory(new PropertyValueFactory<Liste, String>("description"));
        this.table.setItems(data);
    }
    public void supprimer() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette liste ?");
        alert.setContentText("Cliquez sur OK pour continuer ou sur Annuler pour annuler.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Liste listeSelectionnee = table.getSelectionModel().getSelectedItem();
            PreparedStatement requete3 = bdd.getConnexion().prepareStatement("DELETE FROM tache WHERE ref_liste = ?");
            requete3.setInt(1, listeSelectionnee.getIdentifiant());
            requete3.executeUpdate();
            PreparedStatement requete2 = bdd.getConnexion().prepareStatement("DELETE FROM participe WHERE ref_liste = ? and ref_user = ?");
            requete2.setInt(1, listeSelectionnee.getIdentifiant());
            requete2.setInt(2, this.id_user);
            requete2.executeUpdate();
            PreparedStatement requete = bdd.getConnexion().prepareStatement("DELETE FROM liste WHERE id_liste = ?");
            requete.setInt(1, listeSelectionnee.getIdentifiant());
            requete.executeUpdate();
            ListeController listeController = new ListeController();
            AfficherListes afficherListes = new AfficherListes(this.id_user);
            HelloApplication.changeScene("afficherlistes", afficherListes);
            afficherListes.setItems(listeController.getAll(this.id_user));
        }
    }
    public void affichertaches() throws SQLException {
        Liste listeSelectionnee = table.getSelectionModel().getSelectedItem();
        AfficherTaches afficherTaches = new AfficherTaches(this.id_user, listeSelectionnee.getIdentifiant());
        TacheController tacheController = new TacheController();
        HelloApplication.changeScene("affichertaches", afficherTaches);
        afficherTaches.setItems(tacheController.getAll(listeSelectionnee.getIdentifiant()));
    }
    public void retour() throws SQLException {
            AcceuilUti acceuilUti = new AcceuilUti(this.id_user);
            HelloApplication.changeScene("acceuiluti", acceuilUti);
            acceuilUti.setNomPrenom();
    }
    public void modifier() throws SQLException {
        Liste listeSelectionnee = table.getSelectionModel().getSelectedItem();
        ModifierListe modifierListe = new ModifierListe(this.id_user, listeSelectionnee.getIdentifiant());
        HelloApplication.changeScene("modifierliste", modifierListe);
        modifierListe.modifierAfficher();
    }


}
