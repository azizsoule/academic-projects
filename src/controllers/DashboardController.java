package controllers;

import animatefx.animation.SlideInRight;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import mairie.*;
import javafx.stage.Stage;
import mairie.*;
import pdfviewer.Controller;
import javafx.stage.Stage;
import mairie.*;
import pdfviewer.Controller;
import javafx.stage.Stage;
import mairie.*;
import pdfviewer.Controller;
import tray.notification.NotificationType;

import java.net.URL;
import java.io.IOException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.HashMap;
import java.util.HashMap;
import java.util.function.Consumer;


public class DashboardController implements Initializable {

    @FXML
    public TableView extraitList;

    @FXML
    public VBox rootVbox;
    @FXML
    public Button btn_valider;
    @FXML
    public Label agentUsername;
    public TextField professionMere;
    public TextField professionPere;
    public ComboBox comboSexe;
    public Button btn_valider_am;
    public TextField numExtraitConjointe;
    public DatePicker dateExtraitConjointe;
    public TextField lieuMariage;
    public DatePicker dateMariage;
    public TextField numExtraitConjoint;
    public DatePicker dateExtraitConjoint;

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
    private TextField nomPere;

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
    private TableColumn<Ex, Button> voir;

    @FXML
    private TableColumn<Ex, Button> mod;

    @FXML
    private TextField search_field;

    Agent agent;
    int id_conjoint;
    int id_conjointe;

    Database db = new Database("root","root", "mairie");

    //Variable permettant de contenir les ligne du tableau
    final ObservableList<Ex> data = FXCollections.observableArrayList();

    Integer id_modif = null;

    //Expression lamba ayant pour role de remplir le tableView des extraits de naissance
    Func refreshTableView = resultset -> {
        try {

            id_extrait.setCellValueFactory(new PropertyValueFactory<Ex, Integer>("id_extrait"));

            name.setCellValueFactory(new PropertyValueFactory<Ex, String>("nom"));

            prenoms.setCellValueFactory(new PropertyValueFactory<Ex, String>("prenoms"));

            date_de_naissance.setCellValueFactory(new PropertyValueFactory<Ex, String>("date_de_naissance"));

            voir.setCellValueFactory(new PropertyValueFactory<Ex, Button>("visualiser"));

            mod.setCellValueFactory(new PropertyValueFactory<Ex, Button>("modifier"));

            data.clear();

            while (resultset.next()) {

                HashMap<String, Object> values = new HashMap<>() {
                    {
                        put("id_extrait",resultset.getInt("id_extrait"));
                        put("nom",resultset.getString("nom"));
                        put("prenoms",resultset.getString("prenoms"));
                        put("sexe",resultset.getString("sexe"));
                        put("date_naiss",resultset.getDate("date_naiss"));
                        put("heure_naiss",resultset.getString("heure_naiss"));
                        put("lieu_naiss",resultset.getString("lieu_naiss"));
                        put("nom_pere",resultset.getString("nom_pere"));
                        put("domicile_pere",resultset.getString("domicile_pere"));
                        put("nom_mere",resultset.getString("nom_mere"));
                        put("domicile_mere",resultset.getString("domicile_mere"));
                        put("profession_pere",resultset.getString("profession_pere"));
                        put("profession_mere",resultset.getString("profession_mere"));

                    }
                } ;

                Ex ex_row = new Ex(resultset.getInt("id_extrait"), resultset.getString("nom"), resultset.getString("prenoms"), resultset.getDate("date_naiss").toString(), new Button("Voir"), new Button("Modifier"));

                ex_row.getVisualiser().setOnAction(e -> {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../pdfviewer/PdfViewer.fxml"));
                    try {
                        Parent root = (Parent) loader.load();
                        Controller pdfController = loader.getController();

                        Printer.generatePDF(ex_row.getNom()+ex_row.getId_extrait(), new HashMap<>() {
                            {
                                put("NOM", String.valueOf(values.get("nom")));
                                put("PRENOMS",String.valueOf(values.get("prenoms")));
                                put("DATE_NAISSANCE",String.valueOf(values.get("date_naiss").toString()));
                                put("HEURE_NAISSANCE", String.valueOf(values.get("heure_naiss")));
                                put("LIEU_NAISSANCE",String.valueOf(values.get("lieu_naiss")));
                                put("NOM_MERE",String.valueOf(values.get("nom_mere")));
                                put("NOM_PERE",String.valueOf(values.get("nom_pere")));

                                if (values.get("sexe").equals("Homme")) {
                                    put("FILS_FILLE","fils");
                                } else {
                                    put("FILS_FILLE","fille");
                                }

                            }
                        });

                        pdfController.initialize("extraits/"+ex_row.getNom()+ex_row.getId_extrait()+"x.pdf");

                        Stage stage = new Stage();
                        stage.setScene(new Scene(root,600, 800));
                        stage.show();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });

                ex_row.getModifier().setOnAction(e -> {
                    id_modif = (Integer) values.get("id_extrait");

                    paneAdd_an.toFront();
                    btn_valider.setText("MODIFIER");
                    nom.setText((String) values.get("nom"));
                    prenom.setText((String) values.get("prenoms"));
                    comboSexe.setValue(values.get("sexe"));
                    lieuNaiss.setText((String) values.get("lieu_naiss"));
                    dateNaiss.setValue(LocalDate.parse(values.get("date_naiss").toString()));

                    String time = (String) values.get("heure_naiss");

                    String h = time.split(":")[0];
                    String m = time.split(":")[1];

                    heure.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,Integer.parseInt(h)));
                    minutes.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,Integer.parseInt(m)));

                    //pere
                    nomPere.setText((String) values.get("nom_pere"));
                    professionPere.setText((String) values.get("profession_pere"));
                    domicilePere.setText((String) values.get("domicile_pere"));

                    //mere
                    nomMere.setText((String) values.get("nom_mere"));
                    professionMere.setText((String) values.get("profession_mere"));
                    domicileMere.setText((String) values.get("domicile_mere"));

                });

                data.add(ex_row);
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
            SlideInRight sl = new SlideInRight(pane_tableau_de_bord);
            sl.setDelay(Duration.millis(0.0D));

        if (actionEvent.getSource() == btn_actes_naiss) {
            setActive(btn_actes_naiss);
            paneList_an.toFront();
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
        if (actionEvent.getSource() == btn_add_an ) {
            /*paneAdd_an.getChildren().forEach(new Consumer<Node>() {
                @Override
                public void accept(Node node) {
                    if(node instanceof TextField) {
                        ((TextField) node).clear();
                    }
                }
            });*/
            resetAll_an();
            paneAdd_an.toFront();
            btn_valider.setText("VALIDER");
        }
        if (actionEvent.getSource() == btn_add_am )
            paneAdd_am.toFront();
        if (actionEvent.getSource() == btn_ajouter_am )
            paneAdd_am.toFront();
        if (actionEvent.getSource() == btn_ajouter_an ){
            resetAll_an();
            paneAdd_an.toFront();
            btn_valider.setText("VALIDER");
        }
    }

    public void resetAll_an() {

        nom.clear();
        prenom.clear();
        comboSexe.setValue("Sexe");
        heure.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,0));
        minutes.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,0));
        dateNaiss.setValue(LocalDate.now());
        lieuNaiss.clear();
        nomPere.clear();
        nomMere.clear();
        domicilePere.clear();
        domicileMere.clear();
        professionMere.clear();
        professionPere.clear();

    }

    public void resetAll_am() {}

    private boolean isFormOk(Pane pane){
        ArrayList<Node> element  = new ArrayList<Node>();

        for (Node pnode:pane.getChildren()
        ) {

            if (pnode instanceof VBox ){
                for (Node vnode: ((VBox) pnode).getChildren()
                     ) {
                    if (vnode instanceof VBox){
                        for (Node v2node:((VBox) vnode).getChildren()
                             ) {
                            if (v2node instanceof TextField && ((TextField) v2node).getText().isEmpty()){
                                v2node.setStyle("-fx-border-color: RED");
                                return false;
                            }
                            if (v2node instanceof DatePicker && ((DatePicker) v2node).getValue()==null){
                                v2node.setStyle("-fx-border-color: red");
                                return false;
                            }

                        }
                    }
                }
            }
        }
        if (pane.equals(paneAdd_an)){
            if (comboSexe.getValue()==null){
                comboSexe.setStyle("-fx-border-color: red");
                return false;
            }
        }
        return true;
    }

    public void clearFields(Pane pane){
        ArrayList<Node> element  = new ArrayList<Node>();

        for (Node pnode:pane.getChildren()
        ) {

            if (pnode instanceof VBox ){
                for (Node vnode: ((VBox) pnode).getChildren()
                ) {
                    if (vnode instanceof VBox){
                        for (Node v2node:((VBox) vnode).getChildren()
                        ) {
                            if (v2node instanceof TextField ){
                                ((TextField) v2node).clear();
                            }
                            if (v2node instanceof DatePicker){
                                ((DatePicker) v2node).setValue(null);
                            }
                        }
                    }
                }
            }
        }
        if (pane.equals(paneAdd_an)){
            comboSexe.setValue(null);
            heure.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,0));
            minutes.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,0));
            dateNaiss.setValue(null);
        }
//        if (pane.equals(paneAdd_am)){
//            comboSexe.setValue(null);
//            heure.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,0));
//            minutes.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,0));
//            dateNaiss.setValue(null);
//        }
//

    }

    public void insertValue(ActionEvent actionEvent) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date_enreg = simpleDateFormat.format(new Date());

        //Insertion acte de mariage
        if (actionEvent.getSource()== btn_valider ){
            if (isFormOk(paneAdd_an)){

                String sexeUser = comboSexe.getValue().toString();

                Date dateN= java.sql.Date.valueOf(dateNaiss.getValue());
                if (dateN.equals(null))
                    AlertBox.showAlertBox("Echec","Echec de l'insertion", Alert.AlertType.ERROR);

                String date =  simpleDateFormat.format(dateN);

                String heureNaiss = (String) heure.getValue().toString()+':'+minutes.getValue().toString();
                db.connect();

                if(btn_valider.getText().equals("VALIDER")) {
                    try {
                        db.sendQuery("INSERT INTO individus(nom,prenoms,sexe,date_naiss,heure_naiss,lieu_naiss,nom_pere,domicile_pere,profession_pere,nom_mere,domicile_mere,profession_mere,date_enregistrem,numero_enregistrem,id_mairie,id_agent) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",new ArrayList<Object>() {
                                    {
                                        add(nom.getText().trim());
                                        add(prenom.getText().trim());
                                        add(sexeUser);
                                        add(date);
                                        add(heureNaiss);
                                        add(lieuNaiss.getText().trim());
                                        add(nomPere.getText().trim());
                                        add(domicilePere.getText().trim());
                                        add(professionPere.getText().trim());
                                        add(nomMere.getText().trim());
                                        add(domicileMere.getText().trim());
                                        add(professionMere.getText().trim());
                                        add(date_enreg);
                                        add(10);
                                        add(agent.getId_mairie());
                                        add(agent.getId_agent());

                                    }
                                },resultSet -> {}
                        );
                        Operation.insert(date_enreg,"Edition ACTE DE NAISSANCE",agent.getId_agent());
                        Notification.showNotification(NotificationType.SUCCESS,"Succès","Insertion éffectuée avec succès");
                        clearFields(paneAdd_an);

                    } catch (SQLException throwables) {
                        Notification.showNotification(NotificationType.ERROR,"Echec","Echec de l'insertion");
                        throwables.printStackTrace();
                    } finally {
                        db.disconnect();
                    }
                }else {
                    try {
                        db.sendQuery("UPDATE individus SET nom=?,prenoms=?,sexe=?,date_naiss=?,heure_naiss=?,lieu_naiss=?,nom_pere=?,domicile_pere=?,profession_pere=?,nom_mere=?,domicile_mere=?,profession_mere=?,id_agent=? WHERE id_extrait=?",new ArrayList<Object>() {
                                    {
                                        add(nom.getText().trim());
                                        add(prenom.getText().trim());
                                        add(sexeUser);
                                        add(date);
                                        add(heureNaiss);
                                        add(lieuNaiss.getText().trim());
                                        add(nomPere.getText().trim());
                                        add(domicilePere.getText().trim());
                                        add(professionPere.getText().trim());
                                        add(nomMere.getText().trim());
                                        add(domicileMere.getText().trim());
                                        add(professionMere.getText().trim());
                                        add(agent.getId_agent());
                                        add(id_modif);
                                    }
                                },resultSet -> {}
                        );
                        Operation.insert(date_enreg,"MODIFICATION ACTE DE NAISSANCE",agent.getId_agent());
                        Notification.showNotification(NotificationType.SUCCESS,"Succès","Modification éffectuée avec succès");
                        clearFields(paneAdd_an);

                    } catch (SQLException throwables) {
                        Notification.showNotification(NotificationType.ERROR,"Echec","Echec de l'insertion");
                        throwables.printStackTrace();
                    } finally {
                        db.disconnect();
                    }
                }

            }else
                AlertBox.showAlertBox("Attention","Vous n'avez pas remplit le formulaire correctement", Alert.AlertType.INFORMATION);
        }

        //Insertion acte de mariage
        if(actionEvent.getSource()==btn_valider_am){
            //on vérifie que les champs sont bien remplis
            if(isFormOk(paneAdd_am)){

                db.connect();

                //On recupere les différentes dates qu'on convertit en string
                Date dateN= java.sql.Date.valueOf(dateMariage.getValue());
                String dateM =  simpleDateFormat.format(dateN);

                Date y= java.sql.Date.valueOf(dateExtraitConjoint.getValue());
                String dateConjoint =  simpleDateFormat.format(y);

                Date x= java.sql.Date.valueOf(dateExtraitConjointe.getValue());
                String dateConjointe =  simpleDateFormat.format(x);

                //On recupere les id des différents conjoints
                try{
                    //Pour recuperer L'id de l'extrait du conjoint
                    db.sendQuery("SELECT id_extrait FROM individus WHERE numero_enregistrem=? AND date_enregistrem=?",new ArrayList<Object>()
                    {
                        {
                            add(Integer.valueOf(numExtraitConjoint.getText().trim()));
                            add(dateConjoint);
                        }
                    },resultSet -> {
                        if(resultSet.next()){
                            id_conjoint = resultSet.getInt("id_extrait");
                        }
                    });

                    //Pour recuperer L'id de l'extrait de la conjointe
                    db.sendQuery("SELECT id_extrait FROM individus WHERE numero_enregistrem=? AND date_enregistrem=?",new ArrayList<Object>()
                    {
                        {
                            add(Integer.valueOf(numExtraitConjointe.getText().trim()));
                            add(dateConjointe);
                        }
                    },resultSet -> {
                        if(resultSet.next()){
                            id_conjointe = resultSet.getInt("id_extrait");
                        }
                    });
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                db.disconnect();

                //On cree un objet acte de mariage a partir des informations
                ActeMariage acte = new ActeMariage(agent.getId_agent(),dateM,lieuMariage.getText().trim(),id_conjoint,id_conjointe,date_enreg);
                //On enregistre dans la base de données
                try {
                    acte.save();
                    Operation.insert(date_enreg,"Edition ACTE DE MARIAGE",agent.getId_agent());
                    AlertBox.showAlertBox("Succès","Insertion effectuée avec succès", Alert.AlertType.INFORMATION);

                    //Pour reset le formulaire après insertion
                    clearFields(paneAdd_am);

                } catch (SQLException throwables) {
                    AlertBox.showAlertBox("Echec","Echec de l'insertion", Alert.AlertType.ERROR);
                    throwables.printStackTrace();
                }

            //Sinon on affiche un message d'erreur
            }else{
                AlertBox.showAlertBox("Attention","Vous n'avez pas remplit le formulaire correctement", Alert.AlertType.INFORMATION);
            }
        }

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

    //Pour recuperer les info de l'utilisateur depuis l'interface de connection
    public void getAgentInfo(Agent agentMairie){
        //on affiche le nom de l'agent dans le dashboard
        agentUsername.setText(agentMairie.getNom());
        //On remplit l'attribut agent du controller pour pouvoir effectuer des insertions et contrôles
        agent = agentMairie;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //On charge les setup au demmarage du dashboard
        pane_tableau_de_bord.toFront();
        setActive(btn_tableau_de_bord);

        //Pour les valeurs des spinner(Heure,minutes)
        final int initialValue = 0;
        heure.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,initialValue));
        minutes.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,initialValue));
//        dateNaiss.setValue(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));

        //On donne ici les valeurs au comboBox sexe
        comboSexe.getItems().add("Homme");
        comboSexe.getItems().add("Femme");

        db.connect();

        try {
            db.sendQuery("SELECT * FROM individus", refreshTableView);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        db.disconnect();

    }

}
