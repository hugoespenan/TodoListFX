package com.example.todolistjavafx;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class AfficherListes {
    private int id_user;

    @FXML
    private TableColumn<Liste, String> description;

    @FXML
    private TableColumn<Liste, Integer> identifiant;

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
    

}
