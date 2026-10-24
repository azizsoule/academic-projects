package soule.crossword;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("views/welcome.fxml")));
        primaryStage.setTitle("CROSSWORD");
        primaryStage.setScene(new Scene(root));
        KeyCombination keyCodeCombination = new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN);
        Runnable runnable = primaryStage::close;
        primaryStage.getScene().getAccelerators().put(keyCodeCombination, runnable);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
