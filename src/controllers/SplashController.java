package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class SplashController implements Initializable {

    @FXML
    private VBox pane;

    @FXML
    private ProgressBar progressBar;

    double val = 0;

    Timer timer = new Timer();

    TimerTask myTask = new TimerTask() {
        @Override
        public void run() {

            Platform.runLater(() -> {
                val+=0.01;
                progressBar.progressProperty().setValue(val);
                if (progressBar.progressProperty().getValue() == 1.0000000000000007) {
                    closeWin();
                    showLogin();
                    timer.cancel();
                }
            });
        }
    };

    public void closeWin() {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }

    public void showLogin() {
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timer.schedule(myTask, 0, 50);
    }
}
