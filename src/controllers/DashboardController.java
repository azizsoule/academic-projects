package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import mairie.Database;
import mairie.Func;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DashboardController {

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

    public void openPane(ActionEvent actionEvent) {
//        Bouton du vbox
        if (actionEvent.getSource() == btn_tableau_de_bord)
            pane_tableau_de_bord.toFront();

        if (actionEvent.getSource() == btn_actes_naiss) {
            btn_actes_naiss.setStyle("-fx-background-color: linear-gradient(to bottom right,#0056d8, #25ccff, #0056d8)");
            paneList_an.toFront();
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

    private static boolean isFormOk(Pane pane){
        for (Node node:pane.getChildren()
             ) {
            if (node instanceof TextField && ((TextField) node).getText().trim()==""){
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
            Database bd = new Database("admin","admin","projet_mairie");
            bd.connect();



            System.out.println(date);
            System.out.println(date_enreg);

            Func func = resultSet -> {
                try{
                    if (resultSet.rowInserted()){
                        AlertBox.showAlertBox("Succès","Insertion effectuée avec succès", Alert.AlertType.INFORMATION);
                    }else
                        AlertBox.showAlertBox("Echec","Echec de l'insertion", Alert.AlertType.ERROR);

                }catch (SQLException exception ){
                    exception.printStackTrace();
                }
            } ;
            try {
                bd.sendQuery("INSERT INTO individus(nom,prenoms,date_naiss,heure_naiss,lieu_naiss,nom_pere,domicile_pere,nom_mere,domicile_mere,date_enregistrem,numero_enregistrem,id_mairie,id_agent) VALUES (?,?,?,?,?,?,?,?,?,?,?,1,1)",new ArrayList<Object>() {
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
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else
            AlertBox.showAlertBox("Attention","Vous n'avez pas remplit le formulaire correctement", Alert.AlertType.INFORMATION);

    }
}


