package soule.crossword.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WelcomeController {

    @FXML
    private Button startBtn;

    public void onStartBtnPress() {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/crossword.fxml")));
            stage.setTitle("CROSSWORD");
            stage.setScene(new Scene(root));
            KeyCombination keyCodeCombination = new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN);
            Runnable runnable = stage::close;
            stage.getScene().getAccelerators().put(keyCodeCombination, runnable);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
