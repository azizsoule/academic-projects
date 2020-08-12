package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import mairie.Database;
import mairie.Ex;
import mairie.Func;

import java.sql.SQLException;


public class DashboardController {

    @FXML
    public TableView extraitList;

    @FXML
    private Button btn_tableau_de_bord;

    @FXML
    private Button btn_compte;

    @FXML
    private Button btn_actes_naiss;

    @FXML
    private Button btn_acte_mariage;

    @FXML
    private Button btn_settings;

    @FXML
    private Button btn_sign_out;

    @FXML
    private Pane paneAdd_an;

    @FXML
    private Button btn_valider;

    @FXML
    private Pane paneList_am;

    @FXML
    private Button btn_ajouter_am;

    @FXML
    private Pane paneList_an;

    @FXML
    private Button btn_ajouter_an;

    @FXML
    private Pane paneAdd_am;

    @FXML
    private Pane pane_tableau_de_bord;

    @FXML
    private Button btn_add_am;

    @FXML
    private Button btn_add_an;

    @FXML
    private TableColumn<Ex, Integer> id_extrait;

    @FXML
    private TableColumn<Ex, String> nom;

    @FXML
    private TableColumn<Ex, String> prenoms;

    @FXML
    private TableColumn<Ex, String> date_de_naissance;

    //Variable permettant de contenir les ligne du tableau
    final ObservableList<Ex> data = FXCollections.observableArrayList();

    //Expression lamba ayant pour role de remplir le tableView des extraits de naissance
    Func refreshTableView = resultset -> {
        try {

            id_extrait.setCellValueFactory(new PropertyValueFactory<Ex, Integer>("id_extrait"));

            nom.setCellValueFactory(new PropertyValueFactory<Ex, String>("nom"));

            prenoms.setCellValueFactory(new PropertyValueFactory<Ex, String>("prenoms"));

            date_de_naissance.setCellValueFactory(new PropertyValueFactory<Ex, String>("date_de_naissance"));

            while (resultset.next()) {
                data.add(new Ex(resultset.getInt("id_extrait"), resultset.getString("nom"), resultset.getString("prenoms"), resultset.getDate("date_naiss").toString()));
            }

            extraitList.setItems(data);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public void openPane(ActionEvent actionEvent) {
//        Bouton du vbox
        if (actionEvent.getSource() == btn_tableau_de_bord)
            pane_tableau_de_bord.toFront();

        if (actionEvent.getSource() == btn_actes_naiss) {
            btn_actes_naiss.setStyle("-fx-background-color: linear-gradient(to bottom right,#0056d8, #25ccff, #0056d8)");
            paneList_an.toFront();

            Database db = new Database("root","root", "mairie");

            db.connect();

            try {
                db.sendQuery("SELECT id_extrait,nom,prenoms,date_naiss FROM individus", refreshTableView);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            db.disconnect();


        }
        if (actionEvent.getSource() == btn_acte_mariage) {
            btn_acte_mariage.setStyle("-fx-background-color: linear-gradient(to bottom right,#0056d8, #25ccff, #0056d8)");
            paneList_am.toFront();
        }
        if(actionEvent.getSource()==btn_sign_out) {
            btn_sign_out.setStyle("-fx-background-color: linear-gradient(to bottom right,#0056d8, #25ccff, #0056d8)");
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setTitle("Confirmation");
            alertBox.setContentText("Voulez-vous vraiment vous deconnecter ?");
            alertBox.showAndWait().ifPresent(
                    pressedButton-> {
                        if (pressedButton == ButtonType.OK) {
                            System.out.println("Vous etes a présent deconnecté");
                        }else
                            alertBox.close();
                    }
            );
        }
        // Pour le stackPane
        if (actionEvent.getSource() == btn_add_an )
            paneAdd_an.toFront();
        if (actionEvent.getSource() == btn_add_am )
            paneAdd_am.toFront();
        if (actionEvent.getSource() == btn_ajouter_am )
            paneAdd_am.toFront();
        if (actionEvent.getSource() == btn_ajouter_an )
            paneAdd_an.toFront();
    }


}


