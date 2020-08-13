package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mairie.Agent;

import java.io.IOException;

public class LoginController {

    @FXML
    private VBox loginWindow;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    public void connect(ActionEvent actionEvent) throws IOException {
        System.out.println(username.getText()+password.getText());
        if (Agent.agentExist(username.getText().trim(), password.getText().trim())) {
            Stage primaryStage = (Stage) loginWindow.getScene().getWindow();
            primaryStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/dashboard.fxml"));
            Parent root = (Parent) loader.load();
            DashboardController dashboardController =loader.getController();

            //On recupère les info de l'utilisateur connecté
            dashboardController.setLoginInfo(username.getText().trim());

            primaryStage.setTitle("DashBoard");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } else {
            AlertBox.showAlertBox("Erreur", "username ou mot de passe incorrect", Alert.AlertType.ERROR);
        }
    }
}
