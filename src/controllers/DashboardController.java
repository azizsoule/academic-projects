package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import mairie.Database;
import mairie.Ex;
import mairie.Extrait;
import mairie.Func;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DashboardController {

    @FXML
    public TableView extraitList;
    public VBox rootVbox;

    //************************************************************************

    @FXML
    private Button btn_tableau_de_bord;

    @FXML
    private Button btn_compte;

    @FXML
    private Button btn_actes_naiss;

    @FXML
    private Button btn_acte_mariage;

    @FXML
    private Button btn_sign_out;

    @FXML
    private Pane paneAdd_an;

    @FXML
    private static Button btn_valider;

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

//    textfields


    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private Spinner<Integer> heure;

    @FXML
    private Spinner<Integer> minutes;

    @FXML
    private DatePicker dateNaiss;

    @FXML
    private TextField lieuNaiss;

    @FXML
    private TextField nomMere;

    @FXML
    private TextField prenomMere;

    @FXML
    private TextField nomPere;

    @FXML
    private TextField prenomPere;

    @FXML
    private TextField domicilePere;

    @FXML
    private TextField domicileMere;

    //*****************************************************************************

    @FXML
    private TableColumn<Ex, Integer> id_extrait;

    @FXML
    private TableColumn<Ex, String> name;

    @FXML
    private TableColumn<Ex, String> prenoms;

    @FXML
    private TableColumn<Ex, String> date_de_naissance;

    @FXML
    private TextField search_field;


    Database db = new Database("root","root", "mairie");

    //Variable permettant de contenir les ligne du tableau
    final ObservableList<Ex> data = FXCollections.observableArrayList();

    //Expression lamba ayant pour role de remplir le tableView des extraits de naissance
    Func refreshTableView = resultset -> {
        try {

            id_extrait.setCellValueFactory(new PropertyValueFactory<Ex, Integer>("id_extrait"));

            name.setCellValueFactory(new PropertyValueFactory<Ex, String>("nom"));

            prenoms.setCellValueFactory(new PropertyValueFactory<Ex, String>("prenoms"));

            date_de_naissance.setCellValueFactory(new PropertyValueFactory<Ex, String>("date_de_naissance"));

            data.clear();

            while (resultset.next()) {
                data.add(new Ex(resultset.getInt("id_extrait"), resultset.getString("nom"), resultset.getString("prenoms"), resultset.getDate("date_naiss").toString()));
            }

            extraitList.setItems(data);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public void setActive(Button button) {
        for (Node elmt:rootVbox.getChildren()
             ) {
            if(elmt instanceof Button)
                elmt.setStyle("-fx-background-color: #10165F");
        }
        button.setStyle("-fx-background-color: linear-gradient(to bottom right,#0056d8, #25ccff, #0056d8)");
    }

    public void openPane(ActionEvent actionEvent) {
//        Bouton du vbox
        if (actionEvent.getSource() == btn_tableau_de_bord)
            setActive(btn_tableau_de_bord);
            pane_tableau_de_bord.toFront();

        if (actionEvent.getSource() == btn_actes_naiss) {
            setActive(btn_actes_naiss);
            paneList_an.toFront();

            db.connect();

            try {
                db.sendQuery("SELECT id_extrait,nom,prenoms,date_naiss FROM individus", refreshTableView);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            db.disconnect();


        }
        if (actionEvent.getSource() == btn_acte_mariage) {
            setActive(btn_acte_mariage);
            paneList_am.toFront();
        }
        if(actionEvent.getSource()==btn_sign_out) {
            setActive(btn_sign_out);
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


    private static boolean isFormOk(Pane pane){
        for (Node node:pane.getChildren()
        ) {
            if (node instanceof TextField && ((TextField) node).getText().trim()==""){
                btn_valider.setDisable(true);
                return false;
            }
            if(node instanceof DatePicker && ((DatePicker) node).getValue()==null){
                return false;
            }
        }
        return true;
    }

    public void insertValue(ActionEvent actionEvent) {
        if (isFormOk(paneAdd_an)){

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date_enreg = simpleDateFormat.format(new Date());

            Date dateN= java.sql.Date.valueOf(dateNaiss.getValue());
            String date =  simpleDateFormat.format(dateN);


            String heureNaiss = "12:40";
            db.connect();



            System.out.println(date);
            System.out.println(date_enreg);

            Func func = resultSet -> {
            } ;
            try {
                db.sendQuery("INSERT INTO individus(nom,prenoms,date_naiss,heure_naiss,lieu_naiss,nom_pere,domicile_pere,nom_mere,domicile_mere,date_enregistrem,numero_enregistrem,id_mairie,id_agent) VALUES (?,?,?,?,?,?,?,?,?,?,?,1,1)",new ArrayList<Object>() {
                            {
                                add(nom.getText().trim());
                                add(prenom.getText().trim());
                                add(date);
                                add(heureNaiss);
                                add(lieuNaiss.getText().trim());
                                add(nomPere.getText().trim());
                                add(domicilePere.getText().trim());
                                add(nomMere.getText().trim());
                                add(domicileMere.getText().trim());
                                add(date_enreg);
                                add(1);

                            }
                        },func
                );
                AlertBox.showAlertBox("Succès","Insertion effectuée avec succès", Alert.AlertType.INFORMATION);

            } catch (SQLException throwables) {
                AlertBox.showAlertBox("Echec","Echec de l'insertion", Alert.AlertType.ERROR);
                throwables.printStackTrace();
            } finally {
                db.disconnect();
            }
        }else
            AlertBox.showAlertBox("Attention","Vous n'avez pas remplit le formulaire correctement", Alert.AlertType.INFORMATION);

    }

    public void search(ActionEvent inputMethodEvent) {

        search_field.textProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                        data.clear();
                        Extrait.search(t1, refreshTableView);
                    }
                }
        );

    }
}


