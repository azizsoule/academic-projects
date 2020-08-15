package controllers;


import animatefx.animation.SlideInLeft;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mairie.Agent;
import mairie.Database;
import mairie.Operation;
import tray.notification.NotificationType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private VBox loginWindow;

    @FXML
    private Button btn_close;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    Integer id;
    Agent agent;

    public TextField getUsername() {
        return username;
    }

    public TextField getPassword() {
        return password;
    }

    public void connect(ActionEvent actionEvent) throws IOException {
        System.out.println(username.getText()+password.getText());

        if (Agent.agentExist(username.getText().trim(), password.getText().trim())) {

            //Operation.insert(new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "CONNEXION", agent.getId_agent());

            Notification.showNotification(NotificationType.SUCCESS,"Succès","Vous êtes à présent connecté");

            Database db = new Database("root","root","mairie");
            db.connect();
            try {
                db.sendQuery("SELECT * FROM agent WHERE user_name = ? AND mot_d_passe = ?", new ArrayList<>() {
                    {
                        add(username.getText().trim());
                        add(password.getText().trim());
                    }
                }, resultSet -> {
                    try {
                        if (resultSet.next()) {
                             id = resultSet.getInt("id_agent");
                        }
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                });
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            System.out.println(id);
            try {
                agent = new Agent (id);
            }catch (SQLException e){
                System.out.println("erreur ici");
                e.printStackTrace();
            }
            System.out.println(agent.getNom());


            Stage primaryStage = (Stage) loginWindow.getScene().getWindow();
            primaryStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/dashboard.fxml"));
            Parent root = loader.load();
            DashboardController dashboardController =loader.getController();

            //On recupère les info de l'utilisateur connecté
            dashboardController.getAgentInfo(agent);

            primaryStage.setTitle("DashBoard");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
//
//            FadeIn fd = new FadeIn(root);
//            fd.setDelay(Duration.millis(1000));
//            fd.play();

        } else {
            new SlideInLeft(username).play();
            Notification.showNotification(NotificationType.ERROR,"Echec","Username ou mot de passe incorrect!");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("papitou15");
        password.setText("papitou@mairiepassword99");
    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) loginWindow.getScene().getWindow();
        primaryStage.close();
    }
}
